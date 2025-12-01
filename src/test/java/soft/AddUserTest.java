
package soft;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.junit.jupiter.api.*;
import service.UserService;

class AddUserTest {

    private AddUser addUser;

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
        frame.phone.setText("1234567890");
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

    private void genericFieldTest(String name, String email, String phone, String address) throws SQLException {

        UserService mockService = mock(UserService.class);

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

        // ========== FIX HERE ==========
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            verify(mockService, never()).registerUser(any());
        } else {
            verify(mockService, times(1)).registerUser(any());
        }
    }
}