package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modl.User;

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


    public User findByIdAndEmail(int id, String email) throws SQLException {
        String sql = "SELECT * FROM public.users WHERE user_id = ? AND email = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, email.trim().toLowerCase());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("user_id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getDate("membership_date")
                );
            }
        }
        return null;
    }
    
   



}

