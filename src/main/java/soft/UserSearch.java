
package soft;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modl.Book;
import service.BookService;
import service.FindeBooks;
import service.FindeByAuthor;
import service.FindeById;
import service.FindeByTitle;

public class UserSearch extends JFrame {

    private static final long serialVersionUID = 1L;

    protected JPanel contentPane;
    protected JPanel booktable;
    protected JTextField theauthor;
    protected JTextField theisbn;
    protected JTextField thetitle;

    protected final Action actionBack = new SwingActionBack();
    protected final Action actionByAuthor = new SwingActionByAuthor();
    protected final Action actionById = new SwingActionById();
    protected final Action actionByTitle = new SwingActionByTitle();
    protected final Action actionShowAll = new SwingActionShowAll();

    // SwingActions
    protected class SwingActionBack extends AbstractAction {
        public SwingActionBack() {
            putValue(NAME, "<--");
            putValue(SHORT_DESCRIPTION, "Go back to main menu");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            UserMain mainFrame = createUserMain();
            mainFrame.setVisible(true);
            mainFrame.setLocationRelativeTo(null);
            dispose();
        }
    }

    protected class SwingActionByAuthor extends AbstractAction {
        public SwingActionByAuthor() {
            putValue(NAME, "search by author");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String author = theauthor.getText().trim();
            FindeBooks finder = new FindeBooks();
            finder.setStratgy(new FindeByAuthor());
            List<Book> books = finder.findeBook(author);
            makeatablelist(books);
        }
    }

    protected class SwingActionById extends AbstractAction {
        public SwingActionById() {
            putValue(NAME, "search by id");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = theisbn.getText().trim();
            FindeBooks finder = new FindeBooks();
            finder.setStratgy(new FindeById());
            List<Book> books = finder.findeBook(id);
            makeatablelist(books);
        }
    }

    protected class SwingActionByTitle extends AbstractAction {
        public SwingActionByTitle() {
            putValue(NAME, "search by title");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = thetitle.getText().trim();
            FindeBooks finder = new FindeBooks();
            finder.setStratgy(new FindeByTitle());
            List<Book> books = finder.findeBook(title);
            makeatablelist(books);
        }
    }

    protected class SwingActionShowAll extends AbstractAction {
        public SwingActionShowAll() {
            putValue(NAME, "show all books");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                BookService b = new BookService();
                List<Book> books = b.findeAllBooks();
                makeatablelist(books);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // ----------------------------
    // Constructor
    // ----------------------------
    public UserSearch() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 811, 569);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(143, 188, 143));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Panel for table
        JPanel panel = new JPanel();
        panel.setBackground(new Color(189, 183, 107));
        panel.setBounds(10, 297, 778, 225);
        contentPane.add(panel);

        booktable = new JPanel();
        booktable.setForeground(new Color(139, 69, 19));
        panel.add(booktable);
        booktable.setLayout(new BorderLayout());
        booktable.setBackground(new Color(143, 188, 143));

        // Text fields
        theauthor = new JTextField("      author");
        theauthor.setBounds(97, 49, 142, 18);
        theauthor.setForeground(new Color(85, 107, 47));
        theauthor.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
        contentPane.add(theauthor);

        theisbn = new JTextField("      isbn");
        theisbn.setBounds(97, 92, 142, 18);
        theisbn.setForeground(new Color(85, 107, 47));
        theisbn.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
        contentPane.add(theisbn);

        thetitle = new JTextField("      title");
        thetitle.setBounds(97, 138, 142, 18);
        thetitle.setForeground(new Color(85, 107, 47));
        thetitle.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
        contentPane.add(thetitle);

        // Buttons
        JButton btnBack = new JButton();
        btnBack.setAction(actionBack);
        btnBack.setBounds(10, 10, 76, 18);
        btnBack.setBackground(new Color(143, 188, 143));
        contentPane.add(btnBack);

        JButton byAuthor = new JButton();
        byAuthor.setAction(actionByAuthor);
        byAuthor.setBounds(418, 50, 176, 20);
        contentPane.add(byAuthor);

        JButton byId = new JButton();
        byId.setAction(actionById);
        byId.setBounds(418, 93, 176, 20);
        contentPane.add(byId);

        JButton byTitle = new JButton();
        byTitle.setAction(actionByTitle);
        byTitle.setBounds(418, 139, 176, 20);
        contentPane.add(byTitle);

        JButton showAll = new JButton();
        showAll.setAction(actionShowAll);
        showAll.setBounds(618, 265, 154, 24);
        contentPane.add(showAll);
    }

    // Table
    public void makeatablelist(List<Book> books) {
        if (books != null && !books.isEmpty()) {
            String[] columnNames = { "ISBN", "Author", "Category", "Title", "Copies" };
            Object[][] data = new Object[books.size()][5];

            for (int i = 0; i < books.size(); i++) {
                Book b = books.get(i);
                data[i][0] = b.getIsbn();
                data[i][1] = b.getAuthor();
                data[i][2] = b.getCategory();
                data[i][3] = b.getTitle();
                data[i][4] = b.getAvailable_copies();
            }

            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);

            booktable.removeAll();
            booktable.setLayout(new BorderLayout());
            booktable.add(scrollPane, BorderLayout.CENTER);
            booktable.revalidate();
            booktable.repaint();

        } else {
            JOptionPane.showMessageDialog(this, "No books found!", "Search", JOptionPane.WARNING_MESSAGE);
        }
    }

    // يمكن إعادة تعريفها للاختبارات
    protected UserMain createUserMain() {
        return new UserMain();
    }

    // Getters للأزرار وحقول النص    // ----------------------------
    public JButton getBackButton() { return new JButton(actionBack); }
    public JButton getAuthorButton() { return new JButton(actionByAuthor); }
    public JButton getIsbnButton() { return new JButton(actionById); }
    public JButton getTitleButton() { return new JButton(actionByTitle); }
    public JButton getShowAllButton() { return new JButton(actionShowAll); }

    public JTextField getAuthorTextField() { return theauthor; }
    public JTextField getIsbnTextField() { return theisbn; }
    public JTextField getTitleTextField() { return thetitle; }
}