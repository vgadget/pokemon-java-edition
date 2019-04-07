package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import view.components.fonts.PokemonFont;

/**
 *
 * @author Adrian Vazquez
 */
public class Button extends JButton {

    private BufferedImage image;
    private BufferedImage displayedImage;

    private final int BRIGHTNESS_RATE = 50;

    public Button(String text, int fontSize, Color fontColor ,BufferedImage backGround, Dimension buttonSize) throws Exception {
        super();

        this.image = utilities.ImageUtil.resize(backGround, buttonSize.width, buttonSize.height);

        this.image = utilities.ImageUtil.addText(this.image, text, 0, 0, PokemonFont.getFont(fontSize), fontColor);

        this.displayedImage = utilities.ImageUtil.deepCopy(this.image);

        enableInputMethods(true);
        setPreferredSize(buttonSize);

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
                    displayedImage = utilities.ImageUtil.deepCopy(image);
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
