package soft;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import modl.Fine;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.FineService;
import service.PayFineService;

public class UserFinesTest {

    UserFines frame;
    static String lastMessage;

    @BeforeAll
    static void beforeAllTests() {
        // أي إعدادات عامة قبل كل الاختبارات
        System.out.println("Starting UserFines tests...");
        lastMessage = null;
    }

    @BeforeEach
    void setup() {
        lastMessage = null;

        frame = new UserFines() {
            @Override
            protected FineService createFineService() {
                return mock(FineService.class);
            }

            @Override
            protected PayFineService createPayFineService() {
                return mock(PayFineService.class);
            }

            @Override
            protected void showMessageDialog(Object message) {
                lastMessage = message.toString();
            }
        };

        frame.setVisible(true);
    }

    @AfterEach
    void cleanup() {
        frame.dispose();
        lastMessage = null;
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("All UserFines tests completed.");
        lastMessage = null;
    }

    @Test
    void testPayFineSuccess() {
        PayFineService mockService = frame.createPayFineService();
        when(mockService.payfine("1", "101")).thenReturn("Fine paid successfully!");

        frame.userID.setText("1");
        frame.fineID.setText("101");

        frame.action_1.actionPerformed(null);

        assertEquals("Fine paid successfully!", lastMessage);
    }

    @Test
    void testShowAllFinesEmpty() {
        FineService mockService = frame.createFineService();
        when(mockService.findeAlluserfines("1")).thenReturn(new ArrayList<>());

        frame.userID.setText("1");
        frame.action_2.actionPerformed(null);

        assertEquals("No books found!", lastMessage);
    }

    @Test
    void testShowAllFinesWithData() {
        FineService mockService = frame.createFineService();
        List<Fine> fines = new ArrayList<>();
        Fine f = new Fine();
        f.setFineId(101);
        f.setLoanId(201);
        f.setAmount(10);
        f.setDateIssued(java.time.LocalDate.of(2025, 11, 26));
        f.setStatus(false);
        fines.add(f);

        when(mockService.findeAlluserfines("1")).thenReturn(fines);

        frame.userID.setText("1");
        frame.action_2.actionPerformed(null);

        assertNull(lastMessage);
    }
}