package dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modl.Loan;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoansDAOTest {

	
	private LoansDAO loansDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        mockResultSet = mock(ResultSet.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        loansDAO = new LoansDAO() {
            @Override
            protected Connection getConnection() {
                return mockConnection;
            }
        };

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testAllOverDueLoansSuccess() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getDate("due_date")).thenReturn(Date.valueOf(LocalDate.now().minusDays(1)), Date.valueOf(LocalDate.now().plusDays(1)));
        when(mockResultSet.getInt("loan_id")).thenReturn(1, 2);

        loansDAO.allOverDueLoans();

        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    void testAllOverDueLoansEmptyResult() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);

        loansDAO.allOverDueLoans();

        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    void testAllOverDueLoansQueryFailure() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("Query failed"));

        assertThrows(SQLException.class, () -> loansDAO.allOverDueLoans());
    }

    @Test
    void testAllOverDueLoansConnectionFailure() {
        loansDAO = new LoansDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                throw new SQLException("Connection failed");
            }
        };

        assertThrows(SQLException.class, () -> loansDAO.allOverDueLoans());
    }

    @Test
    void testDeleteloanSuccess() throws SQLException {
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean result = loansDAO.deleteloan(1);

        assertTrue(result);
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testDeleteloanNoRowsAffected() throws SQLException {
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        boolean result = loansDAO.deleteloan(999);

        assertFalse(result);
    }

    @Test
    void testDeleteloanFailure() throws SQLException {
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("Delete failed"));

        boolean result = loansDAO.deleteloan(1);

        assertFalse(result);
    }

    @Test
    void testDeleteloanConnectionFailure() {
        loansDAO = new LoansDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                throw new SQLException("Connection failed");
            }
        };

        boolean result = loansDAO.deleteloan(1);

        assertFalse(result);
    }

    @Test
    void testFindeLoanBybookIdFound() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("Loan_id")).thenReturn(1);
        when(mockResultSet.getInt("user_id")).thenReturn(100);
        when(mockResultSet.getInt("book_id")).thenReturn(200);
        when(mockResultSet.getInt("cd_id")).thenReturn(0);
        when(mockResultSet.getDate("loan_date")).thenReturn(Date.valueOf(LocalDate.now().minusDays(5)));
        when(mockResultSet.getDate("due_date")).thenReturn(Date.valueOf(LocalDate.now().plusDays(5)));

        Loan loan = loansDAO.findeLoanBybookId(200);

        assertNotNull(loan);
        assertEquals(1, loan.getLoanId());
        assertEquals(100, loan.getUserId());
        assertEquals(200, loan.getBookId());
        verify(mockPreparedStatement).setInt(1, 200);
    }

    @Test
    void testFindeLoanBybookIdNotFound() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);

        Loan loan = loansDAO.findeLoanBybookId(999);

        assertNull(loan);
    }

    @Test
    void testFindeLoanBybookIdWithNullDates() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("Loan_id")).thenReturn(1);
        when(mockResultSet.getInt("user_id")).thenReturn(100);
        when(mockResultSet.getInt("book_id")).thenReturn(200);
        when(mockResultSet.getInt("cd_id")).thenReturn(0);
        when(mockResultSet.getDate("loan_date")).thenReturn(null);
        when(mockResultSet.getDate("due_date")).thenReturn(null);

        Loan loan = loansDAO.findeLoanBybookId(200);

        assertNotNull(loan);
        assertNull(loan.getLoanDate());
        assertNull(loan.getDueDate());
    }

    @Test
    void testFindeLoanBybookIdQueryFailure() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("Query failed"));

        Loan loan = loansDAO.findeLoanBybookId(1);

        assertNull(loan);
    }

    @Test
    void testFindeLoanBybookIdConnectionFailure() {
        loansDAO = new LoansDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                throw new SQLException("Connection failed");
            }
        };

        Loan loan = loansDAO.findeLoanBybookId(1);

        assertNull(loan);
    }

    @Test
    void testFindeLoanByUserIdMultiple() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("Loan_id")).thenReturn(1, 2);
        when(mockResultSet.getInt("user_id")).thenReturn(100, 100);
        when(mockResultSet.getInt("book_id")).thenReturn(200, 201);
        when(mockResultSet.getInt("cd_id")).thenReturn(0, 0);
        when(mockResultSet.getDate("loan_date")).thenReturn(Date.valueOf(LocalDate.now().minusDays(5)), Date.valueOf(LocalDate.now().minusDays(10)));
        when(mockResultSet.getDate("due_date")).thenReturn(Date.valueOf(LocalDate.now().plusDays(5)), Date.valueOf(LocalDate.now().plusDays(10)));

        java.util.List<Loan> loans = loansDAO.findeLoanByUserId(100);

        assertEquals(2, loans.size());
        assertEquals(200, loans.get(0).getBookId());
        assertEquals(201, loans.get(1).getBookId());
        verify(mockPreparedStatement).setInt(1, 100);
    }

    @Test
    void testFindeLoanByUserIdEmpty() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);

        java.util.List<Loan> loans = loansDAO.findeLoanByUserId(999);

        assertTrue(loans.isEmpty());
    }

    @Test
    void testFindeLoanByUserIdWithNullDates() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("Loan_id")).thenReturn(1);
        when(mockResultSet.getInt("user_id")).thenReturn(100);
        when(mockResultSet.getInt("book_id")).thenReturn(200);
        when(mockResultSet.getInt("cd_id")).thenReturn(0);
        when(mockResultSet.getDate("loan_date")).thenReturn(null);
        when(mockResultSet.getDate("due_date")).thenReturn(null);

        java.util.List<Loan> loans = loansDAO.findeLoanByUserId(100);

        assertEquals(1, loans.size());
        assertNull(loans.get(0).getLoanDate());
        assertNull(loans.get(0).getDueDate());
    }

    @Test
    void testFindeLoanByUserIdQueryFailure() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("Query failed"));

        java.util.List<Loan> loans = loansDAO.findeLoanByUserId(1);

        assertTrue(loans.isEmpty());
    }

    @Test
    void testFindeLoanByUserIdConnectionFailure() {
        loansDAO = new LoansDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                throw new SQLException("Connection failed");
            }
        };

        java.util.List<Loan> loans = loansDAO.findeLoanByUserId(1);

        assertTrue(loans.isEmpty());
    }

    @Test
    void testInsertintloanSuccess() throws SQLException {
        Loan loan = new Loan(0, 100, 200, 0, LocalDate.now().minusDays(1), LocalDate.now().plusDays(14));
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("loan_id")).thenReturn(1);

        int result = loansDAO.insertintloan(loan);

        assertEquals(1, result);
        assertEquals(1, loan.getLoanId());
        verify(mockPreparedStatement).setInt(1, 100);
        verify(mockPreparedStatement).setInt(2, 200);
        verify(mockPreparedStatement).setDate(eq(3), any(Date.class));
        verify(mockPreparedStatement).setDate(eq(4), any(Date.class));
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    void testInsertintloanNoGeneratedId() throws SQLException {
        Loan loan = new Loan(0, 100, 200, 0, LocalDate.now().minusDays(1), LocalDate.now().plusDays(14));
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        int result = loansDAO.insertintloan(loan);

        assertEquals(-1, result);
    }

    @Test
    void testInsertintloanFailure() throws SQLException {
        Loan loan = new Loan(0, 100, 200, 0, LocalDate.now().minusDays(1), LocalDate.now().plusDays(14));
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("Insert failed"));

        int result = loansDAO.insertintloan(loan);

        assertEquals(-1, result);
    }

    @Test
    void testInsertintloanConnectionFailure() {
        loansDAO = new LoansDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                throw new SQLException("Connection failed");
            }
        };

        Loan loan = new Loan(0, 100, 200, 0, LocalDate.now().minusDays(1), LocalDate.now().plusDays(14));
        int result = loansDAO.insertintloan(loan);

        assertEquals(-1, result);
    }

    @Test
    void testInsertloanSuccess() throws SQLException {
        Loan loan = new Loan(0, 100, 200, 0, LocalDate.now().minusDays(1), LocalDate.now().plusDays(14));
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean result = loansDAO.insertloan(loan);

        assertTrue(result);
        verify(mockPreparedStatement).setInt(1, 100);
        verify(mockPreparedStatement).setInt(2, 200);
        verify(mockPreparedStatement).setDate(eq(3), any(Date.class));
        verify(mockPreparedStatement).setDate(eq(4), any(Date.class));
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testInsertloanFailure() throws SQLException {
        Loan loan = new Loan(0, 100, 200, 0, LocalDate.now().minusDays(1), LocalDate.now().plusDays(14));
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("Insert failed"));

        boolean result = loansDAO.insertloan(loan);

        assertFalse(result);
    }

    @Test
    void testInsertloanConnectionFailure() {
        loansDAO = new LoansDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                throw new SQLException("Connection failed");
            }
        };

        Loan loan = new Loan(0, 100, 200, 0, LocalDate.now().minusDays(1), LocalDate.now().plusDays(14));
        boolean result = loansDAO.insertloan(loan);

        assertFalse(result);
    }
}