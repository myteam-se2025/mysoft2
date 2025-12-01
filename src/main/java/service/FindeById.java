package service;

import modl.Book;
import java.sql.SQLException;
import java.util.List;

import dao.*;

/**
 * Strategy to find books by ID.
 */
public class FindeById implements SearchBook {
	 @Override
	public List<Book> findeBook(String searchby) {

		try {
			BookService book = new BookService();
			return book.findbyid(searchby);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

}
