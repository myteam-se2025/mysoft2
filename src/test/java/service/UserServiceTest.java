package service;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.junit.jupiter.api.*;
import modl.User;
import dao.UserDAO;

class UserServiceTest {

    // This is our fake DAO
    private UserDAO mockUserDAO;

    // This is the class we are testing
    private UserService userService;

    @BeforeEach
    void setUp() {
       
        mockUserDAO = mock(UserDAO.class);
       
        userService = new UserService(mockUserDAO);
    }

    @Test
    void registerUser_ShouldCallAddUserOnDAO() throws SQLException {
        
        User user = new User("Mohammed", "moh@gmail.com", "0599123456", "Ramallah", null);

        
        userService.registerUser(user);
        
        verify(mockUserDAO, times(1)).addUser(user);       
    }

    @Test
    void registerUser_ShouldForwardSQLException_IfDAOThrows() throws Exception {
   
    	User user = new User("Mohammed", "moh@gmail.com", "0599123456", "Ramallah", null);
         
    	doThrow(new SQLException("DB error")).when(mockUserDAO).addUser(user);

    	SQLException m = assertThrows(SQLException.class, () -> userService.registerUser(user));

    	assertEquals("DB error", m.getMessage());
    	verify(mockUserDAO).addUser(user);
    }
}































