
package dao;

import java.sql.*;
import modl.Admin;

public class AdminDAO extends BaseDAO {

	// إدراج أدمن جديد
	public void addUser(Admin admin) {
		String sql = "INSERT INTO public.admins (admin_id, username, password, email) VALUES (?, ?, ?, ?)";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, admin.getAdmin_id());
			ps.setString(2, admin.getUsername());
			ps.setString(3, admin.getPassword());
			ps.setString(4, admin.getEmail());

			ps.executeUpdate();

		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}

	// البحث عن أدمن حسب الـ ID والبريد الإلكتروني
	public Admin findByIdAndEmail(int id, String email) {
		String sql = "SELECT * FROM public.admins WHERE admin_id = ? AND email = ?";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.setString(2, email.trim().toLowerCase());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Admin admin = new Admin(rs.getInt("admin_id"), rs.getString("username"), rs.getString("password"),
						rs.getString("email"));
				return admin;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}