package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modl.Book;
import dao.*;

public class loansDAO extends BaseDAO{

	public void findeLoanById(int id)
	{
		
	        String sql = "SELECT * FROM public.loans WHERE book_id = ?";
	        
	        try (Connection con = getConnection() ; PreparedStatement ps = con.prepareStatement(sql)) {

	            
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                Book book = new Book(
	                    rs.getString("title"),
	                    rs.getString("author"),
	                    rs.getString("isbn"),
	                    rs.getString("category"),
	                    rs.getInt("available_copies")
	                );
	                book.setBook_id(rs.getInt("book_id")); // نفس اسم setter في الكلاس
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	    }
	}
	

