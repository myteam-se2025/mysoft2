package service;

import dao.AdminDAO;
import dao.UserDAO;
import modl.Admin;
import modl.User;

public class LogInService {

	public String handleLogin(String email, String password) {

		// ===== فحوصات الإدخال =====
		if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			return "Please enter both email and password.";
		}

		// ===== فحص صيغة الإيميل =====
		if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|org|net)$")) {
			return "Email must end with .com, .org, or .net (e.g. name@example.com)";
		}

		// ===== فحص أن الرقم صحيح =====
		int id;
		try {
			id = Integer.parseInt(password);
			if (id <= 0) {
				return "Password must be a positive number.";
			}
		} catch (NumberFormatException ex) {
			return "Password must be a number.";
		}

		// ===== فحص من قاعدة البيانات =====
		try {
			AdminDAO adminDAO = new AdminDAO();
			UserDAO userDAO = new UserDAO();

			// البحث عن admin
			Admin admin = adminDAO.findByIdAndEmail(id, email);
			if (admin != null)
				return "ADMIN";

			// البحث عن user
			User user = userDAO.findByIdAndEmail(id, email);
			if (user != null)
				return "USER";

			return "No matching user or admin found.";

		} catch (Exception e) { // تم تعديل هنا لتجنب الخطأ
			e.printStackTrace();
			return "Database error occurred.";
		}
	}
}