package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modl.Fine;


public class FineDAO extends BaseDAO {

	public Fine insurtFine()
	{
		
		return null;
		
	}
	
	public Fine findeuserFines  (int id)
	{
		
	        String sql = "SELECT * FROM public.fines WHERE loan_id = ?";
	        
	        try (Connection con = getConnection() ; PreparedStatement ps = con.prepareStatement(sql)) {

	            
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                return new Fine(
	                    rs.getInt("fine_id"),
	                    rs.getInt("loan_id"),
	                    rs.getInt("amount"),
	                    rs.getString("status"),
	                    rs.getDate("issued_date") != null ? rs.getDate("due_date").toLocalDate() : null
	                );
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return null;
		
	}
	
	
	
	public void insertFine (Fine fine)
	{
		 String sql = "INSERT INTO public.fines (loan_id , amount , issued_date , status ) VALUES (?, ?, ? , ?)";
	        try (Connection con = getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	        	 con.setAutoCommit(true);
	           
	            ps.setInt(1, fine.getLoanId());
	            ps.setInt(2, fine.getAmount());
	        
	            ps.setDate(3, java.sql.Date.valueOf(fine.getDateIssued()));      
	            ps.setBoolean(4, fine.getstatus()); 
	             
	            ps.executeUpdate();
	            
	        }

	            catch (SQLException e) {
	                JOptionPane.showMessageDialog(null, e.getMessage());
	            }
	}
	
	}

