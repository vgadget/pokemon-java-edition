/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debug;

import java.awt.Dimension;
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
        view.components.Button boton = new Button(ImageIO.read(new File("img.png")), new Dimension(250, 250));
        view.components.Button boton2 = new Button(ImageIO.read(new File("img.png")), new Dimension(250, 250));

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
