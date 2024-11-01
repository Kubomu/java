import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICalculator extends JFrame implements ActionListener {
    // Display field for showing input and results
    private JTextField display;

    // Variables to store numbers and operator
    private double num1, num2, result;
    private char operator;

    // Constructor to set up the GUI
    public GUICalculator() {
        // Set up the display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        // Create buttons for numbers 0-9 and operations
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        // Add buttons to the panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);  // Set the action listener
            buttonPanel.add(button);
        }

        // Arrange display and button panel in the frame
        this.setLayout(new BorderLayout());
        this.add(display, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);

        // Set frame properties
        this.setTitle("GUI Calculator");
        this.setSize(400, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // Implement action listener to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // If command is a digit, append it to the display
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        }
        // If command is "C", clear the display
        else if (command.equals("C")) {
            display.setText("");
        }
        // If command is an operator, store the first number and operator
        else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            num1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            display.setText("");
        }
        // If command is "=", perform the calculation and display the result
        else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
        }
    }

    // Main method to run the calculator
    public static void main(String[] args) {
        new GUICalculator();
    }
}
