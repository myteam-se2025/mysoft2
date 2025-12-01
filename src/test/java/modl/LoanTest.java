package modl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoanTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	 @Test
	    void testConstructorWithUserIdAndBookId() {
	        int userId = 10;
	        int bookId = 20;
	        Loan loan = new Loan(userId, bookId);

	        assertEquals(userId, loan.getUserId());
	        assertEquals(bookId, loan.getBookId());
	        assertEquals(0, loan.getCdId()); // default cdId
	        assertNotNull(loan.getLoanDate());
	        assertEquals(loan.getLoanDate().plusDays(28), loan.getDueDate());
	        assertEquals(0, loan.getLoanId()); // default loanId
	    }

	    @Test
	    void testConstructorWithStrings() {
	        String userId = "15";
	        String bookId = "25";
	        Loan loan = new Loan(userId, bookId);

	        assertEquals(15, loan.getUserId());
	        assertEquals(25, loan.getBookId());
	        assertEquals(0, loan.getCdId());
	        assertNotNull(loan.getLoanDate());
	        assertEquals(loan.getLoanDate().plusDays(28), loan.getDueDate());
	        assertEquals(0, loan.getLoanId());
	    }

	    @Test
	    void testConstructorWithAllParameters() {
	        LocalDate loanDate = LocalDate.of(2025, 12, 1);
	        LocalDate dueDate = LocalDate.of(2025, 12, 29);
	        Loan loan = new Loan(1, 10, 20, 30, loanDate, dueDate);

	        assertEquals(1, loan.getLoanId());
	        assertEquals(10, loan.getUserId());
	        assertEquals(20, loan.getBookId());
	        assertEquals(30, loan.getCdId());
	        assertEquals(loanDate, loan.getLoanDate());
	        assertEquals(dueDate, loan.getDueDate());
	    }

	    @Test
	    void testSettersAndGetters() {
	        Loan loan = new Loan(1, 2);

	        LocalDate newLoanDate = LocalDate.of(2025, 12, 2);
	        LocalDate newDueDate = LocalDate.of(2025, 12, 30);

	        loan.setLoanId(100);
	        loan.setUserId(200);
	        loan.setBookId(300);
	        loan.setCdId(400);
	        loan.setLoanDate(newLoanDate);
	        loan.setDueDate(newDueDate);

	        assertEquals(100, loan.getLoanId());
	        assertEquals(200, loan.getUserId());
	        assertEquals(300, loan.getBookId());
	        assertEquals(400, loan.getCdId());
	        assertEquals(newLoanDate, loan.getLoanDate());
	        assertEquals(newDueDate, loan.getDueDate());
	    }

}
