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

public class AddCd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCd frame = new AddCd();
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
	public AddCd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add CD");
		lblNewLabel.setForeground(new Color(202, 128, 53));
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(45, 22, 111, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tittle");
		lblNewLabel_1.setForeground(new Color(202, 128, 53));
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(10, 103, 146, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Artist");
		lblNewLabel_2.setForeground(new Color(202, 128, 53));
		lblNewLabel_2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(10, 156, 131, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gener");
		lblNewLabel_3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setForeground(new Color(202, 128, 53));
		lblNewLabel_3.setBounds(10, 204, 131, 14);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(151, 102, 144, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(151, 156, 144, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(151, 203, 144, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Add Cd");
		btnNewButton.setBackground(new Color(96, 77, 49));
		btnNewButton.setForeground(new Color(202, 128, 53));
		btnNewButton.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		btnNewButton.setBounds(410, 347, 184, 23);
		contentPane.add(btnNewButton);

	}
}
