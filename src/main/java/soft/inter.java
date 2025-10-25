package soft;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Action;

public class inter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name1;
	private JTextField pass1;
	private final Action action = new SwingAction();

	String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "123456";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inter frame = new inter();
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
	public inter() {
		setTitle("welcom");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton interbutton = new JButton("inter");
		interbutton.setAction(action);
		interbutton.setForeground(new Color(139, 69, 19));
		interbutton.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 10));
		interbutton.setBackground(new Color(238, 232, 170));
		interbutton.setBounds(324, 217, 84, 20);
		contentPane.add(interbutton);
		
		name1 = new JTextField();
		name1.setBounds(170, 59, 130, 18);
		contentPane.add(name1);
		name1.setColumns(10);
		
		pass1 = new JTextField();
		pass1.setBounds(170, 99, 130, 18);
		contentPane.add(pass1);
		pass1.setColumns(10);
		
		JLabel name = new JLabel("inter name");
		name.setFont(new Font("Rockwell Condensed", Font.BOLD | Font.ITALIC, 18));
		name.setForeground(new Color(139, 69, 19));
		name.setBounds(52, 50, 108, 29);
		contentPane.add(name);
		
		JLabel lblInterPass = new JLabel("inter pass");
		lblInterPass.setForeground(new Color(139, 69, 19));
		lblInterPass.setFont(new Font("Rockwell Condensed", Font.BOLD | Font.ITALIC, 18));
		lblInterPass.setBounds(52, 90, 108, 29);
		contentPane.add(lblInterPass);

	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			
	    
		}
	}
}
