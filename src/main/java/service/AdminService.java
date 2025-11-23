package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DbConnection;

public class AdminService {

	private Connection con;

	public AdminService() throws SQLException {
		con = DbConnection.getConnection();
	}

	// ترجع true اذا كان admin موجود ومطابق
	public boolean login(String username, String email) {
		String sql = "SELECT * FROM admins WHERE username = ? AND email = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, username);
			stmt.setString(2, email);
			ResultSet rs = stmt.executeQuery();
			return rs.next(); // اذا وجد صف = true
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}