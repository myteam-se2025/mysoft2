package dao;

import java.sql.*;
import modl.User;

public class UserDAO extends BaseDAO {


	public boolean addUser(User user) {
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

	// البحث عن مستخدم حسب ID والبريد الإلكتروني
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