package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import texttospeech.Narrator;
import view.components.fonts.PokemonFont;

/**
 *
 * @author Adrian Vazquez
 */
public class CustomButton extends JButton implements AidComponent {

    private String text;
    private String pressedVoiceFeedback;
    private BufferedImage idle;
    private BufferedImage mouseEntered;
    private BufferedImage displayedImage;
    private BufferedImage mousePressed;
    private final int BRIGHTNESS_RATE = 20;

    public CustomButton(String text, int fontSize, Color fontColor, BufferedImage background, Dimension buttonSize) throws Exception {
        super();

        setImage(text, fontSize, fontColor, background, buttonSize);

        enableInputMethods(true);
        setPreferredSize(buttonSize);

        setBorder(BorderFactory.createEmptyBorder());
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                new Thread(() -> {

                    try {
                        displayedImage = mousePressed;
                        repaint();
                        Thread.sleep(200);
                        displayedImage = idle;
                        repaint();
                        mouseEntered(e);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomButton.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }).start();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                displayedImage = mouseEntered;
                repaint();

            }

            @Override
            public void mouseExited(MouseEvent e) {

                displayedImage = idle;
                repaint();

            }
        });

    }

    public CustomButton(String text, int fontSize, Color fontColor, BufferedImage backgroundIdle, BufferedImage backgroundMouseEntered, Dimension buttonSize) throws Exception {

        this(text, fontSize, fontColor, backgroundIdle, buttonSize);
        mouseEntered = utilities.image.ImageUtil.resize(backgroundMouseEntered, buttonSize.width, buttonSize.height);
        mouseEntered = utilities.image.ImageUtil.addText(mouseEntered, text, PokemonFont.getFont(fontSize), fontColor);
        mouseEntered = utilities.image.ImageUtil.setBrightness(mouseEntered, BRIGHTNESS_RATE);

    }

    public void setImage(String text, int fontSize, Color fontColor, BufferedImage background, Dimension buttonSize) throws Exception {
        this.text = text;

        this.idle = utilities.image.ImageUtil.resize(background, buttonSize.width, buttonSize.height);

        this.idle = utilities.image.ImageUtil.addText(this.idle, text, PokemonFont.getFont(fontSize), fontColor);

        this.displayedImage = idle;

        mouseEntered = utilities.image.ImageUtil.setBrightness(displayedImage, BRIGHTNESS_RATE);

        mousePressed = utilities.image.ImageUtil.setBrightness(displayedImage, -BRIGHTNESS_RATE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(displayedImage, 0, 0, this);

    }

    @Override
    public String getDescription() {
        return text;
    }

    public void setDescription(String text) {
        this.text = text;
    }

    @Override
    public void press() {
        for (ActionListener al : this.getActionListeners()) {
            al.actionPerformed(null);
        }
    }
    
    
    @Override
    public void setPressedVoiceFeedback(String text) {
        this.pressedVoiceFeedback = text;
    }

    @Override
    public void getPressedVoiceFeedback() {
       
        if (this.pressedVoiceFeedback != null){
            Narrator.getInstance().speak(this.pressedVoiceFeedback);
        }
    }

}
