import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class F1ToDoListApp {
    private static DefaultListModel<String> taskListModel; // Model for the JList
    private static JList<String> taskList; // JList to display tasks
    private static JTextField taskInputField; // Input field for new tasks

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Set up the main window
            JFrame frame = new JFrame("F1 Race Weekend To-Do List");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 500);

            // Main panel setup
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());

            // Header label
            JLabel header = new JLabel("Formula 1 Race Weekend Planner", SwingConstants.CENTER);
            header.setFont(new Font("Arial", Font.BOLD, 18));
            mainPanel.add(header, BorderLayout.NORTH);

            // Task list setup
            taskListModel = new DefaultListModel<>();
            taskList = new JList<>(taskListModel);
            JScrollPane taskScrollPane = new JScrollPane(taskList);
            mainPanel.add(taskScrollPane, BorderLayout.CENTER);

            // Input field panel
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BorderLayout());
            taskInputField = new JTextField(20); // Increase the size of the text field
            taskInputField.setToolTipText("Enter a new task...");
            taskInputField.setText("Enter a new task..."); // Placeholder text
            taskInputField.setForeground(Color.GRAY); // Set placeholder color
            taskInputField.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    if (taskInputField.getText().equals("Enter a new task...")) {
                        taskInputField.setText("");
                        taskInputField.setForeground(Color.BLACK);
                    }
                }

                public void focusLost(java.awt.event.FocusEvent evt) {
                    if (taskInputField.getText().isEmpty()) {
                        taskInputField.setText("Enter a new task...");
                        taskInputField.setForeground(Color.GRAY);
                    }
                }
            });
            inputPanel.add(taskInputField, BorderLayout.CENTER);

            // Footer for buttons
            JPanel footer = new JPanel();
            footer.setLayout(new FlowLayout());

            JButton addButton = new JButton("Add Task");
            JButton deleteButton = new JButton("Delete Task");
            JButton clearButton = new JButton("Clear All");

            // Adding action listeners
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String task = taskInputField.getText().trim();
                    if (!task.isEmpty() && !task.equals("Enter a new task...")) {
                        taskListModel.addElement(task);
                        taskInputField.setText("");
                        taskInputField.setForeground(Color.GRAY);
                        taskInputField.setText("Enter a new task...");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter a task.");
                    }
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = taskList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        taskListModel.remove(selectedIndex);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please select a task to delete.");
                    }
                }
            });

            clearButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int confirmed = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to clear all tasks?", "Confirm Clear",
                        JOptionPane.YES_NO_OPTION);
                    if (confirmed == JOptionPane.YES_OPTION) {
                        taskListModel.clear();
                    }
                }
            });

            // Adding buttons to footer
            footer.add(addButton);
            footer.add(deleteButton);
            footer.add(clearButton);

            // Add input panel and footer to main panel
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new BorderLayout());
            bottomPanel.add(inputPanel, BorderLayout.CENTER);
            bottomPanel.add(footer, BorderLayout.SOUTH);

            mainPanel.add(bottomPanel, BorderLayout.SOUTH);

            // Add the main panel to the frame
            frame.add(mainPanel);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null); // Center the window

            // Adding days of the race weekend
            String[] days = {"Thursday - Media Day", "Friday - Practice", "Saturday - Qualifying", "Sunday - Main Event"};
            for (String day : days) {
                taskListModel.addElement(day);
            }
        });
    }
}
