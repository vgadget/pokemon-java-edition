package view.menu.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import view.components.AidPanel;

/**
 *
 * @author Adrian Vazquez
 */
public class AnimatedBackgroundPanel extends AidPanel {

    private static final String URI_BACKGROUNDS = "Resources/MainTitle/backgrounds";

    private static final int AVAILABLE_IMAGES = 3;
    private static final int BACKGROUND_SPEED = 1;

    private BufferedImage background;

    private Dimension frameDimension;

    private int x, y; // Background location
    private int dx, dy; // Background location trajectory.

    Random rnd = new Random();
    
    public AnimatedBackgroundPanel(){
        this(utilities.image.Dimensions.getMainMenuResolution());
    } 

    public AnimatedBackgroundPanel(Dimension frameDimension) {

        this.frameDimension = frameDimension;
                
        setUpBackgrounds();

        dx = dy = BACKGROUND_SPEED;

        new Thread(() -> {
            while (true) {

                updateBackgroundLocation();
                repaint();

                try {
                    Thread.sleep(35);
                } catch (InterruptedException ex) {
                }
            }
        }).start();

    }

    private void updateBackgroundLocation() {

        while (!isValidTrajectory(dx, dy)) {

            if (!isValidXTrajectory(dx)) {
                dx = -dx;
                if (rnd.nextBoolean()) {
                    dy = -dy;
                }
            }

            if (!isValidYTrajectory(dy)) {
                dy = -dy;
                if (rnd.nextBoolean()) {
                    dx = -dx;
                }
            }
        }

        x += dx;
        y += dy;

    }

    private boolean isValidTrajectory(int dx, int dy) {

        // Coordinate X
        if (!isValidXTrajectory(dx)) {
            return false;
        }

        // Coordinate Y
        if (!isValidYTrajectory(dy)) {
            return false;
        }

        return true;
    }

    private boolean isValidXTrajectory(int dx) {
        return !((x + dx >= 0) || (x + dx + background.getWidth() <= frameDimension.getWidth()));
    }

    private boolean isValidYTrajectory(int dy) {
        return !((y + dy >= 0) || (y + dy + background.getHeight() <= frameDimension.getHeight()));
    }

    private void setUpBackgrounds() {
        try {
            int i = new Random().nextInt(AVAILABLE_IMAGES);
            background = ImageIO.read(new File(URI_BACKGROUNDS + "/tile(" + i + ").png"));
            background = utilities.image.ImageUtil.resizeProportional(background, utilities.image.ImageUtil.getProportion(frameDimension) * 1.5f);
        } catch (IOException ex) {
            Logger.getLogger(AnimatedBackgroundPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(background, x, y, this);
    }
}
