package service;

import java.util.List;

import modl.Book;

/**
 * Context class for searching books using a strategy.
 */
public class FindeBooks {

	private SearchBook stratgy;

	 /**
     * Searches for books using the current strategy.
     *
     * @param searchBy The search term (title, author, etc.)
     * @return List of books found
     * @throws IllegalStateException if the strategy is not set
     */
	public List<Book> findeBook(String searchby) {
		if (stratgy == null) {
			throw new IllegalStateException("Fine stratgy not set");
		}

		return stratgy.findeBook(searchby);
	}
	
	/**
     * Sets the search strategy.
     *
     * @param strategy A SearchBook implementation
     */
	public void setStratgy(SearchBook stratgy) {
		this.stratgy = stratgy;
	}
}
