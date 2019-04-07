package debug;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.components.Button;

/**
 *
 * @author Adrian Vazquez
 */
public class ViewDebug {

    public static void test() throws Exception {
        JPanel panel = new JPanel();

        BufferedImage img1, img2;

        img1 = ImageIO.read(new File("img.png"));
        img2 = utilities.ImageUtil.deepCopy(img1);

        Dimension d = new Dimension(img2.getWidth(), img2.getHeight());

        view.components.Button boton = new Button("Botón 1", 50, Color.WHITE ,img1, d);
        view.components.Button boton2 = new Button("Botón 2", 50, Color.BLACK ,img2, d);

        panel.add(boton);
        panel.add(boton2);

        JFrame frame = new JFrame("VENTANA");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
