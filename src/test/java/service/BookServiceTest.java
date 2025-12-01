package service;

import dao.BookDAO;
import dao.LoansDAO;
import modl.Book;
import modl.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BookService Unit Tests")
class BookServiceTest {

    @Mock
    private BookDAO bookDAO;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookDAO);
    }

    @Test
    @DisplayName("Add book with valid data should succeed")
    void testAddBookWithValidData() {
        Book book = new Book("Java Programming", "John Doe", "978-0-123456-78-9", "Programming", 5);
        when(bookDAO.insertBook(book)).thenReturn(true);

        String result = bookService.addBook(book);

        assertEquals("Book added successfully!", result);
        verify(bookDAO, times(1)).insertBook(book);
    }

    @Test
    @DisplayName("Add book with null title should fail")
    void testAddBookWithNullTitle() {
        Book book = new Book(null, "John Doe", "978-0-123456-78-9", "Programming", 5);

        String result = bookService.addBook(book);

        assertEquals("Title cannot be empty!", result);
        verify(bookDAO, never()).insertBook(any());
    }

    @Test
    @DisplayName("Add book with empty title should fail")
    void testAddBookWithEmptyTitle() {
        Book book = new Book("  ", "John Doe", "978-0-123456-78-9", "Programming", 5);

        String result = bookService.addBook(book);

        assertEquals("Title cannot be empty!", result);
        verify(bookDAO, never()).insertBook(any());
    }

    @Test
    @DisplayName("Add book with null author should fail")
    void testAddBookWithNullAuthor() {
        Book book = new Book("Java Programming", null, "978-0-123456-78-9", "Programming", 5);

        String result = bookService.addBook(book);

        assertEquals("Author name cannot be empty!", result);
        verify(bookDAO, never()).insertBook(any());
    }

    @Test
    @DisplayName("Add book with empty author should fail")
    void testAddBookWithEmptyAuthor() {
        Book book = new Book("Java Programming", "  ", "978-0-123456-78-9", "Programming", 5);

        String result = bookService.addBook(book);

        assertEquals("Author name cannot be empty!", result);
        verify(bookDAO, never()).insertBook(any());
    }

    @Test
    @DisplayName("Add book with null ISBN should fail")
    void testAddBookWithNullIsbn() {
        Book book = new Book("Java Programming", "John Doe", null, "Programming", 5);

        String result = bookService.addBook(book);

        assertEquals("ISBN cannot be empty!", result);
        verify(bookDAO, never()).insertBook(any());
    }

    @Test
    @DisplayName("Add book with empty ISBN should fail")
    void testAddBookWithEmptyIsbn() {
        Book book = new Book("Java Programming", "John Doe", "  ", "Programming", 5);

        String result = bookService.addBook(book);

        assertEquals("ISBN cannot be empty!", result);
        verify(bookDAO, never()).insertBook(any());
    }

    @Test
    @DisplayName("Add book with null category should fail")
    void testAddBookWithNullCategory() {
        Book book = new Book("Java Programming", "John Doe", "978-0-123456-78-9", null, 5);

        String result = bookService.addBook(book);

        assertEquals("Category cannot be empty!", result);
        verify(bookDAO, never()).insertBook(any());
    }

    @Test
    @DisplayName("Add book with empty category should fail")
    void testAddBookWithEmptyCategory() {
        Book book = new Book("Java Programming", "John Doe", "978-0-123456-78-9", "  ", 5);

        String result = bookService.addBook(book);

        assertEquals("Category cannot be empty!", result);
        verify(bookDAO, never()).insertBook(any());
    }

    @Test
    @DisplayName("Add book with negative copies should fail")
    void testAddBookWithNegativeCopies() {
        Book book = new Book("Java Programming", "John Doe", "978-0-123456-78-9", "Programming", -1);

        String result = bookService.addBook(book);

        assertEquals("Number of copies cannot be negative!", result);
        verify(bookDAO, never()).insertBook(any());
    }

    @Test
    @DisplayName("Add book when database fails should return error")
    void testAddBookDatabaseFailure() {
        Book book = new Book("Java Programming", "John Doe", "978-0-123456-78-9", "Programming", 5);
        when(bookDAO.insertBook(book)).thenReturn(false);

        String result = bookService.addBook(book);

        assertEquals("Error while saving Book to database.", result);
        verify(bookDAO, times(1)).insertBook(book);
    }

    
    @Test
    @DisplayName("Book availability → LoansDAO constructor throws → return null")
    void testBookAvailability_LoansDAOThrowsNumberFormat() {

    	try (MockedConstruction<LoansDAO> mocked =
    	         Mockito.mockConstruction(LoansDAO.class,
    	             (mock, context) -> {
    	                 throw new NumberFormatException("forced");
    	             })) {

    	    BookService service = new BookService(bookDAO);

    	    Loan result = service.bookAvalbltyChack("5");

    	    assertNull(result);
    	}

    }

  

    @Test
    @DisplayName("Find by author with valid author should return books")
    void testFindByAuthorValid() {
        String author = "John Doe";
        List<Book> expectedBooks = Arrays.asList(
                new Book("Book 1", author, "123", "Fiction", 3),
                new Book("Book 2", author, "456", "Non-Fiction", 2)
        );
        when(bookDAO.findbyauthor(author)).thenReturn(expectedBooks);

        List<Book> result = bookService.findbyAuthor(author);

        assertEquals(2, result.size());
        verify(bookDAO, times(1)).findbyauthor(author);
    }

    @Test
    @DisplayName("Find by author with null should return null")
    void testFindByAuthorNull() {
        List<Book> result = bookService.findbyAuthor(null);

        assertNull(result);
        verify(bookDAO, never()).findbyauthor(any());
    }

    @Test
    @DisplayName("Find by author with empty string should return null")
    void testFindByAuthorEmpty() {
        List<Book> result = bookService.findbyAuthor("");

        assertNull(result);
        verify(bookDAO, never()).findbyauthor(any());
    }

    @Test
    @DisplayName("Find by id with valid id should return books")
    void testFindByIdValid() {
        List<Book> expectedBooks = Arrays.asList(
                new Book("Book 1", "Author", "123", "Fiction", 3)
        );
        when(bookDAO.findbyid(1)).thenReturn(expectedBooks);

        List<Book> result = bookService.findbyid("1");

        assertEquals(1, result.size());
        verify(bookDAO, times(1)).findbyid(1);
    }

    @Test
    @DisplayName("Find by id with null should return null")
    void testFindByIdNull() {
        List<Book> result = bookService.findbyid(null);

        assertNull(result);
        verify(bookDAO, never()).findbyid(anyInt());
    }

    @Test
    @DisplayName("Find by id with empty string should return null")
    void testFindByIdEmpty() {
        List<Book> result = bookService.findbyid("");

        assertNull(result);
        verify(bookDAO, never()).findbyid(anyInt());
    }

    @Test
    @DisplayName("Find by id with non-numeric string should return null")
    void testFindByIdNonNumeric() {
        List<Book> result = bookService.findbyid("abc");

        assertNull(result);
        verify(bookDAO, never()).findbyid(anyInt());
    }

    @Test
    @DisplayName("Find by id with negative id should return null")
    void testFindByIdNegative() {
        List<Book> result = bookService.findbyid("-1");

        assertNull(result);
        verify(bookDAO, never()).findbyid(anyInt());
    }

    @Test
    @DisplayName("Find by id with zero should return null")
    void testFindByIdZero() {
        List<Book> result = bookService.findbyid("0");

        assertNull(result);
        verify(bookDAO, never()).findbyid(anyInt());
    }

    @Test
    @DisplayName("Find by title with valid title should return books")
    void testFindByTitleValid() {
        String title = "Java Programming";
        List<Book> expectedBooks = Arrays.asList(
                new Book(title, "Author", "123", "Programming", 5)
        );
        when(bookDAO.findbytitle(title)).thenReturn(expectedBooks);

        List<Book> result = bookService.findbyTitle(title);

        assertEquals(1, result.size());
        verify(bookDAO, times(1)).findbytitle(title);
    }

    @Test
    @DisplayName("Find by title with null should return null")
    void testFindByTitleNull() {
        List<Book> result = bookService.findbyTitle(null);

        assertNull(result);
        verify(bookDAO, never()).findbytitle(any());
    }

    @Test
    @DisplayName("Find by title with empty string should return null")
    void testFindByTitleEmpty() {
        List<Book> result = bookService.findbyTitle("");

        assertNull(result);
        verify(bookDAO, never()).findbytitle(any());
    }

    @Test
    @DisplayName("Find all books should return all books")
    void testFindAllBooks() {
        List<Book> expectedBooks = Arrays.asList(
                new Book("Book 1", "Author 1", "123", "Fiction", 3),
                new Book("Book 2", "Author 2", "456", "Non-Fiction", 2),
                new Book("Book 3", "Author 3", "789", "Programming", 1)
        );
        when(bookDAO.findAllBooks()).thenReturn(expectedBooks);

        List<Book> result = bookService.findeAllBooks();

        assertEquals(3, result.size());
        verify(bookDAO, times(1)).findAllBooks();
    }

    @Test
    @DisplayName("Find all books when empty should return empty list")
    void testFindAllBooksEmpty() {
        when(bookDAO.findAllBooks()).thenReturn(new ArrayList<>());

        List<Book> result = bookService.findeAllBooks();

        assertTrue(result.isEmpty());
        verify(bookDAO, times(1)).findAllBooks();
    }

    @Test
    @DisplayName("Book availability check with null id should return null")
    void testBookAvailabilityCheckNullId() {
        assertNull(bookService.bookAvalbltyChack(null));
    }

    @Test
    @DisplayName("Book availability check with empty id should return null")
    void testBookAvailabilityCheckEmptyId() {
        assertNull(bookService.bookAvalbltyChack(""));
    }

    @Test
    @DisplayName("Book availability check with negative id should return null")
    void testBookAvailabilityCheckNegativeId() {
        assertNull(bookService.bookAvalbltyChack("-5"));
    }

    @Test
    @DisplayName("Book availability check with zero should return null")
    void testBookAvailabilityCheckZeroId() {
        assertNull(bookService.bookAvalbltyChack("0"));
    }

    @Test
    @DisplayName("Book availability check with non-numeric id should return null")
    void testBookAvailabilityCheckNonNumericId() {
        assertNull(bookService.bookAvalbltyChack("abc"));
    }

    @Test
    @DisplayName("Find by author when DAO throws exception should return null")
    void testFindByAuthorException() {
        String author = "Test Author";
        when(bookDAO.findbyauthor(author)).thenThrow(new RuntimeException("Database error"));

        List<Book> result = bookService.findbyAuthor(author);

        assertNull(result);
        verify(bookDAO, times(1)).findbyauthor(author);
    }

    @Test
    @DisplayName("Find by id when DAO throws exception should return null")
    void testFindByIdException() {
        when(bookDAO.findbyid(1)).thenThrow(new RuntimeException("Database error"));

        List<Book> result = bookService.findbyid("1");

        assertNull(result);
        verify(bookDAO, times(1)).findbyid(1);
    }

    @Test
    @DisplayName("Find by title when DAO throws exception should return null")
    void testFindByTitleException() {
        String title = "Test Book";
        when(bookDAO.findbytitle(title)).thenThrow(new RuntimeException("Database error"));

        List<Book> result = bookService.findbyTitle(title);

        assertNull(result);
        verify(bookDAO, times(1)).findbytitle(title);
    }

    @Test
    @DisplayName("Add book with zero copies should succeed")
    void testAddBookWithZeroCopies() {
        Book book = new Book("Java Programming", "John Doe", "978-0-123456-78-9", "Programming", 0);
        when(bookDAO.insertBook(book)).thenReturn(true);

        String result = bookService.addBook(book);

        assertEquals("Book added successfully!", result);
        verify(bookDAO, times(1)).insertBook(book);
    }
}

