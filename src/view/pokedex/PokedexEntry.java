package view.pokedex;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import model.entities.Specie;
import model.entities.Type;
import utilities.image.RGB;

/**
 *
 * @author Adrian Vazquez
 */
public class PokedexEntry extends JLayeredPane {

    private static final String URI_POKEDEX_ENTRY_BACKGROUND = "Resources\\BattleHUD\\Pokedex\\INFO_SCREEN\\pokedexEntry.PNG";
    private static final String URI_BUTTON_BATTLE = "Resources/BattleHUD/Button/button.png"; // I will recycle battle button as type tag XD
    private static final String URI_BUTTON_BATTLE_BACKGROUND = "Resources/BattleHUD/Button/buttonBackground.png";

    /*
        IMPORTANT COMMENT
    
        To fix sizes and component location for every resolution. I will use a grid (in this case the same grid as the background image)
        On the real image every grid has 0.56 pixels of width and height. It represents arround 3.10 percent over all the width of the image and 4.78 over the heigth.
    
        So, with a simple Math operation I Will have a precise grid to place every component on the panel. They will be xGrid and yGrid and will be initialised on setup() methow.
     */
    int xGrid, yGrid;

    private BufferedImage pokedexEntryBackground;
    private Specie specie;

    // POKEMON SPECIE INFO COMPONENTS
    private JTextArea jTextAreaDescription;
    private JLabel specieIdAndName;
    private JLabel type;
    private JLabel secondaryType;

    public PokedexEntry() {

        pokedexEntryBackground = null;

        setup();
    }

    public void setSpecie(Specie specie) {

        this.specie = specie;

        setDescription(this.specie.getDescription());
        setPokedexIdAndName(specie.getPokedexID() + "", specie.getName());
        setType(specie.getType());
        setSecondaryType(specie.getSecondaryType());

        refresh();
    }

    private void setType(Type t) {

        try {
            BufferedImage background = ImageIO.read(new File(URI_BUTTON_BATTLE_BACKGROUND)); // Loads the button background - FIRST LAYER.
            BufferedImage foreground = ImageIO.read(new File(URI_BUTTON_BATTLE)); // Loads the button texture. - SECOND LAYER
            background = utilities.image.ImageUtil.changeEveryColor(background, new RGB(t.getColor().getRed(), t.getColor().getGreen(), t.getColor().getBlue()));

            BufferedImage finalImage = utilities.image.ImageUtil.overlayImages(background, foreground); // OVERLAY BACKGROUND AND TEXTURE AND MERGE BOTH INTO THE SAME IMAGE

            finalImage = utilities.image.ImageUtil.resizeProportional(finalImage, ((xGrid * 6.3) / finalImage.getWidth()));

            //ADDAPT FONT STUFF
            Font f = view.components.fonts.PokemonFont.getFont(1);

            float fontSize = utilities.string.StringUtil.preferedFontSizeforLabel(f, t.getName(), new Dimension((int) (finalImage.getWidth() * 0.6f), ((int) (finalImage.getHeight() * 0.6f))));

            f = view.components.fonts.PokemonFont.getFont((int) fontSize);
            // END OF FONT STUFF    
            
            Color c = Color.WHITE;
            finalImage = utilities.image.ImageUtil.addText(finalImage, t.getName(), f, c);

            this.type.setIcon(new ImageIcon(finalImage));

        } catch (Exception ex) {
            Logger.getLogger(PokedexEntry.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setSecondaryType(Type t) {

        try {
            BufferedImage background = ImageIO.read(new File(URI_BUTTON_BATTLE_BACKGROUND)); // Loads the button background - FIRST LAYER.
            BufferedImage foreground = ImageIO.read(new File(URI_BUTTON_BATTLE)); // Loads the button texture. - SECOND LAYER

            if (t != null) {
                background = utilities.image.ImageUtil.changeEveryColor(background, new RGB(t.getColor().getRed(), t.getColor().getGreen(), t.getColor().getBlue()));
            } else {
                background = utilities.image.ImageUtil.changeEveryColor(background, new RGB(255, 255, 255));
            }

            BufferedImage finalImage = utilities.image.ImageUtil.overlayImages(background, foreground); // OVERLAY BACKGROUND AND TEXTURE AND MERGE BOTH INTO THE SAME IMAGE

            finalImage = utilities.image.ImageUtil.resizeProportional(finalImage, ((xGrid * 6.3) / finalImage.getWidth()));

            if (t == null) {
                finalImage = utilities.image.ImageUtil.setBrightness(finalImage, 80);
            }

            //ADDAPT FONT STUFF
            Font f = view.components.fonts.PokemonFont.getFont(1);

            float fontSize = 0;

            if (t != null) {
                fontSize = utilities.string.StringUtil.preferedFontSizeforLabel(f, t.getName(), new Dimension((int) (finalImage.getWidth() * 0.6f), ((int) (finalImage.getHeight() * 0.6f))));
            }

            f = view.components.fonts.PokemonFont.getFont((int) fontSize);
            // END OF FONT STUFF
            
            if (t != null) {
                Color c = Color.WHITE;
                finalImage = utilities.image.ImageUtil.addText(finalImage, t.getName(), f, c);
            }

            this.secondaryType.setIcon(new ImageIcon(finalImage));

        } catch (Exception ex) {
            Logger.getLogger(PokedexEntry.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setPokedexIdAndName(String pokedexId, String name) {

        String text = " " + pokedexId + "   " + name;

        Font f = view.components.fonts.PokemonFont.getFont(1);

        float fontSize = utilities.string.StringUtil.preferedFontSizeforLabel(f, text, specieIdAndName.getSize());

        f = view.components.fonts.PokemonFont.getFont((int) fontSize);

        this.specieIdAndName.setFont(f);

        this.specieIdAndName.setText(text);

    }

    private void setDescription(String text) {

        Font f = view.components.fonts.PokemonFont.getFont(12);

        int w = this.pokedexEntryBackground.getWidth();

        int h = yGrid * 3;

        float fontSize = utilities.string.StringUtil.preferedFontSizeforText(f, text, new Dimension(w, h));

        f = view.components.fonts.PokemonFont.getFont((int) fontSize);

        this.jTextAreaDescription.setFont(f);

        this.jTextAreaDescription.setText(text);

    }

    private void setup() {
        try {
            //BACKGROUND
            int offset = 0;

            pokedexEntryBackground = ImageIO.read(new File(URI_POKEDEX_ENTRY_BACKGROUND));

            pokedexEntryBackground = utilities.image.ImageUtil.resizeProportional(pokedexEntryBackground, ((utilities.image.Dimensions.getSelectedResolution().getHeight()) / (this.pokedexEntryBackground.getHeight())));

            this.setPreferredSize(new Dimension(pokedexEntryBackground.getWidth() + offset, pokedexEntryBackground.getHeight() + offset));

            JLabel background = new JLabel(new ImageIcon(pokedexEntryBackground));

            background.setSize(this.getPreferredSize());

            // XGRID AND YGRID
            xGrid = (int) (this.pokedexEntryBackground.getWidth() * 0.03f);
            yGrid = (int) xGrid;

            //DESCRIPTION BOX   
            this.jTextAreaDescription = new JTextArea();
            this.jTextAreaDescription.setEditable(false);

            int x, y, w, h;

            offset = 30;
            int verticalOffset = 0;

            x = 0 + 10;

            y = ((this.pokedexEntryBackground.getHeight()) - ((int) (this.pokedexEntryBackground.getHeight() - (this.pokedexEntryBackground.getHeight() * (1f - 0.28f))))) + verticalOffset;

            w = this.pokedexEntryBackground.getWidth() - offset;

            h = ((int) (this.pokedexEntryBackground.getHeight() * (1f - 0.28f)));

            this.jTextAreaDescription.setBounds(x, y, w, h);

            this.jTextAreaDescription.setOpaque(false);

            this.jTextAreaDescription.setForeground(Color.WHITE);
            this.jTextAreaDescription.setLineWrap(true);
            this.jTextAreaDescription.setWrapStyleWord(true);

            //POKEDEX ID AND NAME
            this.specieIdAndName = new JLabel();

            this.specieIdAndName.setLocation(xGrid * 17, (int) (yGrid * 1.45));
            this.specieIdAndName.setSize(xGrid * 12, yGrid * 2);

            //TYPES
            this.type = new JLabel();
            this.type.setLocation(xGrid * 19, (int) (yGrid * 7));
            this.type.setSize(xGrid * 14, yGrid * 2);

            this.secondaryType = new JLabel();
            this.secondaryType.setLocation(xGrid * 26, (int) (yGrid * 7));
            this.secondaryType.setSize(xGrid * 14, yGrid * 2);

            // LAYERS
            add(background, new Integer(0));
            add(this.jTextAreaDescription, new Integer(1));
            add(this.specieIdAndName, new Integer(2));
            add(this.type, new Integer(3));
            add(this.secondaryType, new Integer(4));

        } catch (IOException ex) {
            utilities.DisplayMessage.showErrorDialog(ex.getLocalizedMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }

    public void refresh() {
        repaint();
    }

}
