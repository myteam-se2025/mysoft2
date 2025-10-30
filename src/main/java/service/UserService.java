package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DbConnection;
import dao.UserDAO;
import modl.User;

public class UserService {

    private Connection con;

    public UserService() throws SQLException {
        con = DbConnection.getConnection();
    }

 private UserDAO userdao ;

    public void UoserService() throws SQLException {
        userdao = new UserDAO();
    }
    public void registerUser(User user) {
        userdao.addUser(user);
    }

    // ترجع User object اذا البيانات مطابقة
    public User login(String username, String email) {
        String sql = "SELECT * FROM users WHERE full_name = ? AND email = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getDate("membership_date")
                );
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // اذا ماوجد
    }
}