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

    public User login(String fullName, String email) {
        return userdao.findUserByNameAndEmail(fullName, email);
    }

    
}
