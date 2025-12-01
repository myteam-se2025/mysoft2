package service;

import java.util.ArrayList;

import java.util.List;
import java.util.ArrayList;

import modl.Fine;
import modl.Loan;

public class BorowService {

    private BookService bookService;
    private FineService fineService;
    private final LoanService loanService;

    public BorowService(FineService fineService, LoanService loanService) {
        this.fineService = fineService;
        this.loanService = loanService;
    }
    
    public BorowService(BookService bookService, FineService fineService) {
        this.loanService = new LoanService();
		this.bookService = bookService;
        this.fineService = fineService;
    }
   
    public BorowService() {
        this.fineService = new FineService();
        this.loanService = new LoanService();
    }
    public void borowabook(String user_id, String book_id) {
       
        Loan loan = new Loan(user_id, book_id);
        int generatedLoanId = loanService.addbookloan(loan);

        Fine fine = new Fine(loan.getDueDate(), generatedLoanId);
        fineService.addbookFine(fine);
    }

    public String processBorrowRequest(String user_id, String book_id) {
        int countstatustrue = 0;
        List<Fine> fines = new ArrayList<>();

        try {
            Loan loan = bookService.bookAvalbltyChack(book_id);
            fines = fineService.findeAlluserfines(user_id);

            if (loan != null) {
                return "This book is not available.";
            } else if (!fines.isEmpty()) {
                for (Fine fine : fines) {
                    if (fine.getstatus()) countstatustrue++;
                }
                if (countstatustrue > 0) {
                    return "you have one or more unpayed fines pay them to get the book ";
                }
            } else {
                borowabook(user_id, book_id);
                return "Book borrowed successfully!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}

