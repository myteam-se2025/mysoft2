package soft;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import service.BorowService;

public class UserBorowTest {

    UserBorow frame;
    static BorowService mockService; // static للاستخدام في @BeforeAll و @AfterAll

    // لتخزين آخر رسالة عرضها showMessageDialog
    static String lastMessage;

    @BeforeAll
    static void setupAll() {
        // إنشاء الموك مرة واحدة لجميع الاختبارات
        mockService = mock(BorowService.class);
    }

    @AfterAll
    static void tearDownAll() {
        // تنظيف أي موارد مشتركة بعد كل الاختبارات
        mockService = null;
    }

    @BeforeEach
    void setup() {
        lastMessage = null;

        frame = new UserBorow() {
            @Override
            protected BorowService getBorowService() {
                return mockService; // استخدم الموك بدلاً من الخدمة الحقيقية
            }

            @Override
            protected void showMessageDialog(Object message) {
                lastMessage = message.toString(); // خزّن الرسالة بدل عرضها
            }
        };

        frame.setVisible(false); // لا نريد ظهور الواجهة أثناء الاختبار
    }

    @AfterEach
    void tearDown() {
        frame.dispose();
        frame = null;
    }

    @Test
    void testBorrowSuccess() {
        frame.userid.setText("1");
        frame.bookid.setText("101");

        when(mockService.processBorrowRequest("1", "101")).thenReturn("Borrow Successful");

        frame.handleBorrow();

        verify(mockService, times(1)).processBorrowRequest("1", "101");
        assertEquals("Borrow Successful", lastMessage);
    }

    @Test
    void testBorrowFailure() {
        frame.userid.setText("2");
        frame.bookid.setText("202");

        when(mockService.processBorrowRequest("2", "202")).thenReturn("Borrow Failed: Book unavailable");

        frame.handleBorrow();

        verify(mockService, times(1)).processBorrowRequest("2", "202");
        assertEquals("Borrow Failed: Book unavailable", lastMessage);
    }

    @Test
    void testEmptyFields() {
        frame.userid.setText("");
        frame.bookid.setText("");

        when(mockService.processBorrowRequest("", "")).thenReturn("Borrow Failed: Invalid input");

        frame.handleBorrow();

        verify(mockService, times(1)).processBorrowRequest("", "");
        assertEquals("Borrow Failed: Invalid input", lastMessage);
    }
}