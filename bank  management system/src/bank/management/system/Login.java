package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    
    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login() {
        
        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);
        
        // Setting up the bank logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);   
        JLabel label = new JLabel(i3);  
        label.setBounds(70, 10, 100, 100);
        add(label);
        
        // Label for welcome message
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);
         
        // Label and text field for card number
        JLabel cardno = new JLabel("Card No:");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(120, 150, 400, 30);
        add(cardno);
        
        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150,  250,  30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));    
        add(cardTextField);
         
        // Label and password field for PIN
        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 40);
        add(pin);
        
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220 ,  250,  30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14)); 
        add(pinTextField);
        
        // Button for login
        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this); 
        add(login);
        
        // Button for clearing text fields
        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);
        
        // Button for signup
        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
        
        getContentPane().setBackground(Color.WHITE);    

        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
 
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            // Clearing text fields
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            // Validating login credentials
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where cardnumber = '" + cardnumber + "' and pin = '" + pinnumber + "'";

            try {
                ResultSet rs = (ResultSet) conn.s.executeQuery(query);
                if (rs.next()) {
                    // Opening transactions window if login is successful
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            
        } else if (ae.getSource() == signup) {
            // Opening signup window
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }
    
    // Main method to test the Login class
    public static void main(String args[]) {
        new Login();       
    }
}
    