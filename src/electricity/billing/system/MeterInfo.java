package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MeterInfo extends JFrame implements ActionListener{
    
    JButton submit;
    Choice meterlocation, metertype, phasecode, billtype;
    String meternumber;
    
    MeterInfo(String meternumber){
        this.meternumber = meternumber;
        
        setSize(700, 500);
        setLocation(400, 200);
        
        JPanel p = new JPanel();
        p.setBounds(30, 30, 650, 300);
        p.setBackground(new Color(173, 216, 236));
        p.setLayout(null);
        //p.setForeground(new Color(34, 139, 34));
        add(p);
        
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 25);
        //heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma",Font.PLAIN, 24));
        p.add(heading);
        
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(100, 80, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblmeternumber);
        
        JLabel lblmeterno = new JLabel(meternumber);
        lblmeterno.setBounds(240, 80, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblmeterno);
        
        JLabel lblmeterloc = new JLabel("Meter Location");
        lblmeterloc.setBounds(100, 120, 100, 20);
        //lblmeterno.setForeground(Color.GRAY);
        //lblmeterno.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblmeterloc);
        
        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240, 120, 100, 20);
        //lblmeterno.setForeground(Color.GRAY);
        //lblmeterno.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(meterlocation);
        
        JLabel lblmetertype = new JLabel("Meter Type");
        lblmetertype.setBounds(100, 160, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblmetertype);
        
        metertype = new Choice();
        metertype.add("Electric");
        metertype.add("Solar");
        metertype.add("Smart");
        metertype.setBounds(240, 160, 100, 20);
        //lblmeterno.setForeground(Color.GRAY);
        //lblmeterno.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(metertype);
        
        JLabel lblphasecode = new JLabel("Phase Code");
        lblphasecode.setBounds(100, 200, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblphasecode);
        
        phasecode = new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");        
        phasecode.setBounds(240, 200, 100, 20);
        //lblmeterno.setForeground(Color.GRAY);
        //lblmeterno.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(phasecode);
        
        JLabel lblbilltype = new JLabel("Bill Type");
        lblbilltype.setBounds(100, 240, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblbilltype);
        
        billtype = new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(240, 240, 100, 20);
        //lblmeterno.setForeground(Color.GRAY);
        //lblmeterno.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(billtype);
        
        JLabel lbldays = new JLabel("Days");
        lbldays.setBounds(100, 280, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lbldays);
        
        JLabel days = new JLabel("30 Days");
        days.setBounds(240, 280, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(days);
        
        JLabel lblnote = new JLabel("Note");
        lblnote.setBounds(100, 320, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblnote);
        
        JLabel note = new JLabel("By Default bill is calulated for 30 days only");
        note.setBounds(240, 320, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(note);
        
        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(120, 390, 100, 25);
        submit.addActionListener(this);
        p.add(submit);
        
        setLayout(new BorderLayout());
        
        add(p, "Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            String meter = meternumber;
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typebill = billtype.getSelectedItem();
            String days = "30";
            
            try{
                Conn c = new Conn();
                String query = "insert into meter_info values('"+meter+"', '"+location+"', '"+type+"', '"+code+"', '"+typebill+"', '"+days+"')";
                
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                
                setVisible(false);
                
                //new frame
                
            }catch(Exception e){
                e.printStackTrace();
            }
        } 
    }
    
    public static void main(String[] args) {
        new MeterInfo("");
    }
}
