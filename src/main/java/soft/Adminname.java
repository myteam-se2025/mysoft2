package soft;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class Adminname extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Adminname frame = new Adminname();
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
	public Adminname() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setForeground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 0, 795, 91);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("Add CD");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 AddCd searchFrame = new  AddCd();
				 searchFrame.setVisible(true);
				 searchFrame.setLocationRelativeTo(null);
				 Adminname.this.dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_3.setBounds(10, 11, 161, 35);
		panel.add(btnNewButton_3);
		btnNewButton_3.setBackground(new Color(143, 188, 143));
		
		JButton btnNewButton = new JButton("Add Book");
		btnNewButton.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBounds(205, 11, 195, 35);
		panel.add(btnNewButton);
		btnNewButton.setBackground(new Color(143, 188, 143));
		btnNewButton.setForeground(new Color(101, 64, 27));
		
		JButton btnNewButton_1 = new JButton("UnRegistor");
		btnNewButton_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_1.setBounds(436, 11, 183, 35);
		panel.add(btnNewButton_1);
		btnNewButton_1.setBackground(new Color(143, 188, 143));
		
		JButton btnNewButton_2 = new JButton("Registor");
		btnNewButton_2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 AddUser searchFrame = new  AddUser();
				 searchFrame.setVisible(true);
				 searchFrame.setLocationRelativeTo(null);
				 Adminname.this.dispose();
				 
			}
		});
		btnNewButton_2.setBounds(648, 11, 147, 35);
		panel.add(btnNewButton_2);
		btnNewButton_2.setBackground(new Color(143, 188, 143));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Adminaddbook searchFrame = new  Adminaddbook();
				 searchFrame.setVisible(true);
				 searchFrame.setLocationRelativeTo(null);
				 Adminname.this.dispose();
			}
		});

	}
}
