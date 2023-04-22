import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class InsertDataPanel extends JPanel {
	JTextField nameField;
	JTextField hargaField;
	JTextField qtyField;
	Database db = new Database();
	
    public InsertDataPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel kiri = new JPanel(new GridLayout(3, 1));
        JLabel nameLabel = new JLabel("Nama   : ");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel hargaLabel = new JLabel("Harga   : ");
        hargaLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        hargaLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel qtyLabel = new JLabel("Stok      : ");
        qtyLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        qtyLabel.setHorizontalAlignment(JLabel.CENTER);
        kiri.add(nameLabel);
        kiri.add(hargaLabel);
        kiri.add(qtyLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.LINE_END;
        this.add(kiri, gbc);

        JPanel kanan = new JPanel(new GridLayout(3, 1));
        nameField = new JTextField(10);
        nameField.setFont(new Font("Arial", Font.PLAIN, 22));
        hargaField = new JTextField(10);
        hargaField.setFont(new Font("Arial", Font.PLAIN, 22));
        qtyField = new JTextField(10);
        qtyField.setFont(new Font("Arial", Font.PLAIN, 22));
        kanan.add(nameField);
        kanan.add(hargaField);
        kanan.add(qtyField);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.ipadx = 50;
        this.add(kanan, gbc);

        JPanel bawah = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton sendButton = new JButton("SEND");
        JButton cancelButton = new JButton("CANCEL");
        bawah.add(sendButton);
        bawah.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(InsertDataPanel.this);
                frame.dispose();
            }
        });
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().isEmpty() || hargaField.getText().isEmpty() || qtyField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Data belum diisi!");
                } else {
                	add();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Diinsert");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(InsertDataPanel.this);
                    frame.dispose();
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(bawah, gbc);
    }
    
    public void add() {
        String name = nameField.getText();
        int harga = Integer.parseInt(hargaField.getText());
        int stok = Integer.parseInt(qtyField.getText());
        String kode = "PD-" + (int)(Math.random() * 1000);
        Menu newMenu = new Menu(kode, name, harga, stok);
        db.insert(newMenu);
    }
}
