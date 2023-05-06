package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Login extends JFrame implements ActionListener{
    
    JTextField tfusername ;
    JPasswordField tfpassword;
    JButton login,signup;
    Login() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 30);
        add(lblusername);
        
        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 30);
        add(tfusername);
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 30);
        add(lblpassword);
        
        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 30);
        add(tfpassword);
        
         login = new JButton("LOGIN");
        login.setBounds(150, 140, 150, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
         signup=new JButton("Sign-Up");
        signup.setBounds(150, 200, 150, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);
        
        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae) {
        String username = tfusername.getText();
        char[] password = tfpassword.getPassword();
        String test="";
        for(int i=0;i< password.length;i++){
            test+=password[i];
        }

        if(ae.getSource()==login){
       
        try {
           
            System.out.println(username+" and "+test);
            if(username.length()==0){
                JOptionPane.showMessageDialog(null,"Please Enter username");
                return;
            }

            if(test==""){
                JOptionPane.showMessageDialog(null,"Please Enter password");
                return;
            }

            if(username.length()>20){
                JOptionPane.showMessageDialog(null,"Username is too long");
                return;
            }
            if(test.length()>20){
                JOptionPane.showMessageDialog(null,"Password is too long");
                return;
            }

            String upperCaseChars = "(.*[A-Z].*)";
            if (!test.matches(upperCaseChars ))
            {
                    System.out.println("Password must have atleast one uppercase character");
                    JOptionPane.showMessageDialog(null,"Password must have atleast one uppercase character");
                    return;
            }
            String lowerCaseChars = "(.*[a-z].*)";
            if (!test.matches(lowerCaseChars ))
            {
                    System.out.println("Password must have atleast one lowercase character");
                    JOptionPane.showMessageDialog(null,"Password must have atleast one lowercase character");
                    return;
            }
            String numbers = "(.*[0-9].*)";
            if (!test.matches(numbers ))
            {
                    System.out.println("Password must have atleast one number");
                    JOptionPane.showMessageDialog(null,"Password must have atleast one number");
                    return;
            }
            String specialChars = "(.*[@,#,$,%].*$)";
            if (!test.matches(specialChars ))
            {
                    System.out.println("Password must have atleast one special character among @#$%");
                    JOptionPane.showMessageDialog(null,"Password must have atleast one special character among @#$%");
                    return;
            }
            Conn c = new Conn();
            String query = "select * from login where username = '"+username+"' and password = '"+test+"'";
            
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                setVisible(false);
                new afterlogin(username,test);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
                tfusername.setText("");
                tfpassword.setText("");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Database Connectivity is not established..");
            e.printStackTrace();
        }
    }
        if(ae.getSource()==signup){
            this.setVisible(false);
            new signup();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new Login();
            }
        });
    }
}
