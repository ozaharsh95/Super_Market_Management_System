package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class updateProduct extends JFrame implements ActionListener {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new updateProduct("");
            }
        });
    }


    String productId;
    JTextField tfproid,tfproname,tfqauntity,tfprice,tfcname;
    JComboBox type;
    JButton add, back;

    updateProduct(String productId){
        this.productId=productId;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Product Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        
        JLabel lproid = new JLabel("Product Id");
        lproid.setBounds(50, 150, 150, 30);
        lproid.setFont(new Font("serif", Font.PLAIN, 20));
        add(lproid);
        
        tfproid = new JTextField();
        tfproid.setBounds(200, 150, 150, 30);
        add(tfproid);
        
        JLabel lproname = new JLabel("Product's Name");
        lproname.setBounds(400, 150, 150, 30);
        lproname.setFont(new Font("serif", Font.PLAIN, 20));
        add(lproname);
        
        tfproname = new JTextField();
        tfproname.setBounds(600, 150, 150, 30);
        add(tfproname);

        JLabel labeldob = new JLabel("Type of Product");
        labeldob.setBounds(50, 200, 150, 30);
        labeldob.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldob);
        
        String[] array={"Personal Care","Dairy","Snacks","Beverage"};
        type = new JComboBox(array);
        type.setBounds(200, 200, 150, 30);
        add(type);

        JLabel lqauntity = new JLabel("Qauntity");
        lqauntity.setBounds(400, 200, 150, 30);
        lqauntity.setFont(new Font("serif", Font.PLAIN, 20));
        add(lqauntity);
        
        tfqauntity = new JTextField();
        tfqauntity.setBounds(600, 200, 150, 30);
        add(tfqauntity);


        JLabel labeladdress = new JLabel("Price");
        labeladdress.setBounds(50, 250, 150, 30);
        labeladdress.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeladdress);
        
        tfprice = new JTextField();
        tfprice.setBounds(200, 250, 150, 30);
        add(tfprice);

        JLabel labelphone = new JLabel("Company's Name");
        labelphone.setBounds(400, 250, 150, 30);
        labelphone.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelphone);
        
        tfcname = new JTextField();
        tfcname.setBounds(600, 250, 150, 30);
        add(tfcname);

        try {
            Conn c = new Conn();
            String query = "select * from product where proId = '"+productId+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                tfproid.setText(rs.getString("proId"));
                tfproname.setText(rs.getString("proName"));
                tfqauntity.setText(rs.getString("qauntity"));
                tfprice.setText(rs.getString("price"));
                tfcname.setText(rs.getString("comName"));
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        add = new JButton("Update Details");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);
        
        back = new JButton("Back");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);
        





        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == add) {
            // String proid=tfproid.getText();
            String proname = tfproname.getText();
            String qauntity=tfqauntity.getText();
            String price = tfprice.getText();
            String comname = tfcname.getText();
            String stype=(String) type.getSelectedItem();
            
            
            try {
                Conn conn = new Conn();
                
                String query = "update product set proName ='"+proname+"',qauntity='"+qauntity+"' ,price='"+price+"',comName='"+comname+"',proType='"+stype+"'  where proId='"+productId+"'";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                this.setVisible(false);
                // new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }
}
