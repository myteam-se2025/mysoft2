package soft;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class AdminMain extends JFrame {

    private static final long serialVersionUID = 1L;

  
    protected JButton btnAddCd;       // Add CD
    protected JButton btnAddBook;     // Add Book
    protected JButton btnUnRegister;  // UnRegistor
    protected JButton btnRegister;    // Registor

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminMain frame = new AdminMain();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AdminMain() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 811, 569);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(143, 188, 143));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 128, 128));
        panel.setBounds(0, 0, 795, 91);
        contentPane.add(panel);
        panel.setLayout(null);

        // Add CD Button
        btnAddCd = new JButton("Add CD");
        btnAddCd.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
        btnAddCd.setBounds(10, 11, 161, 35);
        btnAddCd.setBackground(new Color(143, 188, 143));
        btnAddCd.addActionListener(e -> {
            AddCd frame = new AddCd();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            AdminMain.this.dispose();
        });
        panel.add(btnAddCd);

        // Add Book Button
        btnAddBook = new JButton("Add Book");
        btnAddBook.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
        btnAddBook.setBounds(205, 11, 195, 35);
        btnAddBook.setBackground(new Color(143, 188, 143));
        btnAddBook.setForeground(new Color(101, 64, 27));
        btnAddBook.addActionListener(e -> {
            AdminAddBook frame = new AdminAddBook();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            AdminMain.this.dispose();
        });
        panel.add(btnAddBook);

        // UnRegistor Button
        btnUnRegister = new JButton("UnRegistor");
        btnUnRegister.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
        btnUnRegister.setBounds(436, 11, 183, 35);
        btnUnRegister.setBackground(new Color(143, 188, 143));
        panel.add(btnUnRegister);

        // Registor Button
        btnRegister = new JButton("Registor");
        btnRegister.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
        btnRegister.setBounds(648, 11, 147, 35);
        btnRegister.setBackground(new Color(143, 188, 143));
        btnRegister.addActionListener(e -> {
            AddUser frame = new AddUser();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            AdminMain.this.dispose();
        });
        panel.add(btnRegister);
    }

    // ⭐ Getters لاختبارات JUnit فقط
    public JButton getAddCdButton() {
        return btnAddCd;
    }

    public JButton getAddBookButton() {
        return btnAddBook;
    }

    public JButton getUnRegisterButton() {
        return btnUnRegister;
    }

    public JButton getRegisterButton() {
        return btnRegister;
    }
}