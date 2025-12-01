package modl;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

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
        Date membershipDate = Date.valueOf("2025-12-01");
        User user = new User(1, "Khadeja", "khadeja@example.com", "123456789", "Palestine", membershipDate);

        assertEquals(1, user.getUser_id());
        assertEquals("Khadeja", user.getFull_name());
        assertEquals("khadeja@example.com", user.getEmail());
        assertEquals("123456789", user.getPhone());
        assertEquals("Palestine", user.getAddress());
        assertEquals(membershipDate, user.getMembership_date());
    }

    @Test
    void testEmptyConstructorDefaults() {
        User user = new User();

        assertEquals(0, user.getUser_id());
        assertEquals("null", user.getFull_name());
        assertEquals("null", user.getEmail());
        assertEquals("null", user.getPhone());
        assertEquals("null", user.getAddress());
        assertNull(user.getMembership_date());
    }

    @Test
    void testConstructorWithoutUserId() {
        Date membershipDate = Date.valueOf("2025-12-01");
        User user = new User("Masa", "masa@example.com", "987654321", "Gaza", membershipDate);

        assertEquals(0, user.getUser_id()); // default ID
        assertEquals("Masa", user.getFull_name());
        assertEquals("masa@example.com", user.getEmail());
        assertEquals("987654321", user.getPhone());
        assertEquals("Gaza", user.getAddress());
        assertEquals(membershipDate, user.getMembership_date());
    }

    @Test
    void testSettersAndGetters() {
        User user = new User();

        Date membershipDate = Date.valueOf("2025-12-02");

        user.setUser_id(10);
        user.setFull_name("Ali");
        user.setEmail("ali@example.com");
        user.setPhone("111222333");
        user.setAddress("Ramallah");
        user.setMembership_date(membershipDate);

        assertEquals(10, user.getUser_id());
        assertEquals("Ali", user.getFull_name());
        assertEquals("ali@example.com", user.getEmail());
        assertEquals("111222333", user.getPhone());
        assertEquals("Ramallah", user.getAddress());
        assertEquals(membershipDate, user.getMembership_date());
    }

}
