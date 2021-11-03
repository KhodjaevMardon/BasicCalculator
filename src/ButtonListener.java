import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private final JTextField inputField;
    private final StringBuilder sb = new StringBuilder();

    public ButtonListener(JTextField inputField) {
        this.inputField = inputField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        sb.append(inputField.getText()).append(jButton.getText());
        inputField.setText(sb.toString());
        sb.setLength(0);
    }
}
