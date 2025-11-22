package service;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.FineDAO;
import dao.LoansDAO;
import modl.Fine;
import modl.Loan;
import soft.UserBorow;
import service.*;
public class BorowService {

	
	
	
	
	
	
	
	
	
	
	public void borowabook ( String user_id, String book_id)
	{
		 Loan ll = new Loan(user_id,book_id);		 
		 LoanService lo = new LoanService();
		 int genratedLoanId = lo.addbookloan(ll);
		 
		 
		  Fine fine = new Fine(ll.getDueDate() , genratedLoanId);		 
		  FineService ff = new FineService();
		  ff.addbookFine(fine);
		 
	}




	public String processBorrowRequest(String user_id, String book_id) {
		
		BookService b;
		FineService f;
		try {
			
		    b = new BookService();
		    Loan loan = b.bookAvalbltyChack(book_id);
	        if (loan != null) 
	        {
	            return "This book is not available.";
	        }
	        
	        
	        f = new FineService();
            Fine fine = f.userfinescheck(user_id);
            
	        if (fine != null && fine.getstatus()) 
	        {
	            return "You have unpaid fines. Please pay them to borrow books.";
	        }

	        
	        borowabook(user_id, book_id);
	        return "Book borrowed successfully!";

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	}

