package soft;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import modl.Fine;
import modl.Loan;
import service.BorowService;
import service.LoanService;

public class UserBorow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField bookid;
	private final Action action = new SwingAction();
	private JTextField userid;
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UserBorow frame = new UserBorow();
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
	public UserBorow() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

		bookid = new JTextField();
		bookid.setHorizontalAlignment(SwingConstants.CENTER);
		bookid.setBackground(new Color(143, 188, 143));
		bookid.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 18));
		bookid.setForeground(new Color(85, 107, 47));
		bookid.setText("inter isbn here");
		bookid.setBounds(260, 63, 207, 30);
		panel.add(bookid);
		bookid.setColumns(10);

		JButton btnNewButton = new JButton("borow");
		btnNewButton.setAction(action_1);
		btnNewButton.setBackground(new Color(143, 188, 143));
		btnNewButton.setForeground(new Color(85, 107, 47));
		btnNewButton.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
		btnNewButton.setBounds(593, 181, 95, 20);
		panel.add(btnNewButton);
		
		userid = new JTextField();
		userid.setText("inter your id hear");
		userid.setHorizontalAlignment(SwingConstants.CENTER);
		userid.setForeground(new Color(85, 107, 47));
		userid.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 18));
		userid.setColumns(10);
		userid.setBackground(new Color(143, 188, 143));
		userid.setBounds(260, 109, 207, 30);
		panel.add(userid);

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
		@Override
		public void actionPerformed(ActionEvent e) {
			UserMain searchFrame = new UserMain();
			 searchFrame.setVisible(true);
			 searchFrame.setLocationRelativeTo(null);
			 UserBorow.this.dispose();
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			String book_id = bookid.getText();
			String user_id = userid.getText();
			
			Loan l = null;
			BorowService avalble = new BorowService();
			 l =avalble.bookAvalbltyChack(book_id);
			
			if ( l != null)
			{
				 JOptionPane.showMessageDialog(UserBorow.this, " this book is not avalble");
			}else
			{
				 JOptionPane.showMessageDialog(UserBorow.this, "this book is avalble");
				 Fine f = null;
				  f =avalble.userfinescheck(user_id);
				 
				 if( f != null)
				 {
					 JOptionPane.showMessageDialog(UserBorow.this, " you have fines pay thim to get the book");
						
				 }else 
				 {  
					  l = new Loan(user_id,book_id);
					 LoanService lo = new LoanService();
					 lo.addbookloan(l);
					 JOptionPane.showMessageDialog(UserBorow.this, " user got the book");
						
				 }
 	           
			}
			
			
		}
	}
}
