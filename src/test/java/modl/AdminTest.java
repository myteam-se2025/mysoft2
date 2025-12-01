package modl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdminTest {

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
	    void testFullConstructor() {
	        Admin admin = new Admin(1, "user1", "pass1", "email@example.com");

	        assertEquals(1, admin.getAdmin_id());
	        assertEquals("user1", admin.getUsername());
	        assertEquals("pass1", admin.getPassword());
	        assertEquals("email@example.com", admin.getEmail());
	    }

	    @Test
	    void testConstructorWithoutId() {
	        Admin admin = new Admin("user2", "pass2", "email2@example.com");

	        // admin_id should be default 0 because it was never set
	        assertEquals(0, admin.getAdmin_id());
	        assertEquals("user2", admin.getUsername());
	        assertEquals("pass2", admin.getPassword());
	        assertEquals("email2@example.com", admin.getEmail());
	    }

	    @Test
	    void testSetters() {
	        Admin admin = new Admin(0, "", "", "");

	        admin.setAdmin_id(5);
	        admin.setUsername("newUser");
	        admin.setPassword("newPassword");
	        admin.setEmail("new@example.com");

	        assertEquals(5, admin.getAdmin_id());
	        assertEquals("newUser", admin.getUsername());
	        assertEquals("newPassword", admin.getPassword());
	        assertEquals("new@example.com", admin.getEmail());
	    }

}
