package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class addProduct extends JFrame implements ActionListener{
    
    public static void main(String[] args) {
          SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new addProduct();
            }
        });
    }
    JTextField tfproid,tfproname,tfqauntity,tfprice,tfcname;
    JComboBox type;
    JButton add1;
    addProduct(){
        this.getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Product Detail");
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
        
        add1 = new JButton("Add Details");
        add1.setBounds(250, 350, 150, 40);
        add1.addActionListener(this);
        add1.setBackground(Color.BLACK);
        add1.setForeground(Color.WHITE);
        add(add1);

        
        setSize(900, 700);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==add1){

            String sproid=tfproid.getText();
            String sproname=tfproname.getText();
            String stype=(String) type.getSelectedItem();
            String sqauntity=tfqauntity.getText();
            String sprice=tfprice.getText();
            String scname=tfcname.getText();
            try{
                Conn conn=new Conn();
                String query="insert into product values('"+sproid+"','"+sproname+"','"+stype+"','"+sqauntity+"','"+sprice+"','"+scname+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                new homepro();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
