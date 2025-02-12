
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingCalculatorV1 extends JFrame implements ActionListener {

    private JTextField displayField;
    private String currentInput = "";
    private String operator = "";
    private double result = 0;

    public SwingCalculatorV1() {
        setTitle("Simple Calculator");
        setSize(300, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create display field
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 35));
        displayField.setBackground(Color.BLACK);
        displayField.setForeground(Color.GREEN);
        add(displayField, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4)); // 4x4 GridLayout

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setBackground(Color.LIGHT_GRAY);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789.".contains(command)) {
            currentInput += command;
            displayField.setText(currentInput);
        } else if ("+".equals(command) || "-".equals(command) || "*".equals(command) || "/".equals(command)) {
            operator = command;
            result = Double.parseDouble(currentInput);
            currentInput = "";
        } else if ("=".equals(command)) {
            double num2 = Double.parseDouble(currentInput);

            switch (operator) {
                case "+":
                    result += num2;
                    break;
                case "-":
                    result -= num2;
                    break;
                case "*":
                    result *= num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result /= num2;
                    } else {
                        displayField.setText("Error: Division by zero");
                        return;
                    }
                    break;
            }

            currentInput = Double.toString(result);
            displayField.setText(currentInput);
            operator = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SwingCalculatorV1();
        });
    }
}
