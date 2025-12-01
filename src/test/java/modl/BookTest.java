package modl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {

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
	        Book book = new Book("Java Basics", "John Doe", "123456", "Programming", 5);

	        assertEquals("Java Basics", book.getTitle());
	        assertEquals("John Doe", book.getAuthor());
	        assertEquals("123456", book.getIsbn());
	        assertEquals("Programming", book.getCategory());
	        assertEquals(5, book.getAvailable_copies());
	        assertEquals(0, book.getBook_id()); // default ID
	    }

	    @Test
	    void testEmptyConstructorDefaults() {
	        Book book = new Book();

	        assertEquals("null", book.getTitle());
	        assertEquals("null", book.getAuthor());
	        assertEquals("null", book.getIsbn());
	        assertEquals("null", book.getCategory());
	        assertEquals(0, book.getAvailable_copies());
	        assertEquals(0, book.getBook_id());
	    }

	    @Test
	    void testSetters() {
	        Book book = new Book();

	        book.setBook_id(10);
	        book.setTitle("Algorithms");
	        book.setAuthor("CLRS");
	        book.setIsbn("987654");
	        book.setCategory("Computer Science");
	        book.setAvailable_copies(3);

	        assertEquals(10, book.getBook_id());
	        assertEquals("Algorithms", book.getTitle());
	        assertEquals("CLRS", book.getAuthor());
	        assertEquals("987654", book.getIsbn());
	        assertEquals("Computer Science", book.getCategory());
	        assertEquals(3, book.getAvailable_copies());
	    }

}
