import javax.swing.*;
import java.awt.*;

public class HealthPortal extends JFrame {

    public HealthPortal() {
        setTitle("Health Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.add(new JLabel("HEALTH ♥")); // Company logo or text
        headerPanel.add(new JLabel("Delivering to Jaunpur 222180"));
        headerPanel.add(new JTextField("Search Hospitals.in"));
        headerPanel.add(new JComboBox<>(new String[]{"English"}));
        headerPanel.add(new JButton("Hello, sign in"));
        headerPanel.add(new JButton("Return"));
        add(headerPanel, BorderLayout.NORTH);

        // Main Content
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2)); // Adjust layout as needed

        // Options Section
        JPanel optionsPanel = new JPanel();
        optionsPanel.add(new JButton("Doctor"));
        optionsPanel.add(new JButton("Nurse"));
        // ... add other options
        mainPanel.add(optionsPanel);

        // City-Based Sections
        JPanel lucknowPanel = new JPanel();
        lucknowPanel.add(new JLabel(new ImageIcon("lucknow_hospital.jpg")));
        mainPanel.add(lucknowPanel);

        // ... similar panels for Kolkata and Mumbai

        // Action Buttons
        JPanel actionPanel = new JPanel();
        actionPanel.add(new JButton("Fill Your Form"));
        actionPanel.add(new JButton("Meeting with Doctor"));
        actionPanel.add(new JButton("Apply for Insurance"));
        mainPanel.add(actionPanel);

        add(mainPanel, BorderLayout.CENTER);

        // Footer (optional)
        // JPanel footerPanel = new JPanel();
        // // ... add copyright, terms of service, etc.
        // add(footerPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new HealthPortal();
    }
}