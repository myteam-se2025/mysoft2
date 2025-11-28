package soft;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import org.junit.jupiter.api.*;
import service.LogInService;

public class LogInTest {

    LogIn frame;
    static LogInService mockService;

    // لتخزين آخر رسالة عرضها JOptionPane
    static String lastMessage;
    static String lastTitle;
    static int lastMessageType;

    @BeforeAll
    static void setupAll() {
        mockService = mock(LogInService.class);
    }

    @BeforeEach
    void setup() {
        lastMessage = null;
        lastTitle = null;
        lastMessageType = -1;

        frame = new LogIn() {
            @Override
            protected LogInService createService() {
                return mockService; // استخدام الموك
            }

            @Override
            protected void showMessageDialog(Object message, String title, int messageType) {
                lastMessage = message.toString();
                lastTitle = title;
                lastMessageType = messageType;
            }
        };
        frame.setVisible(true);
    }

    @AfterEach
    void tearDown() {
        frame.dispose();
        frame = null;
    }

    @Test
    void testLoginButtonExists() {
        JButton btn = frame.getLoginButton();
        assertNotNull(btn, "زر LogIn يجب أن يكون موجود");
        assertEquals("LogIn", btn.getText());
    }

    @Test
    void testAdminLogin() {
        frame.getEmailField().setText("admin@example.com");
        frame.getPassField().setText("adminpass");

        when(mockService.handleLogin("admin@example.com", "adminpass")).thenReturn("ADMIN");

        frame.getLoginButton().doClick();

        verify(mockService, times(1)).handleLogin("admin@example.com", "adminpass");
     
        assertNull(lastMessage);
    }

    @Test
    void testUserLogin() {
        frame.getEmailField().setText("user@example.com");
        frame.getPassField().setText("userpass");

        when(mockService.handleLogin("user@example.com", "userpass")).thenReturn("USER");

        frame.getLoginButton().doClick();

        verify(mockService, times(1)).handleLogin("user@example.com", "userpass");
        assertNull(lastMessage); 
    }

    @Test
    void testInvalidLoginShowsMessage() {
        frame.getEmailField().setText("wrong@example.com");
        frame.getPassField().setText("wrongpass");

        when(mockService.handleLogin("wrong@example.com", "wrongpass")).thenReturn("Invalid credentials");

        frame.getLoginButton().doClick();

        verify(mockService, times(1)).handleLogin("wrong@example.com", "wrongpass");
        assertEquals("Invalid credentials", lastMessage);
        assertEquals("Login", lastTitle);
        assertEquals(JOptionPane.WARNING_MESSAGE, lastMessageType);
    }
}