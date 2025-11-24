
package service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dao.AdminDAO;
import modl.User;

class UnRegUserServiceTest {

    private AdminDAO mockDao;
    private UnRegUserService service;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        mockDao = Mockito.mock(AdminDAO.class);
        service = new UnRegUserService(mockDao); // حقن الـ mock هنا
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testGetEligibleUsers() {
        // إعداد بيانات mock
        User user1 = new User("Lana", "lana@test.com", "0599", "Nablus", null);
        User user2 = new User("Masa", "masa@test.com", "0599", "Nablus", null);
        List<User> mockList = Arrays.asList(user1, user2);

        when(mockDao.findEligibleUsers()).thenReturn(mockList);

        // استدعاء الدالة
        List<User> result = service.getEligibleUsers();

        // تحقق من النتائج
        assertEquals(2, result.size());
        assertEquals("Lana", result.get(0).getFull_name());
        assertEquals("Masa", result.get(1).getFull_name());

        // تحقق أن الدالة في DAO استدعيت مرة واحدة
        verify(mockDao, times(1)).findEligibleUsers();
    }

    @Test
    void testUnregisterUser() {
        int userId = 10;

        when(mockDao.deleteUserById(userId)).thenReturn(true);

        boolean result = service.unregisterUser(userId);

        assertTrue(result);

        // تحقق أن الدالة في DAO استدعيت مرة واحدة مع نفس الـ ID
        verify(mockDao, times(1)).deleteUserById(userId);
    }
}