package soft;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class Userborow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtInterIsbnHe;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Userborow frame = new Userborow();
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
	public Userborow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.setBounds(25, 90, 745, 411);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtInterIsbnHe = new JTextField();
		txtInterIsbnHe.setHorizontalAlignment(SwingConstants.CENTER);
		txtInterIsbnHe.setBackground(new Color(143, 188, 143));
		txtInterIsbnHe.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 18));
		txtInterIsbnHe.setForeground(new Color(85, 107, 47));
		txtInterIsbnHe.setText("inter isbn here");
		txtInterIsbnHe.setBounds(260, 63, 207, 30);
		panel.add(txtInterIsbnHe);
		txtInterIsbnHe.setColumns(10);
		
		JButton btnNewButton = new JButton("borow");
		btnNewButton.setBackground(new Color(143, 188, 143));
		btnNewButton.setForeground(new Color(85, 107, 47));
		btnNewButton.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
		btnNewButton.setBounds(589, 150, 95, 20);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("borow");
		lblNewLabel.setForeground(new Color(85, 107, 47));
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(338, 25, 110, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("<--");
		btnNewButton_1.setAction(action);
		btnNewButton_1.setBackground(new Color(143, 188, 143));
		btnNewButton_1.setForeground(new Color(85, 107, 47));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(10, 10, 70, 20);
		contentPane.add(btnNewButton_1);

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "<--");
			putValue(SHORT_DESCRIPTION, "get back");
		}
		public void actionPerformed(ActionEvent e) {
			Usermain searchFrame = new Usermain();
			 searchFrame.setVisible(true);
			 searchFrame.setLocationRelativeTo(null);
			 Userborow.this.dispose();
		}
	}
}
