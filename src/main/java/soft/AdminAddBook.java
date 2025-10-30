package soft;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPanel contentPane;
    private JTextField tittle;
    private JTextField auother;
    private JTextField isbn;
    private JTextField category;
    private JTextField copies;

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

        JLabel lblTitle = new JLabel("Title");
        lblTitle.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lblTitle.setForeground(new Color(202, 128, 53));
        lblTitle.setBounds(36, 45, 91, 14);
        contentPane.add(lblTitle);

        JLabel lblAuthor = new JLabel("Author");
        lblAuthor.setForeground(new Color(202, 128, 53));
        lblAuthor.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lblAuthor.setBounds(24, 109, 91, 14);
        contentPane.add(lblAuthor);

        JLabel lblIsbn = new JLabel("ISBN");
        lblIsbn.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lblIsbn.setForeground(new Color(202, 128, 53));
        lblIsbn.setBounds(24, 181, 160, 14);
        contentPane.add(lblIsbn);

        JLabel lblCategory = new JLabel("Category");
        lblCategory.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lblCategory.setForeground(new Color(202, 128, 53));
        lblCategory.setBounds(24, 244, 160, 14);
        contentPane.add(lblCategory);

        JLabel lblCopies = new JLabel("Copies");
        lblCopies.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lblCopies.setForeground(new Color(202, 128, 53));
        lblCopies.setBounds(24, 300, 160, 14);
        contentPane.add(lblCopies);

        tittle = new JTextField();
        tittle.setBounds(238, 45, 186, 25);
        contentPane.add(tittle);

        auother = new JTextField();
        auother.setBounds(238, 104, 186, 25);
        contentPane.add(auother);

        isbn = new JTextField();
        isbn.setBounds(238, 172, 186, 25);
        contentPane.add(isbn);

        category = new JTextField();
        category.setBounds(238, 239, 186, 25);
        contentPane.add(category);

        copies = new JTextField();
        copies.setBounds(238, 295, 186, 25);
        contentPane.add(copies);

        JButton addbook = new JButton("Add Book");
        addbook.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
                String title = tittle.getText();
                String author = auother.getText();
                String isbnCode = isbn.getText();
                String cat = category.getText();
                int copyCount = Integer.parseInt(copies.getText());

                Book book = new Book(title, author, isbnCode, cat, copyCount);
                try {
                    BookService service = new BookService();
                    service.registerBook(book);
                    JOptionPane.showMessageDialog(null, "Book added successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding book: " + ex.getMessage());
                }
            }
        });
        addbook.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        addbook.setForeground(new Color(96, 77, 49));
        addbook.setBounds(409, 400, 186, 30);
        contentPane.add(addbook);
    }
}