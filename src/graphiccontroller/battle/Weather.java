package graphiccontroller.battle;

import Util.Dimensions;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Adrian Vazquez
 */
public class Weather extends JPanel {

    //Local resources - DEBUG
    private String URI_DEFAULT_WEATHER = "Resources/BattleHUD/Weather/Rain";
    private static final int DEFAULT_WEATHER_NO_SPRITES = 4;

    // Array of images. It contains all the images to create an animation
    private BufferedImage sprites[];
    private int currentSprite; // Store what image is currently being loaded

    private Dimension frameDimension; //Store the dimension of the panel.

    public Weather(Dimension frameDimension, BufferedImage sprites[]) {

        this.frameDimension = frameDimension;

        currentSprite = 0;

        if (sprites != null) {

            this.sprites = sprites;

        } else {
            //loadDefaultWeather(); //Debug
        }

    }

    public void loadDefaultWeather() {
        try {
            sprites = new BufferedImage[DEFAULT_WEATHER_NO_SPRITES];

            for (int i = 0; i < DEFAULT_WEATHER_NO_SPRITES; i++) {
                sprites[i] = ImageIO.read(new File(URI_DEFAULT_WEATHER + "/tile(" + i + ").png"));

                if (this.frameDimension.equals(Dimensions.frameDimension720p)) {

                    sprites[i] = Util.ImageUtil.resizeProportional(sprites[i], 4);

                } else if (this.frameDimension.equals(Dimensions.frameDimension1080p)) {
                    sprites[i] = Util.ImageUtil.resizeProportional(sprites[i], 6);

                }

            }

        } catch (Exception e) {
            System.err.print("Error reading rain sprite");
        }
    }

    public void changeWeather(BufferedImage s[]) {

        removeWeather();

        this.sprites = s;

        if (this.sprites == null) {
            loadDefaultWeather();
        } else {
            
            for (int i = 0; i < this.sprites.length; i++) {
                
                if (this.frameDimension.equals(Dimensions.frameDimension720p)) {

                    sprites[i] = Util.ImageUtil.resizeProportional(sprites[i], 4);

                } else if (this.frameDimension.equals(Dimensions.frameDimension1080p)) {

                    sprites[i] = Util.ImageUtil.resizeProportional(sprites[i], 6);

                }

            }
        }

    }

    public void removeWeather() {
        this.sprites = null;
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (this.sprites != null) {

            if (currentSprite > this.sprites.length - 1) {
                currentSprite = 0;
            }

            g.drawImage(sprites[currentSprite], 0, 0, this);

            currentSprite++;

        }

    }

}
