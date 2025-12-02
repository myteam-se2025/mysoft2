package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DbConnection handles the creation and management of the database connection.
 * Provides a method to obtain a Connection object to the PostgreSQL database.
 * 
 * @author khadeja and masa 
 * @version 1.0
 * @since 2025
 */
public class DbConnection  {
	
	private static DbConnection instance;
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "123456";

	private  Connection connection;
	
	public static DbConnection getInstance() {
        if (instance == null) {
            synchronized (DbConnection.class) { // Thread-safe
                if (instance == null) {
                    instance = new DbConnection();
                }
            }
        }
        return instance;
    }
	/**
     * Obtains a Connection to the PostgreSQL database.
     * If the connection is already established and open, it returns the existing connection.
     * 
     * @return a valid Connection object to the database
     * @throws SQLException if a database access error occurs
     */
	public Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			try {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Database connected successfully.");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed to connect to the database.");
			}
		}
		return connection;
	}
}