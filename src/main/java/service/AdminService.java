package service;

import java.sql.SQLException;

import dao.AdminDAO;

public class AdminService {
    private AdminDAO adminDAO;

    public AdminService() throws SQLException {
        adminDAO = new AdminDAO();
    }

    public boolean login(String username, String password) {
        return adminDAO.findByUsername(username) != null
                && adminDAO.findByUsername(username).getPassword().equals(password);
    }
}