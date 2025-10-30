package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modl.Book;

public class BookDao {
    private Connection con;

    public BookDao() throws SQLException {
        con = DbConnection.getConnection();
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO public.books (title, author, isbn, category, available_copies) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getIsbn());
            pstmt.setString(4, book.getCategory());
            pstmt.setInt(5, book.getAvailable_copies());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}