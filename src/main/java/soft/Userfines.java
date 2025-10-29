package soft;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class Userfines extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Userfines frame = new Userfines();
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
	public Userfines() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			 Userfines.this.dispose();
		}
	}
}
