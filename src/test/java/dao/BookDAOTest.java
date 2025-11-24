package dao;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import modl.Book;

public class BookDAOTest {

    @Mock
    private Connection con;

    @Mock
    private PreparedStatement ps;

    @Mock
    private ResultSet rs;

    @InjectMocks
    private BookDAO dao;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);

        // override getConnection()
        dao = Mockito.spy(new BookDAO());
        doReturn(con).when(dao).getConnection();
    }

    // -------------------------------------------------
    // TEST: insertBook()
    // -------------------------------------------------
    @Test
    public void testInsertBook() throws Exception {

        Book book = new Book("Java", "Masa", "111", "Programming", 5);

        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        boolean inserted = dao.insertBook(book);

        assertTrue(inserted);
        verify(ps).setString(1, "Java");
        verify(ps).setString(2, "Masa");
        verify(ps).setString(3, "111");
        verify(ps).setString(4, "Programming");
        verify(ps).setInt(5, 5);
    }

    // -------------------------------------------------
    // TEST: findByTitleAndId()
    // -------------------------------------------------
    @Test
    public void testFindByTitleAndId_found() throws Exception {

        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true);

        when(rs.getString("title")).thenReturn("Java");
        when(rs.getString("author")).thenReturn("Masa");
        when(rs.getString("isbn")).thenReturn("111");
        when(rs.getString("category")).thenReturn("Programming");
        when(rs.getInt("available_copies")).thenReturn(5);
        when(rs.getInt("book_id")).thenReturn(10);

        Book b = dao.findByTitleAndId("Java", 10);

        assertNotNull(b);
        assertEquals("Java", b.getTitle());
        verify(ps).setString(1, "Java");
        verify(ps).setInt(2, 10);
    }

    // -------------------------------------------------
    // TEST: findAllBooks()
    // -------------------------------------------------
    @Test
    public void testFindAllBooks() throws Exception {

        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, false);

        when(rs.getString("title")).thenReturn("Java");
        when(rs.getString("author")).thenReturn("Masa");
        when(rs.getString("isbn")).thenReturn("111");
        when(rs.getString("category")).thenReturn("Programming");
        when(rs.getInt("available_copies")).thenReturn(5);
        when(rs.getInt("book_id")).thenReturn(1);

        List<Book> list = dao.findAllBooks();

        assertEquals(1, list.size());
        assertEquals("Java", list.get(0).getTitle());
    }

    // -------------------------------------------------
    // TEST: findbyauthor()
    // -------------------------------------------------
    @Test
    public void testFindByAuthor() throws Exception {

        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, false);
        when(rs.getString("title")).thenReturn("Java");
        when(rs.getString("author")).thenReturn("Masa");
        when(rs.getString("isbn")).thenReturn("111");
        when(rs.getString("category")).thenReturn("Programming");
        when(rs.getInt("available_copies")).thenReturn(3);
        when(rs.getInt("book_id")).thenReturn(2);

        List<Book> list = dao.findbyauthor("Masa");

        assertEquals(1, list.size());
        verify(ps).setString(1, "Masa");
    }

    // -------------------------------------------------
    // TEST: findbyid()
    // -------------------------------------------------
    @Test
    public void testFindById() throws Exception {

        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, false);

        when(rs.getString("title")).thenReturn("Java");
        when(rs.getString("author")).thenReturn("Khadija");
        when(rs.getString("isbn")).thenReturn("555");
        when(rs.getString("category")).thenReturn("Programming");
        when(rs.getInt("available_copies")).thenReturn(7);
        when(rs.getInt("book_id")).thenReturn(100);

        List<Book> list = dao.findbyid(100);

        assertEquals(1, list.size());
        verify(ps).setInt(1, 100);
    }

    // -------------------------------------------------
    // TEST: findbytitle()
    // -------------------------------------------------
    @Test
    public void testFindByTitle() throws Exception {

        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, false);

        when(rs.getString("title")).thenReturn("Algorithms");
        when(rs.getString("author")).thenReturn("Masa");
        when(rs.getString("isbn")).thenReturn("222");
        when(rs.getString("category")).thenReturn("CS");
        when(rs.getInt("available_copies")).thenReturn(6);
        when(rs.getInt("book_id")).thenReturn(33);

        List<Book> list = dao.findbytitle("Algorithms");

        assertEquals(1, list.size());
        verify(ps).setString(1, "Algorithms");
    }
}