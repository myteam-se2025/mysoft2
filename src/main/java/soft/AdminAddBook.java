package soft;

import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;

import javax.swing.*;

import modl.Book;
import service.BookService;

public class AdminAddBook extends JFrame {

    private static final long serialVersionUID = 1L;

    // === Testing support ===
    public boolean testingMode = false;
    private BookService bookServiceForTest = null;

    public void setBookServiceForTest(BookService mock) {
        this.bookServiceForTest = mock;
    }

    private BookService getBookService() {
        if (bookServiceForTest != null) return bookServiceForTest;
        try {
            return new BookService();
        } catch (Exception e) {
            if (!testingMode)
                JOptionPane.showMessageDialog(this, "Database connection error!");
            return null;
        }
    }

    // UI Components
    public JTextField title2;
    public JTextField author2;
    public JTextField isbn2;
    public JTextField category2;
    public JTextField copies2;
    public JButton addButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminAddBook frame = new AdminAddBook();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AdminAddBook() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 811, 569);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(143, 188, 143));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Add Book");
        lblTitle.setForeground(new Color(202, 128, 53));
        lblTitle.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lblTitle.setBounds(76, 25, 200, 20);
        contentPane.add(lblTitle);

        JLabel lbl1 = new JLabel("Title");
        lbl1.setForeground(new Color(202, 128, 53));
        lbl1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lbl1.setBounds(0, 94, 142, 14);
        contentPane.add(lbl1);

        JLabel lbl2 = new JLabel("Author");
        lbl2.setForeground(new Color(202, 128, 53));
        lbl2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lbl2.setBounds(0, 144, 101, 14);
        contentPane.add(lbl2);

        JLabel lbl3 = new JLabel("ISBN");
        lbl3.setForeground(new Color(202, 128, 53));
        lbl3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lbl3.setBounds(0, 193, 101, 14);
        contentPane.add(lbl3);

        JLabel lbl4 = new JLabel("Category");
        lbl4.setForeground(new Color(202, 128, 53));
        lbl4.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lbl4.setBounds(0, 240, 150, 14);
        contentPane.add(lbl4);

        JLabel lbl5 = new JLabel("Copies");
        lbl5.setForeground(new Color(202, 128, 53));
        lbl5.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lbl5.setBounds(0, 287, 120, 14);
        contentPane.add(lbl5);

        title2 = new JTextField();
        title2.setBounds(147, 94, 166, 20);
        contentPane.add(title2);

        author2 = new JTextField();
        author2.setBounds(147, 144, 166, 20);
        contentPane.add(author2);

        isbn2 = new JTextField();
        isbn2.setBounds(147, 193, 166, 20);
        contentPane.add(isbn2);

        category2 = new JTextField();
        category2.setBounds(147, 240, 166, 20);
        contentPane.add(category2);

        copies2 = new JTextField();
        copies2.setBounds(147, 287, 166, 20);
        contentPane.add(copies2);

        addButton = new JButton("Add Book");
        addButton.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        addButton.setBounds(393, 345, 172, 23);
        contentPane.add(addButton);

        addButton.addActionListener(e -> handleAddBook());
    }

    // For tests
    public void handleAddBookForTest() {
        testingMode = true;
        handleAddBook();
    }

    private void handleAddBook() {

        String title = title2.getText().trim();
        String author = author2.getText().trim();
        String isbn = isbn2.getText().trim();
        String category = category2.getText().trim();

        // Validation
        if (title.isEmpty()) {
            if (!testingMode) JOptionPane.showMessageDialog(this, "Title cannot be empty!");
            return;
        }

        if (isbn.isEmpty()) {
            if (!testingMode) JOptionPane.showMessageDialog(this, "ISBN cannot be empty!");
            return;
        }

        int copies;
        try {
            copies = Integer.parseInt(copies2.getText().trim());
        } catch (NumberFormatException e) {
            if (!testingMode) JOptionPane.showMessageDialog(this, "Copies must be a valid number.");
            return;
        }

        Book book = new Book(title, author, isbn, category, copies);

        try {
            BookService service = getBookService();
            if (service == null) return;

            String msg = service.addBook(book);

            if (!testingMode)
                JOptionPane.showMessageDialog(this, msg);

            if (msg.equals("Book added successfully!")) {
                title2.setText("");
                author2.setText("");
                isbn2.setText("");
                category2.setText("");
                copies2.setText("");
            }

        } catch (Exception ex) {
            if (!testingMode)
                JOptionPane.showMessageDialog(this, "Database Error!");
        }
    }
}
