package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modl.User;
import modl.Admin;

/**
 * AdminDAO handles admin-related database operations including authentication,
 * adding admins, and managing users.
 * Provides methods for admin login, retrieval, addition, and user management.
 * 
 * @author khadeja and masa
 * @version 1.0
 * @since 2025
 */
public class AdminDAO extends BaseDAO {
	
	
	 /**
     * Authenticates an admin using username and email.
     *
     * @param username the admin's username
     * @param email    the admin's email
     * @return true if admin exists with the given credentials, false otherwise
     */
	public boolean login(String username, String email) {
        String sql = "SELECT * FROM admins WHERE username = ? AND email = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, email);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	 /**
     * Adds a new admin to the database.
     *
     * @param admin the Admin object containing admin details
     */
    public void addAdmin(Admin admin) {
        String sql = "INSERT INTO public.admins (username, password, email) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.setString(3, admin.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
        	e.printStackTrace();   }
    }

    /**
     * Finds an admin by ID and email.
     *
     * @param id    the admin's ID
     * @param email the admin's email
     * @return Admin object if found, null otherwise
     */
    public Admin findByIdAndEmail(int id, String email) {
        String sql = "SELECT * FROM public.admins WHERE admin_id = ? AND email = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
           ps.setInt(1, id);
            ps.setString(2, email.trim().toLowerCase());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Adds a new user to the database.
     *
     * @param u the User object containing user details
     * @return true if the insertion was successful, false otherwise
     */
    public boolean addUser(User u) {
        String sql = "INSERT INTO public.users (full_name, email, phone, address, membership_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getFull_name());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPhone());
            ps.setString(4, u.getAddress());
            ps.setDate(5, u.getMembership_date() != null ? new java.sql.Date(u.getMembership_date().getTime())
                                                         : new java.sql.Date(System.currentTimeMillis()));
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves users who are eligible (i.e., users with no active loans).
     *
     * @return a list of eligible User objects
     */
    public List<User> findEligibleUsers() {
        List<User> list = new ArrayList<>();

        String sql =
            "SELECT u.user_id, u.full_name, u.email " +
            "FROM users u " +
            "LEFT JOIN loans l ON u.user_id = l.user_id " +
            "WHERE l.loan_id IS NULL";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User(
                        rs.getString("full_name"),
                        rs.getString("email"),
                        "",
                        "",
                        null
                );
                u.setUser_id(rs.getInt("user_id"));
                list.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    /**
     * Deletes a user by their ID.
     *
     * @param id the user's ID
     * @return true if a row was deleted, false otherwise
     */
    public boolean deleteUserById(int id) {
        String sql = "DELETE FROM public.users WHERE user_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}