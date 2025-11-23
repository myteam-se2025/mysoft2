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

//	public Loans() {}

	public Loan(int userid, int bookid) {
		this.userId = userid;
		this.bookId = bookid;
		this.loanDate = LocalDate.now();
		this.dueDate = loanDate.plusDays(28);

	}

	public Loan(int loanId, int userId, int bookId, int cdId, LocalDate loanDate, LocalDate dueDate) {
		this.loanId = loanId;
		this.userId = userId;
		this.bookId = bookId;
		this.cdId = cdId;
		this.loanDate = loanDate;
		this.dueDate = dueDate;

	}

	public Loan(String userid, String bookid) {

		this.userId = Integer.parseInt(userid);
		this.bookId = Integer.parseInt(bookid);

		this.loanDate = LocalDate.now();
		this.dueDate = loanDate.plusDays(28);
		this.cdId = 0;

	}

	public Integer getBookId() {
		return bookId;
	}

	public Integer getCdId() {
		return cdId;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public void setCdId(Integer cdId) {
		this.cdId = cdId;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
