package service;

import modl.*;
import soft.*;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.*;

public class LogInService {

	
	  public void login( int password, String email) throws SQLException {
	        AdminDAO adminDAO = new AdminDAO() ;
	        UserDAO userDAO = new UserDAO();
	        try {
	            if (adminDAO.searchAdminByEmailAndId(password, email)) {
	                AdminMain adminFrame = new AdminMain();
	                adminFrame.setVisible(true);
	                adminFrame.setLocationRelativeTo(null);
	                return;
	            }else if (userDAO.searchUserByEmailAndId(password, email)) {
	                UserMain userFrame = new UserMain();
	                userFrame.setVisible(true);
	                userFrame.setLocationRelativeTo(null);
	                return;
	            }else {

	            JOptionPane.showMessageDialog(
	            	    null,
	            	    "No matching admin or user found.\nPlease check your ID or email and try again.",
	            	    "Login Failed",
	            	    JOptionPane.WARNING_MESSAGE
	            	);

	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            }
	        }
	            
	        

	    }
	
	

