package service;

import java.sql.SQLException;
import dao.CdDAO;
import modl.Cd;

/**
 * Service class for managing CDs in the library system.
 * Handles validation and interaction with the CdDAO for database operations.
 * 
 * @author Khadeja and Masa
 * @version 1.0
 */
public class CdService {

	private CdDAO cdDao;

	 /**
     * Default constructor initializing CdDAO.
     * 
     * @throws SQLException if database connection fails
     */
	public CdService() throws SQLException {
		cdDao = new CdDAO();
	}
	public CdService(CdDAO cdDao) {
	    this.cdDao = cdDao;
	}
	/**
	 * Adds a new CD after validating its data.
	 * 
	 * @param cd the CD to be added
	 * @return message indicating success or failure
	 */
	public String addCd(Cd cd) {
		
		if (cd.getTitle() == null || cd.getTitle().trim().isEmpty()) {
			return "Title cannot be empty!";
		}
		if (cd.getArtist() == null || cd.getArtist().trim().isEmpty()) {
			return "Artist name cannot be empty!";
		}
		if (cd.getGenre() == null || cd.getGenre().trim().isEmpty()) {
			return "Genre cannot be empty!";
		}
		if (cd.getAvailable_copies() < 0) {
			return "Number of copies cannot be negative!";
		}

		
		boolean success = cdDao.insertCd(cd);
		return success ? "CD added successfully!" : "Error while saving CD to database.";
	}
}