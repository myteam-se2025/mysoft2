package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import dao.AdminDAO;
import dao.UserDAO;
import modl.Admin;
import modl.User;

class LogInServiceTest {

    private LogInService logInService;

    @BeforeEach
    void setUp() {
        logInService = new LogInService();
    }

    @Test
    void testEmptyEmailOrPassword() {
        assertEquals("Please enter both email and password.", logInService.handleLogin("", "123"));
        assertEquals("Please enter both email and password.", logInService.handleLogin(null, "123"));
        assertEquals("Please enter both email and password.", logInService.handleLogin("test@example.com", ""));
        assertEquals("Please enter both email and password.", logInService.handleLogin("test@example.com", null));
    }

    @Test
    void testInvalidEmailFormat() {
        assertEquals(
            "Email must end with .com, .org, or .net (e.g. name@example.com)",
            logInService.handleLogin("user@test.xyz", "123")
        );
    }

    @Test
    void testPasswordNotNumber() {
        assertEquals("Password must be a number.", logInService.handleLogin("user@example.com", "abc"));
    }

    @Test
    void testPasswordNotPositive() {
        assertEquals("Password must be a positive number.", logInService.handleLogin("user@example.com", "-5"));
        assertEquals("Password must be a positive number.", logInService.handleLogin("user@example.com", "0"));
    }

    @Test
    void testAdminFound() throws Exception {
        try (MockedConstruction<AdminDAO> mockedAdmin = mockConstruction(AdminDAO.class, 
             (mock, context) -> when(mock.findByIdAndEmail(1, "admin@example.com")).thenReturn(new Admin()))) {

            try (MockedConstruction<UserDAO> mockedUser = mockConstruction(UserDAO.class)) {
                String result = logInService.handleLogin("admin@example.com", "1");
                assertEquals("ADMIN", result);
            }
        }
    }

    @Test
    void testUserFound() throws Exception {
        try (MockedConstruction<AdminDAO> mockedAdmin = mockConstruction(AdminDAO.class)) {
            try (MockedConstruction<UserDAO> mockedUser = mockConstruction(UserDAO.class, 
                 (mock, context) -> when(mock.findByIdAndEmail(2, "user@example.com")).thenReturn(new User()))) {

                String result = logInService.handleLogin("user@example.com", "2");
                assertEquals("USER", result);
            }
        }
    }

    @Test
    void testNoMatchFound() throws Exception {
        try (MockedConstruction<AdminDAO> mockedAdmin = mockConstruction(AdminDAO.class)) {
            try (MockedConstruction<UserDAO> mockedUser = mockConstruction(UserDAO.class)) {
                String result = logInService.handleLogin("nomatch@example.com", "3");
                assertEquals("No matching user or admin found.", result);
            }
        }
    }

    @Test
    void testDatabaseException() throws Exception {
        try (MockedConstruction<AdminDAO> mockedAdmin = mockConstruction(AdminDAO.class, 
             (mock, context) -> when(mock.findByIdAndEmail(anyInt(), anyString())).thenThrow(new RuntimeException()))) {
            try (MockedConstruction<UserDAO> mockedUser = mockConstruction(UserDAO.class)) {
                String result = logInService.handleLogin("test@example.com", "1");
                assertEquals("Database error occurred.", result);
            }
        }
    }
}
