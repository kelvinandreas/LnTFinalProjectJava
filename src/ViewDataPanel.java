import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class ViewDataPanel extends JPanel {
    private Database db = new Database();
    private List<Menu> menuList = db.getAllMenu();
    private Object[][] data;
    private JTable table;

    public ViewDataPanel() {
        initData();
        initUI();
    }

    private void initData() {
        data = new Object[menuList.size()][4];
        int i = 0;
        for (Menu menu : menuList) {
            data[i][0] = menu.getKode();
            data[i][1] = menu.getNama();
            data[i][2] = menu.getHarga();
            data[i][3] = menu.getStok();
            i++;
        }
    }
    
    private void initUI() {
        table = new JTable(data, new Object[]{"Kode Menu","Nama Menu", "Harga Menu", "Stok Menu"});
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 200));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(50, 30));
        panel.add(buttonPanel, BorderLayout.PAGE_END);
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ViewDataPanel.this);
                frame.dispose();
            }
        });
        buttonPanel.add(closeButton);
        this.add(panel);
    }
}
