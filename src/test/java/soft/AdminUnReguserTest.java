package soft;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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
                lastMessage = message.toString();
                lastTitle = title;
                lastMessageType = messageType;
            }

            @Override
            protected int showConfirmDialog(Object message, String title, int optionType, int messageType) {
                return JOptionPane.YES_OPTION; // بشكل افتراضي نوافق
            }

            @Override
            protected int[] getSelectedIndices(javax.swing.JList<String> userList) {
                int size = userList.getModel().getSize();
                int[] indices = new int[size];
                for (int i = 0; i < size; i++) indices[i] = i;
                return indices; // اختيار كل العناصر تلقائياً
            }
        };
    }

    @AfterEach
    void teardown() {
        if (frame != null) frame.dispose();
    }

   
    //        TEST BUTTON EXISTENCE
   
    @Test
    void testBtnUnregisterExists() {
        JButton btn = frame.getBtnUnregister();
        assertNotNull(btn);
        assertEquals("Unregister Users", btn.getText());
    }

   
    //      TEST EMPTY USER LIST
    @Test
    void testOpenUserSelectionDialogEmptyList() {
        when(mockService.getEligibleUsers()).thenReturn(new ArrayList<>());

        frame.openUserSelectionDialog();

        verify(mockService, times(1)).getEligibleUsers();
        assertEquals("No eligible users found.", lastMessage);
        assertEquals("Info", lastTitle);
        assertEquals(JOptionPane.INFORMATION_MESSAGE, lastMessageType);
    }

    // ----------------------------
    //      TEST SINGLE USER SUCCESS
    // ----------------------------
    @Test
    void testOpenUserSelectionDialogSingleUserSuccess() {
        User u = new User();
        u.setUser_id(1);
        u.setFull_name("Test User");
        u.setEmail("test@example.com");

        List<User> users = new ArrayList<>();
        users.add(u);

        when(mockService.getEligibleUsers()).thenReturn(users);
        when(mockService.unregisterUser(u.getUser_id())).thenReturn(true);

        frame.openUserSelectionDialog();

        verify(mockService, times(1)).getEligibleUsers();
        verify(mockService, times(1)).unregisterUser(u.getUser_id());

        assertEquals("Users unregistered successfully!", lastMessage);
        assertEquals("Success", lastTitle);
        assertEquals(JOptionPane.INFORMATION_MESSAGE, lastMessageType);
    }

    // ----------------------------
    //      TEST MULTIPLE USERS PARTIAL FAIL
    // ----------------------------
    @Test
    void testOpenUserSelectionDialogMultipleUsersPartialFail() {
        User u1 = new User();
        u1.setUser_id(1); u1.setFull_name("User One"); u1.setEmail("one@example.com");

        User u2 = new User();
        u2.setUser_id(2); u2.setFull_name("User Two"); u2.setEmail("two@example.com");

        List<User> users = new ArrayList<>();
        users.add(u1); users.add(u2);

        when(mockService.getEligibleUsers()).thenReturn(users);
        when(mockService.unregisterUser(u1.getUser_id())).thenReturn(true);
        when(mockService.unregisterUser(u2.getUser_id())).thenReturn(false);

        frame.openUserSelectionDialog();

        verify(mockService, times(1)).getEligibleUsers();
        verify(mockService, times(1)).unregisterUser(u1.getUser_id());
        verify(mockService, times(1)).unregisterUser(u2.getUser_id());

        assertEquals("Some users could not be deleted.", lastMessage);
        assertEquals("Partial Error", lastTitle);
        assertEquals(JOptionPane.ERROR_MESSAGE, lastMessageType);
    }

   
    @Test
    void testOpenUserSelectionDialogNoSelection() {
        User u = new User();
        u.setUser_id(1); u.setFull_name("User One"); u.setEmail("one@example.com");

        List<User> users = new ArrayList<>();
        users.add(u);

        AdminUnReguser testFrame = new AdminUnReguser() {
            @Override
            protected List<User> getEligibleUsers() { return users; }
            @Override
            protected int[] getSelectedIndices(javax.swing.JList<String> userList) { return new int[0]; }
            @Override
            protected void showMessageDialog(Object message, String title, int messageType) {
                lastMessage = message.toString();
                lastTitle = title;
                lastMessageType = messageType;
            }
            @Override
            protected boolean unregisterUser(int userId) { return true; }
        };

        testFrame.openUserSelectionDialog();

        verify(mockService, times(1)).getEligibleUsers();
        assertEquals("No user selected.", lastMessage);
        assertEquals("Warning", lastTitle);
        assertEquals(JOptionPane.WARNING_MESSAGE, lastMessageType);
    }

   
    @Test
    void testOpenUserSelectionDialogCancelOption() {
        User u = new User();
        u.setUser_id(1); u.setFull_name("User One"); u.setEmail("one@example.com");

        List<User> users = new ArrayList<>();
        users.add(u);

        AdminUnReguser testFrame = new AdminUnReguser() {
            @Override
            protected List<User> getEligibleUsers() { return users; }
            @Override
            protected int showConfirmDialog(Object message, String title, int optionType, int messageType) {
                return JOptionPane.CANCEL_OPTION; // محاكاة الضغط على Cancel
            }
            @Override
            protected void showMessageDialog(Object message, String title, int messageType) {
                lastMessage = message.toString();
                lastTitle = title;
                lastMessageType = messageType;
            }
            @Override
            protected boolean unregisterUser(int userId) { return true; }
        };

        testFrame.openUserSelectionDialog();

        verify(mockService, times(1)).getEligibleUsers();
        verify(mockService, times(0)).unregisterUser(anyInt());
        assertNull(lastMessage); // لا توجد رسالة لأنه تم الإلغاء
    }
}