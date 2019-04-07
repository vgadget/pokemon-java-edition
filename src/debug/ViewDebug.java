/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

        view.components.Button boton = new Button("Botón 1", 50 ,img1, d);
        view.components.Button boton2 = new Button("Botón 2", 50 ,img2, d);

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
