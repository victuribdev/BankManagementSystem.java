package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {
    
    JButton back;
    String pinnumber;
    private String balance; // Instance variable to store balance
    
    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;
        
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, 1);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);
        
        Conn conn = new Conn();
        try {
            java.sql.ResultSet rs = conn.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinnumber + "'");
            int balance = 0; // Local variable to calculate balance
            while (rs.next()) {
                if (rs.getString("Type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            this.balance = "Your Current Account balance is Rs " + balance;
            JLabel text = new JLabel(this.balance);
            text.setForeground(Color.WHITE);
            text.setBounds(170, 300, 400, 30);
            image.add(text);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    // Action performed when back button is clicked
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);        
    }
    
    public static void main(String args[]) {
        new BalanceEnquiry("");
    }
}
