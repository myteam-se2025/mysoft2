
package soft;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modl.User;
import service.UnRegUserService;

public class AdminUnReguser extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnUnregister;

    //  مخصص للاختبارات: يمكن استبدال الخدمة أو Dialogs
    protected UnRegUserService service = new UnRegUserService();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminUnReguser frame = new AdminUnReguser();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AdminUnReguser() {
        setTitle("Unregister Users");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(153, 181, 115));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Unregister Inactive Users");
        lblTitle.setForeground(new Color(44, 116, 54));
        lblTitle.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lblTitle.setBounds(10, 20, 500, 40);
        contentPane.add(lblTitle);

        JLabel lblInfo = new JLabel("Only users without active loans or unpaid fines can be unregistered.");
        lblInfo.setForeground(new Color(44, 116, 54));
        lblInfo.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 14));
        lblInfo.setBounds(10, 60, 700, 30);
        contentPane.add(lblInfo);

        btnUnregister = new JButton("Unregister Users");
        btnUnregister.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 16));
        btnUnregister.setForeground(new Color(97, 152, 63));
        btnUnregister.setBounds(10, 120, 250, 30);
        contentPane.add(btnUnregister);

        btnUnregister.addActionListener(e -> openUserSelectionDialog());
    }

    // ✅ Getter للزر للاختبارات
    public JButton getBtnUnregister() {
        return btnUnregister;
    }

    // fun محمية للاختبارات
    protected List<User> getEligibleUsers() {
        return service.getEligibleUsers();
    }

    protected boolean unregisterUser(int userId) {
        return service.unregisterUser(userId);
    }

    protected void showMessageDialog(Object message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    protected int showConfirmDialog(Object message, String title, int optionType, int messageType) {
        return JOptionPane.showConfirmDialog(this, message, title, optionType, messageType);
    }

    protected int[] getSelectedIndices(JList<String> userList) {
        return userList.getSelectedIndices();
    }

    // ✅ منطق فتح الحوار
    protected void openUserSelectionDialog() {
        List<User> users = getEligibleUsers();

        if (users.isEmpty()) {
            showMessageDialog("No eligible users found.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        DefaultListModel<String> model = new DefaultListModel<>();
        for (User u : users) {
            model.addElement(u.getUser_id() + " - " + u.getFull_name() + " - " + u.getEmail());
        }

        JList<String> userList = new JList<>(model);
        userList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scroll = new JScrollPane(userList);
        scroll.setPreferredSize(new java.awt.Dimension(600, 300));

        int option = showConfirmDialog(scroll, "Select users to unregister", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option != JOptionPane.OK_OPTION) return;

        int[] selectedIndices = getSelectedIndices(userList);
        if (selectedIndices.length == 0) {
            showMessageDialog("No user selected.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = showConfirmDialog("Are you sure you want to unregister the selected users?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;

        boolean allSuccess = true;
        for (int i : selectedIndices) {
            String data = model.get(i);
            int userId = Integer.parseInt(data.split(" - ")[0]);
            if (!unregisterUser(userId)) {
                allSuccess = false;
            }
        }

        if (allSuccess)
            showMessageDialog("Users unregistered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        else
            showMessageDialog("Some users could not be deleted.", "Partial Error", JOptionPane.ERROR_MESSAGE);
    }
}