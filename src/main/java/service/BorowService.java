package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		 Loan loan = new Loan(user_id,book_id);		 
		 LoanService lo = new LoanService();
		 int genratedLoanId = lo.addbookloan(loan);
		 
		 
		  Fine fine = new Fine(loan.getDueDate() , genratedLoanId);		 
		  FineService ff = new FineService();
		  ff.addbookFine(fine);
		 
	}




	public String processBorrowRequest(String user_id, String book_id) {
		
		int countstatustrue = 0;
		List<Fine> fines = new ArrayList<>();
		
		BookService b;
		FineService f;
		try {
			
		    b = new BookService();
		    Loan loan = b.bookAvalbltyChack(book_id);
		    
		    f = new FineService();
            fines = f.findeAlluserfines(user_id);
           
	        if (loan != null) 
	        {
	            return "This book is not available.";
	        }else if (fines.size()!= 0 ) 
	        {
	        	for (int i =0 ; i < fines.size() ; i++)
	        	{
	        		if (fines.get(i).getstatus())
	        		{
	        			countstatustrue ++;
	        		}
	        	}
	            if (countstatustrue > 0)
	            {
	            	return "you have one or more unpayed fines pay them to get the book ";
	            }
	        	
	        }else {
	        borowabook(user_id, book_id);
	        return "Book borrowed successfully!";
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	}

