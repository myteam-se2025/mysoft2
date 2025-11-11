package service;

import java.sql.SQLException;

import modl.Book;

public class FindeByTitle implements SearchBook{

	
	public Book findeBook(String searchby) {
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
