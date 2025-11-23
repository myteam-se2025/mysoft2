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

import modl.Book;
import service.BookService;

public class AdminAddBook extends JFrame {

	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				AdminAddBook frame = new AdminAddBook();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	private JPanel contentPane;
	private JTextField title2;
	private JTextField author2;
	private JTextField isbn2;
	private JTextField category2;

	private JTextField copies2;

	public AdminAddBook() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Add Book");
		lblTitle.setForeground(new Color(202, 128, 53));
		lblTitle.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblTitle.setBounds(76, 25, 200, 20);
		contentPane.add(lblTitle);

		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setForeground(new Color(202, 128, 53));
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(0, 94, 142, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Author");
		lblNewLabel_2.setForeground(new Color(202, 128, 53));
		lblNewLabel_2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(0, 144, 101, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("ISBN");
		lblNewLabel_3.setForeground(new Color(202, 128, 53));
		lblNewLabel_3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setBounds(0, 193, 101, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Category");
		lblNewLabel_4.setForeground(new Color(202, 128, 53));
		lblNewLabel_4.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setBounds(0, 240, 120, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Copies");
		lblNewLabel_5.setForeground(new Color(202, 128, 53));
		lblNewLabel_5.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_5.setBounds(0, 287, 120, 14);
		contentPane.add(lblNewLabel_5);

		title2 = new JTextField();
		title2.setBounds(147, 94, 166, 20);
		contentPane.add(title2);
		title2.setColumns(10);

		author2 = new JTextField();
		author2.setBounds(147, 144, 166, 20);
		contentPane.add(author2);
		author2.setColumns(10);

		isbn2 = new JTextField();
		isbn2.setBounds(147, 193, 166, 20);
		contentPane.add(isbn2);
		isbn2.setColumns(10);

		category2 = new JTextField();
		category2.setBounds(147, 240, 166, 20);
		contentPane.add(category2);
		category2.setColumns(10);

		copies2 = new JTextField();
		copies2.setBounds(147, 287, 166, 20);
		contentPane.add(copies2);
		copies2.setColumns(10);

		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
		btnAddBook.setForeground(new Color(96, 77, 49));
		btnAddBook.setBounds(393, 345, 172, 23);
		contentPane.add(btnAddBook);

		btnAddBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleAddBook();
			}
		});
	}

	private void handleAddBook() {
		String title = title2.getText().trim();
		String author = author2.getText().trim();
		String isbn = isbn2.getText().trim();
		String category = category2.getText().trim();
		int copies;

		try {
			copies = Integer.parseInt(copies2.getText().trim());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Copies must be a valid number.");
			return;
		}

		Book book = new Book(title, author, isbn, category, copies);

		try {
			BookService bookService = new BookService();
			String message = bookService.addBook(book);
			JOptionPane.showMessageDialog(this, message);

			if (message.equals("Book added successfully!")) {
				title2.setText("");
				author2.setText("");
				isbn2.setText("");
				category2.setText("");
				copies2.setText("");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
		}
	}
}