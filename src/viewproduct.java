package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;


public class viewproduct extends JFrame implements ActionListener{
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
          public void run(){
              new viewproduct();
          }
      });
    }

    JTable table;
    Choice cproductId;
    JButton search, print, update, back;

    viewproduct(){

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel searchlbl = new JLabel("Search by Employee Id");
        searchlbl.setBounds(20, 20, 250, 20);
        add(searchlbl);
        
        cproductId = new Choice();
        cproductId.setBounds(300, 20, 150, 20);
        add(cproductId);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from product");
            while(rs.next()) {
                cproductId.add(rs.getString("proId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from product");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);

        search = new JButton("Search");
        search.setBounds(20, 70, 120, 20);
        search.addActionListener(this);
        add(search);
        
        print = new JButton("Print");
        print.setBounds(160, 70, 120, 20);
        print.addActionListener(this);
        add(print);
        
        update = new JButton("Update");
        update.setBounds(300, 70, 120, 20);
        update.addActionListener(this);
        add(update);
        
        back = new JButton("Back");
        back.setBounds(440, 70, 120, 20);
        back.addActionListener(this);
        add(back);
        
        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == search) {
            String query = "select * from product where proId = '"+cproductId.getSelectedItem()+"'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            setVisible(false);
            // new UpdateEmployee(cproductId.getSelectedItem());
            new updateProduct(cproductId.getSelectedItem());
        } else {
            setVisible(false);
            new Home();
        }
    }
}
