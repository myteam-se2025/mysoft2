package dao;

import java.sql.SQLException;

import modl.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
   
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
    
    
    public void searchUserByNameanid(String name, int id)  {

        String sql = "SELECT * FROM public.users WHERE name = ? AND id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("User Found: " + rs.getString("name") + ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
}
}


    public void searchUserByNameandid2(String name, int id)  {

        String sql = "SELECT * FROM public.users WHERE name = ? AND id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("User Found: " + rs.getString("name") + ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
}
}
    
 
}

