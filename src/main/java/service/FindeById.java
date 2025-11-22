package service;

import modl.Book;
import java.sql.SQLException;
import java.util.List;

import dao.*;

public class FindeById implements SearchBook{

	
	public List<Book> findeBook(String searchby){
		 
		 try {
			 BookService book = new BookService();
			return book.findbyid(searchby);
			
		 } catch (SQLException e) {
			
			e.printStackTrace();
		 }
		
		return null;
	}

	
}
