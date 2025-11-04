package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modl.*;
import dao.*;

public class LoansDAO extends BaseDAO{

	public Loan findeLoanBybookId(int id)
	{
		
	        String sql = "SELECT * FROM public.loans WHERE book_id = ?";
	        
	        try (Connection con = getConnection() ; PreparedStatement ps = con.prepareStatement(sql)) {

	            
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                return new Loan(
	                    rs.getInt("Loan_id"),
	                    rs.getInt("user_id"),
	                    rs.getInt("book_id"),
	                    rs.getInt("cd_id"),
	                    rs.getDate("loan_date") != null ? rs.getDate("loan_date").toLocalDate() : null,
	                    rs.getDate("due_date") != null ? rs.getDate("due_date").toLocalDate() : null,
	                    rs.getDate("retrn_date") != null ? rs.getDate("retrn_date").toLocalDate() : null,
	                    rs.getString("status")
	                );
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return null;
		
	        
	    }
	
	public Loan findeLoanByuserId(int id)
	{
		
	        String sql = "SELECT * FROM public.loans WHERE user_id = ?";
	        
	        try (Connection con = getConnection() ; PreparedStatement ps = con.prepareStatement(sql)) {

	            
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                return new Loan(
	                    rs.getInt("Loan_id"),
	                    rs.getInt("user_id"),
	                    rs.getInt("book_id"),
	                    rs.getInt("cd_id"),
	                    rs.getDate("loan_date") != null ? rs.getDate("loan_date").toLocalDate() : null,
	                    rs.getDate("due_date") != null ? rs.getDate("due_date").toLocalDate() : null,
	                    rs.getDate("retrn_date") != null ? rs.getDate("retrn_date").toLocalDate() : null,
	                    rs.getString("status")
	                );
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return null;
		
	        
	    }


	 public boolean insertloan(Loan loan) {
	        String sql = "INSERT INTO public.loans (loan_id, user_id, book_id, cd-id, loan-date, due_date,return_date , status) VALUES (?, ?, ?, ?, ?,?,?,?)";
	        try (Connection con = getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	          //  ps.setInt(1, loan.getLoanId());
	            ps.setInt(2, loan.getUserId());
	            ps.setInt(3, loan.getBookId());
	        //  ps.setInt(4, loan.getCdId());
	            ps.setDate(5, java.sql.Date.valueOf(loan.getLoanDate()));       ps.executeUpdate();
	            ps.setDate(6, java.sql.Date.valueOf(loan.getDueDate()));  
	          //  ps.setDate(7, java.sql.Date.valueOf(loan.getReturnDate()));  
		      //  ps.setString(8, loan.getStatus());   
	            ps.executeUpdate();
	            
	            return true;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}
	

