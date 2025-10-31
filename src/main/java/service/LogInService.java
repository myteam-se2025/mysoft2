package service;

import modl.*;
import soft.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.*;

public class LogInService {
	
	

	    public String login(int id, String email) throws SQLException{
	    	AdminDAO adminDAO = new AdminDAO() ;
	        UserDAO userDAO = new UserDAO();
	        try {
	            Admin admin = adminDAO.findByIdAndEmail(id, email);
	            if (admin != null) {
	                return "ADMIN";
	            }

	            User user = userDAO.findByIdAndEmail(id, email);
	            if (user != null) {
	                return "USER";
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "ERROR";
	        }
	        return "NOT_FOUND";
	    }
	}
	
	

