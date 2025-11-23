package soft;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class AdminMain extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AdminMain frame = new AdminMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AdminMain() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
			@Override
			public void actionPerformed(ActionEvent e) {
				AddCd searchFrame = new AddCd();
				searchFrame.setVisible(true);
				searchFrame.setLocationRelativeTo(null);
				AdminMain.this.dispose();
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
			@Override
			public void actionPerformed(ActionEvent e) {
				AddUser searchFrame = new AddUser();
				searchFrame.setVisible(true);
				searchFrame.setLocationRelativeTo(null);
				AdminMain.this.dispose();

			}
		});
		btnNewButton_2.setBounds(648, 11, 147, 35);
		panel.add(btnNewButton_2);
		btnNewButton_2.setBackground(new Color(143, 188, 143));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminAddBook searchFrame = new AdminAddBook();
				searchFrame.setVisible(true);
				searchFrame.setLocationRelativeTo(null);
				AdminMain.this.dispose();
			}
		});

	}
}
