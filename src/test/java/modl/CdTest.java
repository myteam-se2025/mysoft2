package modl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CdTest {

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
	    void testConstructorWithParameters() {
	        Cd cd = new Cd("Best Hits", "Artist X", "Pop", 4);

	        assertEquals("Best Hits", cd.getTitle());
	        assertEquals("Artist X", cd.getArtist());
	        assertEquals("Pop", cd.getGenre());
	        assertEquals(4, cd.getAvailable_copies());
	        assertEquals(0, cd.getCd_id()); // default ID
	    }

	    @Test
	    void testDefaultValues() {
	        Cd cd = new Cd("null", "null", "null", 0); // Because you don't have an empty constructor

	        assertEquals("null", cd.getTitle());
	        assertEquals("null", cd.getArtist());
	        assertEquals("null", cd.getGenre());
	        assertEquals(0, cd.getAvailable_copies());
	        assertEquals(0, cd.getCd_id());
	    }

	    @Test
	    void testSetters() {
	        Cd cd = new Cd("A", "B", "C", 1);

	        cd.setCd_id(7);
	        cd.setTitle("New Title");
	        cd.setArtist("New Artist");
	        cd.setGenre("Rock");
	        cd.setAvailable_copies(10);

	        assertEquals(7, cd.getCd_id());
	        assertEquals("New Title", cd.getTitle());
	        assertEquals("New Artist", cd.getArtist());
	        assertEquals("Rock", cd.getGenre());
	        assertEquals(10, cd.getAvailable_copies());
	    }
}
