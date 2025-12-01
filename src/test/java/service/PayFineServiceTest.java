package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import service.*;

class PayFineServiceTest {

    private FineService mockFineService;
    private PayFineService payFineService;

    @BeforeEach
    void setUp() {
        // Create a mock FineService
        mockFineService = mock(FineService.class);
        // Inject the mock into PayFineService
        payFineService = new PayFineService(mockFineService);
    }

    @Test
    void testPayFineCallsFineService() {
        String userId = "1";
        String fineId = "100";

        // Call the method under test
        String result = payFineService.payfine(userId, fineId);

        // Verify that FineService.payFine was called once with correct arguments
        verify(mockFineService, times(1)).payFine(userId, fineId);

        // Assert that the method returns the expected string
        assertEquals("Paid successfully", result);
    }
}
