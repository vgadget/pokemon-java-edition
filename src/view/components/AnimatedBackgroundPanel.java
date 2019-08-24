package view.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import utilities.sound.Sound;

/**
 *
 * @author Adrian Vazquez
 */
public class AnimatedBackgroundPanel extends AidPanelImpl implements Musical{

    private static final String URI_BACKGROUNDS = "Resources/MainTitle/backgrounds";
    private static final String URI_BACKGROUND_SONGS = "Resources/MainTitle/songs";

    private static final int BACKGROUND_SPEED = 1;

    private static BufferedImage background;

    private static Dimension frameDimension;

    private static int x, y; // Background location
    private static int dx, dy; // Background location trajectory.

    private static Random rnd = new Random();

    //Sound
    private static Sound backgroundMusic;

    private boolean canMove = true;

    public AnimatedBackgroundPanel() {
        
        this(utilities.image.Dimensions.getSelectedResolution());
    }
    
    public AnimatedBackgroundPanel(Dimension frameDimension) {

        this.frameDimension = frameDimension;

        if (background == null) {
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

        } else {

            new Thread(() -> {

                while (canMove) {

                    repaint();

                    try {
                        Thread.sleep(35);
                    } catch (InterruptedException ex) {
                    }
                }
            }).start();
        }

    }

    
    
    public void stop() {
        canMove = false;
    }

    public synchronized void playBackgroundMusic() {

        if (backgroundMusic == null) {

            List<File> allMusicFiles = Arrays.asList(
                    new File(URI_BACKGROUND_SONGS)
                            .listFiles(
                                    (pathname) -> {

                                        return pathname.getName().toLowerCase().endsWith(".wav");
                                    }));

            try {

                backgroundMusic = new Sound(allMusicFiles.get(rnd.nextInt(allMusicFiles.size())));

            } catch (Exception ex) {
                utilities.DisplayMessage.showErrorDialog(ex.getLocalizedMessage());
            }

        }

        utilities.sound.SoundPlayer.getInstance().stopMusic();

        utilities.sound.SoundPlayer.getInstance().playMusicChannel(backgroundMusic, true);

    }

    private synchronized void updateBackgroundLocation() {

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

    private static boolean isValidTrajectory(int dx, int dy) {

        // Coordinate X
        if (!isValidXTrajectory(dx)) {
            return false;
        }
        // Coordinate Y

        return isValidYTrajectory(dy);
    }

    private static boolean isValidXTrajectory(int dx) {
        return !((x + dx >= 0) || (x + dx + background.getWidth() <= frameDimension.getWidth()));
    }

    private static boolean isValidYTrajectory(int dy) {
        return !((y + dy >= 0) || (y + dy + background.getHeight() <= frameDimension.getHeight()));
    }

    private static void setUpBackgrounds() {
        try {

            List<File> images = new LinkedList<>();

            images.addAll(Arrays.asList(new File(URI_BACKGROUNDS).listFiles()));

            images
                    .stream()
                    .filter((img) -> (!img.getName().toUpperCase().contains(".PNG")))
                    .forEachOrdered((img)
                            -> {

                        images.remove(img);
                    });

            background = ImageIO.read(images.get(new Random().nextInt(images.size())));

            double proportionX = Math.max((background.getWidth() / frameDimension.getWidth()), (frameDimension.getWidth() / background.getWidth()));
            double proportionY = Math.max((background.getHeight() / frameDimension.getHeight()), (frameDimension.getHeight() / background.getHeight()));

            background = utilities.image.ImageUtil.resizeProportional(background, Math.max(proportionX, proportionY) * 1.5f);

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
