package service;

import dao.AdminDAO;

public class AdminService {

    private AdminDAO adminDAO;

    public AdminService() {
        adminDAO = new AdminDAO();
    }

    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public boolean login(String username, String email) {
        return adminDAO.login(username, email);
    }
}