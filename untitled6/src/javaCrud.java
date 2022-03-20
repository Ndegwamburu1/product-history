import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class javaCrud {
    private JPanel Main;
    private JTextField txtName;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField txtQty;
    private JTextField txtpid;

    private JTextField txtPrice;
    private JButton searchButton;
    private JTextField price;



    public static void main(String[] args) {
        JFrame frame = new JFrame("javaCrud");
        frame.setContentPane(new javaCrud().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public javaCrud() {
        connect();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, price, qty;
                name = txtName.getText();
                price = txtPrice.getText();
                qty = txtQty.getText()
                ;
                try {

                    PreparedStatement pst = con.prepareStatement("insert into products(pname,qty,price)values (?,?,?)");
                    pst.setString(1, name);
                    pst.setString(2, qty);
                    pst.setString(3, price);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "RECORD SAVED SUCCESSFULLY");
                    txtName.setText("");
                    txtQty.setText("");
                    txtPrice.setText("");



                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String pid = txtpid.getText();
                    pst = con.prepareStatement("select pname,price,qty from products where pid = ?");
                    pst.setString(1, pid);
                    ResultSet rs = pst.executeQuery();
                    if (rs.next() == true) {

                        String name = rs.getString(1);
                        String price = rs.getString(2);
                        String qty = rs.getString(3);


                        txtName.setText(name);
                        txtPrice.setText(price);
                        txtQty.setText(qty);
                    } else
                    {

                        txtName.setText("");
                        txtPrice.setText("");
                        txtQty.setText("");
                        JOptionPane.showMessageDialog(null, "invalid entry");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();

                }}


        });




        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pid,name,price,qty;
name=txtName.getText();
price= txtPrice.getText();
qty=txtQty.getText();
pid=txtpid.getText();
try{

    pst = con.prepareStatement("update products set pname=?,price=?,qty=? where pid=?");
            pst.setString(1,name);
    pst.setString(2,price);
    pst.setString(3,qty);
    pst.setString(4,pid);

    pst.executeUpdate();
    JOptionPane.showMessageDialog(null,"record updated");
    txtName.setText("");
    txtPrice.setText("");
    txtQty.setText("");
    txtpid.setText("");
} catch (SQLException ex) {
    ex.printStackTrace();
}
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bid;
                bid= txtpid.getText();
                try{
                    pst=con.prepareStatement("delete from products where pid=?");
                    pst.setString( 1,bid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"record deleted");
                txtName.setText("");
                txtPrice.setText("");
                txtQty.setText("");
                txtpid.setText("");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

}



    Connection con;
    PreparedStatement pst;
    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/GBproducts", "root", "");
            System.out.println("success");
        } catch (ClassNotFoundException ex) {

            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}

