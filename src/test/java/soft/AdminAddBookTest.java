
package soft;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import service.BookService;
import modl.Book;

public class AdminAddBookTest {

    private AdminAddBook addBook;
    private BookService mockService;
    private MockedStatic<JOptionPane> mockJOption;

    @BeforeEach
    void setup() {
        addBook = new AdminAddBook();
        mockService = mock(BookService.class);
        addBook.setBookServiceForTest(mockService);

        mockJOption = mockStatic(JOptionPane.class); // mock JOptionPane
    }

    @AfterEach
    void teardown() {
        mockJOption.close();
        addBook = null;
        mockService = null;
    }

    // ----------------------------------------------------------
    @Test
    @DisplayName("Successful add")
    void testAddBookSuccessfully() throws Exception {

        when(mockService.addBook(any(Book.class))).thenReturn("Book added successfully!");

        addBook.title2.setText("Java 101");
        addBook.author2.setText("John Doe");
        addBook.category2.setText("Programming");
        addBook.copies2.setText("5");
        addBook.isbn2.setText("111222333");

        addBook.handleAddBookForTest();

        verify(mockService, times(1)).addBook(any(Book.class));

        assertEquals("", addBook.title2.getText());
        assertEquals("", addBook.author2.getText());
        assertEquals("", addBook.category2.getText());
        assertEquals("", addBook.copies2.getText());
        assertEquals("", addBook.isbn2.getText());
    }

    // ----------------------------------------------------------
    @Test
    @DisplayName("Empty ISBN should block add")
    void testEmptyISBN() throws Exception {

        addBook.title2.setText("Book1");
        addBook.author2.setText("A1");
        addBook.category2.setText("Fiction");
        addBook.copies2.setText("3");
        addBook.isbn2.setText(""); // EMPTY ISBN

        addBook.handleAddBookForTest();

        verify(mockService, never()).addBook(any(Book.class));
        mockJOption.verify(() -> JOptionPane.showMessageDialog(any(), eq("ISBN cannot be empty!")), times(1));
    }

    // ----------------------------------------------------------
    @Test
    @DisplayName("Invalid copies input")
    void testInvalidCopies() throws Exception {

        addBook.title2.setText("Book1");
        addBook.author2.setText("A1");
        addBook.category2.setText("Fiction");
        addBook.copies2.setText("abc"); // غير صالح
        addBook.isbn2.setText("444555666");

        addBook.handleAddBookForTest();

        verify(mockService, never()).addBook(any(Book.class));
        mockJOption.verify(() -> JOptionPane.showMessageDialog(any(), eq("Copies must be a valid number.")), times(1));
    }

    // ----------------------------------------------------------
    @Test
    @DisplayName("SQLException behavior")
    void testSQLException() throws Exception {

        when(mockService.addBook(any(Book.class))).thenThrow(new RuntimeException("DB error"));

        addBook.title2.setText("Book1");
        addBook.author2.setText("A1");
        addBook.category2.setText("Fiction");
        addBook.copies2.setText("3");
        addBook.isbn2.setText("555777999");

        assertDoesNotThrow(() -> addBook.handleAddBookForTest());
        verify(mockService, times(1)).addBook(any(Book.class));
    }

    // ----------------------------------------------------------
    @Test
    @DisplayName("Service failure → fields should NOT clear")
    void testServiceFailureMessage() throws Exception {

        when(mockService.addBook(any(Book.class))).thenReturn("Error adding book!");

        addBook.title2.setText("T1");
        addBook.author2.setText("A1");
        addBook.category2.setText("G1");
        addBook.copies2.setText("4");
        addBook.isbn2.setText("999");

        addBook.handleAddBookForTest();

        verify(mockService, times(1)).addBook(any(Book.class));

        assertEquals("T1", addBook.title2.getText());
        assertEquals("A1", addBook.author2.getText());
        assertEquals("G1", addBook.category2.getText());
        assertEquals("4", addBook.copies2.getText());
        assertEquals("999", addBook.isbn2.getText());
    }

    // ----------------------------------------------------------
    @Test
    @DisplayName("Empty fields block add")
    void testEmptyFields() throws Exception {

        addBook.title2.setText("");
        addBook.author2.setText("A");
        addBook.category2.setText("G");
        addBook.copies2.setText("3");
        addBook.isbn2.setText("123");

        addBook.handleAddBookForTest();

        verify(mockService, never()).addBook(any(Book.class));
    }

    // ----------------------------------------------------------
    @Test
    @DisplayName("JButton triggers same logic")
    void testButtonAction() {

        addBook.title2.setText("Book1");
        addBook.author2.setText("Author1");
        addBook.category2.setText("G1");
        addBook.copies2.setText("2");
        addBook.isbn2.setText("999");

        when(mockService.addBook(any())).thenReturn("Book added successfully!");

        // simulate button click
        addBook.addButton.doClick();

        verify(mockService, times(1)).addBook(any(Book.class));
    }
}