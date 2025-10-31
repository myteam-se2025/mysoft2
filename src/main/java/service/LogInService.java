package service;

import modl.*;
import soft.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.*;

public class LogInService {
	
	

	
	public String login(int id, String email) {
	    try {
	        AdminDAO adminDAO = new AdminDAO();
	        UserDAO userDAO = new UserDAO();

	        if (adminDAO.findByIdAndEmail(id, email) != null) return "ADMIN";
	        if (userDAO.findByIdAndEmail(id, email) != null) return "USER";

	        return "NOT_FOUND";
	    } catch (SQLException e) {
	        e.printStackTrace(); // You could log this instead
	        return "ERROR";
	    }
	}

	}
	
	

