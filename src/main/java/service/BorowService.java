package service;

import javax.swing.JOptionPane;

import dao.FineDAO;
import dao.LoansDAO;
import modl.Fine;
import modl.Loan;
import soft.UserBorow;

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
	    	
	    	FineDAO fine = new FineDAO();
	    	 return fine.findeuserFines(loan.getLoanId());
	    	}else {
	    		return null;
	    	}   	 
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(null, "ID must be a number.");
	        return null;
	    }
	    
	}
	
	
	
	public void borowabook ( String user_id, String book_id)
	{
		 Loan ll = new Loan(user_id,book_id);		 
		 LoanService lo = new LoanService();
		 int genratedLoanId = lo.addbookloan(ll);
		 
		 
		  Fine fine = new Fine(ll.getDueDate() , genratedLoanId);		 
		  FineServise ff = new FineServise();
		  ff.addbookFine(fine);
		 
	}




	public String processBorrowRequest(String user_id, String book_id) {
		
		 Loan loan = bookAvalbltyChack(book_id);
	        if (loan != null) 
	        {
	            return "This book is not available.";
	        }

	        Fine fine = userfinescheck(user_id);
	        if (fine != null && fine.getstatus()) 
	        {
	            return "You have unpaid fines. Please pay them to borrow books.";
	        }

	        borowabook(user_id, book_id);
	        return "Book borrowed successfully!";

	}
	
	}

