package service;

import java.sql.SQLException;
import dao.CdDAO;
import modl.Cd;

public class CdService {

    private CdDAO cdDao;

    public CdService() throws SQLException {
        cdDao = new CdDAO();
    }

    /**
     * Adds a new CD after validating its data.
     * @param cd the CD to be added
     * @return message indicating success or failure
     */
    public String addCd(Cd cd) {
        // ✅ Basic validation
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

        // ✅ Attempt to add to database
        boolean success = cdDao.insertCd(cd);
        return success ? "CD added successfully!" : "Error while saving CD to database.";
    }
}