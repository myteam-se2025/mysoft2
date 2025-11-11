package service;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.BookDAO;
import modl.Book;
import dao.*;

public class BookService {

    private BookDAO bookDao;

    public BookService() throws SQLException {
        bookDao = new BookDAO();
    }

    /**
     * Adds a new Book after validating its data.
     * @param book the Book to be added
     * @return message indicating success or failure
     */
    public String addBook(Book book) {
        // ✅ Basic validation
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            return "Title cannot be empty!";
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            return "Author name cannot be empty!";
        }
        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            return "ISBN cannot be empty!";
        }
        if (book.getCategory() == null || book.getCategory().trim().isEmpty()) {
            return "Category cannot be empty!";
        }
        if (book.getAvailable_copies() < 0) {
            return "Number of copies cannot be negative!";
        }

        // ✅ Attempt to add to database
        boolean success = bookDao.insertBook(book);
        return success ? "Book added successfully!" : "Error while saving Book to database.";
    }
    
    public Book findbyid(String id )
    {
    	 if (id == null || id.isEmpty()) {
    	        JOptionPane.showMessageDialog(null, "Please enter a book ID.");
    	        return null;
    	    }

    	    int idd;
    	    try {
    	        idd = Integer.parseInt(id);
    	        if (idd <= 0) {
    	            JOptionPane.showMessageDialog(null, "ID must be a positive number.");
    	            return null;
    	        }
    	    } catch (NumberFormatException ex) {
    	        JOptionPane.showMessageDialog(null, "ID must be a number.");
    	        return null;
    	    }
        
    	    try {
    	        BookDAO bookDAO = new BookDAO();
    	        return bookDAO.findbyid(idd);
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        JOptionPane.showMessageDialog(null, "Database error occurred.");
    	        return null;
    	    }
        
    }
    
    
    
    
    
    
    public Book findbyAuthor(String author )
    {
    	 if (author == null || author.isEmpty()) {
    	        JOptionPane.showMessageDialog(null, "Please enter a book ID.");
    	        return null;
    	    }

    	    
        
    	    try {
    	        BookDAO bookDAO = new BookDAO();
    	        return bookDAO.findbyauthor(author);
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        JOptionPane.showMessageDialog(null, "Database error occurred.");
    	        return null;
    	    }
        
    }
    
    
    
    
    
    public Book findbyTitle(String Title )
    {
    	 if (Title == null || Title.isEmpty()) {
    	        JOptionPane.showMessageDialog(null, "Please enter a book ID.");
    	        return null;
    	    }

    	    
        
    	    try {
    	        BookDAO bookDAO = new BookDAO();
    	        return bookDAO.findbytitle(Title);
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        JOptionPane.showMessageDialog(null, "Database error occurred.");
    	        return null;
    	    }
        
    }
}