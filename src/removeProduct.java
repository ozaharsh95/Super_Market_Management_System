package employee.management.system;


import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;


public class removeProduct extends JFrame implements ActionListener{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new removeProduct();
            }
        });
    }

    Choice cEmpId;
    JButton delete, back;

    removeProduct(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel labelempId = new JLabel("Product Id");
        labelempId.setBounds(50, 50, 100, 30);
        add(labelempId);
        
        cEmpId = new Choice();
        cEmpId.setBounds(200, 50, 150, 30);
        add(cEmpId);

        try {
            Conn c = new Conn();
            String query = "select * from product";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                cEmpId.add(rs.getString("proId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        


        JLabel labelname = new JLabel("Product Name");
        labelname.setBounds(50, 100, 100, 30);
        add(labelname);
        
        JLabel lblname = new JLabel();
        lblname.setBounds(200, 100, 100, 30);
        add(lblname);
        
        JLabel labelphone = new JLabel("Company's Name");
        labelphone.setBounds(50, 150, 100, 30);
        add(labelphone);
        
        JLabel lblphone = new JLabel();
        lblphone.setBounds(200, 150, 100, 30);
        add(lblphone);
        
        JLabel labelemail = new JLabel("Qauntity");
        labelemail.setBounds(50, 200, 100, 30);
        add(labelemail);
        
        JLabel lblemail = new JLabel();
        lblemail.setBounds(200, 200, 100, 30);
        add(lblemail);

        try {
            Conn c = new Conn();
            String query = "select * from product where proId = '"+cEmpId.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                lblname.setText(rs.getString("proName"));
                lblphone.setText(rs.getString("comName"));
                lblemail.setText(rs.getString("qauntity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        cEmpId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from product where proId = '"+cEmpId.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()) {
                        lblname.setText(rs.getString("proName"));
                        lblphone.setText(rs.getString("comName"));
                        lblemail.setText(rs.getString("qauntity"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100,30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);
        
        back = new JButton("Back");
        back.setBounds(220, 300, 100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 600, 400);
        add(image);

        setSize(1000, 400);
        setLocation(300, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == delete) {
            try {
                Conn c = new Conn();
                String query = "delete from product where proId = '"+cEmpId.getSelectedItem()+"'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Product Information Deleted Sucessfully");
                setVisible(false);
                new homepro();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new homepro();
        }
    }
}
