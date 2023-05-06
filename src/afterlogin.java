package employee.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;



public class afterlogin extends JFrame implements ActionListener{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new afterlogin("","");
            }
        });
    }

    JButton b1,b2,b3,b4;
    String uname="abc",pass="abc";
    afterlogin(String username,String password){

        this.uname=username;
        this.pass=password;
        

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bg2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20, 20, 1000, 1000);
        add(image);


        b1=new JButton("Employee Management", null);
        b1.setBounds(50, 50, 200, 20);
        b2=new JButton("Product Management", null);
        b2.setBounds(250, 50, 200, 20);
        b3=new JButton("Log Out", null);
        b3.setBounds(450, 50, 200, 20);
        b4=new JButton("Billing System", null);
        b4.setBounds(650,50,200,20);
        
        
        
        System.out.println(uname+" "+pass);
        if(Objects.equals(uname, "admin")){
            System.out.println("Yes");
            image.add(b1);
            image.add(b2);
           
        }
        image.add(b3);
        image.add(b4);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){
            this.setVisible(false);
            new Home();
        }else if (ae.getSource()==b2){
            this.setVisible(false);
            new homepro();
        }else if(ae.getSource()==b3){
            // System.exit(0);
            this.dispose();
            new Login();
        }else{
            new biling();
        }
    }
}
