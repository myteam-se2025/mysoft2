package soft;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.sql.SQLException;

import modl.Cd;
import service.CdService;

public class AddCdTest {

    private AddCd addCd;
    private CdService mockService;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Starting AddCd Test Suite...");
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("Finished AddCd Test Suite.");
    }

    @BeforeEach
    void setup() {
        addCd = new AddCd();
        addCd.testingMode = true;
        mockService = mock(CdService.class);
        addCd.setCdServiceForTest(mockService);
    }

    @AfterEach
    void teardown() {
        addCd = null;
        mockService = null;
    }

    @Test
    @DisplayName("Test adding CD successfully")
    void testAddCdSuccessfully() throws Exception {

        when(mockService.addCd(any(Cd.class))).thenReturn("CD added successfully!");

        addCd.title2.setText("Best Hits");
        addCd.artist2.setText("Nancy");
        addCd.genre2.setText("Pop");
        addCd.copies2.setText("3");

        addCd.handleAddCdForTest();

        verify(mockService, times(1)).addCd(any(Cd.class));

        assertEquals("", addCd.title2.getText());
        assertEquals("", addCd.artist2.getText());
        assertEquals("", addCd.genre2.getText());
        assertEquals("", addCd.copies2.getText());
    }

    @Test
    @DisplayName("Test invalid copies input (not a number)")
    void testInvalidCopies() {

        addCd.title2.setText("CD1");
        addCd.artist2.setText("Artist1");
        addCd.genre2.setText("Rock");
        addCd.copies2.setText("abc");

        addCd.handleAddCdForTest();

        verify(mockService, never()).addCd(any(Cd.class));
    }

    @Test
    @DisplayName("Test SQLException thrown by service")
    void testSQLException() {

        when(mockService.addCd(any(Cd.class)))
                .thenThrow(new RuntimeException("DB error"));

        addCd.title2.setText("CD1");
        addCd.artist2.setText("A1");
        addCd.genre2.setText("Rock");
        addCd.copies2.setText("5");

        assertDoesNotThrow(() -> addCd.handleAddCdForTest());

        verify(mockService, times(1)).addCd(any(Cd.class));
    }

    @Test
    @DisplayName("Test failure message — fields must NOT clear")
    void testServiceFailureMessage() throws SQLException {

        when(mockService.addCd(any(Cd.class))).thenReturn("Error adding CD!");

        addCd.title2.setText("T1");
        addCd.artist2.setText("A1");
        addCd.genre2.setText("G1");
        addCd.copies2.setText("4");

        addCd.handleAddCdForTest();

        verify(mockService, times(1)).addCd(any(Cd.class));

        assertEquals("T1", addCd.title2.getText());
        assertEquals("A1", addCd.artist2.getText());
        assertEquals("G1", addCd.genre2.getText());
        assertEquals("4", addCd.copies2.getText());
    }

    @Test
    @DisplayName("Test empty fields — service must NOT be called")
    void testEmptyFields() {

        addCd.title2.setText("");
        addCd.artist2.setText("Artist");
        addCd.genre2.setText("");
        addCd.copies2.setText("2");

        addCd.handleAddCdForTest();

        verify(mockService, never()).addCd(any(Cd.class));
    }
}