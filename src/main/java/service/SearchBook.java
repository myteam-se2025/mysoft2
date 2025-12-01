package service;

import java.util.List;

import modl.Book;
/**
 * @author khadeja and masa
 * 
 * Interface defining the strategy for searching books.
 * Implementations will provide the actual search logic 
 * (e.g., by title, by author, by ID).
 */
public interface SearchBook {
	 /**
     * Finds books based on the provided search criteria.
     * 
     * @param searchby The search term (e.g., author name, title, or ID)
     * @return A list of Book objects that match the search criteria
     */
	List<Book> findeBook(String searchby);
}
