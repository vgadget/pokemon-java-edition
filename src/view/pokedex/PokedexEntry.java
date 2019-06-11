package view.pokedex;

import controller.SpecieController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import model.SpecieModel;
import model.entities.Specie;
import view.menu.components.AnimatedBackgroundPanel;

/**
 *
 * @author Adrian Vazquez
 */
public class PokedexEntry extends JLayeredPane {

    private static final String URI_POKEDEX_ENTRY_BACKGROUND = "Resources\\BattleHUD\\Pokedex\\INFO_SCREEN\\pokedexEntry.PNG";

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

    public PokedexEntry() {

        pokedexEntryBackground = null;

        setup();
    }

    public void setSpecie(Specie specie) {

        this.specie = specie;

        setDescription(this.specie.getDescription());
        setPokedexIdAndName(specie.getPokedexID() + "", specie.getName());

        refresh();
    }

    private void setPokedexIdAndName(String pokedexId, String name) {

        String text = " " + pokedexId + "   " + name;
        
       

        Font f = view.components.fonts.PokemonFont.getFont(1);

        int w = xGrid*12;

        int h = yGrid*2;

        float fontSize = utilities.string.StringUtil.preferedFontSizeforLabel(f, text, new Dimension(w, h));
                
        f = view.components.fonts.PokemonFont.getFont((int) fontSize);
        
        this.specieIdAndName.setFont(f);

        this.specieIdAndName.setText(text);

    }

    private void setDescription(String text) {

        Font f = view.components.fonts.PokemonFont.getFont(12);

        int w = this.pokedexEntryBackground.getWidth();

        int h = yGrid*3;

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

            this.specieIdAndName.setLocation(xGrid * 17, (int)(yGrid*1.45));
            this.specieIdAndName.setSize(xGrid * 14, yGrid *2);

            // LAYERS
            add(background, new Integer(0));
            add(this.jTextAreaDescription, new Integer(1));
            add(this.specieIdAndName, new Integer(10));

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
