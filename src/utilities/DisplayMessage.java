package utilities;

import javax.swing.JOptionPane;

/**
 *
 * @author Adrian Vazquez
 */
public class DisplayMessage {

    public static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    public static void showDebugDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "DEBUG", JOptionPane.WARNING_MESSAGE);
    }
}
