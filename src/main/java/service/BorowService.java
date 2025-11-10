package service;

import javax.swing.JOptionPane;

import dao.FineDAO;
import dao.LoansDAO;
import modl.Fine;
import modl.Loan;

public class BorowService {

	
	
	
	public Loan bookAvalbltyChack(String book_id)
	{
		
		if (book_id == null || book_id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a book ID.");
			return null;
        }
		
		int idd = 0;
	    try {
	        idd = Integer.parseInt(book_id);
	        if (idd <= 0) {
	            JOptionPane.showMessageDialog(null, "ID must be a positive number.");
	            return null;
	        }
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(null, "ID must be a number.");
	        return null;
	    }
	    
	    try {
	    	
	    	LoansDAO loan = new LoansDAO();
	    	return loan.findeLoanBybookId(idd);
	    	
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(null, "ID must be a number.");
	        return null;
	    }
		
	    	
	    }
	
	
	public Fine userfinescheck(String userid)
	{
		if (userid == null || userid.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a book ID.");
			return null;
        }
		
		int idd = 0;
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
	    
            try {
	    	
	    	LoansDAO lo = new LoansDAO();
	    	Loan loan = lo.findeLoanByuserId(idd);
	    	if (loan != null)
	    	{
	    	int loanid = loan.getLoanId();
	    	FineDAO fine = new FineDAO();
	    	 return fine.findeuserFines(loanid);
	    	}else {
	    		return null;
	    	}
	    	 
	    	 
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(null, "ID must be a number.");
	        return null;
	    }
	    
	}
	}

