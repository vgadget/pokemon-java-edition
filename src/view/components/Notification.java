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
import languajes.LabelTexts;
import model.entities.Sprite;
import texttospeech.Narrator;
import utilities.image.Dimensions;
import utilities.image.Image;
import utilities.sound.Sound;
import utilities.string.StringComparator;

/**
 *
 * @author Adrian Vazquez
 */
public class Notification extends JLabel {

    
    // SINGLETON
    
    private static Notification instance;

    public static Notification getInstance() {

        if (instance == null) {
            instance = new Notification();
        }

        return instance;
    }
    
    // END OF SINGLETON.

    // RESOURCES
    private static final String URI_BACKGROUND = "Resources\\Common\\Notification\\PNG";
    private static final String OVER_AUDIO_FEEDBACK = "Resources\\BattleHUD\\Pokedex\\INFO_SCREEN\\select.wav";
    
    // ATTRIBUTES
    private Sprite notificationBanner; // NOTIFIACTION BANNER SPRITE 
    private int currentSprite; // CURRENT SPRITE TO DISPLAY
    private Dimension bannerDimension; // NOTIFICATION BANNER SPRITE DIMENSION

    private Sound sound; // SOUND TO PLAY WHEN NOTIFICATION RECEIVED.
    
    private String currentDisplayingText = ""; // TEXT TO DISPLAY

    private int x, y; // COORDINATES TO SET IN CASE OF SHOW ANIMATION
    private int hideYCoordenate; // COORDINATE (Y AXIS) TO SET IN CASE OF HIDE ANIMATION

    private int grid; // PIXEL GRID

    // CONSTRUCTOR.
    private Notification() { // SINGLETON

        currentSprite = 0;
        setup();

    }

    /*
    
        THE ONY ONE PUBLIC METHOD. IT CALLS ALL PRIVATE METHODS
        
     */
    public synchronized void displayNotification(String text) {

        try {

            utilities.sound.SoundPlayer.getInstance().playEffectChannel(sound);
            
            Narrator.getInstance().speak(LabelTexts.getInstance().notification() + " " + text);
            Narrator.getInstance().speak(LabelTexts.getInstance().notification() + " " + text);

            if (text.length() > 18) { // IF THE TEXT IS TOO LONG, SHOW AS CAROUSEL

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
                
            } else { // IF THE TEXT CAN BE SHOWN WITHOUT CAROUSEL, SHOW IT NORMALLY.

                setNotificationText(text);
                showMessage();

                Thread.sleep(5000);
            }

            // WHEN IT ENDS TO DISPLAY. HIDE NOTIFICATION BAR.
            hideMessage();
            Thread.sleep(100);

        } catch (InterruptedException ex) {
            //utilities.DisplayMessage.showErrorDialog(ex.getLocalizedMessage());
        }

    }

    private void showMessage() { // GRADUALLY CHANGE PLACE OF THE BANNER TO MAKE THE APPEAR ANIMATION.

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

    private void hideMessage() { // GRADUALLY CHANGE PLACE OF THE BANNER TO MAKE THE HIDE ANIMATION.

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

        // ACORDING TO ATTRIBUTES DISPLAY TEXT ON NOTIFICATION 
        BufferedImage img = notificationBanner.getAnimation()[currentSprite];

        Font f = view.components.fonts.PokemonFont.getFont(1);

        int fontSize = (int) utilities.string.StringUtil.preferedFontSizeforLabel(f, currentDisplayingText, new Dimension((int) (bannerDimension.width * (0.6f)), bannerDimension.height));

        f = view.components.fonts.PokemonFont.getFont(fontSize);

        currentDisplayingText = utilities.string.StringUtil.fixTextWitdth(currentDisplayingText, f, bannerDimension);

        img = utilities.image.ImageUtil.addText(img, currentDisplayingText, f, Color.WHITE);

        return img;
    }

    private void setup() {

        // SET PIXEL GRID
        grid = (int) ((Dimensions.getSelectedResolution().width) * (0.03f));

        // SET BANNER DIMENSION 
        int w, h;

        w = grid * 535;
        h = grid * 100;

        w = w / 50;
        h = h / 50;

        bannerDimension = new Dimension(w, h);

        // LOAD RESOURCES
        try {

            // READ FILES OF THE BANNER ANIMATION (IMAGE FILES)
            File f = new File(URI_BACKGROUND);

            List<File> imageFiles = Arrays.asList(
                    f.listFiles((pathname) -> {
                        return pathname.getName().toLowerCase().endsWith("png");
                    }));

            // SORT FILES BY NAME
            StringComparator comp = new StringComparator();
            imageFiles.sort((File o1, File o2) -> {
                return comp.compare(o1.getName(), o2.getName());
            });

            // READ IMAGES FROM FILES AND RESIZE USING BANNER DIMENSION
            List<Image> imgs = new LinkedList<>();

            for (File file : imageFiles) {
                imgs.add(new Image(utilities.image.ImageUtil.resize((ImageIO.read(file)), bannerDimension.width, bannerDimension.height)));
            }

            // CREATE AND INIIALISE BANNER SPRITE  
            notificationBanner = new Sprite(imgs, 450);

            // LOAD THE SOUND TO BE PLAYED WHEN NOTIFICATION IS DISPLAYED
            this.sound = new Sound(new File(OVER_AUDIO_FEEDBACK));

        } catch (Exception ex) {
            utilities.displaymessage.DisplayMessage.showErrorDialog(ex.getLocalizedMessage());
        }

        // THE X AND Y COORDINATES OF THE NOTIFICATION BAR WHEN IT IS DISPLAYED
        x = (int) ((Dimensions.getSelectedResolution().width - bannerDimension.width) / 2);
        y = grid / 2;

        // THE Y COORDINATE OF THE NOTIFICATION BAR WHEN IS HIDING
        hideYCoordenate = -bannerDimension.height * 2;

        // BY DEFAULT, THE NOTIFICATION BAR IS HIDE.
        setLocation(x, hideYCoordenate);
        setSize(bannerDimension);

        /*  
            This thread is allways showing the notification bar. 
            Does not matter if there are some text or not. 
            And it does not take care about the placing. It just paint info 
            acording to the atributes.
         */
        new Thread(() -> {

            currentDisplayingText = "";

            while (!Thread.interrupted()) {

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
