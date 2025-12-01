package dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modl.Fine;

class FineDAOTest {

    private FineDAO fineDAO;
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
        fineDAO = new FineDAO() {
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
    void testDeletefineSuccess() throws SQLException {
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean result = fineDAO.deletefine(1);

        assertTrue(result);
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testDeletefineNoRowsAffected() throws SQLException {
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        boolean result = fineDAO.deletefine(999);

        assertFalse(result);
    }

    @Test
    void testDeletefineFailure() throws SQLException {
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("Delete failed"));

        boolean result = fineDAO.deletefine(1);

        assertFalse(result);
    }
 
    @Test
    void testDeletefineConnectionFailure() {
        fineDAO = new FineDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                throw new SQLException("Connection failed");
            }
        };

        boolean result = fineDAO.deletefine(1);

        assertFalse(result);
    }

    @Test
    void testFindeAllFinesMultiple() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("fine_id")).thenReturn(1, 2);
        when(mockResultSet.getInt("loan_id")).thenReturn(100, 100);
        when(mockResultSet.getInt("amount")).thenReturn(50, 100);
        when(mockResultSet.getBoolean("status")).thenReturn(true, false);
        when(mockResultSet.getDate("issued_date")).thenReturn(Date.valueOf(LocalDate.now().minusDays(5)), Date.valueOf(LocalDate.now().minusDays(10)));

        List<Fine> fines = fineDAO.findeAllFines(100);

        assertEquals(2, fines.size());
        assertEquals(50, fines.get(0).getAmount());
        assertEquals(100, fines.get(1).getAmount());
        verify(mockPreparedStatement).setInt(1, 100);
    }

    @Test
    void testFindeAllFinesEmpty() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);

        List<Fine> fines = fineDAO.findeAllFines(999);

        assertTrue(fines.isEmpty());
    }

    @Test
    void testFindeAllFinesWithNullDate() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("fine_id")).thenReturn(1);
        when(mockResultSet.getInt("loan_id")).thenReturn(100);
        when(mockResultSet.getInt("amount")).thenReturn(50);
        when(mockResultSet.getBoolean("status")).thenReturn(true);
        when(mockResultSet.getDate("issued_date")).thenReturn(null);

        List<Fine> fines = fineDAO.findeAllFines(100);

        assertEquals(1, fines.size());
        assertNull(fines.get(0).getDateIssued());
    }

    @Test
    void testFindeAllFinesQueryFailure() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("Query failed"));

        List<Fine> fines = fineDAO.findeAllFines(1);

        assertTrue(fines.isEmpty());
    }

    @Test
    void testFindeAllFinesConnectionFailure() {
        fineDAO = new FineDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                throw new SQLException("Connection failed");
            }
        };

        List<Fine> fines = fineDAO.findeAllFines(1);

        assertTrue(fines.isEmpty());
    }

    @Test
    void testFindeFineByFineIdFound() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("fine_id")).thenReturn(1);
        when(mockResultSet.getInt("loan_id")).thenReturn(100);
        when(mockResultSet.getInt("amount")).thenReturn(50);
        when(mockResultSet.getBoolean("status")).thenReturn(true);
        when(mockResultSet.getDate("issued_date")).thenReturn(Date.valueOf(LocalDate.now().minusDays(5)));

        Fine fine = fineDAO.findeFineByFineId(1);

        assertNotNull(fine);
        assertEquals(1, fine.getFineId());
        assertEquals(100, fine.getLoanId());
        assertEquals(50, fine.getAmount());
        verify(mockPreparedStatement).setInt(1, 1);
    }

    @Test
    void testFindeFineByFineIdNotFound() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);

        Fine fine = fineDAO.findeFineByFineId(999);

        assertNull(fine);
    }

    @Test
    void testFindeFineByFineIdWithNullDate() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("fine_id")).thenReturn(1);
        when(mockResultSet.getInt("loan_id")).thenReturn(100);
        when(mockResultSet.getInt("amount")).thenReturn(50);
        when(mockResultSet.getBoolean("status")).thenReturn(true);
        when(mockResultSet.getDate("issued_date")).thenReturn(null);

        Fine fine = fineDAO.findeFineByFineId(1);

        assertNotNull(fine);
        assertNull(fine.getDateIssued());
    }

    @Test
    void testFindeFineByFineIdQueryFailure() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("Query failed"));

        Fine fine = fineDAO.findeFineByFineId(1);

        assertNull(fine);
    }

    @Test
    void testFindeFineByFineIdConnectionFailure() {
        fineDAO = new FineDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                throw new SQLException("Connection failed");
            }
        };

        Fine fine = fineDAO.findeFineByFineId(1);

        assertNull(fine);
    }

    @Test
    void testFindeuserFinesFound() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("fine_id")).thenReturn(1);
        when(mockResultSet.getInt("loan_id")).thenReturn(100);
        when(mockResultSet.getInt("amount")).thenReturn(50);
        when(mockResultSet.getBoolean("status")).thenReturn(true);
        when(mockResultSet.getDate("issued_date")).thenReturn(Date.valueOf(LocalDate.now().minusDays(5)));

        Fine fine = fineDAO.findeuserFines(100);

        assertNotNull(fine);
        assertEquals(100, fine.getLoanId());
        assertEquals(50, fine.getAmount());
        verify(mockPreparedStatement).setInt(1, 100);
    }

    @Test
    void testFindeuserFinesNotFound() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);

        Fine fine = fineDAO.findeuserFines(999);

        assertNull(fine);
    }

    @Test
    void testFindeuserFinesWithNullDate() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("fine_id")).thenReturn(1);
        when(mockResultSet.getInt("loan_id")).thenReturn(100);
        when(mockResultSet.getInt("amount")).thenReturn(50);
        when(mockResultSet.getBoolean("status")).thenReturn(true);
        when(mockResultSet.getDate("issued_date")).thenReturn(null);

        Fine fine = fineDAO.findeuserFines(100);

        assertNotNull(fine);
        assertNull(fine.getDateIssued());
    }

    @Test
    void testFindeuserFinesQueryFailure() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("Query failed"));

        Fine fine = fineDAO.findeuserFines(1);

        assertNull(fine);
    }

    @Test
    void testFindeuserFinesConnectionFailure() {
        fineDAO = new FineDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                throw new SQLException("Connection failed");
            }
        };

        Fine fine = fineDAO.findeuserFines(1);

        assertNull(fine);
    }

    @Test
    void testInsertFineSuccess() throws SQLException {
        Fine fine = new Fine(0, 100, 50, true, LocalDate.now().minusDays(1));
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        fineDAO.insertFine(fine);

        verify(mockConnection).setAutoCommit(true);
        verify(mockPreparedStatement).setInt(1, 100);
        verify(mockPreparedStatement).setInt(2, 50);
        verify(mockPreparedStatement).setDate(eq(3), any(Date.class));
        verify(mockPreparedStatement).setBoolean(4, true);
        verify(mockPreparedStatement).executeUpdate();
    }
//
    @Test
    void testInsertFineFailure() throws SQLException {
        Fine fine = new Fine(0, 100, 50, true, LocalDate.now().minusDays(1));
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("Insert failed"));

        assertDoesNotThrow(() -> fineDAO.insertFine(fine));
    }

    @Test
    void testInsertFineConnectionFailure() {
        fineDAO = new FineDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                throw new SQLException("Connection failed");
            }
        };

        Fine fine = new Fine(0, 100, 50, true, LocalDate.now().minusDays(1));

        assertDoesNotThrow(() -> fineDAO.insertFine(fine));
    }

   
}