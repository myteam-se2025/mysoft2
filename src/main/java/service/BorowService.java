package service;

import java.util.ArrayList;

import java.util.List;
import java.util.ArrayList;

import modl.Fine;
import modl.Loan;

/**
 * Service class to handle borrowing books.
 * Responsible for checking book availability, managing fines, and processing borrow requests.
 * 
 * @author Khadeja and Masa
 * @version 1.0
 */
public class BorowService {

    private BookService bookService;
    private FineService fineService;
    private final LoanService loanService;
    
    /**
     * Constructor with FineService and LoanService injection.
     * 
     * @param fineService the FineService instance
     * @param loanService the LoanService instance
     */
    public BorowService(FineService fineService, LoanService loanService) {
        this.fineService = fineService;
        this.loanService = loanService;
    }
    
    /**
     * Constructor with BookService and FineService injection.
     * Initializes LoanService internally.
     * 
     * @param bookService the BookService instance
     * @param fineService the FineService instance
     */
    public BorowService(BookService bookService, FineService fineService) {
        this.loanService = new LoanService();
		this.bookService = bookService;
        this.fineService = fineService;
    }
   
    /**
     * Default constructor initializing FineService and LoanService.
     */
    public BorowService() {
        this.fineService = new FineService();
        this.loanService = new LoanService();
    }
    /**
     * Borrow a book for a user. Creates a loan and a fine record.
     * 
     * @param user_id the user ID as a String
     * @param book_id the book ID as a String
     */
    public void borowabook(String user_id, String book_id) {
       
        Loan loan = new Loan(user_id, book_id);
        int generatedLoanId = loanService.addbookloan(loan);

        Fine fine = new Fine(loan.getDueDate(), generatedLoanId);
        fineService.addbookFine(fine);
    }

    /**
     * Process a borrow request for a user and book.
     * Checks availability and unpaid fines before borrowing.
     * 
     * @param user_id the user ID as a String
     * @param book_id the book ID as a String
     * @return a message indicating the result of the borrow request, or null if an exception occurs
     */
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

