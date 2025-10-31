package soft;
import service.*;

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

public class LogIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField email1;
	private JTextField pass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 351);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel AdminLabel = new JLabel("        Login");
		AdminLabel.setBounds(177, 10, 238, 25);
		AdminLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		AdminLabel.setBackground(new Color(96, 77, 49));
		AdminLabel.setForeground(new Color(96, 77, 49));
		contentPane.add(AdminLabel);

		JLabel lblNewLabel = new JLabel("passwprd");
		lblNewLabel.setBounds(31, 132, 124, 20);
		lblNewLabel.setForeground(new Color(101, 64, 27));
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(53, 76, 81, 25);
		lblNewLabel_1.setForeground(new Color(101, 64, 27));
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(lblNewLabel_1);

		email1 = new JTextField();
		email1.setBounds(176, 74, 202, 25);
		contentPane.add(email1);
		email1.setColumns(10);

		pass = new JTextField();
		pass.setBounds(176, 133, 202, 25);
		contentPane.add(pass);
		pass.setColumns(10);

		JButton login = new JButton("LogIn");
		login.setBounds(316, 219, 154, 23);
		login.setForeground(new Color(96, 77, 49));
		login.setBackground(new Color(143, 188, 143));
		login.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		
		/*login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
            /*
				String email = email1.getText().trim();
				int idd = Integer.parseInt(pass.getText().trim());
			
				LogInService log = new LogInService();
				 try {
				        log.login(idd, email);
				    } catch (SQLException ex) {
				        JOptionPane.showMessageDialog(
				            null,
				            "Database error occurred while trying to log in.\nPlease try again later.",
				            "Database Error",
				            JOptionPane.ERROR_MESSAGE
				        );
				        ex.printStackTrace();
				    } handleLogin();
          
				
			}
		});*/
		
		contentPane.add(login);

	
		
		login.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                handleLogin();
	            }

				private void handleLogin() {
					
					String email = email1.getText().trim();
					String passtext = pass.getText();
					
					
					
					
					if (email.isEmpty() || passtext.isEmpty())
					{
						 JOptionPane.showMessageDialog(LogIn.this,
					                "Please enter both ID and email.",
					                "Input Error",
					                JOptionPane.WARNING_MESSAGE);
					        return;
					}
					
					
					int id= 0;
					try {
				        id = Integer.parseInt(passtext);
				        if (id <= 0) {
				            JOptionPane.showMessageDialog(LogIn.this,
				                    "ID must be a positive number.",
				                    "Input Error",
				                    JOptionPane.WARNING_MESSAGE);
				            return;
				        }
				    } catch (NumberFormatException ex) {
				        JOptionPane.showMessageDialog(LogIn.this,
				                "ID must be a number.",
				                "Input Error",
				                JOptionPane.WARNING_MESSAGE);
				        return;
				    }
					
					
					 if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|org|net)$")) {
					        JOptionPane.showMessageDialog(LogIn.this,
					        		"Email must hava @ and end with .com, .org, or .net | Example: name@example.com",
					                "Input Error",
					                JOptionPane.WARNING_MESSAGE);
					        return;
					    }
					 
					 
					
					 LogInService service = new LogInService();
					 String result = service.login(id, email);
						    
					 
				        switch (result) {
				            case "ADMIN":
				                new AdminMain().setVisible(true);
				                dispose();
				                break;
				            case "USER":
				                new UserMain().setVisible(true);
				                dispose();
				                break;
				            case "NOT_FOUND":
				                JOptionPane.showMessageDialog(LogIn.this, "No matching user or admin found.", "Login Failed", JOptionPane.WARNING_MESSAGE);
				                break;
				            default:
				                JOptionPane.showMessageDialog(LogIn.this, "Unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
				        }
				}
	        });
		
		
		}
}
	
