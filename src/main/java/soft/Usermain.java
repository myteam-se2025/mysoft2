package soft;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class Usermain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usermain frame = new Usermain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// my code 
	/**
	 * Create the frame.
	 */
	public Usermain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to your library space — knowledge starts here.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 28));
		lblNewLabel.setForeground(new Color(139, 69, 19));
		lblNewLabel.setBounds(28, 152, 737, 129);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(119, 136, 153));
		panel.setBounds(0, 0, 797, 73);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("search new books ");
		btnNewButton.setAction(action);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(143, 188, 143));
		btnNewButton.setBounds(36, 10, 209, 37);
		panel.add(btnNewButton);
		
		JButton btnBorow = new JButton("borow");
		btnBorow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBorow.setAction(action_1);//action_1
		btnBorow.setForeground(Color.BLACK);
		btnBorow.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
		btnBorow.setBackground(new Color(143, 188, 143));
		btnBorow.setBounds(302, 10, 197, 37);
		panel.add(btnBorow);
		
		JButton btnFines = new JButton("fines");
		btnFines.setAction(action_2);
		btnFines.setForeground(Color.BLACK);
		btnFines.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
		btnFines.setBackground(new Color(143, 188, 143));
		btnFines.setBounds(544, 10, 197, 37);
		panel.add(btnFines);
 
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Search");
	        putValue(SHORT_DESCRIPTION, "Search for books");
		}
		public void actionPerformed(ActionEvent e) {
			 Usersearch searchFrame = new Usersearch();
			 searchFrame.setVisible(true);
			 searchFrame.setLocationRelativeTo(null);
			 Usermain.this.dispose();
			
		        
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "borrow ");
			putValue(SHORT_DESCRIPTION, "borrow");
		}
		public void actionPerformed(ActionEvent e) {
			
			 Userborow searchFrame = new Userborow();
			 searchFrame.setVisible(true);
			 searchFrame.setLocationRelativeTo(null);
			 Usermain.this.dispose();
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "fines");
			putValue(SHORT_DESCRIPTION, "fines");
		}
		public void actionPerformed(ActionEvent e) {
			 Userfines searchFrame = new Userfines();
			 searchFrame.setVisible(true);
			 searchFrame.setLocationRelativeTo(null);
			 Usermain.this.dispose();
		}
	}
}
