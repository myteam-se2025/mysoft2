package service;

import javax.swing.JOptionPane;

import modl.Fine;

/**
 * @author khadeja and masa
 * @class PayFineService
 * 
 * Service class to handle paying fines.
 * Uses FineService to interact with the database.
 */
public class PayFineService {

	/**
     * Constructor to inject FineService dependency
     * 
     * @param fineService instance of FineService
     */
    private FineService fineService;

    public PayFineService(FineService fineService) {
        this.fineService = fineService;
    }

    /**
     * Pays a fine for a given user.
     * Delegates the operation to FineService.
     * 
     * @param userid The ID of the user paying the fine
     * @param fineid The ID of the fine to be paid
     * @return String message indicating success
     */
    public String payfine(String userid, String fineid) {
        fineService.payFine(userid, fineid);
        return "Paid successfully";
    }
}
