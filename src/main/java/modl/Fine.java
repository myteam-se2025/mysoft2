package modl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Fine {

	private int fine_id =0;
	private int loan_id =0;
	private int amount =0 ;
	private boolean activestatus = false ;
	private LocalDate issued_date = null;
	
	
	 public Fine(int fineid, int loanid, int amount, boolean string, LocalDate issued_date) {
	  this.fine_id = fineid;
	  this.loan_id = loanid;
	  this.amount = amount;
	  this.activestatus = false;
	  this.issued_date = issued_date;
	 }
	 
	 
	 public Fine (LocalDate startdate , int loanid)
	 {
		 this.loan_id = loanid;
		 this.issued_date = startdate;
		 this.amount = 10;
		 this.activestatus = false;
	 }

	    public Fine() {
		// TODO Auto-generated constructor stub
	}


		// Getters
	    public Integer getFineId() {
	        return fine_id;
	    }

	    public Integer getLoanId() {
	        return loan_id;
	    }

	    public int getAmount() {
	        return amount;
	    }

	    public boolean getstatus() {
	        return activestatus;
	    }

	    public LocalDate getDateIssued() {
	        return issued_date;
	    }

	   


	    // Setters
	    public void setFineId(Integer fineId) {
	        this.fine_id = fineId;
	    }

	    public void setLoanId(Integer loanId) {
	        this.loan_id = loanId;
	    }

	    public void setAmount(int amount) {
	        this.amount = amount;
	    }


	    public void setDateIssued(LocalDate dateIssued) {
	        this.issued_date = dateIssued;
	    }

	   

	    public void setStatus(boolean activestatus) {
	        this.activestatus = activestatus;
	    } 
	
}
