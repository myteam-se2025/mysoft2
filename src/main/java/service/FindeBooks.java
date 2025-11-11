package service;

import modl.Book;

public class FindeBooks {

	private SearchBook stratgy;
	
	public void setStratgy (SearchBook stratgy)
	{
		this.stratgy = stratgy;
	}
	
	public Book findeBook (String searchby)
	{
		if (stratgy == null)
		{
			throw new IllegalStateException("Fine stratgy not set");
		}
		
		return stratgy.findeBook( searchby);
	}
}
