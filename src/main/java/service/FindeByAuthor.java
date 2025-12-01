package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modl.Book;

/**
 * Strategy to find books by author.
 */
public class FindeByAuthor implements SearchBook {

	 @Override
	public List<Book> findeBook(String searchby) {

		try {
			BookService book = new BookService();
			return book.findbyAuthor(searchby);

		} catch (SQLException e) {

			e.printStackTrace();
			return new ArrayList<>();
		}

	}
}
