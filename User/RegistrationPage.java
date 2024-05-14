package User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Class.account;

public class RegistrationPage extends JFrame implements ActionListener,MouseListener {
    private JTextField usernameField, passwordField;
    private JTextField nameField, phoneField;
    private JTextField dobField;
    private JTextField securityAnswerField1, securityAnswerField2;
    private JLabel titleLabel, usernameLabel, passwordLabel,nameLabel,phoneLabel,dobLabel,securityQuestion1Label,securityQuestion2Label;
    private JButton submitButton,backButton;

    public RegistrationPage() {
        super("Registation");
		this.setSize(490, 520);
		this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255));

        titleLabel = new JLabel("Teacher Registration");
        titleLabel.setBounds(160, 10, 200, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 60, 100, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 60, 250, 25);
        panel.add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 100, 100, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(); 
        passwordField.setBounds(150, 100, 250, 25);
        panel.add(passwordField);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 140, 100, 25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 140, 250, 25);
        panel.add(nameField);

        phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(30, 180, 100, 25);
        panel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(150, 180, 250, 25);
        panel.add(phoneField);

        dobLabel = new JLabel("Date of Birth (YYYY-MM-DD):");
        dobLabel.setBounds(30, 220, 180, 25);
        panel.add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(210, 220, 190, 25);
        panel.add(dobField);

        securityQuestion1Label = new JLabel("Your Favourite Car ?");
        securityQuestion1Label.setBounds(30, 260, 150, 25);
        panel.add(securityQuestion1Label);

        securityAnswerField1 = new JTextField();
        securityAnswerField1.setBounds(210, 260, 190, 25);
        panel.add(securityAnswerField1);

        securityQuestion2Label = new JLabel("Your Birth City ?");
        securityQuestion2Label.setBounds(30, 300, 150, 25);
        panel.add(securityQuestion2Label);

        securityAnswerField2 = new JTextField();
        securityAnswerField2.setBounds(210, 300, 190, 25);
        panel.add(securityAnswerField2);

        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 340, 100, 30);
        submitButton.addActionListener(this);
        submitButton.addMouseListener(this);
        panel.add(submitButton);

        backButton = new JButton("Back");
        backButton.setBounds(270, 340, 100, 30);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);
        panel.add(backButton);

        this.add(panel);
    }
    public void mouseClicked(MouseEvent me) {
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == submitButton) {
            submitButton.setBackground(Color.BLUE);
            submitButton.setForeground(Color.WHITE);
        } else if (me.getSource() == backButton) {
            backButton.setBackground(Color.BLUE);
            backButton.setForeground(Color.WHITE);
        }
    }

    public void mouseExited(MouseEvent me) {
        if (me.getSource() == submitButton) {
            submitButton.setBackground(new Color(46, 139, 87));
            submitButton.setForeground(Color.WHITE);
        } else if (me.getSource() == backButton) {
            backButton.setBackground(new Color(70, 130, 180));
            backButton.setForeground(Color.WHITE);
    }
}


public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            String user = usernameField.getText();
                String pass = passwordField.getText();
                String name = nameField.getText();
                String phone = phoneField.getText();
                String dob = dobField.getText();
                String q1=securityAnswerField1.getText();
                String q2 =securityAnswerField2.getText();
                
                if (!user.isEmpty() && !pass.isEmpty() && !name.isEmpty() && !phone.isEmpty() && !dob.isEmpty() &&
                    !q1.isEmpty() && !q2.isEmpty()) {
                    if (user.length() >= 6 && pass.length() >= 6) {
                        account s2 = new account(user, pass, name, phone, dob,q1,q2);
                        s2.add();
                        JOptionPane.showMessageDialog(null, "Successful");
                    } else {
                        JOptionPane.showMessageDialog(null, "Username and password must be at least 6 characters long.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "All fields are needed");
                }
        }
        if (ae.getSource() == backButton) {
            start ee = new start();
            ee.setVisible(true);
            RegistrationPage.this.setVisible(false);
        }
        
    }

}
