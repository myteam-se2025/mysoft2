package service;

import java.sql.SQLException;

import modl.Book;

public class FindeByAuthor implements SearchBook {

	
	public Book findeBook(String searchby) {
		
			 BookService book;
			 try {
				book = new BookService();
				return book.findbyAuthor(searchby);
				
			 } catch (SQLException e) {
				
				e.printStackTrace();
			 }
			
			return null;
		}
	}


