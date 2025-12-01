package service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Service for sending emails using Gmail SMTP.
 * Supports basic authentication and sending simple text emails.
 * 
 * Example usage:
 * EmailService emailService = new EmailService(username, password);
 * emailService.sendEmail("recipient@example.com", "Subject", "Email body text");
 * 
 * Note: Ensure "Less secure apps" or App Password is enabled in Gmail settings.
 */
public class EmailService {

    private final String username;
    private final String password;

    /**
     * Initializes the email service with Gmail credentials.
     * 
     * @param username Gmail username (email address)
     * @param password Gmail password or App Password
     */
    public EmailService(String username, String password) {
        this.username = username;
        this.password = password;
    } 

    /**
     * Sends an email using Gmail SMTP.
     * 
     * @param to Recipient email address
     * @param subject Email subject
     * @param body Email body text
     */
    public void sendEmail(String to, String subject, String body) {

        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

      
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {  
            // Build email
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(body);
//
            // Send 
            Transport.send(msg);

            System.out.println("Email successfully sent to " + to);

        } catch (MessagingException ex) {
            ex.printStackTrace();
            System.out.println("Email failed: " + ex.getMessage());
        }
    }
    /**
     * Example runner using .env file for credentials.
     */
    static void run() {
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("EMAIL_USERNAME");
        String password = dotenv.get("EMAIL_PASSWORD");

        EmailService emailService = new EmailService(username, password);

        String subject = "Book Due Reminder";
        String body = "Dear user, Your book is due soon. Best regards, An Najah Library System";

        emailService.sendEmail("s12218103@stu.najah.edu", subject, body);
        emailService.sendEmail("deemahamdan2004@gmail.com", subject, body);
    }
    public static void main(String[] args) {
        run();
    }
}