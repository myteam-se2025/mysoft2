package soft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import modl.Book;
import modl.Fine;
import service.FineService;
import service.PayFineService;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;

public class UserFines extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Action action = new SwingAction();
	private JTextField userID;
	private JTextField fineID;
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private JPanel table_1;


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

		
		JPanel bane1 = new JPanel(); 
		bane1.setBackground(new Color(189, 183, 107));
		bane1.setBounds(10, 297, 778, 225);
		contentPane.add(bane1);
		
		table_1 = new JPanel();
		table_1.setForeground(new Color(139, 69, 19));
		bane1.add(table_1);
		table_1.setLayout(new BorderLayout());
		table_1.setBackground(new Color(143, 188, 143));
		
		
		JButton btnNewButton = new JButton("<--");
		btnNewButton.setAction(action);
		btnNewButton.setBounds(10, 10, 67, 19);
		btnNewButton.setForeground(new Color(85, 107, 47));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBackground(new Color(143, 188, 143));
		contentPane.add(btnNewButton);
		
		userID = new JTextField();
		userID.setForeground(new Color(210, 180, 140));
		userID.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 18));
		userID.setHorizontalAlignment(SwingConstants.CENTER);
		userID.setText("inter your id");
		userID.setBounds(233, 57, 315, 30);
		contentPane.add(userID);
		userID.setColumns(10);
		
		JButton btnNewButton_1_1 = new JButton("show all fines ");
		btnNewButton_1_1.setAction(action_2);
		btnNewButton_1_1.setBackground(new Color(210, 180, 140));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setForeground(new Color(143, 188, 143));
		btnNewButton_1_1.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		btnNewButton_1_1.setBounds(578, 273, 153, 20);
		contentPane.add(btnNewButton_1_1);
		
		fineID = new JTextField();
		fineID.setHorizontalAlignment(SwingConstants.CENTER);
		fineID.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 18));
		fineID.setForeground(new Color(210, 180, 140));
		fineID.setText("inter fine id to pay");
		fineID.setBounds(291, 176, 193, 24);
		contentPane.add(fineID);
		fineID.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("pay");
		btnNewButton_2.setAction(action_1);
		btnNewButton_2.setBackground(new Color(210, 180, 140));
		btnNewButton_2.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_2.setForeground(new Color(143, 188, 143));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(269, 146, 239, 20);
		contentPane.add(btnNewButton_2);
		
		
		

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
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Pay and return book");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			String fineid = fineID.getText().trim();
			String userid = userID.getText().trim();
			
			PayFineService f = new PayFineService();
			String massage = f.payfine(userid , fineid);
			
			 JOptionPane.showMessageDialog(UserFines.this, massage);
			
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "see recorde");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
		String userid = userID.getText().trim();
			FineService  fineservise = new FineService ();
			List<Fine> fines =fineservise.findeAlluserfines(userid);
			
			 makeatablelist(fines);
			
			 
		}
	}
	
	
	public void makeatablelist(List<Fine> fines) {
		
		if (fines != null && !fines.isEmpty()) {
	    	
	        String[] columnNames = {"fineid", "loanid", "amount", "isuee_date", "status"};

	        Object[][] data = new Object[fines.size()][5];

	        // Fill the table rows
	        for (int i = 0; i < fines.size(); i++) {
	            Fine f = fines.get(i);
	            data[i][0] = f.getFineId();
	            data[i][1] = f.getLoanId();
	            data[i][2] = f.getAmount();
	            data[i][3] = f.getDateIssued();
	            data[i][4] = f.getstatus();
	        }
	        JTable table = new JTable(data, columnNames);
	        JScrollPane scrollPane = new JScrollPane(table);

	        
	        
	        
	        table_1.removeAll();
	        table_1.setLayout(new BorderLayout());
	        table_1.add(scrollPane, BorderLayout.CENTER);
	        table_1.revalidate();
	        table_1.repaint();
	    } else {
	        JOptionPane.showMessageDialog(
	             UserFines.this,
	            "No books found!",
	            "Search",
	            JOptionPane.WARNING_MESSAGE
	        );
	    }
	}
}
