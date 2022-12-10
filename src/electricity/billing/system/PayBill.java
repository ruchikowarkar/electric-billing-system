package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class PayBill extends JFrame implements ActionListener{
    
    Choice month;
    String meter;
    JButton pay, back;
    
    PayBill(String meter){
        this.meter = meter;
        
        setLayout(null);
        setBounds(300, 150, 900, 600);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Electricity Bill");
        heading.setBounds(120, 5, 400, 30);
        heading.setFont(new Font("Tahoma",Font.BOLD, 24));
        add(heading);
        
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(35, 80, 200, 20);
        add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(300, 80, 200, 20);
        add(meternumber);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 140, 200, 20);
        add(lblname);
        
        JLabel name = new JLabel("");
        name.setBounds(300, 140, 200, 20);
        add(name);
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35, 200, 200, 20);
        add(lblmonth);
        
        month = new Choice();
        month.add("January");
        month.add("February");
        month.add("March");
        month.add("April");
        month.add("May");
        month.add("June");
        month.add("July");
        month.add("August");
        month.add("September");
        month.add("October");
        month.add("November");
        month.add("December");
        month.setBounds(300, 200, 200, 20);
        add(month);
        
        JLabel lblunits = new JLabel("Units");
        lblunits.setBounds(35, 260, 200, 20);
        add(lblunits);
        
        JLabel units = new JLabel("");
        units.setBounds(300, 260, 200, 20);
        add(units);
        
        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35, 320, 200, 20);
        add(lbltotalbill);
        
        JLabel totalbill = new JLabel("");
        totalbill.setBounds(300, 320, 200, 20);
        add(totalbill);
        
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(35, 380, 200, 20);
        add(lblstatus);
        
        JLabel status = new JLabel("");
        status.setBounds(300, 380, 200, 20);
        status.setForeground(Color.RED);
        add(status);
        
        try{
            Conn c = new Conn();                
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no ='"+meter+"'");
            while(rs.next()){
                name.setText(rs.getString("name"));
                meternumber.setText(meter);
            }
            rs = c.s.executeQuery("select * from bill where meter_no ='"+meter+"' and month = '"+month.getSelectedItem()+"'");
            while(rs.next()){
                units.setText(rs.getString("units"));
                totalbill.setText(rs.getString("totalbill"));
                status.setText(rs.getString("status"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        month.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ae){
                try{
                    Conn c = new Conn();                
                    ResultSet rs = c.s.executeQuery("select * from bill where meter_no ='"+meter+"' and month = '"+month.getSelectedItem()+"'");
                    while(rs.next()){
                        units.setText(rs.getString("units"));
                        totalbill.setText(rs.getString("totalbill"));
                        status.setText(rs.getString("status"));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        
        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100, 460, 100, 25);
        pay.addActionListener(this);
        add(pay);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230, 460, 100, 25);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 120, 600, 300);
        add(image);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == pay){
            String query = "update bill set status = 'Paid' where meter_no = '"+meter+"' and month = '"+month.getSelectedItem()+"'";
            try{
                Conn c = new Conn();                
                c.s.executeUpdate(query);
            }catch(Exception e){
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);
        } else if(ae.getSource() == back){
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new PayBill("");
    }
}
