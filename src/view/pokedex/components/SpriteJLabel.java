package view.pokedex.components;


import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.entities.Sprite;

public class SpriteJLabel extends JLabel {

    private Sprite sprite;
    private Thread repainter = null;
    
    private  Icon iconAnimation[];

    public SpriteJLabel() {

        super();
    }

    public void setup(Sprite s) {

        sprite = s;

        setText("");
        BufferedImage[] animation = s.getAnimation();

        iconAnimation = new Icon[animation.length];

        for (int i = 0; i < animation.length; i++) {
            iconAnimation[i] = new ImageIcon(animation[i]);
        }

        if (repainter == null) {
            repainter = new Thread(() -> {

                int i = 0;
                boolean backwards = false;

                while (!Thread.interrupted()) {

                    if (i < 0) {
                        i = 0;
                        backwards = false;
                    } else if (i >= iconAnimation.length) {
                        i = iconAnimation.length - 1;
                        backwards = true;
                    }

                    setIcon(iconAnimation[i]);
                    repaint(); //DEBUG

                    if (backwards) {
                        i--;
                    } else {
                        i++;
                    }

                    try {
                        Thread.sleep(sprite.getRefreshRate());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SpriteJLabel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

            repainter.start();
        }
    }

    public Sprite getSprite() {
        return sprite;
    }
}