package soft;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import modl.Book;
import service.BookService;

public class AdminAddBook extends JFrame {

    private static final long serialVersionUID = 1L;

    //  خدمة قابلة للحقن أثناء الاختبار
    private BookService bookServiceForTest = null;
    public void setBookServiceForTest(BookService mock) {
        this.bookServiceForTest = mock;
    }

    private JPanel contentPane;

    // 🔹 المتغيرات محمية لتسهيل الاختبارات
    protected JTextField title2;
    protected JTextField author2;
    protected JTextField isbn2;
    protected JTextField category2;
    protected JTextField copies2;
    protected JButton addButton; //  زر AddBook للوصول من التست

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
        contentPane = new JPanel();
        contentPane.setBackground(new Color(143, 188, 143));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
        addButton.setForeground(new Color(96, 77, 49));
        addButton.setBounds(393, 345, 172, 23);
        contentPane.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddBook();
            }
        });
    }

    //  يرجع الخدمة الحقيقية أو الـ mock أثناء الاختبار
    private BookService getBookService() throws SQLException {
        if (bookServiceForTest != null)
            return bookServiceForTest;
        return new BookService();
    }

    //  تسهّل الاختبار
    public void handleAddBookForTest() {
        handleAddBook();
    }

    private void handleAddBook() {
        String title = title2.getText().trim();
        String author = author2.getText().trim();
        String isbn = isbn2.getText().trim();
        String category = category2.getText().trim();

        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ISBN cannot be empty!");
            return;
        }

        int copies;
        try {
            copies = Integer.parseInt(copies2.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Copies must be a valid number.");
            return;
        }

        Book book = new Book(title, author, isbn, category, copies);

        try {
            BookService service = getBookService();
            String msg = service.addBook(book);

            JOptionPane.showMessageDialog(this, msg);

            if ("Book added successfully!".equals(msg)) {
                title2.setText("");
                author2.setText("");
                isbn2.setText("");
                category2.setText("");
                copies2.setText("");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }
}