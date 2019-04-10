package main;

import javax.swing.JFrame;
import utilities.Dimensions;
import view.menu.components.AnimatedBackgroundPanel;

/**
 *
 * @author Adrian Vazquez
 */
public class Main {

    public static void main(String[] args) throws Exception {

        

        AnimatedBackgroundPanel a = new AnimatedBackgroundPanel(Dimensions.frameDimension720p);
       
        
        JFrame frame = new JFrame("VENTANA");
        frame.setSize(utilities.Dimensions.frameDimension720p);
        frame.setLocationRelativeTo(null);
        frame.add(a);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
