package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import dao.FineDAO;
import dao.LoansDAO;
import modl.Fine;
import modl.Loan;


class FineServiceTest {

    private FineService fineService;

    @BeforeEach
    void setUp() {
        fineService = new FineService();
    }
//
    @Test
    void testAddBookFineSuccess() {
        Fine fine = new Fine(LocalDate.now(), 1);

        try (MockedConstruction<FineDAO> mockedDAO = mockConstruction(FineDAO.class)) {
            String result = fineService.addbookFine(fine);
            FineDAO mockDAO = mockedDAO.constructed().get(0);
            verify(mockDAO, times(1)).insertFine(fine);
            assertNull(result);
        }
    }

    @Test
    void testAddBookFineInvalidLoanId() {
        Fine fine = new Fine();
        fine.setLoanId(0);
        String result = fineService.addbookFine(fine);
        assertEquals("Title cannot be empty!", result);
    }

    @Test
    void testFindeAllUserFinesInvalidUserId() {
        // null
        assertNull(fineService.findeAlluserfines(null));
        // empty
        assertNull(fineService.findeAlluserfines(""));
        // non-number
        assertNull(fineService.findeAlluserfines("abc"));
        // negative
        assertNull(fineService.findeAlluserfines("-5"));
    }

    @Test
    void testFindeAllUserFinesValid() {
        Loan loan1 = new Loan(1, 1);
        Loan loan2 = new Loan(2, 1);
        List<Loan> loans = new ArrayList<>();
        loans.add(loan1);
        loans.add(loan2);

        Fine fine1 = new Fine(LocalDate.now(), 1);
        Fine fine2 = null; // simulate no fine for loan2

        try (MockedConstruction<LoansDAO> mockedLoanDAO = mockConstruction(LoansDAO.class);
             MockedConstruction<FineDAO> mockedFineDAO = mockConstruction(FineDAO.class);
             MockedConstruction<LoanService> mockedLoanService = mockConstruction(LoanService.class,
                (mock, context) -> when(mock.findeAllUserLoans(1)).thenReturn(loans))) {

            FineDAO fineDAOMock = mockedFineDAO.constructed().get(0);
            when(fineDAOMock.findeuserFines(1)).thenReturn(fine1);
            when(fineDAOMock.findeuserFines(2)).thenReturn(fine2);

            List<Fine> result = fineService.findeAlluserfines("1");
            assertEquals(1, result.size());
            assertEquals(fine1, result.get(0));
        }
    }

    @Test
    void testPayFineInvalidInputs() {
        assertNull(fineService.payFine(null, "1"));
        assertNull(fineService.payFine("", "1"));
        assertNull(fineService.payFine("1", null));
        assertNull(fineService.payFine("1", ""));
        assertNull(fineService.payFine("abc", "1"));
        assertNull(fineService.payFine("1", "xyz"));
        assertNull(fineService.payFine("-1", "1"));
        assertNull(fineService.payFine("1", "-5"));
    }

    @Test
    void testPayFineAlreadyPaid() {
        Fine fine = new Fine();
        fine.setLoanId(1);
        fine.setStatus(true);

        try (MockedConstruction<LoansDAO> mockedLoanDAO = mockConstruction(LoansDAO.class);
             MockedConstruction<FineDAO> mockedFineDAO = mockConstruction(FineDAO.class,
                (mock, context) -> when(mock.findeFineByFineId(100)).thenReturn(fine))) {

            String result = fineService.payFine("1", "100");
            assertEquals("fine is payed and book is returned ", result);

            FineDAO fineDAOMock = mockedFineDAO.constructed().get(0);
            verify(fineDAOMock, times(1)).deletefine(100);
        }
    }

    @Test
    void testPayFineNotExpired() {
        Fine fine = new Fine();
        fine.setLoanId(1);
        fine.setStatus(false);

        try (MockedConstruction<LoansDAO> mockedLoanDAO = mockConstruction(LoansDAO.class);
             MockedConstruction<FineDAO> mockedFineDAO = mockConstruction(FineDAO.class,
                (mock, context) -> when(mock.findeFineByFineId(101)).thenReturn(fine))) {

            String result = fineService.payFine("1", "101");
            assertEquals("your loan hasnt expierd yet", result);
        }
    }


}
