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

    private static final String URI_BUTTON_BATTLE = "Resources/BattleHUD/Button/button.png";
    private static final String URI_BUTTON_BATTLE_BACKGROUND = "Resources/BattleHUD/Button/buttonBackground.png";

    public static List<CustomButton> moveSetButtons(MoveSet ms) throws Exception {

        List<CustomButton> customButtons = new LinkedList<>();

        Iterator <Move> it= ms.getMoves();
        
        while (it.hasNext()){
            Move move = it.next();
            customButtons.add(moveButton(move.getName(), move.getType().getName(), ms.getRemainingPP(move) ,move.getPp(), move.getType().getColor()));
        }

        return customButtons;
    }

    private static CustomButton moveButton(String name, String type, int minimumPP, int maximunPP, Color backgroundColor) throws Exception {

        String PP = " PP " + minimumPP + "/" + maximunPP;

        if (name.length() > 15) {
            name = name.substring(0, 14) + "...";
        }

        Font font = PokemonFont.getFont(14);

        BufferedImage foreground = ImageIO.read(new File(URI_BUTTON_BATTLE));
        BufferedImage background = ImageIO.read(new File(URI_BUTTON_BATTLE_BACKGROUND));

        background = utilities.ImageUtil.changeEveryColor(background, new RGB(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue()));

        BufferedImage buttonImage = utilities.ImageUtil.overlayImages(background, foreground);

        buttonImage = utilities.ImageUtil.resizeProportional(buttonImage, 2);

        Dimension buttonSize = new Dimension(
                buttonImage.getWidth(), //Button width
                buttonImage.getHeight() //Button height
        );

        FontMetrics fm = PokemonFont.getFontMetrics(font);
        int namePositionX = (int) ((buttonSize.getWidth() - fm.stringWidth(name)) / 2);
        int namePositionY = (int) (fm.getHeight() * 1.5f);

        buttonImage = utilities.ImageUtil.addText(buttonImage, name, namePositionX, namePositionY, font, Color.WHITE);

        int ppPositionX = (int) ((buttonSize.getWidth() - fm.stringWidth(PP)) / 2);
        int ppPositionY = (int) (fm.getHeight() * 3f);

        Font f = font.deriveFont(font.getSize() + 3);
        buttonImage = utilities.ImageUtil.addText(buttonImage, PP, ppPositionX, ppPositionY, f, Color.WHITE);

        CustomButton cb = new CustomButton("", 0, Color.WHITE, buttonImage, buttonSize);
        cb.setDescription(ButtonText.attackButtonDescription(name, type, minimumPP, maximunPP));

        return cb;

    }

}
