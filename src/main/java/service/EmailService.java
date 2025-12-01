package service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Simple Gmail SMTP Email Sender
 */
public class EmailService {

    private final String username;
    private final String password;

    public EmailService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void sendEmail(String to, String subject, String body) {

        // SMTP settings
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Auth
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