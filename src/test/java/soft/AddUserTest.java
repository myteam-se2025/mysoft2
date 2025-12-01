package soft;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import service.UserService;

class AddUserTest {

    private AddUser addUser;

    @BeforeAll
    static void setUpBeforeClass() {
        System.out.println("Starting AddUser tests...");
    }

    @AfterAll
    static void tearDownAfterClass() {
        System.out.println("Finished AddUser tests.");
    }

    @BeforeEach
    void setUp() {
        addUser = new AddUser();
    }

    @AfterEach
    void tearDown() {
        addUser = null;
    }

    @Test
    void testAddUserSuccessfully() throws SQLException {
        UserService mockService = mock(UserService.class);
        doNothing().when(mockService).registerUser(any());

        AddUser frame = new AddUser() {
            @Override
            protected UserService createUserService() throws SQLException {
                return mockService;
            }
        };

        
        frame.name2.setText("John Doe");
        frame.email.setText("john@example.com");
        frame.phone.setText("1234567890");
        frame.addres.setText("Nablus");

        frame.handleAddUserForTest();

        verify(mockService, times(1)).registerUser(any());
        assertFieldsCleared(frame);
    }

    @Test
    void testSQLExceptionHandled() throws SQLException {
        UserService mockService = mock(UserService.class);
        doThrow(new SQLException("DB error")).when(mockService).registerUser(any());

        AddUser frame = new AddUser() {
            @Override
            protected UserService createUserService() throws SQLException {
                return mockService;
            }
        };

        frame.name2.setText("Alice");
        frame.email.setText("alice@example.com");
        frame.phone.setText("0987654321");
        frame.addres.setText("Ramallah");

        frame.handleAddUserForTest();

        verify(mockService, times(1)).registerUser(any());
    }

    @Test
    void testEmptyName() throws SQLException {
        genericFieldTest("", "bob@example.com", "1234567890", "Nablus");
    }

    @Test
    void testEmptyEmail() throws SQLException {
        genericFieldTest("Bob", "", "1234567890", "Nablus");
        
    }

    @Test
    void testInvalidEmail() throws SQLException {
        genericFieldTest("Bob", "invalid-email", "1234567890", "Nablus");
    }

    @Test
    void testEmptyPhone() throws SQLException {
        genericFieldTest("Bob", "bob@example.com", "", "Nablus");
    }

    @Test
    void testEmptyAddress() throws SQLException {
        genericFieldTest("Bob", "bob@example.com", "1234567890", "");
    }

    @Test
    void testAllFieldsEmpty() throws SQLException {
        genericFieldTest("", "", "", "");
    }

    private void assertFieldsCleared(AddUser frame) {
        assertEquals("", frame.name2.getText());
        assertEquals("", frame.email.getText());
        assertEquals("", frame.phone.getText());
        assertEquals("", frame.addres.getText());
    }

    private void genericFieldTest(String name, String email, String phone, String address) throws SQLException {
        UserService mockService = mock(UserService.class);
        doNothing().when(mockService).registerUser(any());

        AddUser frame = new AddUser() {
            @Override
            protected UserService createUserService() throws SQLException {
                return mockService;
            }
        };

        frame.name2.setText(name);
        frame.email.setText(email);
        frame.phone.setText(phone);
        frame.addres.setText(address);

        frame.handleAddUserForTest();

        verify(mockService, times(1)).registerUser(any());
        assertFieldsCleared(frame);
    }
}