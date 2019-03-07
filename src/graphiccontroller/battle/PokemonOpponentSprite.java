package graphiccontroller.battle;

import Util.Dimensions;
import Util.ImageUtil;
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
public class PokemonOpponentSprite extends JPanel {

    private static final String URI_MISSINGNO_SPRITE = "Resources/BattleHUD/Pokedex/MISSINGNO/Sprite/FRONT";
    private static final int SPRITE_SPEED = 80;

    private boolean threadlocked, drawingLocked, changingSomething;

    private Dimension frameDimension;
    private BufferedImage[] pokemonSprite;
    private int currentSprite;
    private int pokemonSpriteLocationX, pokemonSpriteLocationY;

    private boolean setEnablePlay, playBackWards, withdrawing, blink, dead, fly;

    private PokemonStatusCondition alteredStateAnimation;
    private ChangeStatsAnimation changeStatsAnimation;

    public PokemonOpponentSprite(Dimension frameDimension, BufferedImage sprites[]) {

        this.frameDimension = frameDimension;
        this.pokemonSprite = sprites;

        this.changingSomething = false;
        this.threadlocked = false;
        this.drawingLocked = false;
        setEnablePlay = true;
        this.playBackWards = false;
        this.dead = false;
        this.fly = false;

        setUpSprites(sprites);

        alteredStateAnimation = new PokemonStatusCondition(new Dimension(this.pokemonSprite[0].getWidth(), this.pokemonSprite[0].getHeight()), null, pokemonSpriteLocationX, pokemonSpriteLocationY);

        try {
            changeStatsAnimation = new ChangeStatsAnimation(frameDimension, pokemonSpriteLocationX, pokemonSpriteLocationY);
        } catch (IOException ex) {
            Logger.getLogger(PokemonMainCharacterSprite.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    if (!changingSomething) {
                        if (currentSprite == pokemonSprite.length - 1) {
                            playBackWards = true;
                        } else if (currentSprite == 0) {
                            playBackWards = false;
                        }

                        if (setEnablePlay) {
                            if (playBackWards == true) {
                                currentSprite--;
                            } else {
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
                        Thread.sleep(SPRITE_SPEED);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PokemonMainCharacterSprite.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }

    public void play() {
        setEnablePlay = true;
    }

    public void pause() {
        setEnablePlay = false;
    }

    public void setUpSprites(BufferedImage pokemonSprite[]) {

        this.changingSomething = true;

        while (this.threadlocked == false && this.drawingLocked == false) {

            this.paintComponent(null);

            try {
                Thread.sleep(SPRITE_SPEED);
            } catch (InterruptedException ex) {
                Logger.getLogger(PokemonOpponentSprite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.currentSprite = 0;

        if (this.pokemonSprite == null) {
            this.pokemonSprite = new BufferedImage[20];
            try {

                for (int i = 0; i < 20; i++) {

                    this.pokemonSprite[i] = ImageIO.read(new File(URI_MISSINGNO_SPRITE + "/tile(" + i + ").png"));
                }
            } catch (IOException ex) {
                Logger.getLogger(PokemonMainCharacterSprite.class.getName()).log(Level.SEVERE, null, ex);
            }

            // SET UP THE PROPORTION
            if (frameDimension.equals(Dimensions.frameDimension720p)) {

                for (int i = 0; i < this.pokemonSprite.length; i++) {

                    this.pokemonSprite[i] = ImageUtil.resizeProportional(this.pokemonSprite[i], ImageUtil.getProportion(frameDimension) * 2);

                }
            } else if (frameDimension.equals(Dimensions.frameDimension1080p)) {

                for (int i = 0; i < this.pokemonSprite.length; i++) {

                    this.pokemonSprite[i] = ImageUtil.resizeProportional(this.pokemonSprite[i], ImageUtil.getProportion(frameDimension) * 3);

                }

            }

        } else {
            this.pokemonSprite = pokemonSprite;
        }

        // SET UP THE LOCATION
        appear();

        changingSomething = false;

    }

    public void appear() {
        withdrawing = false;
        dead = false;
        fly = false;

        if (alteredStateAnimation != null) {
            alteredStateAnimation.setEnable(true);
        }

        if (frameDimension.equals(Dimensions.frameDimension720p)) {

            pokemonSpriteLocationX = (int) frameDimension.getWidth() - 395;
            pokemonSpriteLocationY = (int) 40;

        } else if (frameDimension.equals(Dimensions.frameDimension1080p)) {

            pokemonSpriteLocationX = (int) frameDimension.getWidth() - 565;
            pokemonSpriteLocationY = (int) 110;

        }

    }

    public void withdraw() {
        withdrawing = true;
        alteredStateAnimation.setEnable(false);

    }

    public void blink() {
        this.blink = !this.blink;
    }

    public void dead() {
        this.dead = true;
        alteredStateAnimation.setEnable(false);

    }

    public void fly() {
        fly = true;
        alteredStateAnimation.setEnable(false);
    }

    public void removeAlteredState() {
        alteredStateAnimation.setState(null);
    }

    public void setAlteredState(BufferedImage sprites[]) {
        alteredStateAnimation.setState(sprites);
    }

    public void loadDefaultsetAlteredState() { //Debug
        alteredStateAnimation.loadDefaultState();
    }

    public void statsDownAnimation() {
        this.changeStatsAnimation.doAnimationDown();
    }

    public void statsUpAnimation() {
        this.changeStatsAnimation.doAnimationUP();
    }

    @Override
    protected void paintComponent(Graphics g) {

//        if (currentSprite == this.pokemonSprite.length - 1) {
//            playBackWards = true;
//        } else if (currentSprite == 0) {
//            playBackWards = false;
//        }
        if (!changingSomething && g != null) {
            if (blink) {

                if (currentSprite % 2 == 0 || currentSprite % 8 == 0) {
                    g.drawImage(pokemonSprite[currentSprite], pokemonSpriteLocationX, pokemonSpriteLocationY, this);
                    this.alteredStateAnimation.paintComponent(g);

                }

            } else {
                g.drawImage(pokemonSprite[currentSprite], pokemonSpriteLocationX, pokemonSpriteLocationY, this);
                this.alteredStateAnimation.paintComponent(g);

            }

            if (withdrawing) {

                if (pokemonSpriteLocationX < frameDimension.width) {
                    pokemonSpriteLocationX += 50;
                }

            } else if (dead) {

                if (pokemonSpriteLocationY < frameDimension.getHeight()) {
                    pokemonSpriteLocationY += 100;
                }

            } else if (fly) {
                if (pokemonSpriteLocationY > -frameDimension.getHeight() / 2) {
                    pokemonSpriteLocationY -= frameDimension.getHeight() / 8;
                }
            } else {
                if (changeStatsAnimation != null) {
                    changeStatsAnimation.paintComponent(g);
                }
            }

            if (drawingLocked) {
                drawingLocked = false;
            }

        } else {
            drawingLocked = true;
        }
    }

}
