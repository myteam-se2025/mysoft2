//import io.github.cdimascio.dotenv.Dotenv;

public class LibraryFineProcessor {

    private final EmailService emailService;
//    private final FineCalculator calculator;

    // Constructor - injected dependencies (perfect for testing!)
    //public LibraryFineProcessor(EmailService emailService, FineCalculator calculator) {
        //this.emailService = emailService;
       // this.calculator = calculator;
   // }

    //public void processFine(String email, FineCalculationStrategy strategy, int daysLate) {
        //calculator.setStrategy(strategy);
        //double fine = calculator.calculateFine(daysLate);

       // String message = String.format(
         //   "Dear, You returned your book %d days late. Your fine is $%.2f",
           // daysLate, fine
//        );

        // Fixed: Send to the actual user's email (not hardcoded!)
      //  emailService.sendEmail(email, "Library Fine Notice", message);
    //}
}