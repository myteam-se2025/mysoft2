package soft;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import service.BorowService;

public class UserBorow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    protected JTextField bookid;
    protected JTextField userid;
    protected JButton btnBorow;

    // خدمة قابلة للحقن للاختبارات
    private BorowService borrowServiceForTest = null;

    public void setBorrowServiceForTest(BorowService mock) {
        this.borrowServiceForTest = mock;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserBorow frame = new UserBorow();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UserBorow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 811, 569);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(143, 188, 143));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(210, 180, 140));
        panel.setBounds(25, 90, 745, 411);
        contentPane.add(panel);
        panel.setLayout(null);

        bookid = new JTextField("Enter ISBN here");
        bookid.setHorizontalAlignment(SwingConstants.CENTER);
        bookid.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 18));
        bookid.setForeground(new Color(85, 107, 47));
        bookid.setBackground(new Color(143, 188, 143));
        bookid.setBounds(260, 63, 207, 30);
        panel.add(bookid);

        userid = new JTextField("Enter your ID here");
        userid.setHorizontalAlignment(SwingConstants.CENTER);
        userid.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 18));
        userid.setForeground(new Color(85, 107, 47));
        userid.setBackground(new Color(143, 188, 143));
        userid.setBounds(260, 109, 207, 30);
        panel.add(userid);

        btnBorow = new JButton("Borrow");
        btnBorow.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 14));
        btnBorow.setForeground(new Color(85, 107, 47));
        btnBorow.setBackground(new Color(143, 188, 143));
        btnBorow.setBounds(593, 181, 95, 30);
        panel.add(btnBorow);

        btnBorow.addActionListener(e -> handleBorrow());
    }

    //      Protected Methods
    protected BorowService getBorowService() {
        if (borrowServiceForTest != null)
            return borrowServiceForTest;
        return new BorowService();
    }

    protected void showMessageDialog(Object message) {
        JOptionPane.showMessageDialog(this, message);
    }

    //      Handle Action
    public void handleBorrow() {
        String book_id = bookid.getText().trim();
        String user_id = userid.getText().trim();

        BorowService service = getBorowService();
        String message = service.processBorrowRequest(user_id, book_id);

        showMessageDialog(message);
    }
}