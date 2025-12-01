package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;

import modl.Fine;
import modl.Loan;
import service.*;

@ExtendWith(MockitoExtension.class)
class BorowServiceTest {

    @Mock
    private BookService bookService;

    @Mock
    private FineService fineService;

    private BorowService borrowService;
    private LoanService  loanService;

    @BeforeEach
    void setUp() {
        borrowService = new BorowService(bookService, fineService);
    }

    @Test
    @DisplayName("processBorrowRequest → null user or book → returns null")
    void testProcessBorrowRequestNullUserOrBook() {
        when(bookService.bookAvalbltyChack(null)).thenReturn(null);
        when(fineService.findeAlluserfines(null)).thenReturn(new ArrayList<>());

        String result = borrowService.processBorrowRequest(null, null);

        assertNull(result);
    }

    @Test
    @DisplayName("processBorrowRequest → book unavailable → returns message")
    void testProcessBorrowRequestBookUnavailable() {
        Loan loan = new Loan("1", "1");
        when(bookService.bookAvalbltyChack("1")).thenReturn(loan);
        when(fineService.findeAlluserfines("1")).thenReturn(new ArrayList<>());

        String result = borrowService.processBorrowRequest("1", "1");

        assertEquals("This book is not available.", result);
    }

    @Test
    @DisplayName("processBorrowRequest → user has fines → returns fines message")
    void testProcessBorrowRequestUserHasFines() {
        when(bookService.bookAvalbltyChack("1")).thenReturn(null);
        Fine fine = new Fine();
        fine.setStatus(true);
        List<Fine> fines = List.of(fine);
        when(fineService.findeAlluserfines("1")).thenReturn(fines);

        String result = borrowService.processBorrowRequest("1", "1");

        assertEquals("you have one or more unpayed fines pay them to get the book ", result);
    }
    @Test
    void coverNoArgConstructor() {
        new BorowService();   }

    @Test
    @DisplayName("processBorrowRequest → book available & no fines → success")
    void testProcessBorrowRequestSuccess() {
        when(bookService.bookAvalbltyChack("1")).thenReturn(null);
        when(fineService.findeAlluserfines("1")).thenReturn(new ArrayList<>());

        String result = borrowService.processBorrowRequest("1", "1");

        assertEquals("Book borrowed successfully!", result);
    }

    @Test
    void testProcessBorrowRequest_SQLException_CoversCatchBlockAndReturnNull() {

           FineService realFineService = new FineService();
        LoanService realLoanService = new LoanService();

         try (MockedConstruction<BookService> ignored = mockConstruction(BookService.class,
                (mock, context) -> when(mock.bookAvalbltyChack(anyString())).thenThrow(new SQLException("DB connection failed")))) {

            // Now create the SUT with real dependencies
            BorowService borrowService = new BorowService(realFineService, realLoanService);

            // This single line makes both lines 60 and 64 green
            String result = borrowService.processBorrowRequest("1", "1");

            assertNull(result);
        }
    }
}
