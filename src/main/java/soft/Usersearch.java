package soft;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class Usersearch extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable booktable;
	private JTextField theauthor;
	private JTextField theisbn;
	private JTextField thetitle;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usersearch frame = new Usersearch();
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
	public Usersearch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setForeground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(189, 183, 107));
		panel.setBounds(10, 265, 778, 259);
		contentPane.add(panel);
		
		booktable = new JTable();
		booktable.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 10));
		booktable.setForeground(new Color(139, 69, 19));
		booktable.setBackground(new Color(245, 245, 220));
		panel.add(booktable);
		
		JButton showall = new JButton("show all");
		showall.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		showall.setForeground(new Color(85, 107, 47));
		showall.setBackground(new Color(245, 222, 179));
		showall.setBounds(675, 231, 102, 24);
		contentPane.add(showall);
		
		JButton byauthor = new JButton("searsh by author");
		byauthor.setBackground(new Color(255, 222, 173));
		byauthor.setForeground(new Color(85, 107, 47));
		byauthor.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		byauthor.setBounds(418, 50, 176, 20);
		contentPane.add(byauthor);
		
		JButton byisbn = new JButton("searsh by isbn");
		byisbn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		byisbn.setForeground(new Color(85, 107, 47));
		byisbn.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		byisbn.setBackground(new Color(255, 222, 173));
		byisbn.setBounds(418, 93, 176, 20);
		contentPane.add(byisbn);
		
		JButton bytitle = new JButton("searsh by title");
		bytitle.setForeground(new Color(85, 107, 47));
		bytitle.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		bytitle.setBackground(new Color(255, 222, 173));
		bytitle.setBounds(418, 139, 176, 20);
		contentPane.add(bytitle);
		
		theauthor = new JTextField();
		theauthor.setHorizontalAlignment(SwingConstants.LEFT);
		theauthor.setText("      author");
		theauthor.setForeground(new Color(85, 107, 47));
		theauthor.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
		theauthor.setBounds(97, 49, 142, 18);
		contentPane.add(theauthor);
		theauthor.setColumns(10);
		
		theisbn = new JTextField();
		theisbn.setText("      isbn");
		theisbn.setHorizontalAlignment(SwingConstants.LEFT);
		theisbn.setForeground(new Color(85, 107, 47));
		theisbn.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
		theisbn.setColumns(10);
		theisbn.setBounds(97, 92, 142, 18);
		contentPane.add(theisbn);
		
		thetitle = new JTextField();
		thetitle.setText("      title");
		thetitle.setHorizontalAlignment(SwingConstants.LEFT);
		thetitle.setForeground(new Color(85, 107, 47));
		thetitle.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
		thetitle.setColumns(10);
		thetitle.setBounds(97, 138, 142, 18);
		contentPane.add(thetitle);
		
		JButton btnNewButton = new JButton("<--");
		btnNewButton.setAction(action);
		btnNewButton.setForeground(new Color(85, 107, 47));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBackground(new Color(143, 188, 143));
		btnNewButton.setBounds(10, 10, 76, 18);
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
			 Usersearch.this.dispose();
		}
	}
}
