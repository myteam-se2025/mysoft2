package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.LoansDAO;
import modl.Loan;

/**
 * @author khadeja and masa
 * @class LoanService
 * 
 * Service class to handle all loan-related operations in the library system.
 * This includes adding loans, checking overdue loans, and retrieving user loans.
 */
public class LoanService {

	 /**
     * Adds a new loan for a user and a book.
     * 
     * @param loan Loan object containing user ID, book ID, and loan/due dates
     * @return The generated loan ID from the database
     * @throws IllegalArgumentException if the book ID or user ID is missing
     */
	public int addbookloan(Loan loan) {
		if (loan.getBookId() == null || loan.getBookId() == 0) {
			throw new IllegalArgumentException("Book ID cannot be empty!");

		}
		if (loan.getUserId() == null || loan.getUserId() == 0) {
			throw new IllegalArgumentException("User ID cannot be empty!");
		}

		LoansDAO l = new LoansDAO();
		return l.insertintloan(loan);

	}
	
	/**
     * Checks for overdue loans in the system.
     * Currently prints a message and can be extended to mark overdue fines.
     */
	private void checkOverdueLoans() {
		System.out.println("Checking for overdue loans... " + LocalDate.now());

		LoansDAO r = new LoansDAO();
/*
		 r.allOverDueLoans(); */
	}

	/**
     * Retrieves all loans for a specific user.
     * 
     * @param userId The ID of the user
     * @return List of Loan objects for the user; empty list if none found
     */
	public List<Loan> findeAllUserLoans(int userid) {
		List<Loan> loans = new ArrayList<>();

		LoansDAO loansdao = new LoansDAO();
		loans = loansdao.findeLoanByUserId(userid);
		return loans;
	}

}
