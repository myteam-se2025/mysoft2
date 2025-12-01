package soft;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import service.BookService;
import modl.Book;

public class AdminAddBookTest {

    private AdminAddBook addBookUI;
    private BookService mockService;

    @BeforeEach
    void setup() {
        addBookUI = new AdminAddBook();
        addBookUI.testingMode = true;   // ⬅ يمنع JOptionPane

        mockService = mock(BookService.class);
        addBookUI.setBookServiceForTest(mockService);  // ⬅ ربط الـ mock
    }

    // -------------------------------------------------------
    @Test
    @DisplayName("Test adding book successfully")
    void testAddBookSuccess() {

        when(mockService.addBook(any(Book.class))).thenReturn("Book added successfully!");

        addBookUI.title2.setText("Java");
        addBookUI.author2.setText("James");
        addBookUI.isbn2.setText("12345");
        addBookUI.category2.setText("Programming");
        addBookUI.copies2.setText("5");

        addBookUI.handleAddBookForTest();

        verify(mockService, times(1)).addBook(any(Book.class));

        assertEquals("", addBookUI.title2.getText());
        assertEquals("", addBookUI.author2.getText());
        assertEquals("", addBookUI.isbn2.getText());
        assertEquals("", addBookUI.category2.getText());
        assertEquals("", addBookUI.copies2.getText());
    }

    // -------------------------------------------------------
    @Test
    @DisplayName("Test invalid copies (not a number)")
    void testInvalidCopies() {

        addBookUI.title2.setText("Java");
        addBookUI.author2.setText("James");
        addBookUI.isbn2.setText("12345");
        addBookUI.category2.setText("Programming");
        addBookUI.copies2.setText("abc");  // ⬅ خطأ

        addBookUI.handleAddBookForTest();

        verify(mockService, never()).addBook(any(Book.class));
    }

    // -------------------------------------------------------
    @Test
    @DisplayName("Test empty title field — service not called")
    void testEmptyTitle() {

        addBookUI.title2.setText("");
        addBookUI.author2.setText("James");
        addBookUI.isbn2.setText("12345");
        addBookUI.category2.setText("Programming");
        addBookUI.copies2.setText("5");

        addBookUI.handleAddBookForTest();

        verify(mockService, never()).addBook(any(Book.class));
    }

    // -------------------------------------------------------
    @Test
    @DisplayName("Test empty ISBN field — service not called")
    void testEmptyISBN() {

        addBookUI.title2.setText("Java");
        addBookUI.author2.setText("James");
        addBookUI.isbn2.setText("");
        addBookUI.category2.setText("Programming");
        addBookUI.copies2.setText("5");

        addBookUI.handleAddBookForTest();

        verify(mockService, never()).addBook(any(Book.class));
    }

    // -------------------------------------------------------
    @Test
    @DisplayName("Test service failure message — fields must NOT clear")
    void testServiceFailureMessage() {

        when(mockService.addBook(any(Book.class))).thenReturn("Error adding book!");

        addBookUI.title2.setText("Java");
        addBookUI.author2.setText("James");
        addBookUI.isbn2.setText("12345");
        addBookUI.category2.setText("Programming");
        addBookUI.copies2.setText("5");

        addBookUI.handleAddBookForTest();

        verify(mockService, times(1)).addBook(any(Book.class));

        assertEquals("Java", addBookUI.title2.getText());
        assertEquals("James", addBookUI.author2.getText());
        assertEquals("12345", addBookUI.isbn2.getText());
        assertEquals("Programming", addBookUI.category2.getText());
        assertEquals("5", addBookUI.copies2.getText());
    }

    // -------------------------------------------------------
    @Test
    @DisplayName("Test SQLException — must NOT crash")
    void testSQLExceptionSafe() {

        when(mockService.addBook(any(Book.class))).thenThrow(new RuntimeException("DB error"));

        addBookUI.title2.setText("Book");
        addBookUI.author2.setText("A");
        addBookUI.isbn2.setText("111");
        addBookUI.category2.setText("Cat");
        addBookUI.copies2.setText("3");

        assertDoesNotThrow(() -> addBookUI.handleAddBookForTest());
        verify(mockService, times(1)).addBook(any(Book.class));
    }
}
