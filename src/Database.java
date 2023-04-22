import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Database {
	Connection con;
	Statement s;
	PreparedStatement ps;
	ResultSet rs;
    public boolean isChange;
	
    public Database() {
        connect();
    }
	
    public void connect() {
    	try {
			Class.forName("com.mysql.cj.jbdc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	String url = "jdbc:mysql://localhost:3306/finalprojectlnt";
    	String userName = "root";
    	String password = "";
    	
    	try {
			con = DriverManager.getConnection(url, userName, password);
	    	s = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void insert(Menu menu) {
    	try {
			ps = con.prepareStatement("INSERT INTO `tablemenu`(`Kode Menu`, `Nama Menu`, `Harga Menu`, `Stok Menu`) VALUES (?, ?, ?, ?)");
	    	ps.setString(1, menu.getKode());
	    	ps.setString(2, menu.getNama());
	    	ps.setInt(3, menu.getHarga());
	    	ps.setInt(4, menu.getStok());
	    	ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void update(Menu menu) {
    	isChange = false;
    	try {
    		ps = con.prepareStatement("UPDATE `tablemenu` SET `Harga Menu`=?, `Stok Menu`=? WHERE `Kode Menu`=?");
	    	ps.setInt(1, menu.getHarga());
	    	ps.setInt(2, menu.getStok());
	    	ps.setString(3,menu.getKode());
	        int rowsUpdated = ps.executeUpdate();
	        if (rowsUpdated == 0) JOptionPane.showMessageDialog(null, "Kode Menu Tidak Ditemukan.");
	        else isChange = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public void delete(Menu menu) {
    	isChange = false;
    	try {
            ps = con.prepareStatement("DELETE FROM `tablemenu` WHERE `Kode Menu`=?");
            ps.setString(1,menu.getKode());
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted == 0) JOptionPane.showMessageDialog(null, "Kode Menu Tidak Ditemukan.");
            else isChange = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Menu> getAllMenu() {
        List<Menu> menuList = new ArrayList<Menu>();
        try {
            rs = s.executeQuery("SELECT * FROM tablemenu");
            while (rs.next()) {
                String kode = rs.getString("Kode Menu");
                String nama = rs.getString("Nama Menu");
                int harga = rs.getInt("Harga Menu");
                int stok = rs.getInt("Stok Menu");
                Menu menu = new Menu(kode, nama, harga, stok);
                menuList.add(menu);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }
}
