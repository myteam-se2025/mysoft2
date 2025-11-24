package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dao.CdDAO;
import modl.Cd;

class CdServiceTest {

    private CdDAO mockDao;
    private CdService cdService;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        mockDao = Mockito.mock(CdDAO.class);
        cdService = new CdService(mockDao);   // حقن الـ mock
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    // ============================
    // ✔ Test: Title validation
    // ============================
    @Test
    void testAddCd_TitleEmpty() {
        Cd cd = new Cd("", "Artist", "Pop", 5);

        String result = cdService.addCd(cd);

        assertEquals("Title cannot be empty!", result);
        verify(mockDao, times(0)).insertCd(any());
    }

    // ============================
    // ✔ Test: Artist validation
    // ============================
    @Test
    void testAddCd_ArtistEmpty() {
        Cd cd = new Cd("CD1", "", "Pop", 5);

        String result = cdService.addCd(cd);

        assertEquals("Artist name cannot be empty!", result);
        verify(mockDao, times(0)).insertCd(any());
    }

    // ============================
    // ✔ Test: Genre validation
    // ============================
    @Test
    void testAddCd_GenreEmpty() {
        Cd cd = new Cd("CD1", "Artist", "", 5);

        String result = cdService.addCd(cd);

        assertEquals("Genre cannot be empty!", result);
        verify(mockDao, times(0)).insertCd(any());
    }

    // ============================
    // ✔ Test: Negative copies
    // ============================
    @Test
    void testAddCd_NegativeCopies() {
        Cd cd = new Cd("CD1", "Artist", "Pop", -3);

        String result = cdService.addCd(cd);

        assertEquals("Number of copies cannot be negative!", result);
        verify(mockDao, times(0)).insertCd(any());
    }

    // ============================
    // ✔ Test: Successful insert
    // ============================
    @Test
    void testAddCd_Success() {
        Cd cd = new Cd("CD1", "Artist", "Pop", 5);

        when(mockDao.insertCd(cd)).thenReturn(true);

        String result = cdService.addCd(cd);

        assertEquals("CD added successfully!", result);
        verify(mockDao, times(1)).insertCd(cd);
    }

    // ============================
    // ✔ Test: Insert fails
    // ============================
    @Test
    void testAddCd_Failure() {
        Cd cd = new Cd("CD1", "Artist", "Pop", 5);

        when(mockDao.insertCd(cd)).thenReturn(false);

        String result = cdService.addCd(cd);

        assertEquals("Error while saving CD to database.", result);
        verify(mockDao, times(1)).insertCd(cd);
    }
}