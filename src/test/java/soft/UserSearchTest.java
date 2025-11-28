package soft;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modl.Book;
import service.BookService;

public class UserSearchTest {

    static UserSearch frame;
    static BookService mockBookService;

    // لتخزين الكتب عند عمل makeatablelist
    List<Book> lastBooks;

    @BeforeAll
    static void beforeAllTests() {
        mockBookService = mock(BookService.class); // Mock لخدمة الكتب
    }

    @BeforeEach
    void setup() {
        frame = new UserSearch() {
            @Override
            public void setVisible(boolean b) {
                // منع ظهور النافذة الحقيقية
            }

            @Override
            public void dispose() {
                // منع إغلاق النافذة الحقيقية
            }

            @Override
            public void makeatablelist(List<Book> books) {
                lastBooks = books; // تخزين الكتب للتحقق منها في الاختبارات
            }
        };
        frame.setVisible(true);
    }

    @AfterEach
    void tearDown() {
        frame.dispose();
        frame = null;
        lastBooks = null;
    }

    @AfterAll
    static void afterAllTests() {
        mockBookService = null;
    }

    // ----------------------------
    // TEST CASES
    // ----------------------------

    @Test
    void testButtonsExist() {
        JButton backBtn = frame.getBackButton();
        JButton authorBtn = frame.getAuthorButton();
        JButton isbnBtn = frame.getIsbnButton();
        JButton titleBtn = frame.getTitleButton();
        JButton showAllBtn = frame.getShowAllButton();

        assertNotNull(backBtn);
        assertNotNull(authorBtn);
        assertNotNull(isbnBtn);
        assertNotNull(titleBtn);
        assertNotNull(showAllBtn);

        assertEquals("<--", backBtn.getAction().getValue(javax.swing.Action.NAME));
        assertEquals("search by author", authorBtn.getAction().getValue(javax.swing.Action.NAME));
        assertEquals("search by id", isbnBtn.getAction().getValue(javax.swing.Action.NAME));
        assertEquals("search by title", titleBtn.getAction().getValue(javax.swing.Action.NAME));
        assertEquals("show all books", showAllBtn.getAction().getValue(javax.swing.Action.NAME));
    }

    @Test
    void testTextFieldsExist() {
        JTextField authorField = frame.getAuthorTextField();
        JTextField isbnField = frame.getIsbnTextField();
        JTextField titleField = frame.getTitleTextField();

        assertNotNull(authorField);
        assertNotNull(isbnField);
        assertNotNull(titleField);

        authorField.setText("Author Name");
        isbnField.setText("12345");
        titleField.setText("Book Title");

        assertEquals("Author Name", authorField.getText());
        assertEquals("12345", isbnField.getText());
        assertEquals("Book Title", titleField.getText());
    }

    @Test
    void testMakeATableListWithBooks() {
        List<Book> books = new ArrayList<>();
        Book b1 = new Book();
        b1.setIsbn("111");
        b1.setAuthor("Author1");
        b1.setCategory("Cat1");
        b1.setTitle("Title1");
        b1.setAvailable_copies(5);
        books.add(b1);

        frame.makeatablelist(books);
        assertNotNull(lastBooks);
        assertEquals(1, lastBooks.size());
        assertEquals("111", lastBooks.get(0).getIsbn());
    }

    @Test
    void testMakeATableListWithEmptyList() {
        frame.makeatablelist(new ArrayList<>());
        assertTrue(lastBooks == null || lastBooks.isEmpty());
    }

    @Test
    void testMakeATableListWithNullList() {
        frame.makeatablelist(null);
        assertTrue(lastBooks == null || lastBooks.isEmpty());
    }
}