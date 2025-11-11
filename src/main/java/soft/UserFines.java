package soft;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import javax.swing.JTable;

public class UserFines extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Action action = new SwingAction();
	private JTextField txtInterYourId;
	private JTextField txtAmountToPay;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UserFines frame = new UserFines();
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
	public UserFines() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setForeground(new Color(250, 250, 210));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("<--");
		btnNewButton.setAction(action);
		btnNewButton.setBounds(10, 10, 67, 19);
		btnNewButton.setForeground(new Color(85, 107, 47));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBackground(new Color(143, 188, 143));
		contentPane.add(btnNewButton);
		
		txtInterYourId = new JTextField();
		txtInterYourId.setForeground(new Color(210, 180, 140));
		txtInterYourId.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 18));
		txtInterYourId.setHorizontalAlignment(SwingConstants.CENTER);
		txtInterYourId.setText("inter your id");
		txtInterYourId.setBounds(230, 63, 315, 30);
		contentPane.add(txtInterYourId);
		txtInterYourId.setColumns(10);
		
		JButton btnNewButton_1_1 = new JButton("show all fines ");
		btnNewButton_1_1.setBackground(new Color(210, 180, 140));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setForeground(new Color(143, 188, 143));
		btnNewButton_1_1.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		btnNewButton_1_1.setBounds(578, 273, 153, 20);
		contentPane.add(btnNewButton_1_1);
		
		txtAmountToPay = new JTextField();
		txtAmountToPay.setHorizontalAlignment(SwingConstants.CENTER);
		txtAmountToPay.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 18));
		txtAmountToPay.setForeground(new Color(210, 180, 140));
		txtAmountToPay.setText("inter fine id to pay");
		txtAmountToPay.setBounds(285, 184, 193, 24);
		contentPane.add(txtAmountToPay);
		txtAmountToPay.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("pay");
		btnNewButton_2.setBackground(new Color(210, 180, 140));
		btnNewButton_2.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_2.setForeground(new Color(143, 188, 143));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(337, 154, 89, 20);
		contentPane.add(btnNewButton_2);
		
		table = new JTable();
		table.setForeground(new Color(143, 188, 143));
		table.setBackground(new Color(210, 180, 140));
		table.setBounds(66, 303, 665, 208);
		contentPane.add(table);

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "<--");
			putValue(SHORT_DESCRIPTION, "get back");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			UserMain searchFrame = new UserMain();
			 searchFrame.setVisible(true);
			 searchFrame.setLocationRelativeTo(null);
			 UserFines.this.dispose();
		}
	}
}
