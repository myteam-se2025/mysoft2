package soft;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Adminunreguser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Adminunreguser frame = new Adminunreguser();
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
	public Adminunreguser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 181, 115));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 142, 775, 317);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("If you want To Unregister aUser So That Inactive Accounts Are Removed ");
		lblNewLabel.setForeground(new Color(44, 116, 54));
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(10, 39, 775, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Click on A Button");
		lblNewLabel_1.setForeground(new Color(44, 116, 54));
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(10, 83, 228, 27);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("UnRegister");
		btnNewButton.setForeground(new Color(97, 152, 63));
		btnNewButton.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		btnNewButton.setBounds(298, 496, 213, 23);
		contentPane.add(btnNewButton);

	}

}
