package modl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FineTest {

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
	    void testConstructorWithAllParameters() {
	        LocalDate date = LocalDate.of(2025, 12, 1);
	        Fine fine = new Fine(1, 100, 50, true, date);

	        assertEquals(1, fine.getFineId());
	        assertEquals(100, fine.getLoanId());
	        assertEquals(50, fine.getAmount());
	        assertTrue(fine.getstatus());
	        assertEquals(date, fine.getDateIssued());
	    }

	    @Test
	    void testConstructorWithStartDateAndLoanId() {
	        LocalDate date = LocalDate.of(2025, 12, 1);
	        Fine fine = new Fine(date, 200);

	        assertEquals(0, fine.getFineId()); // default
	        assertEquals(200, fine.getLoanId());
	        assertEquals(10, fine.getAmount()); // default amount
	        assertFalse(fine.getstatus()); // default status
	        assertEquals(date, fine.getDateIssued());
	    }

	    @Test
	    void testEmptyConstructorDefaults() {
	        Fine fine = new Fine();

	        assertEquals(0, fine.getFineId());
	        assertEquals(0, fine.getLoanId());
	        assertEquals(0, fine.getAmount());
	        assertFalse(fine.getstatus());
	        assertNull(fine.getDateIssued());
	    }

	    @Test
	    void testSettersAndGetters() {
	        Fine fine = new Fine();

	        LocalDate date = LocalDate.of(2025, 12, 2);

	        fine.setFineId(5);
	        fine.setLoanId(50);
	        fine.setAmount(25);
	        fine.setStatus(true);
	        fine.setDateIssued(date);

	        assertEquals(5, fine.getFineId());
	        assertEquals(50, fine.getLoanId());
	        assertEquals(25, fine.getAmount());
	        assertTrue(fine.getstatus());
	        assertEquals(date, fine.getDateIssued());
	    }

}
