package dao;

import java.sql.*;
import modl.User;

/**
 * UserDAO handles all database operations related to library users.
 * Provides methods to add new users and find users by ID and email.
 * 
 * @author khadeja and masa
 * @version 1.0
 * @since 2025
 */
public class UserDAO extends BaseDAO {

	 /**
     * Adds a new user to the database.
     *
     * @param user the User object containing user details
     * @return true if the insertion was successful, false otherwise
     * @throws SQLException if a database access error occurs
     */
	public boolean addUser(User user) throws SQLException {
		String sql = "INSERT INTO public.users (full_name, email, phone, address, membership_date) VALUES (?, ?, ?, ?, ?)";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, user.getFull_name());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getAddress());
			ps.setDate(5, new java.sql.Date(user.getMembership_date().getTime()));

			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		} 
	}

	/**
     * Finds a user by their ID and email.
     *
     * @param id the user ID
     * @param email the user's email
     * @return the User object if found, null otherwise
     */
	public User findByIdAndEmail(int id, String email) {
		String sql = "SELECT * FROM public.users WHERE user_id = ? AND email = ?";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.setString(2, email.trim().toLowerCase());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User user = new User(rs.getString("full_name"), rs.getString("email"), rs.getString("phone"),
						rs.getString("address"), rs.getDate("membership_date"));
				user.setUser_id(rs.getInt("user_id")); // ضبط الـ ID بعد جلب البيانات
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}