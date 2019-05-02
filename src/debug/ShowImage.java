package debug;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Adrian Vazquez
 */
public class ShowImage extends JPanel {

    private BufferedImage img;


    public ShowImage(BufferedImage img) {
        this.img = img;

       

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);

    }

}
