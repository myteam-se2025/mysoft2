package service;

import modl.*;
import soft.*;
import dao.*;

public class LogInService {

	
	  public void login( int password, String email) {
	        AdminDAO adminDAO = null;
	        UserDAO userDAO = null;
	        try {
	            adminDAO = new AdminDAO();
	            userDAO = new UserDAO();
	            
	           if (adminDAO.searchAdminByemailandid(password , email))
	           {
	        	   
	        	   AdminMain adminFrame = new AdminMain();
            	   adminFrame.setVisible(true);
            	   adminFrame.setLocationRelativeTo(null);
	           }
	        } catch (Exception e) {
	            try {
	               if (userDAO.searchUserByemailandid(password , email))
	               {
	            	   UserMain usermFrame = new UserMain();
		        	   usermFrame.setVisible(true);
		        	   usermFrame.setLocationRelativeTo(null);
	               }
	            	
	               
	            } catch (Exception ex) {
	                
	            }
	        }

	    }
	
	
}
