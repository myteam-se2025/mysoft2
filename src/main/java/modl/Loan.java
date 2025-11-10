package modl;

import java.time.LocalDate;

public class Loan {

	private static final long serialVersionUID = 1L;

	private int loanId;
	private int userId;
	private int bookId;
	private int cdId;
	private LocalDate loanDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private String status;

//	public Loans() {}
	

	public  Loan(int loanId, int userId, int bookId, int cdId,
				 LocalDate loanDate, LocalDate dueDate, LocalDate returnDate, String status) {
		this.loanId = loanId;
		this.userId = userId;
		this.bookId = bookId;
		this.cdId = cdId;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.status = status;
	}
	
	public Loan (int userid, int bookid )
	{
		this.userId = userid;
		this.bookId= bookid;
		this.loanDate= LocalDate.now();
		this.dueDate = loanDate.plusDays(28);
				
	}
	public Loan (String userid, String bookid )
	{
		int s = Integer.parseInt(userid);
        int d = Integer.parseInt(bookid);
		this.userId = s;
		this.bookId= d;
		this.loanDate= LocalDate.now();
		this.dueDate = loanDate.plusDays(28);
		this.cdId = 0;
				
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getCdId() {
		return cdId;
	}

	public void setCdId(Integer cdId) {
		this.cdId = cdId;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
