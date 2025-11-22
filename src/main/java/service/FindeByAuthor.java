package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modl.Book;

public class FindeByAuthor implements SearchBook {

	
	public  List<Book> findeBook(String searchby) {
		
			 
			 try {
				 BookService book = new BookService();
				return book.findbyAuthor(searchby);
				
			 } catch (SQLException e) {
				
				e.printStackTrace();
				 return new ArrayList<>();
			 }
			
		}
	}


