package soft;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modl.User;
import service.UserService;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name2;
	private JTextField email;
	private JTextField phone;
	private JTextField addres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser frame = new AddUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add User");
		lblNewLabel.setForeground(new Color(202, 128, 53));
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(76, 25, 123, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("FullName");
		lblNewLabel_1.setForeground(new Color(202, 128, 53));
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(0, 94, 142, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setForeground(new Color(202, 128, 53));
		lblNewLabel_2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(0, 144, 101, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setForeground(new Color(202, 128, 53));
		lblNewLabel_3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setBounds(0, 193, 101, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Addres");
		lblNewLabel_4.setForeground(new Color(202, 128, 53));
		lblNewLabel_4.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setBounds(0, 240, 91, 14);
		contentPane.add(lblNewLabel_4);
		
		name2 = new JTextField();
		name2.setBounds(147, 94, 166, 20);
		contentPane.add(name2);
		name2.setColumns(10);
		
		email = new JTextField();
		email.setBounds(147, 144, 166, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(147, 193, 166, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		addres = new JTextField();
		addres.setBounds(147, 240, 166, 20);
		contentPane.add(addres);
		addres.setColumns(10);
		
		JButton btnNewButton = new JButton("AddUser");
		btnNewButton.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		btnNewButton.setForeground(new Color(96, 77, 49));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = name2.getText();
				String email2 = email.getText();
				String phone2 = phone.getText();
				String addres2 = addres.getText();
		
				  User user = new User(name, email2, phone2, addres2, new java.sql.Date(System.currentTimeMillis()));    
				    
			        try {
			            UserService userService = new UserService();
			            userService.registerUser(user);
			        } catch (Exception e1) {
			            e1.printStackTrace();
			}
			    }
			
		});
		btnNewButton.setBounds(393, 345, 172, 23);
		contentPane.add(btnNewButton);

	}

}
