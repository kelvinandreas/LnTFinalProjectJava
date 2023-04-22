import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DeleteDataPanel extends JPanel {
	JTextField kodeField;
	Database db = new Database();
	
    public DeleteDataPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        JPanel atas = new JPanel(new GridLayout(1, 2));
        atas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        JLabel kodeLabel = new JLabel("Kode:");
        kodeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        kodeLabel.setHorizontalAlignment(JLabel.CENTER);
        kodeField = new JTextField(10);
        kodeField.setFont(new Font("Arial", Font.PLAIN, 18));
        kodeField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        atas.add(kodeLabel);
        atas.add(kodeField);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(atas, gbc);

        JPanel bawah = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton deleteButton = new JButton("DELETE");
        JButton cancelButton = new JButton("CANCEL");
        bawah.add(deleteButton);
        bawah.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(DeleteDataPanel.this);
                frame.dispose();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (kodeField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Kode belum diisi!");
                } else {
                	delete();
					if(db.isChange) {
                		JOptionPane.showMessageDialog(null, "Data Berhasil Didelete");
                		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(DeleteDataPanel.this);
                        frame.dispose();
                	}
                }
            }
        });
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(bawah, gbc);
    }
    
    public void delete() {
    	String kode = kodeField.getText();
        Menu newMenu = new Menu(kode);
        db.delete(newMenu);
    }
}