package modl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Fine {

	private int fine_id =0;
	private int loan_id =0;
	private BigDecimal amount ;
	private String status = null ;
	private LocalDate issued_date = null;
	
	
	 public Fine(int fineid, int loanid, BigDecimal amount, String status, LocalDate issued_date) {
	  this.fine_id = fineid;
	  this.loan_id = loanid;
	  this.amount = amount;
	  this.status = status;
	  this.issued_date = issued_date;
	 }

	    // Getters
	    public Integer getFineId() {
	        return fine_id;
	    }

	    public Integer getLoanId() {
	        return loan_id;
	    }

	    public BigDecimal getAmount() {
	        return amount;
	    }

	    public String getstatus() {
	        return status;
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

	    public void setAmount(BigDecimal amount) {
	        this.amount = amount;
	    }


	    public void setDateIssued(LocalDate dateIssued) {
	        this.issued_date = dateIssued;
	    }

	   

	    public void setStatus(String status) {
	        this.status = status;
	    } 
	
}
