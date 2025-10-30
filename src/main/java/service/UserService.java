package service;

import java.sql.SQLException;

import dao.UserDAO;
import modl.User;

public class UserService {

 
    
    private UserDAO userdao ;
    
    public UserService() throws SQLException {
        userdao = new UserDAO();
    }
    
    public void registerUser(User user) {
        userdao.addUser(user);
    }

   public void searchUser(String name, int id) {
	userdao.searchUserByNameanid(name, id);
    }

    
}
