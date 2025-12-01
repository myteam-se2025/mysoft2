 package service;

import java.sql.SQLException;
import java.util.List;

import modl.Book;
import modl.Loan;
import dao.*;

/**
 * Service class to handle book-related operations.
 * Acts as a layer between the DAO and higher-level application logic.
 * Provides methods to add books, check availability, and search books.
 * 
 * @author Khadeja and Masa
 * @version 1.0
 */ 
public class BookService {

    private BookDAO bookDao;
    private LoansDAO loansDAO;

    /**
     * Default constructor initializing BookDAO.
     *
     * @throws SQLException if there is a database connection error
     */
    public BookService() throws SQLException {
        bookDao = new BookDAO();
    }
    /**
     * Constructor to inject an existing BookDAO instance.
     *
     * @param bookDao the BookDAO instance to be used
     */
    public BookService(BookDAO bookDao) {
        this.bookDao = bookDao;
    }
    /**
     * Constructor to inject both BookDAO and LoansDAO instances.
     *
     * @param bookDao the BookDAO instance
     * @param loansDAO the LoansDAO instance
     */
    public BookService(BookDAO bookDao , LoansDAO loansDAO ) {
        this.bookDao = bookDao;
    }

    /**
     * Adds a new book after validating the fields.
     *
     * @param book the Book object to add
     * @return a success message if added, or an error message if validation fails
     */
    public String addBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            return "Title cannot be empty!";
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            return "Author name cannot be empty!";
        }
        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            return "ISBN cannot be empty!";
        }
        if (book.getCategory() == null || book.getCategory().trim().isEmpty()) {
            return "Category cannot be empty!";
        }
        if (book.getAvailable_copies() < 0) {
            return "Number of copies cannot be negative!";
        }

        boolean success = bookDao.insertBook(book);
        return success ? "Book added successfully!" : "Error while saving Book to database.";
    }

    /**
     * Checks if a book is available by its ID.
     *
     * @param book_id the ID of the book as a String
     * @return a Loan object if the book is currently loaned, otherwise null
     */
    public Loan bookAvalbltyChack(String book_id) {
        if (book_id == null || book_id.isEmpty()) {
            return null;
        }

        int idd = 0;
        try {
            idd = Integer.parseInt(book_id);
            if (idd <= 0) {
                return null;
            }
        } catch (NumberFormatException ex) {
            return null;
        }

        try {
           LoansDAO loan = new LoansDAO();
            return loan.findeLoanBybookId(idd);
        
        } catch (NumberFormatException ex) {
            return null;
        }
    }
    /**
     * Finds books by author name.
     *
     * @param author the author name to search for
     * @return a list of Book objects or null if input is invalid
     */
    public List<Book> findbyAuthor(String author) {
        if (author == null || author.isEmpty()) {
            return null;
        }

        try {
            return bookDao.findbyauthor(author);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Finds books by ID.
     *
     * @param id the book ID as a String
     * @return a list of Book objects matching the ID or null if invalid
     */
    public List<Book> findbyid(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }

        int idd;
        try {
            idd = Integer.parseInt(id);
            if (idd <= 0) {
                return null;
            }
        } catch (NumberFormatException ex) {
            return null;
        }

        try {
            return bookDao.findbyid(idd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Finds books by title.
     *
     * @param Title the title to search for
     * @return a list of Book objects matching the title or null if invalid
     */
    public List<Book> findbyTitle(String Title) {
        if (Title == null || Title.isEmpty()) {
            return null;
        }

        try {
            return bookDao.findbytitle(Title);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Retrieves all books from the database.
     *
     * @return a list of all Book objects
     */
    public List<Book> findeAllBooks() {
        List<Book> b = bookDao.findAllBooks();
        return b;
    }
}
