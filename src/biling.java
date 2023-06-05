package employee.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;
import java.awt.BorderLayout;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class biling extends JFrame implements ActionListener{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new biling();
            }
        });
    }

    JDateChooser dcdob;
    Choice cproductId,cproductName;
    JTextField jtq2,jtq,jtcustomerName,jtcustomerNum;
    int total=0;
    JTable jt;
    JButton bt1,bt2,bill,refresh,saveas;
    DefaultTableModel model;
    JTextArea area;
    boolean flag=false;
    int count = 2;
    final JFileChooser fc = new JFileChooser();

    biling(){
        JLabel customerName=new JLabel("Customer's Name");
        customerName.setBounds(20, 20, 150, 20);
        this.add(customerName);

        jtcustomerName=new JTextField();
        jtcustomerName.setBounds(170, 20, 150, 20);
        this.add(jtcustomerName);

        JLabel customerNum=new JLabel("Contact Number");
        customerNum.setBounds(340, 20, 150, 20);
        this.add(customerNum);

        jtcustomerNum=new JTextField();
        jtcustomerNum.setBounds(500, 20, 150, 20);
        this.add(jtcustomerNum);

        JLabel labeldob = new JLabel("Date ");
        labeldob.setBounds(680, 20, 150, 20);
        this.add(labeldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(740, 20, 150, 20);
        this.add(dcdob);

        JLabel title1=new JLabel("Product Detailes:");
        title1.setBounds(20, 50, 200, 30);
        this.add(title1);

        JLabel lproid=new JLabel("Select Product by ProductId");
        lproid.setBounds(20, 80, 250, 30);
        this.add(lproid);

        cproductId = new Choice();
        cproductId.setBounds(270, 80, 150, 30);
        this.add(cproductId);


        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from product");
            while(rs.next()) {
                cproductId.add(rs.getString("proId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lq=new JLabel("Qauntity");
        lq.setBounds(430,80,100,30);
        this.add(lq);

        jtq=new JTextField();
        jtq.setBounds(530, 80, 100, 30);
        this.add(jtq);

        bt1=new JButton("Add1");
        bt1.setBounds(700, 80, 100, 30);
        this.add(bt1);
        bt1.addActionListener(this);
        
        refresh=new JButton("Refresh");
        refresh.setBounds(800, 80, 100, 30);
        this.add(refresh);
        refresh.addActionListener(this);
        
        saveas=new JButton("SaveAs");
       saveas.setBounds(900, 80, 100, 30);
       this.add(saveas);
       saveas.addActionListener(this);


        JLabel lproName=new JLabel("Select Product by ProductName");
        lproName.setBounds(20, 120, 250, 30);
        this.add(lproName);

        cproductName = new Choice();
        cproductName.setBounds(270, 120, 150, 30);
        this.add(cproductName);



        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from product");
            while(rs.next()) {
                cproductName.add(rs.getString("proName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        JLabel lq2=new JLabel("Qauntity");
        lq2.setBounds(430,120,100,30);
        this.add(lq2);

        jtq2=new JTextField();
        jtq2.setBounds(530, 120, 100, 30);
        this.add(jtq2);

         bt2=new JButton("Add2");
        bt2.setBounds(700, 120, 100, 30);
        this.add(bt2);
        bt2.addActionListener(this);

        String[] columns={"ProductId","ProductName","Qauntity","Price","Total"};
        String[][] data={};
        model=new DefaultTableModel(data, columns);

        jt=new JTable(model);
        JScrollPane jsp=new JScrollPane(jt);
        this.add(jsp);
        jsp.setBounds(20, 200, 600, 500);


        area=new JTextArea();
        JScrollPane jspta=new JScrollPane(area);
        this.add(jspta);
        jspta.setBounds(700, 200, 350, 500);



        bill=new JButton("Bill", null);
        bill.addActionListener(this);
        this.add(bill);
        bill.setBounds(800, 120, 100, 30);



        this.setLayout(null);
        this.setTitle("Biling System");
        this.setVisible(true);
        this.setSize(1100, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==bt1){
            String productId=cproductId.getSelectedItem();
            String proname="abc",price="0",originalQaun="0";
            if(jtq.getText().length()==0){
                JOptionPane.showMessageDialog(null,"Please Enter the Qauntity..");
                return;
            }
            try{
                Conn conn = new Conn();
                String query="select * from product where proId='"+productId+"'";
                System.out.println("hello");
                ResultSet rs= conn.s.executeQuery(query);

                while(rs.next()){
                    proname=rs.getString("proName");
                    price=rs.getString("price");
                    originalQaun=rs.getString("qauntity");
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Database Connectivity is not established...");
                e.printStackTrace();
            }
            
            int intPrice=Integer.parseInt(price);
            int intqan=Integer.parseInt(jtq.getText());
            
            int intoriginalQaun=Integer.parseInt(originalQaun);
            if(intoriginalQaun<intqan){
                JOptionPane.showMessageDialog(null,"You've Entered the Qauntity higher than "+intoriginalQaun+"..");
                return;
            }
            total=total+intPrice*intqan;
            int updatedQaun=intoriginalQaun-intqan;
            System.out.println(productId+" "+proname+" "+jtq.getText()+" "+price+" "+total);

            model.addRow(new Object[]{
                productId,
                proname,
                jtq.getText(),
                price,
                total
            });

            

            try{
                Conn conn = new Conn();
        
                String query="update product set qauntity='"+updatedQaun+""+"' where proId='"+productId+"'";

                conn.s.executeUpdate(query);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Database Connectivity is not established...");
                e.printStackTrace();
            }
            
    }
        if(ae.getSource()==bt2){
            String productName=cproductName.getSelectedItem();
            String proId="abc",price="0",originalQaun="0";
            if(jtq2.getText().length()==0){
                JOptionPane.showMessageDialog(null,"Please Enter the Qauntity..");
                return;
            }
            try{
                Conn conn = new Conn();
                String query="select * from product where proName='"+productName+"'";
                System.out.println("hello");
                ResultSet rs= conn.s.executeQuery(query);

                while(rs.next()){
                    proId=rs.getString("proId");
                    price=rs.getString("price");
                    originalQaun=rs.getString("qauntity");
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Database Connectivity is not established...");
                e.printStackTrace();
            }
            
            int intPrice=Integer.parseInt(price);
            int intqan=Integer.parseInt(jtq2.getText());
            
            int intoriginalQaun=Integer.parseInt(originalQaun);
            if(intoriginalQaun<intqan){
                JOptionPane.showMessageDialog(null,"You've Entered the Qauntity higher than "+intoriginalQaun+"..");
                return;
            }
            total=total+intPrice*intqan;
            int updatedQaun=intoriginalQaun-intqan;
            System.out.println(proId+" "+productName+" "+jtq2.getText()+" "+price+" "+total);

            model.addRow(new Object[]{
                proId,
                productName,
                jtq2.getText(),
                price,
                total
            });

            

            try{
                Conn conn = new Conn();
        
                String query="update product set qauntity='"+updatedQaun+""+"' where proName='"+productName+"'";
                conn.s.executeUpdate(query);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Database Connectivity is not established...");
                e.printStackTrace();
            }
            
        }
        
        if(ae.getSource()==bill){
            System.out.println("bill button is pressed");
            flag=true;
            if(jtcustomerName.getText().length()==0){
                JOptionPane.showMessageDialog(null,"Please Enter the Customer Name..");
                return;
            }
            if(jtcustomerNum.getText().length()==0){
                JOptionPane.showMessageDialog(null,"Please Enter the Contact Detailes...");
                return;
            }
            
            area.setText(area.getText()+"************************************************************\n");
            // area.setText(area.getText()+"***************************************************\n");
            area.setText(area.getText()+"**************************AJ SUPER MART*********************\n");
            // area.setText(area.getText()+"***************************************************\n");
            area.setText(area.getText()+"************************************************************\n");
            area.setText(area.getText()+"Customer's Name :"+jtcustomerName.getText()+"\n");
            area.setText(area.getText()+"Contact Number  :"+jtcustomerNum.getText()+"\n");
            area.setText(area.getText()+"Date            :"+((JTextField) dcdob.getDateEditor().getUiComponent()).getText()+"\n\n\n");

            model=(DefaultTableModel)  jt.getModel();
            
            area.setText(area.getText()+"ProductId"+"\t\t"+"ProductName"+"\t\t"+"Qauntity"+"\t\t"+"Price"+"\t\t"+"Total\n");

            for(int i=0;i<jt.getRowCount();i++){
                String proid=jt.getValueAt(i, 0).toString();
                String proname=jt.getValueAt(i, 1).toString();
                String qantity=jt.getValueAt(i, 2).toString();
                String price=jt.getValueAt(i, 3).toString();
                String Total=jt.getValueAt(i, 4).toString();

                area.setText(area.getText()+proid+"\t\t"+proname+"\t\t"+qantity+"\t\t"+price+"\t\t"+Total+"\n");


            }
            area.setText(area.getText()+"\n************************************************\n");
            area.setText(area.getText()+"\n************THANK YOU FOR SHOPPING**************\n");
            area.setText(area.getText()+"\n************************************************\n");
        }   

        if(ae.getSource()==refresh){
            jtcustomerName.setText("");
            jtcustomerNum.setText("");
            jtq.setText("");
            jtq2.setText("");
            area.setText("");

            int rows = jt.getRowCount();
            model=(DefaultTableModel)  jt.getModel();
            for(int i=0;i<rows;i++){
                model.removeRow(i);
            }

            
            

        }

        if(ae.getSource()==saveas){
            if(flag==false){
                JOptionPane.showMessageDialog(null,"PLease Generate bill first..");
                return;
            }

            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text File", "txt");
            final JFileChooser saveAsFileChooser = new JFileChooser();
            saveAsFileChooser.setApproveButtonText("Save");
            saveAsFileChooser.setFileFilter(extensionFilter);
            int actionDialog = saveAsFileChooser.showOpenDialog(this);
            if (actionDialog != JFileChooser.APPROVE_OPTION) {
               return;
            }
      
            // !! File fileName = new File(SaveAs.getSelectedFile() + ".txt");
            File file = saveAsFileChooser.getSelectedFile();
            if (!file.getName().endsWith(".txt")) {
               file = new File(file.getAbsolutePath() + ".txt");
            }
      
            BufferedWriter outFile = null;
            try {
               outFile = new BufferedWriter(new FileWriter(file));
      
               area.write(outFile);
      
            } catch (IOException ex) {
               ex.printStackTrace();
            } finally {
               if (outFile != null) {
                  try {
                     outFile.close();
                  } catch (IOException e) {}
               }
            }
        }
}
}
