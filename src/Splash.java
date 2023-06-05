package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Splash extends JFrame implements ActionListener {
    
    JButton clickhere,signup;
    Splash() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bg.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(200, 0, 600, 600);
        add(image);
        
        clickhere = new JButton("Log-In");
        clickhere.setBounds(400, 600, 100, 40);
       
        clickhere.addActionListener(this);

        signup = new JButton("Sign-Up");
        signup.setBounds(600, 600, 100, 40);
       
        signup.addActionListener(this);

        this.add(clickhere);
        this.add(signup);

        
        this.setSize(1000, 1000);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==clickhere){
            setVisible(false);
            new Login();
        }
        if(ae.getSource()==signup){
            setVisible(false);
            new signup();
        }
    }
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new Splash();
            }
        });
    }
}
