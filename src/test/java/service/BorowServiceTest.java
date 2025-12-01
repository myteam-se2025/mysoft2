package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import modl.*;
class BorowServiceTest {

    private BorowService borowService;

    @BeforeEach
    void setUp() {
        borowService = new BorowService();
    }

    @Test
    void testBorowabookCallsLoanAndFineService() {
        try (MockedConstruction<LoanService> mockedLoanService = mockConstruction(LoanService.class,
                (mock, context) -> when(mock.addbookloan(any(Loan.class))).thenReturn(100));
             MockedConstruction<FineService> mockedFineService = mockConstruction(FineService.class)) {

            borowService.borowabook("1", "1");

            LoanService loanMock = mockedLoanService.constructed().get(0);
            FineService fineMock = mockedFineService.constructed().get(0);

            verify(loanMock, times(1)).addbookloan(any(Loan.class));
            verify(fineMock, times(1)).addbookFine(any(Fine.class));
        }
    }

    @Test
    void testProcessBorrowRequestBookNotAvailable() throws SQLException {
        try (MockedConstruction<BookService> mockedBookService = mockConstruction(BookService.class,
                (mock, context) -> when(mock.bookAvalbltyChack("1")).thenReturn(new Loan(1,1)))) {

            String result = borowService.processBorrowRequest("1", "1");
            assertEquals("This book is not available.", result);
        }
    }

    @Test
    void testProcessBorrowRequestUserHasUnpaidFines() throws SQLException {
        List<Fine> fines = new ArrayList<>();
        Fine fine = new Fine(LocalDate.now(), 1);
        fine.setStatus(true);
        fines.add(fine);

        try (MockedConstruction<BookService> mockedBookService = mockConstruction(BookService.class,
                 (mock, context) -> when(mock.bookAvalbltyChack("1")).thenReturn(null));
             MockedConstruction<FineService> mockedFineService = mockConstruction(FineService.class,
                 (mock, context) -> when(mock.findeAlluserfines("1")).thenReturn(fines))) {

            String result = borowService.processBorrowRequest("1", "1");
            assertEquals("you have one or more unpayed fines pay them to get the book ", result);
        }
    }

    @Test
    void testProcessBorrowRequestSuccess() throws SQLException {
        List<Fine> fines = new ArrayList<>(); 

        try (MockedConstruction<BookService> mockedBookService = mockConstruction(BookService.class,
                 (mock, context) -> when(mock.bookAvalbltyChack("1")).thenReturn(null));
             MockedConstruction<FineService> mockedFineService = mockConstruction(FineService.class,
                 (mock, context) -> when(mock.findeAlluserfines("1")).thenReturn(fines));
             MockedConstruction<LoanService> mockedLoanService = mockConstruction(LoanService.class,
                 (mock, context) -> when(mock.addbookloan(any(Loan.class))).thenReturn(101));
             MockedConstruction<FineService> mockedFineService2 = mockConstruction(FineService.class)) {

            String result = borowService.processBorrowRequest("1", "1");
            assertEquals("Book borrowed successfully!", result);
        }
    }
}
