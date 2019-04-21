package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import languajes.ButtonText;
import model.pokemon.Move;
import model.pokemon.MoveSet;
import utilities.RGB;
import view.components.fonts.PokemonFont;

/**
 *
 * @author Adrian Vazquez
 */
public class ButtonFactory {

    // Menu button resources
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

    // Battle button resources
    private static final String URI_BUTTON_BATTLE = "Resources/BattleHUD/Button/button.png"; // Image resources to make the backgroud of buttons into battle
    private static final String URI_BUTTON_BATTLE_BACKGROUND = "Resources/BattleHUD/Button/buttonBackground.png";

    public static List<CustomButton> moveSetButtons(MoveSet ms) throws Exception {

        List<CustomButton> customButtons = new LinkedList<>();

        Iterator<Move> it = ms.getMoves();

        while (it.hasNext()) {
            Move move = it.next();
            customButtons.add(moveButton(move.getName(), move.getType().getName(), ms.getRemainingPP(move), move.getPp(), move.getType().getColor()));
        }

        return customButtons;
    }

    private static CustomButton moveButton(String name, String type, int minimumPP, int maximunPP, Color backgroundColor) throws Exception {
        
        Font font = PokemonFont.getFont(14); // Font: Pokemon, Size: 14.
        FontMetrics fm = PokemonFont.getFontMetrics(font); // Font metrics

        /*
            The button is composed by 3 layers:
                
                1- The first one is only a green shadow. This green color will be replaced by the characteristic color of the move type. 
        
                2- The second layer is the button texture and have some transparencies. This texture will be drawn over the fisrt layer.
        
                3- The last layer is composed by three texts. Name, Type and available PPs of the movement.
         */
        BufferedImage background = ImageIO.read(new File(URI_BUTTON_BATTLE_BACKGROUND)); // Loads the button background - FIRST LAYER.
        BufferedImage foreground = ImageIO.read(new File(URI_BUTTON_BATTLE)); // Loads the button texture. - SECOND LAYER

        // CHANGE EVERY COLOR OF THE BACKGROUND TO USE THE PROPERLY COLOR BASE IN MOVEMENT TYPE.
        background = utilities.ImageUtil.changeEveryColor(background, new RGB(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue()));

        BufferedImage buttonImage = utilities.ImageUtil.overlayImages(background, foreground); // OVERLAY BACKGROUND AND TEXTURE AND MERGE BOTH INTO THE SAME IMAGE

        buttonImage = utilities.ImageUtil.resizeProportional(buttonImage, 2); // RESIZE, MAKE THE BUTTON TWO TIMES BIGGER 

        Dimension buttonSize = new Dimension(
                buttonImage.getWidth(), //Button width
                buttonImage.getHeight() //Button height
        );

        // PLACE THE NAME PROPERLY. - SOME MATHS.
        int i = 0;
        while (fm.stringWidth(name) > buttonSize.getWidth() - 50) { //Addapt the text if is too large.

            name = name.substring(0, name.length() - i);
            i++;
        }
        
        if (i > 0){
            name = name.substring(0, name.length() - 1) + "...";
        }

        int namePositionX = (int) ((buttonSize.getWidth() - fm.stringWidth(name)) / 2);
        int namePositionY = (int) (fm.getHeight());

        buttonImage = utilities.ImageUtil.addText(buttonImage, name, namePositionX, namePositionY, font, Color.WHITE);

        //PLACE TYPE PROPERLY - MORE MATHS.
        type = type.toUpperCase();
        int typePositionX = (int) ((buttonSize.getWidth() - fm.stringWidth(type)) / 2);
        int typePositionY = (int) (buttonSize.getHeight() - 1.5f * fm.getHeight());

        buttonImage = utilities.ImageUtil.addText(buttonImage, type, typePositionX, typePositionY, font, backgroundColor.brighter());

        // PLACE PPs PROPERLY. - MORE MATHS, AGAIN.
        String PP = " PP  " + minimumPP + "/" + maximunPP;
        int ppPositionX = (int) ((buttonSize.getWidth() - fm.stringWidth(PP)) / 2);
        int ppPositionY = (int) (buttonSize.getHeight() - (fm.getHeight() / 2));

        Font f = font.deriveFont(font.getSize() + 3);
        buttonImage = utilities.ImageUtil.addText(buttonImage, PP, ppPositionX, ppPositionY, f, Color.WHITE);

        /* 
            We have set up a button with 3 kinds of custom text placed over one image. So we can't use 
            one button text. We will use empty button title and obviusly font size = 0.
            We have to send a Color for the font, choose anyone, doesn't matter.
            Send button image and buttonSize -- THE ONLY USEFULL PARAMETERS FOR IN BATTLE BUTTON.
         */
        CustomButton cb = new CustomButton("", 0, Color.WHITE, buttonImage, buttonSize);

        cb.setDescription(ButtonText.getInstance().attackButtonDescription(name, type, minimumPP, maximunPP)); // SET CUSTOM NARRATOR DESCRIPTION FOR THE BUTTON.

        return cb;

    }

}
