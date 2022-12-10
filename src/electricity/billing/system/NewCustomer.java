package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener{
    
    JButton next, cancel;
    JTextField tfname, tfaddress, tfstate, tfcity, tfemail, tfphone;
    JLabel lblmeter;
    
    NewCustomer(){
        setSize(700, 500);
        setLocation(400, 200);
        
        JPanel p = new JPanel();
        p.setBounds(30, 30, 650, 300);
        p.setBackground(new Color(173, 216, 236));
        p.setLayout(null);
        //p.setForeground(new Color(34, 139, 34));
        add(p);
        
        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 25);
        //heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma",Font.PLAIN, 24));
        p.add(heading);
        
        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(100, 80, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(240, 80, 200, 20);
        p.add(tfname);
        
        JLabel lblmeterno = new JLabel("Meter Number");
        lblmeterno.setBounds(100, 120, 100, 20);
        //lblmeterno.setForeground(Color.GRAY);
        //lblmeterno.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblmeterno);
        
        lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(240, 120, 100, 20);
        //lblmeterno.setForeground(Color.GRAY);
        //lblmeterno.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblmeter);
        
        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        lblmeter.setText("" + Math.abs(number));
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100, 160, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lbladdress);
        
        tfaddress = new JTextField();
        tfaddress.setBounds(240, 160, 200, 20);
        p.add(tfaddress);
        
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(100, 200, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblstate);
        
        tfstate = new JTextField();
        tfstate.setBounds(240, 200, 200, 20);
        p.add(tfstate);
        
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(100, 240, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblcity);
        
        tfcity = new JTextField();
        tfcity.setBounds(240, 240, 200, 20);
        p.add(tfcity);
        
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(100, 280, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(240, 280, 200, 20);
        p.add(tfemail);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(100, 320, 100, 20);
        //lblname.setForeground(Color.GRAY);
        //lblname.setFont(new Font("Tahoma",Font.BOLD, 14));
        p.add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(240, 320, 200, 20);
        p.add(tfphone);
        
        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(120, 390, 100, 25);
        next.addActionListener(this);
        p.add(next);
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(250, 390, 100, 25);
        cancel.addActionListener(this);
        p.add(cancel);
        
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
        if(ae.getSource() == next){
            String name = tfname.getText();
            String meter = lblmeter.getText();
            String address = tfaddress.getText();
            String state = tfstate.getText();
            String city = tfcity.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();
            
            try{
                Conn c = new Conn();
                String query1 = "insert into customer values('"+name+"', '"+meter+"', '"+address+"', '"+state+"', '"+city+"', '"+email+"', '"+phone+"')";
                String query2 = "insert into login values('"+meter+"', '', '"+name+"', '', '')";
                
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                
                setVisible(false);
                
                //new frame
                new MeterInfo(meter);
            }catch(Exception e){
                e.printStackTrace();
            }
        } else if(ae.getSource() == cancel){
            setVisible(false);
           
        }
    }
    
    public static void main(String[] args) {
        new NewCustomer();
    }
}
