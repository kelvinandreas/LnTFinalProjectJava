import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame{
	Database db = new Database();

    public Main() {
        mainFrame();
    }

    public void mainFrame(){
        titlePanel("MainFrame");
        listButton();
        this.setTitle("PT Pudding");
        this.setLayout(new GridLayout(1,2));
        this.setSize(new Dimension(700, 400));
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocation(600, 200);
        this.setResizable(false);
        this.setVisible(true);
    }

    public Component titlePanel(String typeString){
        JLabel title;
        if(typeString == "MainFrame")title = new JLabel("PT PUDDING");
        else if(typeString == "Insert") title = new JLabel("Insert Data");
        else if(typeString == "View") title = new JLabel("View Data");
        else if(typeString == "Update") title = new JLabel("Update Data");
        else title = new JLabel("Delete Data");
        
        if(typeString == "MainFrame") title.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD,50));
        else title.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD,30));
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(title);
        return add(titlePanel);
    }

    public void listButton(){
        JPanel buttonPanel = new JPanel(new GridLayout(4,1));
        JButton guiInsert = new JButton("Insert Data");
        guiInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame insertFrame = new JFrame("Insert Data");
                InsertDataPanel insertPanel = new InsertDataPanel();
                insertFrame.add(titlePanel("Insert"));
                insertFrame.setLayout(new GridLayout(2,1));
                insertFrame.add(insertPanel);
                insertFrame.setSize(new Dimension(700, 400));
                insertFrame.setLocation(600, 200);
                insertFrame.setResizable(false);
                insertFrame.setVisible(true);
            }
        });
        
        JButton guiView = new JButton("View Data");
        guiView.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    JFrame viewFrame = new JFrame("View Data");
        	    ViewDataPanel viewPanel = new ViewDataPanel();
        	    viewFrame.setLayout(new BorderLayout());
        	    viewFrame.add(titlePanel("View"), BorderLayout.NORTH);
        	    int frameHeight = 400;
        	    int titlePanelHeight = (int)(frameHeight * 0.33);
        	    viewFrame.add(viewPanel, BorderLayout.CENTER);
        	    viewFrame.setSize(new Dimension(700, frameHeight));
        	    viewFrame.setLocation(600, 200);
        	    viewFrame.setResizable(false);
        	    viewFrame.setVisible(true);
        	}

        });
        
        JButton guiUpdate = new JButton("Update Data");
        guiUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame updateFrame = new JFrame("Update Data");
                UpdateDataPanel updatePanel = new UpdateDataPanel();
                updateFrame.add(titlePanel("Update"));
                updateFrame.setLayout(new GridLayout(2,1));
                updateFrame.add(updatePanel);
                updateFrame.setSize(new Dimension(700, 400));
                updateFrame.setLocation(600, 200);
                updateFrame.setResizable(false);
                updateFrame.setVisible(true);
            }
        });
        
        JButton guiDelete = new JButton("Delete Data");
        guiDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame deleteFrame = new JFrame("Delete Data");
                DeleteDataPanel deletePanel = new DeleteDataPanel();
                deleteFrame.add(titlePanel("Delete"));
                deleteFrame.setLayout(new GridLayout(2,1));
                deleteFrame.add(deletePanel);
                deleteFrame.setSize(new Dimension(700, 400));
                deleteFrame.setLocation(600, 200);
                deleteFrame.setResizable(false);
                deleteFrame.setVisible(true);
            }
        });
        
        buttonPanel.add(guiInsert);
        buttonPanel.add(guiView);
        buttonPanel.add(guiUpdate);
        buttonPanel.add(guiDelete);
        add(buttonPanel);
    }

    public static void main(String[] args) {
        new Main();
    }
}