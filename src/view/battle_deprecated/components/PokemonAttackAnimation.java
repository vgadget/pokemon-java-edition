package view.battle_deprecated.components;

import utilities.image.Dimensions;
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
public class PokemonAttackAnimation extends JPanel {

    private final String URI_DEFAULT_ATTACK_FRONT = "Resources/BattleHUD/Attack/Default/FRONT";
    private final String URI_DEFAULT_ATTACK_BACK = "Resources/BattleHUD/Attack/Default/BACK";

    // PROPORTION: 984 x 553
    private Dimension frameSize;

    private static final int SPEED = 100;

    private BufferedImage[] attack;
    private int currentSprite;

    //private boolean threadlocked, drawingLocked, changingSomething;
    public PokemonAttackAnimation(Dimension frameSize) {
        this.frameSize = frameSize;

        this.currentSprite = -1;

        new Thread(() -> {
            while (true) {

                if (attack != null) {
                    if (currentSprite >= 0 && currentSprite < attack.length) {
                        currentSprite++;
                    }
                }

                try {
                    Thread.sleep(SPEED);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PokemonAttackAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

    }

    public synchronized void setAttack(BufferedImage[] front) {

        this.paintComponent(null);

        try {
            Thread.sleep(SPEED);
        } catch (InterruptedException ex) {
            Logger.getLogger(PokemonAttackAnimation.class.getName()).log(Level.SEVERE, null, ex);

        }

        if (this.frameSize.equals(Dimensions.frameDimension720p)) {

            this.attack = front;

        } else if (this.frameSize.equals(Dimensions.frameDimension1080p)) {

            this.attack = front;

            for (int i = 0; i < this.attack.length; i++) {

                this.attack[i] = utilities.image.ImageUtil.resizeProportional(this.attack[i], 1.5);

            }

        }

    }

    public void loadDefaultAttackAnimationMainCharacter() throws IOException {

        BufferedImage[] attack = new BufferedImage[8];

        for (int i = 0; i < attack.length; i++) {

            attack[i] = ImageIO.read(new File(URI_DEFAULT_ATTACK_FRONT + "/tile(" + i + ").png"));

        }

        setAttack(attack);

    }

    public void loadDefaultAttackAnimationOpponent() throws IOException {

        BufferedImage[] attack = new BufferedImage[8];

        for (int i = 0; i < attack.length; i++) {

            attack[i] = ImageIO.read(new File(URI_DEFAULT_ATTACK_BACK + "/tile(" + i + ").png"));

        }

        setAttack(attack);

    }

    public void doAnimation() {

        if (this.attack != null) {
            currentSprite = 0;
        }

    }

    public boolean isPlaying() {

        if (this.attack != null) {
            if (this.currentSprite >= 0 && this.currentSprite < this.attack.length) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void paintComponent(Graphics g) {

        if (g != null) {

            if (this.attack != null) {

                if (currentSprite >= 0 && currentSprite < this.attack.length) {
                    g.drawImage(this.attack[currentSprite], 0, 0, this);
                }

            }
        }
    }

}
