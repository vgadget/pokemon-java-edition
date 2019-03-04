/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.graphicscontrolers;

import java.awt.Dimension;
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
public class PokemonAlteredState extends JPanel {

    private static final String URI_DEBUG_STATE = "Resources/BattleHUD/AlteredState/DEBUG";
    private static final String URI_DEFAULT_STATE = "Resources/BattleHUD/AlteredState/DEFAULT";

    private Dimension spriteDimension;
    private int locationX, locationY;
    private BufferedImage stateSprites[];
    private int currentSprite = 0;
    private boolean enable;

    public PokemonAlteredState(Dimension spriteDimension, BufferedImage sprites[], int locationX, int locationY) {
        this.spriteDimension = spriteDimension;
        this.locationX = locationX;
        this.locationY = locationY;
        this.stateSprites = sprites;
        this.enable = false;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    if (stateSprites != null && enable) {
                        if (currentSprite >= stateSprites.length) {
                            currentSprite = 0;
                        }

                        try {
                            Thread.sleep(115);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PokemonAlteredState.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        currentSprite++;
                    } else {

                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PokemonAlteredState.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }).start();

    }

    public void loadDefaultState() {
        loadDebugState();
        this.enable = true;
    }

    private void loadDebugState() {

        stateSprites = new BufferedImage[5];

        for (int i = 0; i < stateSprites.length; i++) {
            try {
                stateSprites[i] = ImageIO.read(new File(URI_DEFAULT_STATE + "/tile(" + i + ").png"));
                stateSprites[i] = Util.ImageUtil.resize(stateSprites[i], (int) spriteDimension.getWidth(), (int) spriteDimension.getHeight());
            } catch (IOException ex) {
                Logger.getLogger(PokemonAlteredState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setState(BufferedImage sprites[]) {
        this.stateSprites = sprites;
        this.enable = true;
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {

        if (enable && stateSprites != null) {
            g.drawImage(stateSprites[currentSprite], locationX, locationY, this);
        }

    }
}
