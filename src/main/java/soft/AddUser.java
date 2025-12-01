package soft;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import modl.User;
import service.UserService;

public class AddUser extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    protected JTextField name2;
    protected JTextField email;
    protected JTextField phone;
    protected JTextField addres;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddUser frame = new AddUser();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AddUser() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 811, 569);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(143, 188, 143));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Add User");
        lblTitle.setForeground(new Color(202, 128, 53));
        lblTitle.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lblTitle.setBounds(76, 25, 200, 20);
        contentPane.add(lblTitle);

        JLabel lblName = new JLabel("FullName");
        lblName.setForeground(new Color(202, 128, 53));
        lblName.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lblName.setBounds(0, 94, 142, 20);
        contentPane.add(lblName);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(new Color(202, 128, 53));
        lblEmail.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lblEmail.setBounds(0, 144, 142, 20);
        contentPane.add(lblEmail);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setForeground(new Color(202, 128, 53));
        lblPhone.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lblPhone.setBounds(0, 193, 142, 20);
        contentPane.add(lblPhone);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setForeground(new Color(202, 128, 53));
        lblAddress.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lblAddress.setBounds(0, 240, 142, 20);
        contentPane.add(lblAddress);

        name2 = new JTextField();
        name2.setBounds(147, 94, 166, 20);
        contentPane.add(name2);

        email = new JTextField();
        email.setBounds(147, 144, 166, 20);
        contentPane.add(email);

        phone = new JTextField();
        phone.setBounds(147, 193, 166, 20);
        contentPane.add(phone);

        addres = new JTextField();
        addres.setBounds(147, 240, 166, 20);
        contentPane.add(addres);

        JButton btnAdd = new JButton("AddUser");
        btnAdd.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        btnAdd.setForeground(new Color(96, 77, 49));
        btnAdd.setBounds(393, 345, 172, 30);
        contentPane.add(btnAdd);

        btnAdd.addActionListener((ActionEvent e) -> handleAddUser());
    }

    // TEST ONLY
    protected void handleAddUserForTest() {
        handleAddUser();
    }

    // For mock overriding
    protected UserService createUserService() throws SQLException {
        return new UserService(null);
    }

    private void handleAddUser() {
        try {
            if (name2.getText().isEmpty() ||
                email.getText().isEmpty() ||
                phone.getText().isEmpty() ||
                addres.getText().isEmpty()) {

                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            if (!phone.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Phone must be numeric!");
                return;
            }

            User user = new User(
                name2.getText(),
                email.getText(),
                phone.getText(),
                addres.getText(),
                new java.sql.Date(System.currentTimeMillis())
            );

            UserService service = createUserService();
            service.registerUser(user);

            JOptionPane.showMessageDialog(this, "User added successfully!");

            name2.setText("");
            email.setText("");
            phone.setText("");
            addres.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding user!");
        }
    }
}