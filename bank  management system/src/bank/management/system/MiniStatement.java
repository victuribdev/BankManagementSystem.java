package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.ResultSet;

public class MiniStatement extends JFrame{
    
    MiniStatement(String pinnumber){
        setTitle("Mini Statement");
        
        setLayout(null);
        
        // Label for bank name
        JLabel bank = new JLabel("USA Bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);
        
        // Label for card number
        JLabel card = new JLabel();
        card.setBounds(20, 80, 300, 20);
        add(card);
        
        // Label for balance information
        JLabel balance = new JLabel();
        balance.setBounds(20, 400, 300, 20);
        add(balance);
        
        try{
          Conn conn = new Conn();          
          java.sql.ResultSet rs = conn.s.executeQuery("Select * from login where pin = '"+pinnumber+"'");
          while(rs.next()){
              // Displaying partial card number for security
              card.setText("Card Number: "+ rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
          }
        }catch (Exception e){
            System.out.println(e);
        }
        
        try{
            Conn conn = new Conn();
            int bal = 0;
            java.sql.ResultSet rs = conn.s.executeQuery("Select * from bank where pin = '"+pinnumber+"'");
            while(rs.next()){
                // Displaying transaction details
                balance.setText(balance.getText() + "<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                // Calculating current account balance
                if (rs.getString("Type").equals("Deposit")){
                    bal+= Integer.parseInt((String) rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt((String) rs.getString("amount"));
                }
            }
            
            // Displaying current account balance
            balance.setText("Your current account balance is Rs"+ bal);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Setting bounds for transaction details label
        balance.setBounds(20, 140, 400, 200);
        
        // Setting size, location, background color, and visibility of the frame
        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    // Main method to test the MiniStatement class
    public static void main(String args[]) {
        new MiniStatement("");
    }
}
