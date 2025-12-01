package service;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class LibraryFineProcessorTest {

    @Test
    void testStudentFineEmailIsSent() {
        // Arrange
        EmailService mockEmailService = mock(EmailService.class);
       // FineCalculator calculator = new FineCalculator();
      //  LibraryFineProcessor processor = new LibraryFineProcessor(mockEmailService, calculator);

       // FineCalculationStrategy studentStrategy = new StudentFineStrategy();

        // Act
       // processor.processFine("hayasam@najah.edu", studentStrategy, 5);

        // Assert
        verify(mockEmailService, times(1))
            .sendEmail(eq("hayasam@najah.edu"), 
                       eq("Library Fine Notice"), 
                       contains("Your fine is"));
    }
}
