package soft;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name2;
	private JTextField email2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 351);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel AdminLabel = new JLabel("        Login");
		AdminLabel.setBounds(10, 11, 238, 25);
		AdminLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		AdminLabel.setBackground(new Color(96, 77, 49));
		AdminLabel.setForeground(new Color(96, 77, 49));
		contentPane.add(AdminLabel);

		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(10, 79, 118, 14);
		lblNewLabel.setForeground(new Color(101, 64, 27));
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(10, 144, 124, 14);
		lblNewLabel_1.setForeground(new Color(101, 64, 27));
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(lblNewLabel_1);

		name2 = new JTextField();
		name2.setBounds(176, 74, 202, 25);
		contentPane.add(name2);
		name2.setColumns(10);

		email2 = new JTextField();
		email2.setBounds(176, 133, 202, 25);
		contentPane.add(email2);
		email2.setColumns(10);

		JButton login = new JButton("LogIn");
		login.setBounds(316, 219, 154, 23);
		login.setForeground(new Color(96, 77, 49));
		login.setBackground(new Color(143, 188, 143));
		login.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = name2.getText().trim();
				String email = email2.getText().trim();

				if (username.isEmpty() || email.isEmpty()) {
					javax.swing.JOptionPane.showMessageDialog(LogIn.this, "Please fill all fields!");
					return;
				}

				try {
					service.AdminService adminService = new service.AdminService();
					if (adminService.login(username, email)) {
						soft.AdmiNname adminPage = new soft.AdmiNname(); // صححت الاسم هنا
						adminPage.setVisible(true);
						adminPage.setLocationRelativeTo(null);
						LogIn.this.dispose();
					} else {
						service.UserService userService = new service.UserService();
						if (userService.login(username, email) != null) {
							soft.UserMain userPage = new soft.UserMain();
							userPage.setVisible(true);
							userPage.setLocationRelativeTo(null);
							LogIn.this.dispose();
						} else {
							javax.swing.JOptionPane.showMessageDialog(LogIn.this, "Invalid credentials!");
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					javax.swing.JOptionPane.showMessageDialog(LogIn.this, "Database error: " + ex.getMessage());
				}
			}
		});
		contentPane.add(login);
	}
}