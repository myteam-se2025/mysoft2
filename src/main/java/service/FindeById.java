package service;

import modl.Book;
import java.sql.SQLException;
import dao.*;

public class FindeById implements SearchBook{

	
	public Book findeBook(String searchby){
		 BookService book;
		 try {
			book = new BookService();
			return book.findbyid(searchby);
			
		 } catch (SQLException e) {
			
			e.printStackTrace();
		 }
		
		return null;
	}

	
}
