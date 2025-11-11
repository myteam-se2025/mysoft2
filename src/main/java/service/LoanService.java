package service;

import java.time.LocalDate;

import dao.LoansDAO;
import modl.Loan;

public class LoanService {

	
	public int addbookloan(Loan loan )
	{
		if (loan.getBookId() == null || loan.getBookId() ==0) {
			 throw new IllegalArgumentException("Book ID cannot be empty!");
			   
		}
		if (loan.getUserId() == null || loan.getUserId() == 0) {
			 throw new IllegalArgumentException("User ID cannot be empty!");
      }
		
		LoansDAO l = new LoansDAO();
		return l.insertintloan(loan);
		
		
	}
	
	
	
	private void checkOverdueLoans ()
	{
		  System.out.println("Checking for overdue loans... " + LocalDate.now());

		  LoansDAO r = new LoansDAO();
		  
		/*  r.allOverDueLoans(); */
	}
	
	}

