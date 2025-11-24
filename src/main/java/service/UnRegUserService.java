package service;

import dao.AdminDAO;
import modl.User;
import java.util.List;

public class UnRegUserService {

    private AdminDAO dao;

    public UnRegUserService() {
        this.dao = new AdminDAO();
    }

    // Constructor للسماح بالـ mock
    public UnRegUserService(AdminDAO dao) {
        this.dao = dao;
    }

    public List<User> getEligibleUsers() {
        return dao.findEligibleUsers();
    }

    public boolean unregisterUser(int userId) {
        return dao.deleteUserById(userId);
    }
}