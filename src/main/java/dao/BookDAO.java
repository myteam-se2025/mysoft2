
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modl.Book;
/**
 * Data Access Object (DAO) class for handling database operations related to Books.
 * Provides methods to find, insert, and retrieve Book objects from the database.
 * 
 * @author Khadeja and masa
 * @version 1.0
 * @since 2025
 */ 
public class BookDAO extends BaseDAO {


	 private static final String COL_AUTHOR = "author";
	 /**
     * Retrieves all books from the database.
     * 
     * @return a list of all Book objects
     */
	public List<Book> findAllBooks() {

		List<Book> books = new ArrayList<>();

		 String sql = "SELECT book_id, title, "+COL_AUTHOR+", isbn, category, available_copies " +
                 "FROM public.books";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
 
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book(
				rs.getString("title"), rs.getString(COL_AUTHOR), rs.getString("isbn"),
				rs.getString("category"), rs.getInt("available_copies"));
				book.setBook_id(rs.getInt("book_id"));
				if (book != null) {
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	/**
     * Finds books by a specific author.
     * 
     * @param author the author name to search for
     * @return a list of Book objects written by the specified author
     */

	public List<Book> findbyauthor(String author) {
		List<Book> books = new ArrayList<>();

		String sql = "SELECT * FROM public.books WHERE author = ?";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, author);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book(rs.getString("title"), rs.getString("author"), rs.getString("isbn"),
						rs.getString("category"), rs.getInt("available_copies"));
				book.setBook_id(rs.getInt("book_id"));
				if (book != null) {
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	/**
     * Finds books by their ID.
     * 
     * @param id the unique ID of the book
     * @return a list containing the Book object with the specified ID, or empty list if not found
     */
	public List<Book> findbyid(int id) {
		List<Book> books = new ArrayList<>();

		String sql = "SELECT * FROM public.books WHERE book_id = ?";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book(rs.getString("title"), rs.getString("author"), rs.getString("isbn"),
						rs.getString("category"), rs.getInt("available_copies"));
				book.setBook_id(rs.getInt("book_id"));
				if (book != null) {
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	/**
     * Finds books by their title.
     * 
     * @param title the title of the book to search for
     * @return a list of Book objects with the specified title
     */
	public List<Book> findbytitle(String title) {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM public.books WHERE title = ?";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, title);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book(rs.getString("title"), rs.getString("author"), rs.getString("isbn"),
						rs.getString("category"), rs.getInt("available_copies"));
				book.setBook_id(rs.getInt("book_id"));
				if (book != null) {
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	/**
     * Finds a book by both title and ID.
     * 
     * @param title the title of the book
     * @param id    the ID of the book
     * @return the Book object matching the title and ID, or null if not found
     */
	public Book findByTitleAndId(String title, int id) {
		String sql = "SELECT * FROM public.books WHERE title = ? AND book_id = ?";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, title);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Book book = new Book(rs.getString("title"), rs.getString("author"), rs.getString("isbn"),
						rs.getString("category"), rs.getInt("available_copies"));
				book.setBook_id(rs.getInt("book_id")); // نفس اسم setter في الكلاس
				return book;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
     * Inserts a new book into the database.
     * 
     * @param book the Book object to be inserted
     * @return true if insertion was successful, false otherwise
     */
	public boolean insertBook(Book book) {
		String sql = "INSERT INTO public.books (title, author, isbn, category, available_copies) VALUES (?, ?, ?, ?, ?)";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getIsbn());
			ps.setString(4, book.getCategory());
			ps.setInt(5, book.getAvailable_copies()); // استخدم الاسم الحالي في الكلاس
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}