package service;

import java.sql.SQLException;
<<<<<<< HEAD
=======

import dao.DbConnection;
import dao.UserDAO;
>>>>>>> branch 'master' of https://github.com/khadejahammodeh/mysoft2.git
import modl.User;
import dao.UserDAO;

public class UserService {

    private UserDAO userdao;

    public UserService() throws SQLException {
        userdao = new UserDAO();
    }

<<<<<<< HEAD
    public void registerUser(User user) {
        userdao.addUser(user);
    }

    // ✅ دالة login التي يتم استدعاؤها من Admin.java
=======
 private UserDAO userdao ;

    public void UoserService() throws SQLException {
        userdao = new UserDAO();
    }
    public void registerUser(User user) {
        userdao.addUser(user);
    }

    // ترجع User object اذا البيانات مطابقة
>>>>>>> branch 'master' of https://github.com/khadejahammodeh/mysoft2.git
    public User login(String username, String email) {
        return userdao.findUserByNameAndEmail(username, email);
    }
}