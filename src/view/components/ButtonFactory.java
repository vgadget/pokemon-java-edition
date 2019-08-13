package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import languajes.ButtonTexts;
import model.entities.Move;
import model.entities.MoveSet;
import model.entities.Specie;
import utilities.image.Dimensions;
import utilities.image.RGB;
import utilities.sound.Sound;
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

            int grid = (int) (Dimensions.getSelectedResolution().getWidth() * 0.03f);

            if (grid == 0) {
                grid = 1;
            }

            Dimension buttonSize = new Dimension(
                    (int)(grid * 14.7f), //Button width
                    ((int) (grid * 3.6f)) //Button height
            );

            idleBackground = utilities.image.ImageUtil.resize(idleBackground, buttonSize.width, buttonSize.height);
            mouseEnteredBackground = utilities.image.ImageUtil.resize(mouseEnteredBackground, buttonSize.width, buttonSize.height);

            int fontSize = (int) utilities.string.StringUtil.preferedFontSizeforLabel(view.components.fonts.PokemonFont.getFont(12), text, new Dimension((int) (buttonSize.width * 0.4f), (int) (buttonSize.height * 0.3f)));

            CustomButton jb = new CustomButton(text, fontSize, Color.WHITE, idleBackground, mouseEnteredBackground, buttonSize);

            jb.setBounds(0, 0, idleBackground.getWidth(), idleBackground.getHeight());

            if (over == null || entered == null) {
                loadSounds();
            }

            jb.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    utilities.sound.SoundPlayer.getInstance().playEffectChannel(over);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    utilities.sound.SoundPlayer.getInstance().playEffectChannel(entered);
                }

            });

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
        background = utilities.image.ImageUtil.changeEveryColor(background, new RGB(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue()));

        BufferedImage buttonImage = utilities.image.ImageUtil.overlayImages(background, foreground); // OVERLAY BACKGROUND AND TEXTURE AND MERGE BOTH INTO THE SAME IMAGE

        buttonImage = utilities.image.ImageUtil.resizeProportional(buttonImage, 2); // RESIZE, MAKE THE BUTTON TWO TIMES BIGGER 

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

        if (i > 0) {
            name = name.substring(0, name.length() - 1) + "...";
        }

        int namePositionX = (int) ((buttonSize.getWidth() - fm.stringWidth(name)) / 2);
        int namePositionY = (int) (fm.getHeight());

        buttonImage = utilities.image.ImageUtil.addText(buttonImage, name, namePositionX, namePositionY, font, Color.WHITE);

        //PLACE TYPE PROPERLY - MORE MATHS.
        type = type.toUpperCase();
        int typePositionX = (int) ((buttonSize.getWidth() - fm.stringWidth(type)) / 2);
        int typePositionY = (int) (buttonSize.getHeight() - 1.5f * fm.getHeight());

        buttonImage = utilities.image.ImageUtil.addText(buttonImage, type, typePositionX, typePositionY, font, backgroundColor.brighter());

        // PLACE PPs PROPERLY. - MORE MATHS, AGAIN.
        String PP = " PP  " + minimumPP + "/" + maximunPP;
        int ppPositionX = (int) ((buttonSize.getWidth() - fm.stringWidth(PP)) / 2);
        int ppPositionY = (int) (buttonSize.getHeight() - (fm.getHeight() / 2));

        Font f = font.deriveFont(font.getSize() + 3);
        buttonImage = utilities.image.ImageUtil.addText(buttonImage, PP, ppPositionX, ppPositionY, f, Color.WHITE);

        /* 
            We have set up a button with 3 kinds of custom text placed over one image. So we can't use 
            one button text. We will use empty button title and obviusly font size = 0.
            We have to send a Color for the font, choose anyone, doesn't matter.
            Send button image and buttonSize -- THE ONLY USEFULL PARAMETERS FOR IN BATTLE BUTTON.
         */
        CustomButton cb = new CustomButton("", 0, Color.WHITE, buttonImage, buttonSize);

        cb.setDescription(ButtonTexts.getInstance().attackButtonDescription(name, type, minimumPP, maximunPP)); // SET CUSTOM NARRATOR DESCRIPTION FOR THE BUTTON.

        return cb;

    }

    private static final String URI_POKEDEX_SPECIE_BUTTON = "Resources\\BattleHUD\\Pokedex\\INFO_SCREEN\\pokedexButton.png";
    private static final String OVER_AUDIO_FEEDBACK = "Resources\\BattleHUD\\Pokedex\\INFO_SCREEN\\select.wav";
    private static final String CLIC_AUDIO_FEEDBACK = "Resources\\BattleHUD\\Pokedex\\INFO_SCREEN\\confirm.wav";

    private static Sound over;
    private static Sound entered;

    private static void loadSounds() {
        try {
            over = new Sound(new File(OVER_AUDIO_FEEDBACK));
            entered = new Sound(new File(CLIC_AUDIO_FEEDBACK));
        } catch (Exception e) {

        }
    }

    public static CustomButton getPokedexSpecieButton(Specie s, Dimension frameDimension) throws Exception {

        String text = " " + s.getPokedexID() + " - " + s.getName();

        return pokedexButton(text, frameDimension);
    }

    public static CustomButton pokedexButton(String text, Dimension frameDimension) throws Exception {

        float grid = (float) (frameDimension.getWidth() * (0.3f));

        Dimension buttonDimension = new Dimension((int) (1.2f*(grid * 3.51)), (int) (1.2f*(grid * 0.45))); // Button proportion 

        Font f = view.components.fonts.PokemonFont.getFont(1);

        Dimension labelDimension = new Dimension(buttonDimension);

        labelDimension.setSize((int) (labelDimension.getWidth() * 0.40f), labelDimension.getHeight());

        int fontSize = (int) utilities.string.StringUtil.preferedFontSizeforLabel(f, text, labelDimension);
        f = view.components.fonts.PokemonFont.getFont(fontSize);

        if (text.length() > 18) {
            text = text.substring(0, 16) + "...";
        }

        BufferedImage background = ImageIO.read(new File(URI_POKEDEX_SPECIE_BUTTON));
        CustomButton cb = new CustomButton(text, fontSize, Color.WHITE, background, buttonDimension);

        if (over == null || entered == null) {
            loadSounds();
        }

        cb.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                utilities.sound.SoundPlayer.getInstance().playEffectChannel(over);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                utilities.sound.SoundPlayer.getInstance().playEffectChannel(entered);
            }

        });

        return cb;
    }

}
