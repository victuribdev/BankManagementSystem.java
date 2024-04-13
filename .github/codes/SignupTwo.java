package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Class to handle additional signup details
public class SignupTwo extends JFrame implements ActionListener {

    long random;
    JTextField id;
    JButton next;
    JRadioButton syes, sno, eyes, eno;
    JComboBox category, occupation, education, income;
    String formno;

    // Constructor to initialize the signup page
    SignupTwo(String formno) {
        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        // Title for the page
        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(260, 40, 400, 30);
        add(additionalDetails);

        // Category dropdown
        JLabel name = new JLabel("Category:");
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBounds(100, 140, 200, 30);
        add(name);
        String valcategory[] = {"General", "OBC", "SC", "ST", "Other"};
        category = new JComboBox(valcategory);
        category.setBounds(300, 140, 400, 30);
        category.setBackground(Color.WHITE);
        add(category);

        // Income dropdown
        JLabel fname = new JLabel("Income:");
        fname.setFont(new Font("Raleway", Font.BOLD, 20));
        fname.setBounds(100, 190, 100, 30);
        add(fname);
        String incomecategory[] = {"Null", "<1,50,000", "2,50,000", "5,00,000", "Upto 10,00,000"};
        income = new JComboBox(incomecategory);
        income.setBounds(300, 190, 400, 30);
        income.setBackground(Color.WHITE);
        add(income);

        // Education dropdown
        JLabel dob = new JLabel("Educational Qualif.:");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100, 240, 200, 30);
        add(dob);
        String educationValues[] = {"Non-Graduation", "Graduate", "Post-Graduation", "Doctrate", "Others"};
        education= new JComboBox(educationValues);
        education.setBounds(300, 240, 400, 30);
        education.setBackground(Color.WHITE);
        add(education);

        // Occupation dropdown
        JLabel gender = new JLabel("Occupation:");
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        gender.setBounds(100, 290, 200, 30);
        add(gender);
        String occupationValues[] = {"Salaried", "Self-Employed", "Business", "Student", "Others"};
        occupation= new JComboBox(occupationValues);
        occupation.setBounds(300, 290, 400, 30);
        occupation.setBackground(Color.WHITE);
        add(occupation);

        // ID Number field
        JLabel address = new JLabel("ID Number");
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        address.setBounds(100, 340, 200, 30);
        add(address);
        id = new JTextField();
        id.setFont(new Font("Raleway", Font.BOLD, 14));
        id.setBounds(300, 340, 400, 30);
        add(id);

        // Senior Citizen radio buttons
        JLabel miratal = new JLabel("Senior Citizen:");
        miratal.setFont(new Font("Raleway", Font.BOLD, 20));
        miratal.setBounds(100, 390, 200, 30);
        add(miratal);
        syes = new JRadioButton("Yes");
        syes.setBounds(300, 390, 100, 30);
        syes.setBackground(Color.WHITE);
        add(syes);
        sno = new JRadioButton("No");
        sno.setBounds(450, 390, 100, 30);
        sno.setBackground(Color.WHITE);
        add(sno);
        ButtonGroup maritalgroup = new ButtonGroup();
        maritalgroup.add(syes);
        maritalgroup.add(sno);

        // Existing Account radio buttons
        JLabel pincode = new JLabel ("Existing Account:");
        pincode.setFont(new Font("Raleway", Font.BOLD, 20));
        pincode.setBounds(100, 440, 200, 30);
        add(pincode);
        eyes = new JRadioButton("Yes");
        eyes.setBounds(300, 440, 100, 30);
        eyes.setBackground(Color.WHITE);
        add(eyes);
        eno = new JRadioButton("No");
        eno.setBounds(450, 440, 100, 30);
        eno.setBackground(Color.WHITE);
        add(eno);
        ButtonGroup emaritalgroup = new ButtonGroup();
        emaritalgroup.add(eyes);
        emaritalgroup.add(eno);

        // Next button
        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    // Action performed when the Next button is clicked
    public void actionPerformed(ActionEvent ae) {
        // Extracting user inputs
         String formno = "" + random; //long
         String scategory = (String) category.getSelectedItem();
         String sincome = (String) income.getSelectedItem();
         String seducation = (String) education.getSelectedItem();
         String soccupation = (String) occupation.getSelectedItem();
         String seniorcitizen = null;
         if(syes.isSelected()) {
             seniorcitizen = "syes";
         } else if (sno.isSelected()) {
             seniorcitizen = "sno";
         }

         String existingaccount = null;
         if (eyes.isSelected()) {
             existingaccount = "eyes";
         } else if (eno.isSelected()) {
             existingaccount = "eno";
         }

         String sid = id.getText();

         try {
             if (id.equals("")) {
                 // Display an error message if ID is empty
                 JOptionPane.showMessageDialog(null, "Id is Required");  
             } else {
                 // Insert data into the database
                 Conn conn = new Conn();
                 String query = "insert into signuptwo values('"+formno+"','"+scategory+"','"+sincome+"','"+seducation+"','"+soccupation+"','"+sid+"','"+seniorcitizen+"','"+existingaccount+"')";
                 conn.s.executeUpdate(query);
             }
             setVisible(false);
             // Move to the next signup page
             new SignupThree(formno).setVisible(true);
         } catch (Exception e) {
             // Print any exceptions that occur
             System.out.println(e);
         }

    }

    public static void main(String args[]) {    
        new SignupTwo("");
    }
}
