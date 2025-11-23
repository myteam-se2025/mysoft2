package service;

import java.sql.SQLException;
import java.util.List;

import modl.Book;

public class FindeByTitle implements SearchBook {

	public List<Book> findeBook(String searchby) {
		BookService book;
		try {
			book = new BookService();
			return book.findbyTitle(searchby);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

}
