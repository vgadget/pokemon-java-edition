package view.battle.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Adrian Vazquez
 */
public class PokeballAnimation extends JPanel {

    public static final String URI_POKEBALL_SPRITES = "Resources/BattleHUD/Trainer/Pokeball";
    private BufferedImage sprites[];
    private static final int SPRITE_SPED = 80; //Milliseconds

    
    private int numberOfSprites = 16;
    private int currentSprite;
    private Dimension frameSize;

    private int x, y;
    private boolean activateAnimation;

    public PokeballAnimation(Dimension frameSize) throws IOException {

        this.frameSize = frameSize;

        activateAnimation = false;

        sprites = new BufferedImage[numberOfSprites];

        for (int i = 0; i < numberOfSprites; i++) {
            sprites[i] = ImageIO.read(new File(URI_POKEBALL_SPRITES + "/tile(" + i + ").png"));
            sprites[i] = utilities.image.ImageUtil.resizeProportional(sprites[i], utilities.image.ImageUtil.getProportion(frameSize));
        }

        currentSprite = numberOfSprites;

        new Thread(() -> {

            while (true) {

                if (currentSprite >= 0 && currentSprite < numberOfSprites) {
                    
                    currentSprite++;
                    
                }
                try {
                    Thread.sleep(SPRITE_SPED);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PokeballAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }).start();

    }

    public void doAnimation(int x, int y) {

        currentSprite = 0;
        activateAnimation = true;
        this.x = x;
        this.y = y;
    }

    @Override
    public void paintComponent(Graphics g) {

        if (currentSprite < numberOfSprites && activateAnimation) {
            g.drawImage(sprites[currentSprite], x, y, this);
        } else {
            activateAnimation = false;
        }
    }

}
