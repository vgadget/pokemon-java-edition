package graphiccontroller.battle;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Adrian Vazquez
 */
public class PokeballAnimation extends JPanel {

    public static final String URI_POKEBALL_SPRITES = "Resources/BattleHUD/Trainer/Pokeball";
    private BufferedImage sprites[];
    
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
            sprites[i] = Util.ImageUtil.resizeProportional(sprites[i], Util.ImageUtil.getProportion(frameSize));
        }

        currentSprite = numberOfSprites;
    }

    public void doAnimation(int x, int y) {

        currentSprite = 0;
        activateAnimation = true;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (currentSprite < numberOfSprites && activateAnimation) {
            g.drawImage(sprites[currentSprite], x, y, this);
            currentSprite++;
        } else {
            activateAnimation = false;
        }
    }

}
