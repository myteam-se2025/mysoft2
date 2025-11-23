package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dao.UserDAO;
import modl.User;

class UserServiceTest {

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
	void test() {
		 
        UserDAO mockDao = Mockito.mock(UserDAO.class);

       
        UserService userService = new UserService(mockDao);

        
        User user = new User( "John", "john@example.com" , "1221934" , "nablus",new Date(2004,12,1));

       
        userService.registerUser(user);

          verify(mockDao, times(1)).addUser(user);
	}

}
