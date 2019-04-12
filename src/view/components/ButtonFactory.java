package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Adrian Vazquez
 */
public class ButtonFactory {

    private static final String URI_BUTTON_MENU_IDLE = "Resources/MainTitle/buttons/menuButton/idle.png";
    private static final String URI_BUTTON_MENU_MOUSE_ENTERED = "Resources/MainTitle/buttons/menuButton/mouseEntered.png";

    public static CustomButton menuButton(String text) {

        try {

            BufferedImage idleBackground = ImageIO.read(new File(URI_BUTTON_MENU_IDLE));
            BufferedImage mouseEnteredBackground = ImageIO.read(new File(URI_BUTTON_MENU_MOUSE_ENTERED));

            
            
            Dimension buttonSize = new Dimension(
                    idleBackground.getWidth() / 2, //Button width
                    idleBackground.getHeight() / 2 //Button height
            );
            
            
            CustomButton jb = new CustomButton(text, 18, Color.WHITE, idleBackground, mouseEnteredBackground, buttonSize);

            jb.setBounds(0, 0, idleBackground.getWidth() / 2, idleBackground.getHeight() / 2);
            
            return jb;
        } catch (Exception ex) {
            Logger.getLogger(ButtonFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
