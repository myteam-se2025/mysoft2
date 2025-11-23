package soft;

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

import modl.Cd;
import service.CdService;

public class AddCd extends JFrame {

	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				AddCd frame = new AddCd();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	private JPanel contentPane;
	private JTextField title2;
	private JTextField artist2;
	private JTextField genre2;

	private JTextField copies2;

	public AddCd() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Add CD");
		lblTitle.setForeground(new Color(202, 128, 53));
		lblTitle.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblTitle.setBounds(76, 25, 123, 14);
		contentPane.add(lblTitle);

		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setForeground(new Color(202, 128, 53));
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(0, 94, 142, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Artist");
		lblNewLabel_2.setForeground(new Color(202, 128, 53));
		lblNewLabel_2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(0, 144, 101, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Genre");
		lblNewLabel_3.setForeground(new Color(202, 128, 53));
		lblNewLabel_3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setBounds(0, 193, 101, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Copies");
		lblNewLabel_4.setForeground(new Color(202, 128, 53));
		lblNewLabel_4.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setBounds(0, 240, 91, 14);
		contentPane.add(lblNewLabel_4);

		title2 = new JTextField();
		title2.setBounds(147, 94, 166, 20);
		contentPane.add(title2);
		title2.setColumns(10);

		artist2 = new JTextField();
		artist2.setBounds(147, 144, 166, 20);
		contentPane.add(artist2);
		artist2.setColumns(10);

		genre2 = new JTextField();
		genre2.setBounds(147, 193, 166, 20);
		contentPane.add(genre2);
		genre2.setColumns(10);

		copies2 = new JTextField();
		copies2.setBounds(147, 240, 166, 20);
		contentPane.add(copies2);
		copies2.setColumns(10);

		JButton btnAddCd = new JButton("Add CD");
		btnAddCd.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		btnAddCd.setForeground(new Color(96, 77, 49));
		btnAddCd.setBounds(393, 345, 172, 23);
		contentPane.add(btnAddCd);

		// ✅ Event Listener بسيط يعتمد على CdService
		btnAddCd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleAddCd();
			}
		});
	}

	private void handleAddCd() {
		String title = title2.getText().trim();
		String artist = artist2.getText().trim();
		String genre = genre2.getText().trim();
		int copies;

		try {
			copies = Integer.parseInt(copies2.getText().trim());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Copies must be a valid number.");
			return;
		}

		Cd cd = new Cd(title, artist, genre, copies);

		try {
			CdService cdService = new CdService();
			String message = cdService.addCd(cd);
			JOptionPane.showMessageDialog(this, message);

			// ✅ Clear fields only if added successfully
			if (message.equals("CD added successfully!")) {
				title2.setText("");
				artist2.setText("");
				genre2.setText("");
				copies2.setText("");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
		}
	}
}