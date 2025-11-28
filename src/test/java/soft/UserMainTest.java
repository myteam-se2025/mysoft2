package soft;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.swing.JButton;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserMainTest {

    static UserMain frame;
    static UserSearch mockSearch;
    static UserBorow mockBorrow;
    static UserFines mockFines;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Starting UserMain tests...");

        // إنشاء الموكات للنوافذ لتجنب فتحها فعلياً
        mockSearch = mock(UserSearch.class);
        mockBorrow = mock(UserBorow.class);
        mockFines = mock(UserFines.class);
    }

    @BeforeEach
    void setup() {
        frame = new UserMain() {
            @Override
            public void setVisible(boolean b) {
                // منع ظهور الواجهة فعلياً
            }

            @Override
            public void dispose() {
                // منع غلق الواجهة فعلياً
            }

            // استخدام الموكات بدل فتح النوافذ الحقيقية
            @Override
            protected UserSearch createUserSearch() {
                return mockSearch;
            }

            @Override
            protected UserBorow createUserBorow() {
                return mockBorrow;
            }

            @Override
            protected UserFines createUserFines() {
                return mockFines;
            }
        };

        frame.setVisible(true);
    }

    @AfterEach
    void tearDown() {
        frame.dispose();
        frame = null;
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("All UserMain tests completed.");
        mockSearch = null;
        mockBorrow = null;
        mockFines = null;
    }

  

    @Test
    void testButtonsExist() {
        assertNotNull(frame.btnSearch, "Search button must exist");
        assertNotNull(frame.btnBorrow, "Borrow button must exist");
        assertNotNull(frame.btnFines, "Fines button must exist");

        assertEquals("Search", frame.btnSearch.getAction().getValue(javax.swing.Action.NAME));
        assertEquals("borrow ", frame.btnBorrow.getAction().getValue(javax.swing.Action.NAME));
        assertEquals("fines", frame.btnFines.getAction().getValue(javax.swing.Action.NAME));
    }

    @Test
    void testSearchButtonOpensUserSearch() {
        frame.btnSearch.doClick();
        verify(mockSearch, times(1)).setVisible(true);
    }

    @Test
    void testBorrowButtonOpensUserBorrow() {
        frame.btnBorrow.doClick();
        verify(mockBorrow, times(1)).setVisible(true);
    }

    @Test
    void testFinesButtonOpensUserFines() {
        frame.btnFines.doClick();
        verify(mockFines, times(1)).setVisible(true);
    }
}