package service;

import java.sql.SQLException;

import dao.DbConnection;
import dao.UserDAO;

import modl.User;
import dao.UserDAO;

public class UserService {

	private UserDAO userdao;
	
	public UserService() throws SQLException {
		userdao = new UserDAO();
	}
	public UserService(UserDAO userdao) {
        this.userdao = userdao; 
    }
	public void registerUser(User user) {
		userdao.addUser(user);
	}

}