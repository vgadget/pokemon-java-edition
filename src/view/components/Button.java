package view.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author Adrian Vazquez
 */
public class Button extends JButton {

    private BufferedImage image;
    private BufferedImage displayedImage;
    
    private final int BRIGHTNESS_RATE = 50;

    public Button(BufferedImage img, Dimension size) throws Exception {
        super();

        this.image = utilities.ImageUtil.resize(img, size.width, size.height);
        this.displayedImage = utilities.ImageUtil.deepCopy(this.image);
        
        enableInputMethods(true);
        setPreferredSize(size);

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                new Thread(() -> {
                    try {
                        displayedImage = utilities.ImageUtil.setBrightness(displayedImage, -BRIGHTNESS_RATE);
                        repaint();
                        Thread.sleep(200);
                        displayedImage = utilities.ImageUtil.deepCopy(image);
                        repaint();
                        mouseEntered(e);
                    } catch (Exception ex) {
                        Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }).start();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                try {
                    displayedImage = utilities.ImageUtil.setBrightness(displayedImage, BRIGHTNESS_RATE);
                    repaint();
                } catch (Exception ex) {
                    Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                try {
                    displayedImage =  utilities.ImageUtil.deepCopy(image);
                    repaint();
                } catch (Exception ex) {
                    Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(displayedImage, 0, 0, this);

    }

}
