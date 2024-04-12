package registation;
import Search.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class StudentEnrollmentSystem extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private JTextField nameField, idField, emailField, joinDateField, cgpaField, additionalDataField;
    private JComboBox<String> paymentStatusCombo;
    private JButton addButton, saveButton, deleteButton, exitButton;
    private File file;
    private Set<String> existingEmails = new HashSet<>();
    private Set<String> existingIds = new HashSet<>();

    public StudentEnrollmentSystem() {
        setTitle("Student Enrollment System");
        setSize(1150, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with a gradient background
        JPanel mainPanel = new JPanel(new BorderLayout());
        //mainPanel.setBackground(new GradientPaint(0, 0, new Color(173, 216, 239), 0, getHeight(), new Color(135, 206, 235)));

        // Left panel with input fields and buttons
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(230, 245, 255)); // Light blue background

        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 5, 5)); // Increased rows for additional data
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));
        inputPanel.setBackground(new Color(230, 245, 255)); // Light blue background

        // Labels with clear fonts
        JLabel nameLabel = new JLabel("Name :");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(nameLabel);
        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(nameField);

        JLabel idLabel = new JLabel("ID :");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(idLabel);
        idField = new JTextField(20);
        idField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(idField);

        JLabel emailLabel = new JLabel("Email :");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(emailLabel);
        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(emailField);

        JLabel joinDateLabel = new JLabel("Join Date (YYYY-MM-DD) :");
        joinDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(joinDateLabel);
        joinDateField = new JTextField(20);
        joinDateField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(joinDateField);

        JLabel cgpaLabel = new JLabel("CGPA (0.0 - 4.0) :");
        cgpaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(cgpaLabel);
        cgpaField = new JTextField(20);
        cgpaField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(cgpaField);

        JLabel additionalDataLabel = new JLabel("Section :"); // New label for additional data
        additionalDataLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(additionalDataLabel);
        additionalDataField = new JTextField(20); // New field for additional data
        additionalDataField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(additionalDataField);

        JLabel paymentStatusLabel = new JLabel("Payment Status:");
        paymentStatusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(paymentStatusLabel);
        String[] paymentOptions = {"Paid", "Not Paid"};
        paymentStatusCombo = new JComboBox<>(paymentOptions);
        paymentStatusCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(paymentStatusCombo);

        addButton = new JButton("Add");
        addButton.setBackground(new Color(51, 160, 74)); // Green button
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });
        inputPanel.add(addButton);

        // Button panel for styled positioning of delete, save, and exit buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // Right-aligned with spacing

        deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(230, 81, 81)); // Red button
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 14));
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRecord();
            }
        });
        buttonPanel.add(deleteButton);

        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(39, 174, 96));
// Green button
saveButton.setForeground(Color.WHITE);
saveButton.setFont(new Font("Arial", Font.PLAIN, 14));
saveButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        saveRecordsToFile();
    }
});
buttonPanel.add(saveButton);

exitButton = new JButton("Search");
exitButton.setBackground(new Color(102, 102, 102)); // Gray button
exitButton.setForeground(Color.WHITE);
exitButton.setFont(new Font("Arial", Font.PLAIN, 14));
exitButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        openSearch();
        dispose();
       
    }
});
buttonPanel.add(exitButton);

leftPanel.add(inputPanel, BorderLayout.NORTH);
leftPanel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to bottom

mainPanel.add(leftPanel, BorderLayout.WEST);

// Right panel with table
model = new DefaultTableModel();
table = new JTable(model);
model.addColumn("Name");
model.addColumn("ID");
model.addColumn("Email");
model.addColumn("Join Date");
model.addColumn("CGPA");
model.addColumn("Section"); // Added additional column
model.addColumn("Payment Status");

JScrollPane scrollPane = new JScrollPane(table); 

mainPanel.add(scrollPane, BorderLayout.CENTER);

add(mainPanel);

file = new File("Data/student_records.txt");
loadRecordsFromFile();
}

private void addRecord() {
String name = nameField.getText().trim();
String id = idField.getText().trim();
String email = emailField.getText().trim();
String joinDate = joinDateField.getText().trim();
String cgpaStr = cgpaField.getText().trim();
String additionalData = additionalDataField.getText().trim(); // Get additional data
String paymentStatus = (String) paymentStatusCombo.getSelectedItem();

// Check if any field is empty or contains only whitespace characters
if (name.isEmpty() || id.isEmpty() || email.isEmpty() || joinDate.isEmpty() || cgpaStr.isEmpty() || additionalData.isEmpty() || paymentStatus.isEmpty()) {
    JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}

if (existingIds.contains(id)) {
    JOptionPane.showMessageDialog(this, "ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}

if (existingEmails.contains(email)) {
    JOptionPane.showMessageDialog(this, "Email already exists.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}

// Check if the name or email already exists in the table
for (int i = 0; i < model.getRowCount(); i++) {
    String existingName = (String) model.getValueAt(i, 0);
    String exid =(String) model.getValueAt(i, 1);
    String existingEmail = (String) model.getValueAt(i, 2);
    if (existingName.equals(name)) {
        JOptionPane.showMessageDialog(this, "Name '" + name + "' already exists in the table.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if(exid.equals(id)){
        JOptionPane.showMessageDialog(this, "ID '" + id + "' already exists in the table.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (existingEmail.equals(email)) {
        JOptionPane.showMessageDialog(this, "Email '" + email + "' already exists in the table.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
}



try {
    float cgpa = Float.parseFloat(cgpaStr);
    if (cgpa < 0 || cgpa > 4.0) {
        JOptionPane.showMessageDialog(this,"CGPA must be between 0.0 and 4.0", "Error", JOptionPane.ERROR_MESSAGE); 
        return;
    }

    model.addRow(new Object[]{name, id, email, joinDate, cgpa, additionalData, paymentStatus}); // Include additional data in the row
    existingIds.add(id);
    existingEmails.add(email);
    clearFields();
} catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(this, "Invalid CGPA format", "Error", JOptionPane.ERROR_MESSAGE);
}
}

private void deleteRecord() {
int selectedRow = table.getSelectedRow();
if (selectedRow != -1) {
    String idToRemove = (String) model.getValueAt(selectedRow, 1);
    String emailToRemove = (String) model.getValueAt(selectedRow, 2);
    model.removeRow(selectedRow);
    existingIds.remove(idToRemove);
    existingEmails.remove(emailToRemove);
} else {
    JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
}
}

private void openSearch() {
    // Execute the StudentEnrollmentSystem.java file
   SearchApp var = new SearchApp();
}

private void loadRecordsFromFile() {
try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
    String line;
    while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        model.addRow(parts);
        existingIds.add(parts[1]); // Assuming ID is at index 1
        existingEmails.add(parts[2]); // Assuming Email is at index 2
    }
} catch (IOException e) {
    e.printStackTrace();
}
}


private void saveRecordsToFile() {
try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
    for (int i = 0; i < model.getRowCount(); i++) {
        StringBuilder lineBuilder = new StringBuilder();
        for (int j = 0; j < model.getColumnCount(); j++) {
            if (j > 0) {
                lineBuilder.append(",");
            }
            lineBuilder.append(model.getValueAt(i, j));
        }
        writer.write(lineBuilder.toString() + "\n");
    }
} catch (IOException e) {
    e.printStackTrace();
}
}

private void clearFields() {
nameField.setText("");
idField.setText("");
emailField.setText("");
joinDateField.setText("");
cgpaField.setText("");
additionalDataField.setText(""); // Clear additional data field
paymentStatusCombo.setSelectedIndex(0); // Reset combo box to "Paid"
}

public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
    public void run() {
        new StudentEnrollmentSystem().setVisible(true);
    }
});
}
}
