package view.menu;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JPanel;
import view.menu.components.AnimatedBackgroundPanel;

/**
 *
 * @author Adrian Vazquez
 */
public class MainMenuView extends javax.swing.JFrame {
    
    
    private JPanel panel;

    public MainMenuView(Dimension frameDimension) throws IOException {

        panel = new AnimatedBackgroundPanel(frameDimension);

        add(panel);

        
        setSize(frameDimension);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

    }
             
}
