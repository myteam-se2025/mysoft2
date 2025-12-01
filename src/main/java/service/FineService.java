package service;

import dao.FineDAO;
import dao.LoansDAO;
import modl.Fine;
import modl.Loan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author khadeja and masa
 * @class FineService
 * 
 * Service class to handle all fine-related operations in the library system.
 * This includes adding fines, finding fines for users, and paying fines.
 */
public class FineService {

    private final FineDAO fineDAO;
    private final LoanService loanService;
    private final LoansDAO loansDAO;

    /**
     * Default constructor initializing DAO and service instances.
     */
    public FineService() {
        this.fineDAO = new FineDAO();
        this.loanService = new LoanService();
        this.loansDAO = new LoansDAO();
    }
    
    /**
     * Constructor with dependency injection.
     * 
     * @param fineDAO DAO for fine operations
     * @param loanService Service for loan operations
     * @param loansDAO DAO for loan operations
     */
    public FineService(FineDAO fineDAO, LoanService loanService, LoansDAO loansDAO) {
        this.fineDAO = fineDAO;
        this.loanService = loanService;
        this.loansDAO = loansDAO;
    }
    
 
    /**
     * Adds a fine to the system for a specific loan.
     * 
     * @param fine Fine object containing loan ID and fine details
     * @return Success or error message
     */
    public String addbookFine(Fine fine) {

        if (fine.getLoanId() == null || fine.getLoanId() == 0) {
        	return "Loan ID cannot be empty!";
        }

        fineDAO.insertFine(fine);
        return null;
    }

    /**
     * Finds all fines for a given user ID.
     * 
     * @param userId The ID of the user
     * @return List of Fine objects; returns empty list if none found or invalid input
     */
    public List<Fine> findeAlluserfines(String userid) {
        List<Fine> fines = new ArrayList<>();

        if (userid == null || userid.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a user ID.");
            return null;
        }

        int idd;
        try {
            idd = Integer.parseInt(userid);
            if (idd <= 0) {
                JOptionPane.showMessageDialog(null, "ID must be a positive number.");
                return null;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "ID must be a number.");
            return null;
        }

        List<Loan> loans = loanService.findeAllUserLoans(idd);

        for (Loan loan : loans) {
            Fine fine = fineDAO.findeuserFines(loan.getLoanId());
            if (fine != null) {
                fines.add(fine);
            }
        }

        return fines;
    }

    /**
     * Pays a fine for a user and returns the book if the fine is active.
     * 
     * @param userId The ID of the user
     * @param fineId The ID of the fine
     * @return Message indicating success, failure, or if loan hasn't expired
     */
    public String payFine(String userid, String fineid) {

        if (userid == null || userid.isEmpty()) return null;
        if (fineid == null || fineid.isEmpty()) return null;

        int uidd, fidd;
        try {
            uidd = Integer.parseInt(userid);
            fidd = Integer.parseInt(fineid);
            if (uidd <= 0 || fidd <= 0) return null;
        } catch (NumberFormatException ex) {
            return null;
        }

        Fine fine = fineDAO.findeFineByFineId(fidd);

        if (fine.getstatus()) {
            fineDAO.deletefine(fidd);
            loansDAO.deleteloan(fine.getLoanId());
            return "fine is payed and book is returned ";
        } else {
            return "your loan hasnt expierd yet";
        }
    }
}
