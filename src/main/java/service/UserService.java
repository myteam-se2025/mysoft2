package service;

import java.sql.SQLException;

import dao.DbConnection;
import dao.UserDAO;

import modl.User;
import dao.UserDAO;

/**
 * @author khadeja and masa
 * 
 * Service class to manage User operations.
 * Provides functionality to register new users.
 */
public class UserService {

	private UserDAO userdao;
	
	 /**
     * Constructor for dependency injection, allows using a custom UserDAO (e.g., for testing).
     * 
     * @param userdao UserDAO instance to use
     */
	public UserService(UserDAO userdao) {
        this.userdao = userdao; 
    }
	public void registerUser(User user)throws SQLException {
		userdao.addUser(user);
	}

}