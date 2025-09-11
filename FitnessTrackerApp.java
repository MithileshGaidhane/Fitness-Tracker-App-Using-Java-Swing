import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FitnessTrackerApp extends JFrame {
    private JTextField stepsField;
    private JTextField distanceField;
    private JButton trackButton;
    private JButton resetButton; // Optional: Reset inputs
    private JLabel resultLabel;

    public FitnessTrackerApp() {
        setTitle("Health and Fitness Tracker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 220);
        setLocationRelativeTo(null); // Center the window

        stepsField = new JTextField(10);
        distanceField = new JTextField(10);
        trackButton = new JButton("Track Activity");
        resetButton = new JButton("Reset");
        resultLabel = new JLabel();

        // Action for the Track button
        trackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int steps = Integer.parseInt(stepsField.getText());
                    double distance = Double.parseDouble(distanceField.getText());

                    if (steps < 0 || distance < 0) {
                        throw new NumberFormatException();
                    }

                    double caloriesBurned = calculateCaloriesBurned(steps, distance);
                    resultLabel.setText("Calories Burned: " + String.format("%.2f", caloriesBurned));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FitnessTrackerApp.this,
                            "Please enter valid positive numbers for steps and distance.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action for the Reset button
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stepsField.setText("");
                distanceField.setText("");
                resultLabel.setText("");
            }
        });

        // Input panel layout
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("Steps taken:"));
        inputPanel.add(stepsField);
        inputPanel.add(new JLabel("Distance (km):"));
        inputPanel.add(distanceField);
        inputPanel.add(trackButton);
        inputPanel.add(resetButton); // Optional: reset functionality

        // Result panel
        JPanel resultPanel = new JPanel();
        resultPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        resultPanel.add(resultLabel);

        add(inputPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
    }

    // Method to calculate estimated calories burned
    private double calculateCaloriesBurned(int steps, double distance) {
        double caloriesPerStep = 0.04;     // Rough estimate per step
        double caloriesPerKm = 0.1;        // Additional factor per km (optional)

        return (steps * caloriesPerStep) + (distance * caloriesPerKm);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FitnessTrackerApp().setVisible(true);
            }
        });
    }
}
