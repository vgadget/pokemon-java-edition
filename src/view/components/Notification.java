/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.entities.Sprite;
import utilities.image.Dimensions;
import utilities.image.Image;
import utilities.sound.Sound;
import utilities.string.StringComparator;

/**
 *
 * @author Adrian Vazquez
 */
public class Notification extends JLabel {

    private static Notification instance;

    public static Notification getInstance() {

        if (instance == null) {
            instance = new Notification();
        }

        return instance;
    }

    private final String URI_BACKGROUND = "Resources\\Common\\Notification\\PNG";
    private static final String OVER_AUDIO_FEEDBACK = "Resources\\BattleHUD\\Pokedex\\INFO_SCREEN\\select.wav";
    private Sound sound;

    private Sprite notificationBanner;
    private Dimension bannerDimension;

    private String currentDisplayingText = "";
    private int currentSprite;

    private int x, y;
    private int hideYCoordenate;

    private int grid;

    private Notification() {

        currentSprite = 0;
        setup();

    }

    public synchronized void displayNotification(String text) {

        try {
            
            utilities.sound.SoundPlayer.getInstance().playEffectChannel(sound);

            if (text.length() > 18) {

                setNotificationText(text.substring(0, 18));
                showMessage();

                text += "   ";

                Thread.sleep(3000);

                for (int j = 0; j < 2; j++) {

                    for (int i = 0; i < text.length(); i++) {
                        text = utilities.string.StringUtil.carousel(text);
                        setNotificationText(text.substring(0, 18));
                        repaint();
                        Thread.sleep(300);

                    }

                }
            } else {

                setNotificationText(text);
                showMessage();

                Thread.sleep(5000);
            }

            hideMessage();
            Thread.sleep(100);

        } catch (InterruptedException ex) {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showMessage() {

        new Thread(() -> {

            for (int i = hideYCoordenate; i < y; i += ((int) (grid / 10))) {

                setLocation(x, i);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }).start();

    }

    private void hideMessage() {

        new Thread(() -> {

            for (int i = y; i >= hideYCoordenate; i -= ((int) (grid / 10))) {

                setLocation(x, i);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }).start();

    }

    private synchronized void setNotificationText(String text) {

        this.currentDisplayingText = text;

    }

    private BufferedImage getCurrentImageToDisplay() {

        BufferedImage img = notificationBanner.getAnimation()[currentSprite];

        Font f = view.components.fonts.PokemonFont.getFont(1);

        int fontSize = (int) utilities.string.StringUtil.preferedFontSizeforLabel(f, currentDisplayingText, new Dimension((int) (bannerDimension.width * (0.6f)), bannerDimension.height));

        f = view.components.fonts.PokemonFont.getFont(fontSize);

        currentDisplayingText = utilities.string.StringUtil.fixTextWitdth(currentDisplayingText, f, bannerDimension);

        img = utilities.image.ImageUtil.addText(img, currentDisplayingText, f, Color.WHITE);

        return img;
    }

    private void setup() {

        grid = (int) ((Dimensions.getSelectedResolution().width) * (0.03f));
        int w, h;

        w = grid * 535;
        h = grid * 100;

        w = w / 50;
        h = h / 50;

        bannerDimension = new Dimension(w, h);

        try {

            File f = new File(URI_BACKGROUND);

            List<File> imageFiles = Arrays.asList(
                    f.listFiles((pathname) -> {
                        return pathname.getName().toLowerCase().endsWith("png");
                    }));

            StringComparator comp = new StringComparator();
            imageFiles.sort((File o1, File o2) -> {
                return comp.compare(o1.getName(), o2.getName());
            });

            List<Image> imgs = new LinkedList<>();

            for (File file : imageFiles) {
                imgs.add(new Image(utilities.image.ImageUtil.resize((ImageIO.read(file)), bannerDimension.width, bannerDimension.height)));
            }

            notificationBanner = new Sprite(imgs, 450);

            this.sound = new Sound(new File(OVER_AUDIO_FEEDBACK));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        x = (int) ((Dimensions.getSelectedResolution().width - bannerDimension.width) / 2);
        y = grid / 2;

        hideYCoordenate = -bannerDimension.height * 2;

        setLocation(x, hideYCoordenate);
        setSize(bannerDimension);

        new Thread(() -> {

            currentDisplayingText = "";

            while (true) {

                if (currentSprite >= notificationBanner.getAnimation().length) {
                    currentSprite = 0;
                }

                setIcon(new ImageIcon(getCurrentImageToDisplay()));

                repaint();

                try {
                    Thread.sleep(notificationBanner.getRefreshRate());
                } catch (InterruptedException ex) {
                }

                currentSprite++;
            }

        }).start();

    }

}
