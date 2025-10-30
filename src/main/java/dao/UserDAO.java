package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modl.User;

public class UserDAO {

	private static boolean uFound = false;
    private Connection con;

    public UserDAO() throws SQLException {
        con = DbConnection.getConnection();
    }

    public void addUser(User user) {

        String sql = "INSERT INTO public.users (full_name, email, phone, address,membership_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, user.getFull_name());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getAddress());
            pstmt.setDate(5, new java.sql.Date(user.getMembership_date().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean searchUserByEmailAndId( int id , String email)  {
    	
    	
    	  boolean userFound = false;
    	    String sql = "SELECT * FROM public.users WHERE user_id = ? AND email = ?";

    	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
    	        pstmt.setInt(1, id);
    	        pstmt.setString(2, email.trim().toLowerCase());

    	        ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("User Found: " + rs.getString("name") + ", Email: " + rs.getString("email"));
                uFound = true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
}
        return uFound;
}
    public User findUserByNameAndEmail(String fullName, String email) {
        String sql = "SELECT * FROM users WHERE full_name = ? AND email = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, fullName);
            pstmt.setString(2, email);
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                System.out.println("User Found: " + rs.getString("name") + ", Email: " + rs.getString("email"));
                uFound = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
}
        return null;
    }




}

