 package service;

import java.sql.SQLException;
import java.util.List;

import modl.Book;
import modl.Loan;
import dao.*;

public class BookService {

    private BookDAO bookDao;
    private LoansDAO loansDAO;

    public BookService() throws SQLException {
        bookDao = new BookDAO();
    }

    public BookService(BookDAO bookDao) {
        this.bookDao = bookDao;
    }
    
    public BookService(BookDAO bookDao , LoansDAO loansDAO ) {
        this.bookDao = bookDao;
    }


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

    public List<Book> findeAllBooks() {
        List<Book> b = bookDao.findAllBooks();
        return b;
    }
}
