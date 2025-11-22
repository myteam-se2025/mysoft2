package service;

import javax.swing.JOptionPane;

import modl.Fine;

public class PayFineService {

	
	
	
	public String payfine(String userid , String fineid)
	{
		FineService f = new FineService();
		//Fine fine=	f.userfinescheck(userid);
	
		f.payFine(userid, fineid);
	    
	    
		return null; 
	}
}
