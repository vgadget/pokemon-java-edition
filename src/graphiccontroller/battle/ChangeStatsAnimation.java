package graphiccontroller.battle;

import utilities.Dimensions;
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
public class ChangeStatsAnimation extends JPanel {

    private static final String URI_CHANGE_STATS_DOWN = "Resources/BattleHUD/ChangeStats/DOWN";
    private static final String URI_CHANGE_STATS_UP = "Resources/BattleHUD/ChangeStats/UP";

    private static final int SPEED = 70;
    private static final int NUMBER_OF_SPRITES = 18;

    private int locationXUp, locationYUp, locationXDown, locationYDown;

    private BufferedImage spriteUP[], spriteDown[];
    private int currentSpriteUP, currentSpriteDown;

    public ChangeStatsAnimation(Dimension frameDimension, int locationX, int locationY) throws IOException {

        spriteUP = new BufferedImage[NUMBER_OF_SPRITES];
        spriteDown = new BufferedImage[NUMBER_OF_SPRITES];

        for (int i = 0; i < NUMBER_OF_SPRITES; i++) {

            spriteUP[i] = ImageIO.read(new File(URI_CHANGE_STATS_UP + "/tile(" + i + ").png"));
            spriteDown[i] = ImageIO.read(new File(URI_CHANGE_STATS_DOWN + "/tile(" + i + ").png"));

            if (frameDimension.equals(Dimensions.frameDimension720p)) {

                spriteUP[i] = utilities.ImageUtil.resizeProportional(spriteUP[i], 2);
                spriteDown[i] = utilities.ImageUtil.resizeProportional(spriteDown[i], 2);

            } else if (frameDimension.equals(Dimensions.frameDimension1080p)) {
                spriteUP[i] = utilities.ImageUtil.resizeProportional(spriteUP[i], 4);
                spriteDown[i] = utilities.ImageUtil.resizeProportional(spriteDown[i], 4);
            }
        }

        if (frameDimension.equals(Dimensions.frameDimension720p)) {

            this.locationXUp = locationX;
            this.locationYUp = locationY - 100;

            this.locationXDown = locationX - 40;
            this.locationYDown = locationY - 50;

        } else if (frameDimension.equals(Dimensions.frameDimension1080p)) {

            this.locationXUp = locationX - 150;
            this.locationYUp = locationY - 300;

            this.locationXDown = locationX - 150;
            this.locationYDown = locationY - 150;

        }

        new Thread(() -> {
            while (true) {
                if (currentSpriteUP < spriteUP.length) {
                    currentSpriteUP++;
                } else if (currentSpriteDown < spriteDown.length) {
                    currentSpriteDown++;
                }
                
                try {
                    Thread.sleep(SPEED);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ChangeStatsAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }).start();

        currentSpriteDown = currentSpriteUP = NUMBER_OF_SPRITES + 1;
    }

    public void doAnimationUP() {
        currentSpriteUP = 0;
    }

    public void doAnimationDown() {
        currentSpriteDown = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (currentSpriteUP < spriteUP.length && spriteUP != null) {

            g.drawImage(spriteUP[currentSpriteUP], this.locationXUp, this.locationYUp, this);

        } else if (currentSpriteDown < spriteDown.length && spriteDown != null) {

            g.drawImage(spriteDown[currentSpriteDown], this.locationXDown, this.locationYDown, this);

        }

    }

}
