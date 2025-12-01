package dao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseDAOTest {

    private BaseDAO baseDAO; // We will test through a fake subclass

    private Connection mockCon;
    private PreparedStatement mockPs;
    private ResultSet mockRs;

    @BeforeEach
    void setUp() {
        // Mockito cannot create abstract classes → we create a tiny fake subclass
        baseDAO = new BaseDAO() {};

        // Mock JDBC objects
        mockCon = mock(Connection.class);
        mockPs = mock(PreparedStatement.class);
        mockRs = mock(ResultSet.class);
    }

    @Test
    void closeResources_ShouldCloseAll_WhenNotNull() throws Exception {
        // Arrange
        // Make sure connection is not already closed
        when(mockCon.isClosed()).thenReturn(false);

        // Act
        baseDAO.closeResources(mockCon, mockPs, mockRs);

        // Assert: check each close() call happened
        verify(mockRs).close();
        verify(mockPs).close();
        verify(mockCon).close();
    }

    @Test
    void closeResources_ShouldSkipNullResources() throws Exception {
        // Arrange
        when(mockCon.isClosed()).thenReturn(false);

        // Act
        baseDAO.closeResources(mockCon, null, null);

        // Assert
        verify(mockCon).close();
        // MockPs and mockRs are null → nothing to verify
    }

    @Test
    void closeResources_ShouldNotCloseAlreadyClosedConnection() throws Exception {
        // Arrange
        when(mockCon.isClosed()).thenReturn(true);

        // Act
        baseDAO.closeResources(mockCon, mockPs, mockRs);

        // Assert: con.close() should NOT be called
        verify(mockCon, never()).close();

        // But ps/rs should close normally
        verify(mockPs).close();
        verify(mockRs).close();
    }
}
