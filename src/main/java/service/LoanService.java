package service;

import dao.LoansDAO;
import modl.Loan;

public class LoanService {

	
	public String addbookloan(Loan loan )
	{
		if (loan.getBookId() == null || loan.getBookId() ==0) {
            return "Title cannot be empty!";
		}
		if (loan.getUserId() == null || loan.getUserId() == 0) {
            return "Title cannot be empty!";
        }
		
		LoansDAO l = new LoansDAO();
		l.insertloan(loan);
		
		return null;
	}
	
	}

