package dao;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import modl.Cd;

public class CdDAOTest {

    @Mock
    private Connection con;

    @Mock
    private PreparedStatement ps;

    @Mock
    private ResultSet rs;

    @InjectMocks
    private CdDAO dao;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);

        dao = Mockito.spy(new CdDAO());
        doReturn(con).when(dao).getConnection();
    }

    // -------------------------------------------------
    // TEST: insertCd()
    // -------------------------------------------------
    @Test
    public void testInsertCd() throws Exception {

        Cd cd = new Cd("Hits", "Adele", "Pop", 4);

        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);

        boolean added = dao.insertCd(cd);

        assertTrue(added);
        verify(ps).setString(1, "Hits");
        verify(ps).setString(2, "Adele");
        verify(ps).setString(3, "Pop");
        verify(ps).setInt(4, 4);
    }

    // -------------------------------------------------
    // TEST: searchCdByTitleAndId()
    // -------------------------------------------------
    @Test
    public void testSearchCdByTitleAndId_found() throws Exception {

        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true);

        when(rs.getString("title")).thenReturn("Hits");
        when(rs.getString("artist")).thenReturn("Adele");
        when(rs.getString("genre")).thenReturn("Pop");
        when(rs.getInt("available_copies")).thenReturn(4);
        when(rs.getInt("cd_id")).thenReturn(5);

        Cd cd = dao.searchCdByTitleAndId("Hits", 5);

        assertNotNull(cd);
        assertEquals("Hits", cd.getTitle());
        verify(ps).setString(1, "Hits");
        verify(ps).setInt(2, 5);
    }
}