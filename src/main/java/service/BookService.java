package service;

import java.sql.SQLException;
import dao.BookDAO;
import modl.Book;

public class BookService {

    private BookDAO bookDao;

    public BookService() throws SQLException {
        bookDao = new BookDAO();
    }

    /**
     * Adds a new Book after validating its data.
     * @param book the Book to be added
     * @return message indicating success or failure
     */
    public String addBook(Book book) {
        // ✅ Basic validation
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

        // ✅ Attempt to add to database
        boolean success = bookDao.insertBook(book);
        return success ? "Book added successfully!" : "Error while saving Book to database.";
    }
}