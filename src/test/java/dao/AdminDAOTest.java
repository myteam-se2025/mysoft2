package dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import modl.Admin;
import modl.User;

class AdminDAOTest {

    private AdminDAO adminDAO;
    private Connection mockConnection;
    private PreparedStatement mockPs;
    private ResultSet mockRs;

    @BeforeEach
    void setUp() throws Exception {
        mockConnection = mock(Connection.class);
        mockPs = mock(PreparedStatement.class);
        mockRs = mock(ResultSet.class);

        adminDAO = new AdminDAO() {
            @Override
            protected Connection getConnection() {
                return mockConnection;
            }
        };

        // Default behavior: return mockPs for ANY SQL string
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
    }

    // -----------------------------------------------------------------------
    // TEST: login()
    // -----------------------------------------------------------------------

    @Test
    void testLoginSuccess() throws Exception {
        when(mockRs.next()).thenReturn(true);

        boolean result = adminDAO.login("masa", "m@m.com");

        assertTrue(result);
        verify(mockPs).executeQuery();
    }

    @Test
    void testLoginFail() throws Exception {
        when(mockRs.next()).thenReturn(false);

        boolean result = adminDAO.login("wrong", "x@x.com");

        assertFalse(result);
        verify(mockPs).executeQuery();
    }

    @Test
    void testLoginSQLException() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenThrow(new SQLException());

        boolean result = adminDAO.login("admin", "admin@mail.com");

        assertFalse(result);
    }

    // -----------------------------------------------------------------------
    // TEST: addAdmin()
    // -----------------------------------------------------------------------

    @Test
    void testAddAdmin() throws Exception {
        Admin admin = new Admin("admin1", "1234", "a@a.com");

        adminDAO.addAdmin(admin);

        verify(mockPs).setString(1, "admin1");
        verify(mockPs).setString(2, "1234");
        verify(mockPs).setString(3, "a@a.com");
        verify(mockPs).executeUpdate();
    }

   
    @Test
    void testAddAdminSQLException() throws Exception {
        Admin admin = new Admin("masa", "pass123", "m@m.com");

        // Correct order: first return the mock PS, then make its method throw
          when(mockPs.executeUpdate()).thenThrow(new SQLException("Database error"));

        assertDoesNotThrow(() -> adminDAO.addAdmin(admin));

        verify(mockPs).executeUpdate(); // optional: prove it was called
        }

    // -----------------------------------------------------------------------
    // TEST: findByIdAndEmail()
    // -----------------------------------------------------------------------

    @Test
    void testFindByIdAndEmailSuccess() throws Exception {
        when(mockRs.next()).thenReturn(true);

        when(mockRs.getInt("admin_id")).thenReturn(3);
        when(mockRs.getString("username")).thenReturn("admin3");
        when(mockRs.getString("password")).thenReturn("pass");
        when(mockRs.getString("email")).thenReturn("admin@test.com");

        Admin result = adminDAO.findByIdAndEmail(3, "admin@test.com");

        assertNotNull(result);
        assertEquals(3, result.getAdmin_id());
        assertEquals("admin3", result.getUsername());
        assertEquals("pass", result.getPassword());
        assertEquals("admin@test.com", result.getEmail());
    }

    @Test
    void testFindByIdAndEmailFail() throws Exception {
        when(mockRs.next()).thenReturn(false);

        Admin result = adminDAO.findByIdAndEmail(9, "none@test.com");

        assertNull(result);
    }

    @Test
    void testFindByIdAndEmailSQLException() throws Exception {
        when(mockConnection.prepareStatement(anyString()))
                .thenThrow(new SQLException());

        Admin result = adminDAO.findByIdAndEmail(1, "test@test.com");

        assertNull(result);
    }

    // -----------------------------------------------------------------------
    // TEST: addUser()
    // -----------------------------------------------------------------------

    @Test
    void testAddUser() throws Exception {
        User user = new User("Masa", "m@m.com", "0599", "Nablus", null);

        when(mockPs.executeUpdate()).thenReturn(1);

        boolean result = adminDAO.addUser(user);

        assertTrue(result);
        verify(mockPs).setString(1, "Masa");
        verify(mockPs).setString(2, "m@m.com");
        verify(mockPs).setString(3, "0599");
        verify(mockPs).setString(4, "Nablus");
    }

    @Test
    void testAddUserSQLException() throws Exception {
        User user = new User("Masa", "m@m.com", "0599", "Nablus", null);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPs);
        when(mockPs.executeUpdate()).thenThrow(new SQLException());

        boolean result = adminDAO.addUser(user);

        assertFalse(result);
    }

    // -----------------------------------------------------------------------
    // TEST: deleteUserById()
    // -----------------------------------------------------------------------

    @Test
    void testDeleteUserById() throws Exception {
        when(mockPs.executeUpdate()).thenReturn(1);

        boolean result = adminDAO.deleteUserById(10);

        assertTrue(result);
        verify(mockPs).setInt(1, 10);
    }

    @Test
    void testDeleteUserByIdSQLException() throws Exception {
        when(mockConnection.prepareStatement(anyString()))
                .thenThrow(new SQLException());

        boolean result = adminDAO.deleteUserById(10);

        assertFalse(result);
    }

    // -----------------------------------------------------------------------
    // TEST: findEligibleUsers()
    // -----------------------------------------------------------------------

    @Test
    void testFindEligibleUsers() throws Exception {
        when(mockRs.next()).thenReturn(true, false);

        when(mockRs.getInt("user_id")).thenReturn(7);
        when(mockRs.getString("full_name")).thenReturn("Reem");
        when(mockRs.getString("email")).thenReturn("reem@test.com");

        List<User> list = adminDAO.findEligibleUsers();

        assertEquals(1, list.size());
        assertEquals("Reem", list.get(0).getFull_name());
        assertEquals("reem@test.com", list.get(0).getEmail());
        assertEquals(7, list.get(0).getUser_id());
    }

    @Test
    void testFindEligibleUsersEmpty() throws Exception {
        when(mockRs.next()).thenReturn(false);

        List<User> list = adminDAO.findEligibleUsers();

        assertTrue(list.isEmpty());
    }

    @Test
    void testFindEligibleUsersSQLException() throws Exception {
        when(mockConnection.prepareStatement(anyString()))
                .thenThrow(new SQLException());

        List<User> list = adminDAO.findEligibleUsers();

        assertTrue(list.isEmpty());
    }
}
