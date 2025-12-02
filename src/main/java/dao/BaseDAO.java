package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.*;

/**
 * Abstract base class for all DAO classes.
 * Provides common database utility methods such as obtaining a connection
 * and closing resources.
 *  
 * @author khadeja and masa
 * @version 1.0 
 * @since 2025
 */
public abstract class BaseDAO {

	/**
     * Closes database resources if they are not null.
     * This includes ResultSet, PreparedStatement, and Connection.
     *
     * @param con the Connection object to close
     * @param ps  the PreparedStatement object to close
     * @param rs  the ResultSet object to close
     */
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

	/**
     * Obtains a database connection from DbConnection.
     *
     * @return a valid Connection object
     * @throws SQLException if a database access error occurs
     */
	
	protected Connection getConnection() throws SQLException {
		 return DbConnection.getInstance().getConnection(); // use singleton
		   }
}