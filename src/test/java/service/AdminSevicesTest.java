package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dao.AdminDAO;

class AdminServiceTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testLoginSuccess() throws Exception {

        // Mock للـ DAO
        AdminDAO mockDao = Mockito.mock(AdminDAO.class);

        // Service باستخدام mock dao
        AdminService adminService = new AdminService(mockDao);

        // لما نعمل login يجب يرجع true
        when(mockDao.login("admin", "123")).thenReturn(true);

        // الفعل
        boolean result = adminService.login("admin", "123");

        // التحقق
        assertTrue(result);
        verify(mockDao, times(1)).login("admin", "123");
    }

    @Test
    void testLoginFail() throws Exception {

        AdminDAO mockDao = Mockito.mock(AdminDAO.class);

        AdminService adminService = new AdminService(mockDao);

        // login فاشل → false
        when(mockDao.login("wrong", "000")).thenReturn(false);

        boolean result = adminService.login("wrong", "000");

        assertFalse(result);
        verify(mockDao, times(1)).login("wrong", "000");
    }
}