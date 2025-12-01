package service;

import dao.AdminDAO;
import modl.User;
import java.util.List;

/**
 * @author khadeja and masa
 * 
 * Service class to manage unregistered users.
 * Provides functionality to get eligible users and unregister them.
 */
public class UnRegUserService {

    private AdminDAO dao;

    /**
     * Default constructor initializes the AdminDAO.
     */
    public UnRegUserService() {
        this.dao = new AdminDAO();
    }

    /**
     * Constructor for dependency injection (e.g., for testing with mocks).
     * 
     * @param dao AdminDAO instance to use
     */
    public UnRegUserService(AdminDAO dao) {
        this.dao = dao;
    }
    /**
     * Retrieves a list of users who are eligible for unregistration.
     * 
     * @return List of eligible User objects
     */
    public List<User> getEligibleUsers() {
        return dao.findEligibleUsers();
    }
    /**
     * Unregisters a user by their ID.
     * 
     * @param userId ID of the user to remove
     * @return true if deletion was successful, false otherwise
     */
    public boolean unregisterUser(int userId) {
        return dao.deleteUserById(userId);
    }
}