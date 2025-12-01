package soft;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import modl.User;
import org.junit.jupiter.api.*;
import service.UnRegUserService;

public class AdminUnReguserTest {

    private AdminUnReguser frame;
    private UnRegUserService mockService;

    private static String lastMessage;
    private static String lastTitle;
    private static int lastMessageType;

    @BeforeEach
    void setup() {

        mockService = mock(UnRegUserService.class);

        lastMessage = null;
        lastTitle = null;
        lastMessageType = -1;

        frame = new AdminUnReguser() {

            @Override
            protected List<User> getEligibleUsers() {
                return mockService.getEligibleUsers();
            }

            @Override
            protected boolean unregisterUser(int userId) {
                return mockService.unregisterUser(userId);
            }

            @Override
            protected void showMessageDialog(Object message, String title, int messageType) {
                lastMessage = (message == null ? null : message.toString());
                lastTitle = title;
                lastMessageType = messageType;
            }

            @Override
            protected int showConfirmDialog(Object message, String title, int optionType, int messageType) {
                return JOptionPane.YES_OPTION; // always confirm
            }

            @Override
            protected int[] getSelectedIndices(JList<String> userList) {
                int size = userList.getModel().getSize();
                int[] arr = new int[size];
                for (int i = 0; i < size; i++) arr[i] = i;
                return arr; // select all by default
            }
        };
    }

    @AfterEach
    void teardown() {
        if (frame != null) frame.dispose();
    }

    // ----------------------------------------------------
    // TEST: Button Exists
    // ----------------------------------------------------
    @Test
    void testBtnUnregisterExists() {
        JButton btn = frame.getBtnUnregister();
        assertNotNull(btn);
        assertEquals("Unregister Users", btn.getText());
    }

    // ----------------------------------------------------
    // TEST: Empty Eligible Users List
    // ----------------------------------------------------
    @Test
    void testOpenUserSelectionDialogEmptyList() {
        when(mockService.getEligibleUsers()).thenReturn(new ArrayList<>());

        frame.openUserSelectionDialog();

        verify(mockService, times(1)).getEligibleUsers();

        assertEquals("No eligible users found.", lastMessage);
        assertEquals("Info", lastTitle);
        assertEquals(JOptionPane.INFORMATION_MESSAGE, lastMessageType);
    }

    // ----------------------------------------------------
    // TEST: Single User Success
    // ----------------------------------------------------
    @Test
    void testOpenUserSelectionDialogSingleUserSuccess() {

        User u = new User();
        u.setUser_id(1);
        u.setFull_name("Test User");
        u.setEmail("test@example.com");

        List<User> users = new ArrayList<>();
        users.add(u);

        when(mockService.getEligibleUsers()).thenReturn(users);
        when(mockService.unregisterUser(1)).thenReturn(true);

        frame.openUserSelectionDialog();

        verify(mockService, times(1)).getEligibleUsers();
        verify(mockService, times(1)).unregisterUser(1);

        assertEquals("Users unregistered successfully!", lastMessage);
        assertEquals("Success", lastTitle);
        assertEquals(JOptionPane.INFORMATION_MESSAGE, lastMessageType);
    }

    // ----------------------------------------------------
    // TEST: Multiple Users Partial Fail
    // ----------------------------------------------------
    @Test
    void testOpenUserSelectionDialogMultipleUsersPartialFail() {

        User u1 = new User();
        u1.setUser_id(1);
        u1.setFull_name("User One");
        u1.setEmail("one@example.com");

        User u2 = new User();
        u2.setUser_id(2);
        u2.setFull_name("User Two");
        u2.setEmail("two@example.com");

        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);

        when(mockService.getEligibleUsers()).thenReturn(users);
        when(mockService.unregisterUser(1)).thenReturn(true);
        when(mockService.unregisterUser(2)).thenReturn(false);

        frame.openUserSelectionDialog();

        verify(mockService, times(1)).getEligibleUsers();
        verify(mockService, times(1)).unregisterUser(1);
        verify(mockService, times(1)).unregisterUser(2);

        assertEquals("Some users could not be deleted.", lastMessage);
        assertEquals("Partial Error", lastTitle);
        assertEquals(JOptionPane.ERROR_MESSAGE, lastMessageType);
    }

    // ----------------------------------------------------
    // TEST: No Selection
    // ----------------------------------------------------
    @Test
    void testOpenUserSelectionDialogNoSelection() {

        User u = new User();
        u.setUser_id(1);
        u.setFull_name("User One");
        u.setEmail("one@example.com");

        List<User> users = new ArrayList<>();
        users.add(u);

        AdminUnReguser testFrame = new AdminUnReguser() {

            @Override
            protected List<User> getEligibleUsers() { return users; }

            @Override
            protected int[] getSelectedIndices(JList<String> userList) { return new int[0]; }

            @Override
            protected void showMessageDialog(Object message, String title, int messageType) {
                lastMessage = (message == null ? null : message.toString());
                lastTitle = title;
                lastMessageType = messageType;
            }

            @Override
            protected boolean unregisterUser(int userId) { return true; }
        };

        testFrame.openUserSelectionDialog();

        assertEquals("No user selected.", lastMessage);
        assertEquals("Warning", lastTitle);
        assertEquals(JOptionPane.WARNING_MESSAGE, lastMessageType);
    }

    // ----------------------------------------------------
    // TEST: Cancel Dialog
    // ----------------------------------------------------
    @Test
    void testOpenUserSelectionDialogCancelOption() {

        User u = new User();
        u.setUser_id(1);
        u.setFull_name("User One");
        u.setEmail("one@example.com");

        List<User> users = new ArrayList<>();
        users.add(u);

        AdminUnReguser testFrame = new AdminUnReguser() {

            @Override
            protected List<User> getEligibleUsers() { return users; }

            @Override
            protected int showConfirmDialog(Object message, String title, int optionType, int messageType) {
                return JOptionPane.CANCEL_OPTION;
            }

            @Override
            protected void showMessageDialog(Object message, String title, int messageType) {
                lastMessage = (message == null ? null : message.toString());
                lastTitle = title;
                lastMessageType = messageType;
            }

            @Override
            protected boolean unregisterUser(int userId) { return true; }
        };

        testFrame.openUserSelectionDialog();

        assertNull(lastMessage); // dialog cancelled → no message
    }
}
