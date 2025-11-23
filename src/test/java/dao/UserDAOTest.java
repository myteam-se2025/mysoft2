package dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.SQLException;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import modl.User;

class UserDAOTest {

	private UserDAO userDAO;
	private Connection mockConnection ;
	private PreparedStatement mockPreparedStatement;
	   
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	
	mockConnection = mock(Connection.class);
	  // 2. Override getConnection to return mock connection
     userDAO = new UserDAO() {
         @Override
         protected Connection getConnection() {
             return mockConnection;
         }
     };
     
     when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
     
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void addUsertest_seccess() {
		
		 User user = new User("John", "john@example.com", "1221934", "Nablus", new Date(2020, 11, 1));

	        // Mock executeUpdate to succeed
	        try {
				when(mockPreparedStatement.executeUpdate()).thenReturn(1);
			
				  boolean result = userDAO.addUser(user);

			        // Assertions
			        assertTrue(result);

			        // Verify interactions
			        verify(mockPreparedStatement).setString(1, user.getFull_name());
			        verify(mockPreparedStatement).executeUpdate();
				
	        
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        
	}

}
