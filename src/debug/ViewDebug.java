package debug;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.components.ButtonFactory;
import view.components.CustomButton;

/**
 *
 * @author Adrian Vazquez
 */
public class ViewDebug {

    public static void test() throws Exception {
        JPanel panel = new JPanel();


//        panel.add(ButtonFactory.menuButton("one"));
//        panel.add(ButtonFactory.menuButton("two"));

        JFrame frame = new JFrame("VENTANA");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
