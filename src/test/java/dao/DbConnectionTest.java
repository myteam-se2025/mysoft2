package dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class DbConnectionTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    void testGetConnectionSuccessFirstCall() throws SQLException {
        try (MockedStatic<DriverManager> mockedDriver = mockStatic(DriverManager.class)) {
            Connection mockConnection = mock(Connection.class);
            mockedDriver.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);
            when(mockConnection.isClosed()).thenReturn(false);

            Connection result = DbConnection.getConnection();

            assertNotNull(result);
            assertEquals(mockConnection, result);
            assertTrue(outContent.toString().contains("Database connected successfully."));
        }
    }

    @Test
    void testGetConnectionReuseOpenConnection() throws SQLException {
        try (MockedStatic<DriverManager> mockedDriver = mockStatic(DriverManager.class)) {
            Connection mockConnection = mock(Connection.class);
            mockedDriver.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);
            when(mockConnection.isClosed()).thenReturn(false);

            Connection first = DbConnection.getConnection();
            Connection second = DbConnection.getConnection();

            assertEquals(first, second);
            assertTrue(outContent.toString().contains("Database connected successfully."));
        }
    }

    @Test
    void testGetConnectionReopenClosedConnection() throws SQLException {
        try (MockedStatic<DriverManager> mockedDriver = mockStatic(DriverManager.class)) {
            Connection mockConnection1 = mock(Connection.class);
            Connection mockConnection2 = mock(Connection.class);
            mockedDriver.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                    .thenReturn(mockConnection1)
                    .thenReturn(mockConnection2);
            when(mockConnection1.isClosed()).thenReturn(false).thenReturn(true);
            when(mockConnection2.isClosed()).thenReturn(false);

            Connection first = DbConnection.getConnection();
            Connection second = DbConnection.getConnection();

            assertNotEquals(first, second);
            assertEquals(mockConnection2, second);
            assertTrue(outContent.toString().contains("Database connected successfully."));
        }
    }

    @Test
    void testGetConnectionFailure() {
        try (MockedStatic<DriverManager> mockedDriver = mockStatic(DriverManager.class)) {
            mockedDriver.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString())).thenThrow(new SQLException("Connection failed"));

            assertThrows(SQLException.class, DbConnection::getConnection);
            assertTrue(outContent.toString().contains("Failed to connect to the database."));
        }
    }

    @Test
    void testGetConnectionMultipleCallsWithFailure() throws SQLException {
        try (MockedStatic<DriverManager> mockedDriver = mockStatic(DriverManager.class)) {
            Connection mockConnection = mock(Connection.class);
            mockedDriver.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                    .thenReturn(mockConnection)
                    .thenThrow(new SQLException("Connection failed"));
            when(mockConnection.isClosed()).thenReturn(false).thenReturn(true);

            Connection first = DbConnection.getConnection();
            assertNotNull(first);

            assertThrows(SQLException.class, DbConnection::getConnection);
            assertTrue(outContent.toString().contains("Database connected successfully."));
            assertTrue(outContent.toString().contains("Failed to connect to the database."));
        }
    }
}