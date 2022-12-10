package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class GenerateBill extends JFrame implements ActionListener{
    String meter;
    Choice month;
    JButton bill;
    JTextArea area;
    
    GenerateBill(String meter){
        this.meter = meter;
        
        setSize(500, 800);
        setLocation(550, 30);
        
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        //panel.setBounds(30, 30, 650, 300);
        add(panel);
        
        JLabel heading = new JLabel("Generate Bill");
        JLabel meternumber = new JLabel(meter);
        
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
        
        area= new JTextArea(50, 15);
        area.setText("\n\n\t--------Click on the--------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month");
        area.setFont(new Font("Senserif", Font.ITALIC, 18));
        
        JScrollPane pane = new JScrollPane(area);
        
        bill = new JButton("Generate Bill");
        bill.setBackground(Color.BLACK);
        bill.setForeground(Color.WHITE);
        bill.addActionListener(this);
        
        panel.add(heading);
        panel.add(meternumber);
        panel.add(month);
        add(panel, "North");
        
        add(pane, "Center");
        add(bill, "South");
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        try{
            Conn c = new Conn();   
            
            String smonth = month.getSelectedItem();
            
            area.setText("\tReliance Power Limited\n  ELECTRICITY BILL GENERATED FOR THE MONTH\n\tOF "+smonth+", 2022\n\n\n");
            
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no ='"+meter+"'");
            while(rs.next()){
                area.append("\n    Customer Name: " + rs.getString("name"));
                area.append("\n    Meter Number    : " + rs.getString("meter_no"));
                area.append("\n    Address              : " + rs.getString("address"));
                area.append("\n    City                      : " + rs.getString("city"));
                area.append("\n    State                    : " + rs.getString("state"));
                area.append("\n    Email                   : " + rs.getString("email"));
                area.append("\n    Phone                 : " + rs.getString("phone"));
                area.append("\n--------------------------------------------------");
                area.append("\n");
            }
            
            rs = c.s.executeQuery("select * from meter_info where meter_no ='"+meter+"'");
            while(rs.next()){
                area.append("\n    Meter Location  : " + rs.getString("meter_location"));
                area.append("\n    Meter Type        : " + rs.getString("meter_type"));
                area.append("\n    Phase Code      : " + rs.getString("phase_code"));
                area.append("\n    Bill Type             : " + rs.getString("bill_type"));
                area.append("\n    Days                   : " + rs.getString("days"));
                area.append("\n--------------------------------------------------");
                area.append("\n");
            }
            
            rs = c.s.executeQuery("select * from tax");
            while(rs.next()){
                area.append("\n");
                area.append("\n    Cost Per Unit                : " + rs.getString("cost_per_unit"));
                area.append("\n    Meter Rent                    : " + rs.getString("meter_rent"));
                area.append("\n    Service Charge            : " + rs.getString("service_charge"));
                area.append("\n    Service Tax                  : " + rs.getString("service_tax"));
                area.append("\n    Swacch_Bharat_Cess: " + rs.getString("swacch_bharat_cess"));
                area.append("\n    Fixed Tax                     : " + rs.getString("fixed_tax"));
                area.append("\n--------------------------------------------------");
                area.append("\n");
            }
            
            rs = c.s.executeQuery("select * from bill where meter_no ='"+meter+"' and month='"+smonth+"'");
            while(rs.next()){
                area.append("\n    Current Month         : " + rs.getString("month"));
                area.append("\n    Units Consumed     : " + rs.getString("units"));
                area.append("\n    Total Charges         : " + rs.getString("totalbill"));
                area.append("\n--------------------------------------------------");
                area.append("\n    Total Payable         : " + rs.getString("totalbill"));
                area.append("\n--------------------------------------------------");
                area.append("\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new GenerateBill("");
    }
}
