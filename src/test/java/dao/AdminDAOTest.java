package dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modl.Admin;
import modl.User;

class AdminDAOTest {

    private AdminDAO adminDAO;
    private Connection mockConnection;
    private PreparedStatement mockPs;
    private ResultSet mockRs;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {}

    @AfterAll
    static void tearDownAfterClass() throws Exception {}

    @BeforeEach
    void setUp() throws Exception {

        mockConnection = mock(Connection.class);
        mockPs = mock(PreparedStatement.class);
        mockRs = mock(ResultSet.class);

        // override getConnection()
        adminDAO = new AdminDAO() {
            @Override
            protected Connection getConnection() {
                return mockConnection;
            }
        };

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
    }

    @AfterEach
    void tearDown() throws Exception {}

    // -----------------------------------------------------------------------
    // TEST: login()
    // -----------------------------------------------------------------------
    @Test
    void testLoginSuccess() throws Exception {

        when(mockRs.next()).thenReturn(true);

        boolean result = adminDAO.login("masa", "m@m.com");

        assertTrue(result);

        verify(mockPs).setString(1, "masa");
        verify(mockPs).setString(2, "m@m.com");
        verify(mockPs).executeQuery();
    }

    @Test
    void testLoginFail() throws Exception {

        when(mockRs.next()).thenReturn(false);

        boolean result = adminDAO.login("wrong", "x@x.com");

        assertFalse(result);
        verify(mockPs).executeQuery();
    }

    // -----------------------------------------------------------------------
    // TEST: addAdmin()
    // -----------------------------------------------------------------------
    @Test
    void testAddAdmin() throws Exception {

        Admin admin = new Admin(0, "admin1", "1234", "a@a.com");

        when(mockPs.executeUpdate()).thenReturn(1);

        adminDAO.addAdmin(admin);

        verify(mockPs).setString(1, "admin1");
        verify(mockPs).setString(2, "1234");
        verify(mockPs).setString(3, "a@a.com");
        verify(mockPs).executeUpdate();
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

        verify(mockPs).setInt(1, 3);
        verify(mockPs).setString(2, "admin@test.com");
    }

    @Test
    void testFindByIdAndEmailFail() throws Exception {

        when(mockRs.next()).thenReturn(false);

        Admin result = adminDAO.findByIdAndEmail(9, "none@test.com");

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

}