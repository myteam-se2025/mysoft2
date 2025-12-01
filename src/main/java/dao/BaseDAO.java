package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.*;

public abstract class BaseDAO {

	
	protected void closeResources(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null && !con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	// 🔹 دالة ترجع Connection جاهزة من DbConnection
	protected Connection getConnection() throws SQLException {
		DbConnection c = new DbConnection();
		return c.getConnection();
	}
}