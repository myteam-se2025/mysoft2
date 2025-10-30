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


    public void registerUser(User user) {
        userdao.addUser(user);
    }

    

    public User login(String username, String email) {
        return userdao.findUserByNameAndEmail(username, email);
    }
}