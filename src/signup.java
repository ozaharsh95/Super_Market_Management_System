package employee.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class signup extends JFrame implements ActionListener{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new signup();
            }
        });
    }

    JComboBox jcb;
    JTextField jtf1,jtf2;
    JButton bt1;



    signup(){
        
        this.setLayout(null);

        JLabel l1=new JLabel("Add Information");
        l1.setBounds(20, 50, 400, 30);
        this.add(l1);


        JLabel l2=new JLabel("Designation");
        l2.setBounds(20,100,150,30);
        this.add(l2);


        String array[]={"Manager","Cashier"};
        jcb=new JComboBox(array);
        jcb.setBounds(200, 100, 150, 30);
        this.add(jcb);


        JLabel l3=new JLabel("Username");
        l3.setBounds(20, 150, 150, 30);
        this.add(l3);


        jtf1=new JTextField();
        jtf1.setBounds(200, 150, 150, 30);
        this.add(jtf1);

        JLabel l4=new JLabel("Password");
        l4.setBounds(20, 200, 150, 30);
        this.add(l4);


        jtf2=new JTextField();
        jtf2.setBounds(200, 200, 150, 30);
        this.add(jtf2);


        bt1=new JButton("Submit");
        bt1.setBounds(70, 300, 150, 30);
        this.add(bt1);
        bt1.addActionListener(this);



        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==bt1){
            String susername=jtf1.getText();
            String spassword=jtf2.getText();
            String sdesignation=(String) jcb.getSelectedItem();
            String username=susername;
            String test=spassword;

            try{

                if(username.length()==0){
                    JOptionPane.showMessageDialog(null,"Please Enter username");
                    return;
                }
    
                if(test.length()==0){
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
                Conn conn=new Conn();
                String query="insert into login values('"+susername+"','"+spassword+"','"+sdesignation+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                new Login();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
