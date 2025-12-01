package service;

import dao.AdminDAO;

/**
 * Service class to handle admin-related operations such as authentication.
 * It acts as a layer between the DAO and any higher-level application logic.
 * 
 * @author Khadeja and Masa
 * @version 1.0
 */
public class AdminService {

    private AdminDAO adminDAO;

    /**
     * Default constructor initializes the AdminDAO internally.
     */
    public AdminService() {
        adminDAO = new AdminDAO();
    }
    /**
     * Constructor to inject an existing AdminDAO instance.
     *
     * @param adminDAO the AdminDAO instance to be used by this service
     */
    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    /**
     * Authenticates an admin based on username and email.
     *
     * @param username the username of the admin
     * @param email the email of the admin
     * @return true if login is successful, false otherwise
     */
    public boolean login(String username, String email) {
        return adminDAO.login(username, email);
    }
}