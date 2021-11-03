import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {
    public Form(String title) {
        setTitle(title);
        setBounds(800, 400, 300, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));
        // textField.
        // controlPanel {
        //      actions
        //      digits
        //      clear + zero + '='
        // }


        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Verdana", Font.BOLD, 30));
        inputField.setEditable(false);
        ButtonListener buttonListener = new ButtonListener(inputField);

        JPanel inputPanel = new JPanel(new BorderLayout());
        JPanel digitPanel = new JPanel(new GridLayout(5, 3));

        inputPanel.add(inputField, BorderLayout.CENTER);

        JButton plus = new JButton("+");
        JButton minus = new JButton("-");
        JButton multiply = new JButton("*");
        digitPanel.add(plus);
        digitPanel.add(minus);
        digitPanel.add(multiply);
        plus.addActionListener(buttonListener);
        minus.addActionListener(buttonListener);
        multiply.addActionListener(buttonListener);

        for (int i = 1; i <= 9; i++) {
            JButton digit = new JButton(String.valueOf(i));
            digit.addActionListener(buttonListener);
            digitPanel.add(digit);
        }

        JButton submit = new JButton(" = ");
        JButton zero = new JButton("0");
        JButton clear = new JButton("C");

        submit.addActionListener(e -> {
            // TODO: доделать рабочий калькулятор.
            char[] arr = inputField.getText().toCharArray();
            // String arithExpression = inputField.getText();
            char action = ' ';
            int count = 0, actionIndex = 0, a = 0, b = 0;
            boolean isValid = true;
            // boolean isValid = arithExpression.matches("-*[0-9]+[+*-/][0-9]+");
            String answer = "";
            // System.out.println(isValid + "\n" + arithExpression + "\n" + Arrays.toString(arr) + "\n");


            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == '+' || arr[i] == '-' || arr[i] == '*') {
                    action = arr[i];
                    actionIndex = i;
                    count++;
                    if (count > 1 || i == 0 || i == arr.length - 1) {
                        isValid = false;
                        break;
                    }
                }
            }

            if (isValid) {
                // perform an action
                // parse two operands to integer type
                for (int i = 0; i < actionIndex; i++) {
                    a *= 10;
                    a += Character.getNumericValue(arr[i]);
                }

                for (int i = actionIndex + 1; i < arr.length; i++) {
                    b *= 10;
                    b += Character.getNumericValue(arr[i]);
                }
                // calculate
                switch (action) {
                    case '+' -> answer = String.valueOf(a + b);
                    case '-' -> answer = String.valueOf(a - b);
                    case '*' -> answer = String.valueOf(a * b);
                    case '/' -> answer = String.valueOf(a / b);
                }
            } else {
                // expression is not valid, tell that.
                answer = "Invalid expression";
            }
            inputField.setText(answer);
        });

        clear.addActionListener(e -> inputField.setText(""));

        digitPanel.add(clear);
        digitPanel.add(zero);
        digitPanel.add(submit);

        add(inputPanel);
        add(digitPanel);

        setVisible(true);
    }
}