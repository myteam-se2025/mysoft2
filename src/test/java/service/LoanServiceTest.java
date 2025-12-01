package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import dao.LoansDAO;
import modl.Loan;


class LoanServiceTest {

    private LoanService loanService;

    @BeforeEach
    void setUp() {
        loanService = new LoanService();
    }

    @Test
    void testAddBookLoanSuccess() {
        Loan loan = new Loan(1, 1); // valid BookId and UserId

        try (MockedConstruction<LoansDAO> mockedDAO = mockConstruction(LoansDAO.class,
            (mock, context) -> when(mock.insertintloan(loan)).thenReturn(42))) {

            int result = loanService.addbookloan(loan);

            assertEquals(42, result);
            LoansDAO mockDAO = mockedDAO.constructed().get(0);
            verify(mockDAO, times(1)).insertintloan(loan);
        }
    }

    @Test
    void testAddBookLoanWithEmptyBookId() {
        Loan loan = new Loan();
        loan.setBookId(0);
        loan.setUserId(1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> loanService.addbookloan(loan));

        assertEquals("Book ID cannot be empty!", exception.getMessage());
    }

    @Test
    void testAddBookLoanWithEmptyUserId() {
        Loan loan = new Loan();
        loan.setBookId(1);
        loan.setUserId(0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> loanService.addbookloan(loan));

        assertEquals("User ID cannot be empty!", exception.getMessage());
    }

    @Test
    void testFindeAllUserLoans() {
        int userId = 1;
        List<Loan> mockLoans = new ArrayList<>();
        mockLoans.add(new Loan(1, 1));
        mockLoans.add(new Loan(2, 1));

        try (MockedConstruction<LoansDAO> mockedDAO = mockConstruction(LoansDAO.class,
            (mock, context) -> when(mock.findeLoanByUserId(userId)).thenReturn(mockLoans))) {

            List<Loan> result = loanService.findeAllUserLoans(userId);

            assertEquals(2, result.size());
            assertEquals(mockLoans, result);
            LoansDAO mockDAO = mockedDAO.constructed().get(0);
            verify(mockDAO, times(1)).findeLoanByUserId(userId);
        }
    }

}
