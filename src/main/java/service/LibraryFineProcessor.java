package service;

import io.github.cdimascio.dotenv.Dotenv;
import modl.*;
public class LibraryFineProcessor {
    
    private final EmailService emailService;
    

    // Constructor-injected dependencies (perfect for testing!)
    public LibraryFineProcessor(EmailService emailService) {
        this.emailService = emailService;
       
   }

    public void processFine(String email , Fine u ) {
    	
    	 
        double fine = u.getAmount();

        String message = String.format(
            "Dear, You returned your book %d days late. Your fine is $%.2f");

       //  Fixed: Send to the actual user's email (not hardcoded!)
         emailService.sendEmail(email, "Library Fine Notice", message);
    }
}





