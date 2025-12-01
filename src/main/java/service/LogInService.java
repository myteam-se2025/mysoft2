package service;

import dao.AdminDAO;
import dao.UserDAO;
import modl.Admin;
import modl.User;

/**
 * @author khadeja and masa
 * @class LogInService
 * 
 * Service class to handle login functionality for both Admins and Users.
 * Validates input, checks email format, and verifies credentials against the database.
 */
public class LogInService {

	 /**
     * Handles login for a user or admin.
     * 
     * @param email    The email of the user/admin
     * @param password The password (expected as a numeric string)
     * @return A string indicating the result:
     *         - "ADMIN" if an admin with matching credentials is found
     *         - "USER" if a user with matching credentials is found
     *         - Error message if input is invalid or no match found
     */
	public String handleLogin(String email, String password) {

		
		if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			return "Please enter both email and password.";
		}

		
		if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|org|net)$")) {
			return "Email must end with .com, .org, or .net (e.g. name@example.com)";
		}

		
		int id;
		try {
			id = Integer.parseInt(password);
			if (id <= 0) {
				return "Password must be a positive number.";
			}
		} catch (NumberFormatException ex) {
			return "Password must be a number.";
		}

		
		try {
			AdminDAO adminDAO = new AdminDAO();
			UserDAO userDAO = new UserDAO();

			
			Admin admin = adminDAO.findByIdAndEmail(id, email);
			if (admin != null)
				return "ADMIN";

			
			User user = userDAO.findByIdAndEmail(id, email);
			if (user != null)
				return "USER";

			return "No matching user or admin found.";

		} catch (Exception e) { 
			e.printStackTrace();
			return "Database error occurred.";
		}
	}
}