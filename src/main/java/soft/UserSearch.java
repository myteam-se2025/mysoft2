package soft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import modl.*;
import service.BookService;
import service.FindeBooks;
import service.FindeByAuthor;
import service.FindeById;
import service.FindeByTitle;
import dao.*;

public class UserSearch extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel booktable;
	private JTextField theauthor;
	private JTextField theisbn;
	private JTextField thetitle;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UserSearch frame = new UserSearch();
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
	public UserSearch() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setForeground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(189, 183, 107));
		panel.setBounds(10, 297, 778, 225);
		contentPane.add(panel);
		
		booktable = new JPanel();
		booktable.setForeground(new Color(139, 69, 19));
		panel.add(booktable);
		booktable.setLayout(new BorderLayout());
		booktable.setBackground(new Color(143, 188, 143));

		JButton showall = new JButton("show all");
		showall.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		showall.setForeground(new Color(85, 107, 47));
		showall.setBackground(new Color(245, 222, 179));
		showall.setBounds(670, 265, 102, 24);
		contentPane.add(showall);

		JButton byauthor = new JButton("searsh by author");
		byauthor.setAction(action_1);
		byauthor.setBackground(new Color(255, 222, 173));
		byauthor.setForeground(new Color(85, 107, 47));
		byauthor.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		byauthor.setBounds(418, 50, 176, 20);
		contentPane.add(byauthor);

		JButton byisbn = new JButton("searsh by isbn");
		byisbn.setAction(action_2);
		byisbn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		byisbn.setForeground(new Color(85, 107, 47));
		byisbn.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 15));
		byisbn.setBackground(new Color(255, 222, 173));
		byisbn.setBounds(418, 93, 176, 20);
		contentPane.add(byisbn);

		JButton bytitle = new JButton("searsh by title");
		bytitle.setAction(action_3);
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
			@Override
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
		@Override
		public void actionPerformed(ActionEvent e) {
			 UserMain searchFrame = new UserMain();
			 searchFrame.setVisible(true);
			 searchFrame.setLocationRelativeTo(null);
			 UserSearch.this.dispose();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "search by author");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		
			
			String author = theauthor.getText().trim() ;
		    FindeBooks bok = new FindeBooks();
			bok.setStratgy(new FindeByAuthor());
            Book mybook = bok.findeBook(author);
			 
            makeatable(mybook);
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "search by id");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
		
			String id = theisbn.getText().trim();
		    FindeBooks bok = new FindeBooks();			
			bok.setStratgy(new FindeById());			
            Book mybook = bok.findeBook(id);
			
            makeatable(mybook);
			
		}
		
		
		
		
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "search by title");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			
			String title = thetitle.getText().trim();
			
			    FindeBooks bok = new FindeBooks();				
				bok.setStratgy(new FindeByTitle());				
	            Book mybook = bok.findeBook(title);
				
	            makeatable(mybook);
				
		}
	}
	
	
	
	
	public void makeatable( Book mybook)
	{
		if (mybook != null) {
		    Object[][] data = {
		        {
		            mybook.getIsbn(),
		            mybook.getAuthor(),
		            mybook.getCategory(),
		            mybook.getTitle(),
		            mybook.getAvailable_copies()
		        }
		    };

		    String[] columnNames = {"isbn", "Author", "category", "Title", "copies"};

		    JTable table = new JTable(data, columnNames);
		    JScrollPane scrollPane = new JScrollPane(table);

		    
		    booktable.removeAll();
		    booktable.setLayout(new BorderLayout());
		    booktable.add(scrollPane, BorderLayout.CENTER);
		    booktable.revalidate();
		    booktable.repaint();


		} else {
		    JOptionPane.showMessageDialog(UserSearch.this, "Book not found!", "Search", JOptionPane.WARNING_MESSAGE);
		}
	}
}
