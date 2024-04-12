package Search;
import registation.StudentEnrollmentSystem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SearchApp extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JTextField searchField;

    public SearchApp() {
        setTitle("Search Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        getContentPane().setBackground(new Color(173, 216, 230)); // Set background color to sky blue

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setBackground(new Color(173, 216, 230)); // Set table background to sky blue

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        model.addColumn("Name");
        model.addColumn("ID");
        model.addColumn("Email");
        model.addColumn("Join Date");
        model.addColumn("CGPA");
        model.addColumn("Section");
        model.addColumn("Payment Status");

        loadFromFile("Data/student_records.txt"); // Load data from file

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(173, 216, 230)); // Set search panel background to sky blue

        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.BLUE);
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText().trim();
                if (!query.isEmpty()) {
                    searchByID(query);
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(255, 165, 0)); // Orange color for back button
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open(); // Open StudentEnrollmentSystem.java
                dispose(); // Close the current SearchApp window
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(102, 102, 102));
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });

        searchPanel.add(new JLabel("Search by ID: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(backButton); // Add back button
        searchPanel.add(exitButton);
        getContentPane().add(searchPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                model.addRow(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data from file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchByID(String id) {
        DefaultTableModel searchModel = new DefaultTableModel();
        searchModel.setColumnIdentifiers(new Object[]{"Name", "ID", "Email", "Join Date", "CGPA", "Section", "Payment Status"});

        // Search through the rows of the original table model
        for (int i = 0; i < model.getRowCount(); i++) {
            String value = model.getValueAt(i, 1).toString(); // ID is in the second column
            if (value.equals(id)) {
                Object[] rowData = new Object[model.getColumnCount()];
                for (int j = 0; j < model.getColumnCount(); j++) {
                    rowData[j] = model.getValueAt(i, j);
                }
                searchModel.addRow(rowData);
                break; // Found the ID, no need to continue searching
            }
        }

        if (searchModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "ID not found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            table.setModel(searchModel);
        }
    }

    private void open() {
        // Execute the StudentEnrollmentSystem.java file
        StudentEnrollmentSystem S2 = new StudentEnrollmentSystem();
        S2.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SearchApp();
            }
        });
    }
}
