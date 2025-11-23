package soft;

import service.LogInService;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LogIn extends JFrame {

	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				LogIn frame = new LogIn();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	private JPanel contentPane;
	private JTextField email1;

	private JTextField pass;

	public LogIn() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 351);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel AdminLabel = new JLabel("Login");
		AdminLabel.setBounds(200, 10, 150, 25);
		AdminLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		AdminLabel.setForeground(new Color(96, 77, 49));
		contentPane.add(AdminLabel);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(70, 76, 81, 25);
		lblEmail.setForeground(new Color(101, 64, 27));
		lblEmail.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(40, 132, 124, 20);
		lblPassword.setForeground(new Color(101, 64, 27));
		lblPassword.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(lblPassword);

		email1 = new JTextField();
		email1.setBounds(176, 74, 202, 25);
		contentPane.add(email1);

		pass = new JTextField();
		pass.setBounds(176, 133, 202, 25);
		contentPane.add(pass);

		JButton login = new JButton("LogIn");
		login.setBounds(316, 219, 154, 23);
		login.setForeground(new Color(96, 77, 49));
		login.setBackground(new Color(143, 188, 143));
		login.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(login);

		// الزر يستدعي الخدمة
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = email1.getText();
				String password = pass.getText();

				LogInService service = new LogInService();
				String result = service.handleLogin(email, password);

				switch (result) {
				case "ADMIN":
					new AdminMain().setVisible(true);
					dispose();
					break;
				case "USER":
					new UserMain().setVisible(true);
					dispose();
					break;
				default:
					JOptionPane.showMessageDialog(LogIn.this, result, "Login", JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		});
	}
}