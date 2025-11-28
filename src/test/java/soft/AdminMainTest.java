package soft;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.EventQueue;
import java.awt.Frame;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.jupiter.api.*;

public class AdminMainTest {

    private AdminMain adminMain;

    @BeforeEach
    void setup() {
        adminMain = new AdminMain();
        adminMain.setVisible(true);
    }

    @AfterEach
    void tearDown() {
        if (adminMain != null) {
            adminMain.dispose();
        }
    }

    // -------------------------------------------------------
    // ⭐ TESTING BUTTONS EXISTENCE
    // -------------------------------------------------------
    @Test
    void testAddCdButtonExists() {
        JButton btn = adminMain.getAddCdButton();
        assertNotNull(btn);
        assertEquals("Add CD", btn.getText());
    }

    @Test
    void testAddBookButtonExists() {
        JButton btn = adminMain.getAddBookButton();
        assertNotNull(btn);
        assertEquals("Add Book", btn.getText());
    }

    @Test
    void testUnRegisterButtonExists() {
        JButton btn = adminMain.getUnRegisterButton();
        assertNotNull(btn);
        assertEquals("UnRegistor", btn.getText());
    }

    @Test
    void testRegisterButtonExists() {
        JButton btn = adminMain.getRegisterButton();
        assertNotNull(btn);
        assertEquals("Registor", btn.getText());
    }

    // -------------------------------------------------------
    // ⭐ TESTING BUTTON ACTIONS — Open correct window
    // -------------------------------------------------------
    @Test
    void testAddBookButtonOpensAdminAddBook() throws Exception {
        AtomicReference<JFrame> openedFrame = new AtomicReference<>();

        EventQueue.invokeAndWait(() -> {
            adminMain.getAddBookButton().doClick();
            for (Frame f : JFrame.getFrames()) {
                if (f.isVisible() && f instanceof AdminAddBook) {
                    openedFrame.set((JFrame) f);
                }
            }
        });

        assertNotNull(openedFrame.get());
        assertTrue(openedFrame.get() instanceof AdminAddBook);
        assertFalse(adminMain.isVisible());
        openedFrame.get().dispose();
    }

    @Test
    void testAddCdButtonOpensAddCd() throws Exception {
        AtomicReference<JFrame> openedFrame = new AtomicReference<>();

        EventQueue.invokeAndWait(() -> {
            adminMain.getAddCdButton().doClick();
            for (Frame f : JFrame.getFrames()) {
                if (f.isVisible() && f instanceof AddCd) {
                    openedFrame.set((JFrame) f);
                }
            }
        });

        assertNotNull(openedFrame.get());
        assertTrue(openedFrame.get() instanceof AddCd);
        assertFalse(adminMain.isVisible());
        openedFrame.get().dispose();
    }

    @Test
    void testRegisterButtonOpensAddUser() throws Exception {
        AtomicReference<JFrame> openedFrame = new AtomicReference<>();

        EventQueue.invokeAndWait(() -> {
            adminMain.getRegisterButton().doClick();
            for (Frame f : JFrame.getFrames()) {
                if (f.isVisible() && f instanceof AddUser) {
                    openedFrame.set((JFrame) f);
                }
            }
        });

        assertNotNull(openedFrame.get());
        assertTrue(openedFrame.get() instanceof AddUser);
        assertFalse(adminMain.isVisible());
        openedFrame.get().dispose();
    }

  
}