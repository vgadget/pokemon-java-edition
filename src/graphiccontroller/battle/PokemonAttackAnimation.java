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
public class PokemonAttackAnimation extends JPanel {

    private String URI_DEFAULT_ATTACK_FRONT = "Resources/BattleHUD/Attack/Default/FRONT";
    private String URI_DEFAULT_ATTACK_BACK = "Resources/BattleHUD/Attack/Default/BACK";
    
    // PROPORTION: 984 x 553

    private Dimension frameSize;

    private static final int SPEED = 100;

    private BufferedImage[] attack;
    private int currentSprite;

    private boolean threadlocked, drawingLocked, changingSomething;

    public PokemonAttackAnimation(Dimension frameSize) {
        this.frameSize = frameSize;

        this.currentSprite = -1;

        this.threadlocked = false;
        this.changingSomething = false;
        this.drawingLocked = false;

        new Thread(() -> {
            while (true) {
                
                if (!changingSomething) {
                    if (attack != null) {
                        if (currentSprite >= 0 && currentSprite < attack.length) {
                            currentSprite++;
                        }
                    }
                    
                    if (threadlocked) {
                        threadlocked = false;
                    }
                    
                } else {
                    threadlocked = true;
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

        changingSomething = true;

        while (this.threadlocked == false || this.drawingLocked == false) {

            this.paintComponent(null);

            try {
                Thread.sleep(SPEED);
            } catch (InterruptedException ex) {
                Logger.getLogger(PokemonAttackAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (this.frameSize.equals(Dimensions.frameDimension720p)) {
            
            this.attack = front;
            
        } else if (this.frameSize.equals(Dimensions.frameDimension1080p)) {
            
            this.attack = front;

            for (int i = 0; i < this.attack.length; i++) {

                this.attack[i] = utilities.ImageUtil.resizeProportional(this.attack[i], 1.5);

            }

        }

        this.changingSomething = false;
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

        if (this.attack != null && !this.changingSomething) {
            if (this.currentSprite >= 0 && this.currentSprite < this.attack.length) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (!changingSomething && g != null) {

            if (this.attack != null) {

                if (currentSprite >= 0 && currentSprite < this.attack.length) {
                    g.drawImage(this.attack[currentSprite], 0, 0, this);
                }

                if (drawingLocked) {
                    drawingLocked = false;
                }
            }
        } else {
            drawingLocked = true;
        }
    }

}
