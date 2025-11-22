package service;

import javax.swing.JOptionPane;

import modl.Fine;

public class PayFineService {

	
	
	
	public String payfine(String userid , String fineid)
	{
		FineService f = new FineService();
		Fine fine=	f.userfinescheck(userid);
		if(fine == null)
		{
		return " user id is does'not exist  ";
		}
		
		f.payFine(userid, fineid);
	    
	    
		return null; 
	}
}
