
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modl.Book;

public class BookDAO extends BaseDAO {

	public void bookavalablty(String isbn) {

	}

	public List<Book> findAllBooks() {

		List<Book> books = new ArrayList<>();

		String sql = "SELECT * FROM public.books ";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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