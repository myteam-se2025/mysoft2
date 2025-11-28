package soft;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import modl.Cd;
import service.CdService;

public class AddCdTest{

    private AddCd addCd;
    private CdService mockService;

    @BeforeAll
    @DisplayName(" Runs once before ALL tests")
    static void beforeAllTests() {
        System.out.println("Starting TestAddCd Test Suite...");
    }

    @AfterAll
    @DisplayName(" Runs once after ALL tests")
    static void afterAllTests() {
        System.out.println("Finished TestAddCd Test Suite.");
    }

    @BeforeEach
    @DisplayName("Setup test environment")
    void setup() {
        addCd = new AddCd();
        mockService = mock(CdService.class);
        addCd.setCdServiceForTest(mockService);
    }

    @AfterEach
    @DisplayName("Clean up after test")
    void teardown() {
        addCd = null;
        mockService = null;
    }

    // ---------------------------------------------------------------------
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

    // ---------------------------------------------------------------------
    @Test
    @DisplayName("Test invalid copies input (not a number)")
    void testInvalidCopies() throws Exception {

        addCd.title2.setText("CD1");
        addCd.artist2.setText("Artist1");
        addCd.genre2.setText("Rock");
        addCd.copies2.setText("abc"); // ❌ ليست رقم

        addCd.handleAddCdForTest();

        verify(mockService, never()).addCd(any(Cd.class));
    }

    // ---------------------------------------------------------------------
    @Test
    @DisplayName("Test SQLException thrown by service")
    void testSQLException() throws Exception {

        when(mockService.addCd(any(Cd.class))).thenThrow(new SQLException("DB error"));

        addCd.title2.setText("CD1");
        addCd.artist2.setText("A1");
        addCd.genre2.setText("Rock");
        addCd.copies2.setText("5");

        addCd.handleAddCdForTest();

        verify(mockService, times(1)).addCd(any(Cd.class));
    }

    // ---------------------------------------------------------------------
    @Test
    @DisplayName("Test failure message returned — fields must NOT be cleared")
    void testServiceFailureMessage() throws Exception {

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

    // ---------------------------------------------------------------------
    @Test
    @DisplayName("Test empty fields — service must NOT be called")
    void testEmptyFields() throws Exception {

        addCd.title2.setText("");
        addCd.artist2.setText("");
        addCd.genre2.setText("");
        addCd.copies2.setText("2");

        addCd.handleAddCdForTest();

        verify(mockService, never()).addCd(any(Cd.class));
    }
}