package soft;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.*;

import javax.swing.border.EmptyBorder;

public class UserMain extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    protected JButton btnSearch;
    protected JButton btnBorrow;
    protected JButton btnFines;

    protected class SwingAction extends AbstractAction {
        public SwingAction() {
            putValue(NAME, "Search");
            putValue(SHORT_DESCRIPTION, "Search for books");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            openUserSearch();
        }
    }

    protected class SwingAction_1 extends AbstractAction {
        public SwingAction_1() {
            putValue(NAME, "borrow ");
            putValue(SHORT_DESCRIPTION, "borrow");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            openUserBorrow();
        }
    }

    protected class SwingAction_2 extends AbstractAction {
        public SwingAction_2() {
            putValue(NAME, "fines");
            putValue(SHORT_DESCRIPTION, "fines");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            openUserFines();
        }
    }

    protected final Action action = new SwingAction();
    protected final Action action_1 = new SwingAction_1();
    protected final Action action_2 = new SwingAction_2();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserMain frame = new UserMain();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UserMain() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 811, 569);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(143, 188, 143));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(119, 136, 153));
        panel.setBounds(0, 0, 797, 73);
        contentPane.add(panel);
        panel.setLayout(null);

        btnSearch = new JButton("search new books ");
        btnSearch.setAction(action);
        btnSearch.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
        btnSearch.setForeground(new Color(0, 0, 0));
        btnSearch.setBackground(new Color(143, 188, 143));
        btnSearch.setBounds(36, 10, 209, 37);
        panel.add(btnSearch);

        btnBorrow = new JButton("borrow");
        btnBorrow.setAction(action_1);
        btnBorrow.setForeground(Color.BLACK);
        btnBorrow.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
        btnBorrow.setBackground(new Color(143, 188, 143));
        btnBorrow.setBounds(302, 10, 197, 37);
        panel.add(btnBorrow);

        btnFines = new JButton("fines");
        btnFines.setAction(action_2);
        btnFines.setForeground(Color.BLACK);
        btnFines.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
        btnFines.setBackground(new Color(143, 188, 143));
        btnFines.setBounds(544, 10, 197, 37);
        panel.add(btnFines);
    }

    // Protected methods لتسهيل الاختبارات
    protected void openUserSearch() {
        UserSearch searchFrame = createUserSearch();
        searchFrame.setVisible(true);
        searchFrame.setLocationRelativeTo(null);
        dispose();
    }

    protected void openUserBorrow() {
        UserBorow borrowFrame = createUserBorow();
        borrowFrame.setVisible(true);
        borrowFrame.setLocationRelativeTo(null);
        dispose();
    }

    protected void openUserFines() {
        UserFines finesFrame = createUserFines();
        finesFrame.setVisible(true);
        finesFrame.setLocationRelativeTo(null);
        dispose();
    }

    // يمكن إعادة تعريفها للاختبارات
    protected UserSearch createUserSearch() { return new UserSearch(); }
    protected UserBorow createUserBorow() { return new UserBorow(); }
    protected UserFines createUserFines() { return new UserFines(); }
}