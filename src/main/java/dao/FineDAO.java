package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modl.*;


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
	                    rs.getBigDecimal("amount"),
	                    rs.getString("status"),
	                    rs.getDate("issued_date") != null ? rs.getDate("due_date").toLocalDate() : null
	                );
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return null;
		
	}
}
