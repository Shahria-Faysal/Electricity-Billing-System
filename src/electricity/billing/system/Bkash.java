package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Bkash extends JFrame implements ActionListener {
    
    String meter;
    JButton back;
    
    Bkash(String meter){
        this.meter = meter;
        
        JEditorPane j = new JEditorPane();
        j.setEditable(false);
        
        try {
            j.setPage("https://www.bkash.com/products-services/add-money?gad_source=1&gad_campaignid=21439289226&gbraid=0AAAAA9iiHy6ewaW7Kz8h1XbnNQEsf6abb&gclid=CjwKCAiA3rPKBhBZEiwAhPNFQIM28BMpb4PMuoQ59HZV4JGQvVDgKXTZPQ7F2dnHZ3B3tyZSoyDDvRoCW5kQAvD_BwE");
        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load<html>");          
        }
        
        JScrollPane pane = new JScrollPane(j);
        add(pane);
        
        back = new JButton("Back");
        back.setBounds(640, 20, 80, 30);
        back.addActionListener(this);
        j.add(back);
        
        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);
    }
    
     public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new PayBill(meter);
    }
    
    public static void main(String[] args){
        new Bkash("");
    }
}
