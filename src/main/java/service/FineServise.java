package service;

import dao.FineDAO;
import dao.LoansDAO;
import modl.Fine;


public class FineServise {

	
	
	
	public String addbookFine(Fine fine )
	{
		if (fine.getLoanId() == null || fine.getLoanId() ==0) {
            return "Title cannot be empty!";
		}
		
		if (fine.getLoanId() == null || fine.getLoanId() == 0) {
		    return "Title cannot be empty!";
		}


		
		FineDAO f = new FineDAO();
		f.insertFine(fine);
		
		return null;
	}
}
