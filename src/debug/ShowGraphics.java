/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debug;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Adrian Vazquez
 */
public class ShowGraphics extends JPanel {

    private BufferedImage[] imgs;
    private int currentImage;
    private int speed;
    private boolean backwards;

    public ShowGraphics(BufferedImage[] imgs, int speed) {
        this.imgs = imgs;
        currentImage = 0;
        this.speed = speed;
        backwards = false;

        new Thread(() -> {
            while (true) {
                

                if (currentImage == imgs.length-1) {
                    backwards = true;
                }
                
                if (currentImage == 0){
                    backwards = false;
                }
                

                try {
                    Thread.sleep(speed);
                    
                } catch (InterruptedException ex) {
                }
                
                repaint();
                
                if (backwards){
                    currentImage--;
                } else {
                    currentImage++;
                }

            }
        }).start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgs[currentImage], 0, 0, this);

    }

}
