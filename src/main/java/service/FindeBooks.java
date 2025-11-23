package service;

import java.util.List;

import modl.Book;

public class FindeBooks {

	private SearchBook stratgy;

	public List<Book> findeBook(String searchby) {
		if (stratgy == null) {
			throw new IllegalStateException("Fine stratgy not set");
		}

		return stratgy.findeBook(searchby);
	}

	public void setStratgy(SearchBook stratgy) {
		this.stratgy = stratgy;
	}
}
