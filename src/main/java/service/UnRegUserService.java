package service;

import dao.AdminDAO;
import modl.User;
import java.util.List;

public class UnRegUserService {

    private AdminDAO dao = new AdminDAO();

    // جلب المستخدمين المؤهلين للحذف
    public List<User> getEligibleUsers() {
        return dao.findEligibleUsers();
    }

    // حذف مستخدم
    public boolean unregisterUser(int userId) {
        return dao.deleteUserById(userId);
    }
}