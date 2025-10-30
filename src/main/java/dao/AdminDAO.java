package dao;


import java.sql.SQLException;

import modl.Admin;
import modl.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

	public class AdminDAO {

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
		    public void searchAdminByNameandid(String name, int id)  {

		        String sql = "SELECT * FROM public.admins WHERE admin_id = ? AND username";
		        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
		        	pstmt.setInt(1, id);
		        	pstmt.setString(2, name);
		          
		            ResultSet rs = pstmt.executeQuery();
		            while (rs.next()) {
		                System.out.println("User Found: " + rs.getString("name") + ", Email: " + rs.getString("email"));
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		}
		}

	}
	
