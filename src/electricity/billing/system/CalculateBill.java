package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener{
    
    JButton submit, cancel;
    JTextField units;
    JLabel name, address;
    Choice meternumber, month;
    
    CalculateBill(){
        setSize(700, 500);
        setLocation(400, 150);
        
        JPanel p = new JPanel();
        p.setBounds(30, 30, 650, 300);
        p.setBackground(new Color(173, 216, 236));
        p.setLayout(null);
        //p.setForeground(new Color(34, 139, 34));
        add(p);
        
        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(180, 10, 200, 25);
        //heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma",Font.PLAIN, 24));
        p.add(heading);
        
        JLabel lblmeterno = new JLabel("Meter No");
        lblmeterno.setBounds(100, 80, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblmeterno);
        
        meternumber = new Choice();
        try{
            Conn c = new Conn();                
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                meternumber.add(rs.getString("meter_no"));
            }                            
        }catch(Exception e){
            e.printStackTrace();
        }
        meternumber.setBounds(240, 80, 200, 20);
        p.add(meternumber);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100, 120, 100, 20);
        //lblmeterno.setForeground(Color.GRAY);
        //lblmeterno.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblname);
        
        name = new JLabel();
        name.setBounds(240, 120, 100, 20);
        //lblmeterno.setForeground(Color.GRAY);
        //lblmeterno.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(name);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100, 160, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lbladdress);
        
        address = new JLabel("");
        address.setBounds(240, 160, 200, 20);
        p.add(address);
        
        meternumber.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                try{
                    Conn c = new Conn();                
                    ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meternumber.getSelectedItem()+"'");
                    while(rs.next()){
                        name.setText(rs.getString("name"));
                        address.setText(rs.getString("address"));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        
        JLabel lblunits = new JLabel("Units Consumed");
        lblunits.setBounds(100, 200, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblunits);
        
        units = new JTextField();
        units.setBounds(240, 200, 200, 20);
        p.add(units);
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(100, 240, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblmonth);
        
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
        month.setBounds(240, 240, 200, 20);
        p.add(month);
                
        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(120, 350, 100, 25);
        submit.addActionListener(this);
        p.add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(250, 350, 100, 25);
        cancel.addActionListener(this);
        p.add(cancel);
        
        setLayout(new BorderLayout());
        
        add(p, "Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            String meter = meternumber.getSelectedItem();
            String unit = units.getText();
            String smonth = month.getSelectedItem();
            
            int totalbill = 0;
            int unit_consumed = Integer.parseInt(unit);
            
            try{
                Conn c = new Conn();
                String query1 = "select * from tax";
                ResultSet rs = c.s.executeQuery(query1);
                
                while(rs.next()){
                    totalbill += unit_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                    totalbill += Integer.parseInt(rs.getString("meter_rent"));
                    totalbill += Integer.parseInt(rs.getString("service_charge"));
                    totalbill += Integer.parseInt(rs.getString("service_tax"));
                    totalbill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalbill += Integer.parseInt(rs.getString("fixed_tax"));
                } 
            }catch(Exception e){
                e.printStackTrace();
            }

            try{
                Conn c = new Conn();
                String query2 = "insert into bill values('"+meter+"', '"+smonth+"', '"+unit+"', '"+totalbill+"', 'Not Paid')";
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer Bill Updates Successfully Successfully");
                setVisible(false);
            }catch(Exception e){
                e.printStackTrace();
            }
        } else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new CalculateBill();
    }
}