package service;

import dao.FineDAO;
import dao.LoansDAO;
import modl.Fine;
import modl.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FineServiceTest {

    @Mock
    FineDAO fineDAO;

    @Mock
    LoanService loanService;

    @Mock
    LoansDAO loansDAO;

    FineService fineService;

    @BeforeEach
    void setup() {
    	new FineService();   
        
       
        fineService = new FineService(fineDAO, loanService, loansDAO);  }

    // ---------------- addbookFine ------------------

   
    
    @Test
    void testAddBookFine_Valid() {
        Fine fine = new Fine(LocalDate.now(), 1);
        fine.setLoanId(1);

        String result = fineService.addbookFine(fine);

        assertNull(result);
        verify(fineDAO).insertFine(fine);
    }

    @Test
    void testAddBookFine_LoanIdZero() {
        Fine fine = new Fine();
        fine.setLoanId(0);

        String result = fineService.addbookFine(fine);

        assertEquals("Loan ID cannot be empty!", result);
        verify(fineDAO, never()).insertFine(any());
    }

    
    @Test
    @DisplayName("payFine → empty userId → return null")
    void testPayFine_EmptyUserId() {
        String result = fineService.payFine("", "1");
        assertNull(result);
        verify(fineDAO, never()).findeFineByFineId(anyInt());
    }

    @Test
    @DisplayName("payFine → empty fineId → return null")
    void testPayFine_EmptyFineId() {
        String result = fineService.payFine("1", "");
        assertNull(result);
        verify(fineDAO, never()).findeFineByFineId(anyInt());
    }

    @Test
    @DisplayName("payFine → userId = 0 → return null")
    void testPayFine_ZeroUserId() {
        String result = fineService.payFine("0", "1");
        assertNull(result);
        verify(fineDAO, never()).findeFineByFineId(anyInt());
    }

    @Test
    @DisplayName("payFine → fineId = 0 or negative → return null")
    void testPayFine_InvalidFineId_ZeroOrNegative() {
        assertNull(fineService.payFine("1", "0"));
        assertNull(fineService.payFine("1", "-5"));
        verify(fineDAO, never()).findeFineByFineId(anyInt());
    }
   
    
    @Test
    @DisplayName("payFine → non-numeric IDs → catch NumberFormatException → return null")
    void testPayFine_NonNumericIds() {
        String result = fineService.payFine("abc", "xyz");
        assertNull(result);
        verify(fineDAO, never()).findeFineByFineId(anyInt());
    }
    
    @Test
    @DisplayName("addbookFine → loanId null → return error")
    void testAddBookFine_LoanIdNull() {
        Fine fine = new Fine();                 // loanId is null by default
        // or explicitly: fine.setLoanId(null);

        String result = fineService.addbookFine(fine);

        assertEquals("Loan ID cannot be empty!", result);
        verify(fineDAO, never()).insertFine(any());
    }
    @Test
    @DisplayName("findeAlluserfines → userId empty → return null")
    void testFindUserFines_UserIdEmpty() {
        assertNull(fineService.findeAlluserfines(""));
        verify(loanService, never()).findeAllUserLoans(anyInt());
    }

    @Test
    void testPayFine_InvalidUserId() {
        assertNull(fineService.payFine("abc", "1"));
        verify(fineDAO, never()).findeFineByFineId(anyInt());
    }
    // ---------------- findeAlluserfines ------------------

    @Test
    void testFindAllFines_Valid() {
        List<Loan> loans = Arrays.asList(
                new Loan(1,1,1,0,LocalDate.now(),LocalDate.now()),
                new Loan(2,1,2,0,LocalDate.now(),LocalDate.now())
        );
        Fine f1 = new Fine(1,1,10,false, LocalDate.now());
        Fine f2 = new Fine(2,2,10,true, LocalDate.now());

        when(loanService.findeAllUserLoans(1)).thenReturn(loans);
        when(fineDAO.findeuserFines(1)).thenReturn(f1);
        when(fineDAO.findeuserFines(2)).thenReturn(f2);

        List<Fine> result = fineService.findeAlluserfines("1");

        assertEquals(2, result.size());
    }

    @Test
    void testFindAllUserFines_NullUserId() {
        assertNull(fineService.findeAlluserfines(null));
        verify(loanService, never()).findeAllUserLoans(anyInt());
    }

    @Test
    void testFindAllUserFines_NonNumeric() {
        assertNull(fineService.findeAlluserfines("abc"));
        verify(loanService, never()).findeAllUserLoans(anyInt());
    }

    @Test
    void testFindAllUserFines_NoLoans() {
        when(loanService.findeAllUserLoans(1)).thenReturn(Collections.emptyList());

        List<Fine> result = fineService.findeAlluserfines("1");

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindAllUserFines_SomeNullFines() {
        List<Loan> loans = Arrays.asList(
                new Loan(1,1,1,0,LocalDate.now(),LocalDate.now()),
                new Loan(2,1,2,0,LocalDate.now(),LocalDate.now())
        );

        when(loanService.findeAllUserLoans(1)).thenReturn(loans);
        when(fineDAO.findeuserFines(1)).thenReturn(new Fine());
        when(fineDAO.findeuserFines(2)).thenReturn(null);

        List<Fine> result = fineService.findeAlluserfines("1");

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("findeAlluserfines → userId negative → return null")
    void testFindUserFines_Negative() {
        assertNull(fineService.findeAlluserfines("-5"));
        verify(loanService, never()).findeAllUserLoans(anyInt());
    }
    
    @Test
    @DisplayName("findeAlluserfines → userId zero → return null")
    void testFindUserFines_Zero() {
        assertNull(fineService.findeAlluserfines("0"));
        verify(loanService, never()).findeAllUserLoans(anyInt());
    }

    
    // ---------------- payFine ------------------

    @Test
    void testPayFine_Overdue() {
        Fine f = new Fine(1,100,10,true,LocalDate.now());

        when(fineDAO.findeFineByFineId(1)).thenReturn(f);

        String msg = fineService.payFine("1", "1");

        assertEquals("fine is payed and book is returned ", msg);
        verify(fineDAO).deletefine(1);
        verify(loansDAO).deleteloan(100);
    }

    @Test
    void testPayFine_NotOverdue() {
        Fine f = new Fine(1,100,10,false,LocalDate.now());
        when(fineDAO.findeFineByFineId(1)).thenReturn(f);

        String msg = fineService.payFine("1", "1");

        assertEquals("your loan hasnt expierd yet", msg);
        verify(fineDAO, never()).deletefine(anyInt());
    }

    @Test
    @DisplayName("findeAlluserfines → userId null → return null")
    void testFindUserFines_UserIdNull() {
        assertNull(fineService.findeAlluserfines(null));
        verify(loanService, never()).findeAllUserLoans(anyInt());
    }
    
    @Test
    @DisplayName("payFine → userid null → hits the first null/empty check")
    void testPayFine_UserIdNull_CoversFirstIf() {
        String result = fineService.payFine(null, "123");

        assertNull(result);
        verifyNoInteractions(fineDAO);        // nothing after the first if should run
        verifyNoInteractions(loansDAO);
    }
    @Test
    void testPayFine_FineIdNull_CoversSecondIf() {
        fineService.payFine("123", null);
    }
    @Test
    void testPayFine_InvalidFineId() {
        assertNull(fineService.payFine("1", "abc"));
        verify(fineDAO, never()).findeFineByFineId(anyInt());
    }
}
