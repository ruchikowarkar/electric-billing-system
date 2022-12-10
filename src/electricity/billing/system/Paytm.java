package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener{
    String meter;
    JButton back;
    
    Paytm(String meter){
        this.meter = meter;
        
        setSize(800, 600);
        setLocation(400, 150);
        
        JEditorPane j = new JEditorPane();
        j.setEditable(false);
        try{
            j.setPage("https://paytm.com/online-payments");
        }catch(Exception e){
            j.setContentType("text/html");
            j.setText("<html>Could not load<html>");
        }
        
        JScrollPane sp = new JScrollPane(j);
        add(sp);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(640, 20, 80, 25);
        back.addActionListener(this);
        j.add(back);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new PayBill(meter);
    }
    
    public static void main(String[] args) {
        new Paytm("");
    }
}
