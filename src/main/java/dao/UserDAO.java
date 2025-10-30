package dao;

import java.sql.SQLException;

import modl.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
