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

import modl.Cd;
import service.CdService;

public class AddCd extends JFrame {

    private static final long serialVersionUID = 1L;

    public boolean testingMode = false;

    private CdService cdServiceForTest = null;

    public void setCdServiceForTest(CdService mock) {
        this.cdServiceForTest = mock;
    }

    private JPanel contentPane;

    protected JTextField title2;
    protected JTextField artist2;
    protected JTextField genre2;
    protected JTextField copies2;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddCd frame = new AddCd();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AddCd() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 811, 569);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(143, 188, 143));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Add CD");
        lblTitle.setForeground(new Color(202, 128, 53));
        lblTitle.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lblTitle.setBounds(76, 25, 123, 14);
        contentPane.add(lblTitle);

        JLabel lbl1 = new JLabel("Title");
        lbl1.setForeground(new Color(202, 128, 53));
        lbl1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lbl1.setBounds(0, 94, 142, 14);
        contentPane.add(lbl1);

        JLabel lbl2 = new JLabel("Artist");
        lbl2.setForeground(new Color(202, 128, 53));
        lbl2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lbl2.setBounds(0, 144, 101, 14);
        contentPane.add(lbl2);

        JLabel lbl3 = new JLabel("Genre");
        lbl3.setForeground(new Color(202, 128, 53));
        lbl3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lbl3.setBounds(0, 193, 101, 14);
        contentPane.add(lbl3);

        JLabel lbl4 = new JLabel("Copies");
        lbl4.setForeground(new Color(202, 128, 53));
        lbl4.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        lbl4.setBounds(0, 240, 91, 14);
        contentPane.add(lbl4);

        title2 = new JTextField();
        title2.setBounds(147, 94, 166, 20);
        contentPane.add(title2);

        artist2 = new JTextField();
        artist2.setBounds(147, 144, 166, 20);
        contentPane.add(artist2);

        genre2 = new JTextField();
        genre2.setBounds(147, 193, 166, 20);
        contentPane.add(genre2);

        copies2 = new JTextField();
        copies2.setBounds(147, 240, 166, 20);
        contentPane.add(copies2);

        JButton btnAddCd = new JButton("Add CD");
        btnAddCd.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 18));
        btnAddCd.setForeground(new Color(96, 77, 49));
        btnAddCd.setBounds(393, 345, 172, 23);
        contentPane.add(btnAddCd);

        btnAddCd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddCd();
            }
        });
    }

    private CdService getCdService() {
        if (cdServiceForTest != null)
            return cdServiceForTest;

        try {
            return new CdService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void handleAddCdForTest() {
        handleAddCd();
    }

    private void handleAddCd() {

        String title = title2.getText().trim();
        String artist = artist2.getText().trim();
        String genre = genre2.getText().trim();
        String copiesText = copies2.getText().trim();

        if (title.isEmpty() || artist.isEmpty() || genre.isEmpty() || copiesText.isEmpty()) {
            if (!testingMode)
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
            return;
        }

        int copies;
        try {
            copies = Integer.parseInt(copiesText);
        } catch (NumberFormatException ex) {
            if (!testingMode)
                JOptionPane.showMessageDialog(this, "Copies must be a valid number.");
            return;
        }

        Cd cd = new Cd(title, artist, genre, copies);

        try {
            CdService service = getCdService();
            String msg = service.addCd(cd);

            if (!testingMode)
                JOptionPane.showMessageDialog(this, msg);

            if (msg.equals("CD added successfully!")) {
                title2.setText("");
                artist2.setText("");
                genre2.setText("");
                copies2.setText("");
            }

        } catch (Exception e) {   // ← ← ← التعديل الوحيد هنا
            if (!testingMode)
                JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }
}