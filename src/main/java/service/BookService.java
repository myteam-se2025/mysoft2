
package service;

import java.sql.SQLException;

import dao.BookDao;
import modl.Book;

public class BookService {
    private BookDao bookDao;

    public BookService() throws SQLException {
        bookDao = new BookDao();
    }

    public void registerBook(Book book) {
        bookDao.addBook(book);
    }
}