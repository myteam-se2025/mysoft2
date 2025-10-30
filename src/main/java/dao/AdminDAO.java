package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modl.Admin;

	public class AdminDAO {

		private static boolean aFound = false;
		private Connection con;

		    public AdminDAO() throws SQLException {
		        con = DbConnection.getConnection();
		    }

		    public  void addUser(Admin admin)  {

		        String sql =  "INSERT INTO public.admins (int admin_id , String username , password ,email ) VALUES (?, ?, ?, ?)";

		        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
		            pstmt.setInt(1, admin.getAdmin_id());
		            pstmt.setString(2, admin.getUsername());
		            pstmt.setString(3, admin.getPassword());
		            pstmt.setString(4, admin.getEmail());

		            pstmt.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    
		    
		    
		    public boolean searchAdminByEmailAndId(int id, String email) {
		        boolean aFound = false;
		        
		        String sql = "SELECT * FROM public.admins WHERE admin_id = ? AND email = ?";

		        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
		            pstmt.setInt(1, id);
		            pstmt.setString(2, email);

		            ResultSet rs = pstmt.executeQuery();
		            if (rs.next()) {
		                System.out.println("Admin Found: " + rs.getString("name") + ", Email: " + rs.getString("email"));
		                aFound = true;
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return aFound;
		    }


		   
	}
	/**/
