package modl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Represents a Fine in the library management system.
 * Contains information about the fine amount, associated loan, status, and issued date.
 * Provides constructors, getters, and setters for all fields.
 * 
 * @author Khadeja and Masa
 * @version 1.0
 * @since 2025
 */
public class Fine {

	private int fine_id = 0;
	private int loan_id = 0;
	private int amount = 0;
	private boolean activestatus = false;
	private LocalDate issued_date = null;

	/**
     * Default constructor initializing fields with default values.
     */
	public Fine() {
		this.fine_id = fine_id;
		this.loan_id = loan_id;
		this.amount = amount;
		this.activestatus = activestatus;
		this.issued_date = issued_date;	}

	 /**
     * Constructs a Fine with all fields specified.
     *
     * @param fineid the fine ID
     * @param loanid the associated loan ID
     * @param amount the amount of the fine
     * @param activestatus the status of the fine
     * @param issued_date the date the fine was issued
     */
	public Fine(int fineid, int loanid, int amount, boolean activestatus, LocalDate issued_date) {
		this.fine_id = fineid;
		this.loan_id = loanid;
		this.amount = amount;
		this.activestatus = activestatus;
		this.issued_date = issued_date;
	}

	 /**
     * Constructs a Fine with default amount and inactive status.
     *
     * @param startdate the date the fine is issued
     * @param loanid the associated loan ID
     */
	public Fine(LocalDate startdate, int loanid) {
		this.loan_id = loanid;
		this.issued_date = startdate;
		this.amount = 10;
		this.activestatus = false;
	}

	/** @return the amount of the fine */
    public int getAmount() {
        return amount;
    }

    /** @return the date the fine was issued */
    public LocalDate getDateIssued() {
        return issued_date;
    }

    /** @return the fine ID */
    public Integer getFineId() {
        return fine_id;
    }

    /** @return the loan ID associated with the fine */
    public Integer getLoanId() {
        return loan_id;
    }

    /** @return true if the fine is active, false otherwise */
    public boolean getstatus() {
        return activestatus;
    }

    /** @param amount sets the fine amount */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /** @param dateIssued sets the date the fine was issued */
    public void setDateIssued(LocalDate dateIssued) {
        this.issued_date = dateIssued;
    }

    /** @param fineId sets the fine ID */
    public void setFineId(Integer fineId) {
        this.fine_id = fineId;
    }

    /** @param loanId sets the associated loan ID */
    public void setLoanId(Integer loanId) {
        this.loan_id = loanId;
    }

    /** @param activestatus sets the status of the fine */
    public void setStatus(boolean activestatus) {
        this.activestatus = activestatus;
    }

}
