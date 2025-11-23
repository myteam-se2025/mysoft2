package dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import modl.*;

class UserDAOTest {

    private UserDAO userDAO;
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
          userDAO = new UserDAO() {
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
    void testAddUserSuccess() throws SQLException {
        User user = new User("John", "john@example.com", "1221934", "Nablus", new Date(2020, 11, 1));

         when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean result = userDAO.addUser(user);

        assertTrue(result);

         verify(mockPreparedStatement).setString(1, user.getFull_name());
        verify(mockPreparedStatement).setString(2, user.getEmail());
        verify(mockPreparedStatement).setString(3, user.getPhone());
        verify(mockPreparedStatement).setString(4, user.getAddress());
        verify(mockPreparedStatement).setDate(eq(5), any(java.sql.Date.class));
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testAddUserFailure() throws SQLException {
        User user = new User("John", "john@example.com", "1221934", "Nablus", new Date(104, 11, 1));

        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        boolean result = userDAO.addUser(user);

        assertFalse(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    

    @Test
    void testAddUserConnectionFailure() {
        userDAO = new UserDAO() {
            @Override
            protected Connection getConnection() throws SQLException {
                throw new SQLException("Connection failed");
            }
        };

        User user = new User("John", "john@example.com", "1221934", "Nablus", new Date(2020, 11, 1));
        boolean result = userDAO.addUser(user);

        assertFalse(result);
    }

    @Test
    void testFindByIdAndEmailUserExists() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("user_id")).thenReturn(1);
        when(mockResultSet.getString("full_name")).thenReturn("John Doe");
        when(mockResultSet.getString("email")).thenReturn("john@example.com");
        when(mockResultSet.getString("phone")).thenReturn("123456789");
        when(mockResultSet.getString("address")).thenReturn("Nablus");
        when(mockResultSet.getDate("membership_date")).thenReturn(new java.sql.Date(new Date(0, 0, 0).getTime()));

        User user = userDAO.findByIdAndEmail(1, "john@example.com");

        assertNotNull(user);
        assertEquals(1, user.getUser_id());
        assertEquals("John Doe", user.getFull_name());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("123456789", user.getPhone());
        assertEquals("Nablus", user.getAddress());
    }

    @Test
    void testFindByIdAndEmailUserDoesNotExist() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);

          User user = userDAO.findByIdAndEmail(999, "nonexistent@example.com");

        assertNull(user);
    }

    @Test
    void testFindByIdAndEmailWithTrimmedEmail() throws SQLException {
        String inputEmail = " John@Example.com ";
        String normalizedEmail = "john@example.com";

        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("user_id")).thenReturn(1);
        when(mockResultSet.getString("full_name")).thenReturn("John Doe");
        when(mockResultSet.getString("email")).thenReturn(normalizedEmail);
        when(mockResultSet.getString("phone")).thenReturn("123456789");
        when(mockResultSet.getString("address")).thenReturn("Nablus");
        when(mockResultSet.getDate("membership_date")).thenReturn(new java.sql.Date(new Date(0, 0, 0).getTime()));

        User user = userDAO.findByIdAndEmail(1, inputEmail);

        assertNotNull(user);
        assertEquals(normalizedEmail, user.getEmail());
        verify(mockPreparedStatement).setString(2, normalizedEmail);  // Verify normalization happened
    }

    

    @Test
    void testFindByIdAndEmailQueryFailure() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("Query failed"));

        User user = userDAO.findByIdAndEmail(1, "john@example.com");

        assertNull(user);
    }

    @Test
    void testFindByIdAndEmailWithNullDateInDB() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("user_id")).thenReturn(1);
        when(mockResultSet.getString("full_name")).thenReturn("John Doe");
        when(mockResultSet.getString("email")).thenReturn("john@example.com");
        when(mockResultSet.getString("phone")).thenReturn("123456789");
        when(mockResultSet.getString("address")).thenReturn("Nablus");
        when(mockResultSet.getDate("membership_date")).thenReturn(null);  // Null date from DB

        User user = userDAO.findByIdAndEmail(1, "john@example.com");

        assertNotNull(user);
        assertNull(user.getMembership_date());  // Assuming User can handle null date
    }
}