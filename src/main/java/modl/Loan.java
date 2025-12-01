package modl;

import java.time.LocalDate;

/**
 * Represents a Loan in the library management system.
 * Contains information about the loaned book or CD, the user, loan date, and due date.
 * Provides constructors, getters, and setters for all fields.
 * 
 * @author Khadeja and Masa
 * @version 1.0
 * @since 2025
 */
public class Loan {

	private static final long serialVersionUID = 1L;

	private int loanId;
	private int userId;
	private int bookId;
	private int cdId;
	private LocalDate loanDate;
	private LocalDate dueDate;

	
	
	
	 /**
     * Constructs a Loan using string inputs for user and book IDs.
     *
     * @param userid the user ID as a string
     * @param bookid the book ID as a string
     */
	public Loan(int userid, int bookid) {
		this.userId = userid;
		this.bookId = bookid;
		this.loanDate = LocalDate.now();
		this.dueDate = loanDate.plusDays(28);

	}

	 /**
     * Constructs a Loan with all fields specified.
     *
     * @param loanId the loan ID
     * @param userId the user ID
     * @param bookId the book ID
     * @param cdId the CD ID
     * @param loanDate the loan date
     * @param dueDate the due date
     */
	public Loan(int loanId, int userId, int bookId, int cdId, LocalDate loanDate, LocalDate dueDate) {
		this.loanId = loanId;
		this.userId = userId;
		this.bookId = bookId;
		this.cdId = cdId;
		this.loanDate = loanDate;
		this.dueDate = dueDate;

	}

	/**
     * Constructs a Loan for a specific user and book, with default loan and due dates.
     *
     * @param userid the ID of the user
     * @param bookid the ID of the book
     */
	public Loan(String userid, String bookid) {

		this.userId = Integer.parseInt(userid);
		this.bookId = Integer.parseInt(bookid);

		this.loanDate = LocalDate.now();
		this.dueDate = loanDate.plusDays(28);
		this.cdId = 0;

	}

	public Loan() {
        this.loanId = 0;
        this.userId = 0;
        this.bookId = 0;
        this.cdId = 0;
        this.loanDate = LocalDate.now();
        this.dueDate = loanDate.plusDays(28);
    }

	
	  /** @return the book ID */
    public Integer getBookId() {
        return bookId;
    }

    /** @return the CD ID */
    public Integer getCdId() {
        return cdId;
    }

    /** @return the due date */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /** @return the loan date */
    public LocalDate getLoanDate() {
        return loanDate;
    }

    /** @return the loan ID */
    public Integer getLoanId() {
        return loanId;
    }

    /** @return the user ID */
    public Integer getUserId() {
        return userId;
    }

    /** @param bookId sets the book ID */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /** @param cdId sets the CD ID */
    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }

    /** @param dueDate sets the due date */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /** @param loanDate sets the loan date */
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    /** @param loanId sets the loan ID */
    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    /** @param userId sets the user ID */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
