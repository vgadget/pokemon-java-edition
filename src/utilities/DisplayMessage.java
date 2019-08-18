package utilities;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author Adrian Vazquez
 */
public class DisplayMessage {

    public static void showErrorDialog(String message) {

        System.err.println(message);
//JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public static void showDebugDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "DEBUG", JOptionPane.WARNING_MESSAGE);
    }

}
