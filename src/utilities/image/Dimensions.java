package utilities.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Adrian Vazquez
 */
public class Dimensions {

    //deprecated
    public static final Dimension frameDimension1080p = new Dimension((int) (1920 / 1.3), (int) (1080 / 1.3));
    public static final Dimension frameDimension720p = new Dimension((int) (1280 / 1.3), (int) (720 / 1.3));
    public static final Dimension frameDimension2k = new Dimension((int) (1280 * 2 / 1.3), (int) (720 * 2 / 1.3));
    //deprecated

    private static Dimension selectedResolution = getFullScreenResolution();

    private static boolean enableFullScreen = true;
    private static boolean isEnabledFullScreen = false;

    public static Dimension getSelectedResolution() {

        if (enableFullScreen && !isEnabledFullScreen) {
            
            enterFullScreenMode();
            isEnabledFullScreen = true;
            
        } else if (!enableFullScreen) {

            isEnabledFullScreen = false;
            enterWindowedScreenMode();

        }

        return selectedResolution;
    }

    private static JFrame blackLines;

    public static void enterFullScreenMode() {

        selectedResolution = getFullScreenResolution();

        if (blackLines != null) {
            blackLines.dispose();
        }

        blackLines = new JFrame();
        blackLines.setUndecorated(true);
        blackLines.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        blackLines.getContentPane().setBackground(Color.BLACK);
        blackLines.setResizable(false);
        blackLines.setLocationRelativeTo(null);
        blackLines.setAlwaysOnTop(true);
        blackLines.setVisible(true);

    }

    public static void enterWindowedScreenMode() {

        if (blackLines != null) {
            blackLines.dispose();
            blackLines = null;
        }

        selectedResolution = getWindowedResolution();
    }

    private static Dimension getWindowedResolution() {

        Dimension fullScreen = getFullScreenResolution();

        return new Dimension((int) (fullScreen.width / 1.3f), (int) (fullScreen.height / 1.3f));
    }

    private static Dimension getFullScreenResolution() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = screenSize.width;

        return new Dimension((int) (width), (int) ((width * 9) / 16));

    }

}
