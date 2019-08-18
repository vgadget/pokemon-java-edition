package view.pokedex;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import model.entities.Specie;
import model.entities.Sprite;
import model.entities.Type;
import utilities.image.RGB;
import utilities.sound.Sound;
import view.pokedex.components.SpriteJLabel;       //Hay un bug: Las voces se mezclan

/**
 *
 * @author Adrian Vazquez
 */
public class PokedexEntryView extends JLayeredPane {

    private static final String URI_POKEDEX_ENTRY_BACKGROUND = "Resources\\BattleHUD\\Pokedex\\INFO_SCREEN\\pokedexEntry.PNG";
    private static final String URI_BUTTON_BATTLE = "Resources/BattleHUD/Button/button.png"; // I will recycle battle button as type tag XD
    private static final String URI_BUTTON_BATTLE_BACKGROUND = "Resources/BattleHUD/Button/buttonBackground.png";

    /*
        IMPORTANT COMMENT
    
        To fix sizes and component location for every resolution. I will use a grid (in this case the same grid as the background image)
        On the real image every grid has 0.56 pixels of width and height. It represents arround 3.10 percent over all the width of the image and 4.78 over the heigth.
        
        For simplicity I will use the X percentage of the grid to be more precise.
    
        So, with a simple Math operation I Will have a precise grid to place every component on the panel. They will be xGrid and yGrid and will be initialised on setup() methow.
        xGrid and yGrid will be equal.
     */
    int xGrid, yGrid;

    private BufferedImage pokedexEntryBackground;
    private Specie specie;

    // POKEMON SPECIE INFO COMPONENTS
    private JTextArea jTextAreaDescription;
    private JLabel specieIdAndName;
    private JLabel type;
    private JLabel secondaryType;
    private JLabel height;
    private JLabel weight;
    private SpriteJLabel sprite;
    private JButton cryButton;

    private Dimension size;

    public PokedexEntryView() {

        pokedexEntryBackground = null;
        setup();
        

    }

    public synchronized void setSpecie(Specie s) {

        if (s != null) {
            this.specie = s;

//            Thread t1, t2, t3; // To optimice load
//
//            t1 = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    setSprite(specie.getFrontSprite());
//                }
//            });
//
//            t2 = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    setType(specie.getType());
//                    setSecondaryType(specie.getSecondaryType());
//                    setPokedexIdAndName(specie.getPokedexID() + "", specie.getName());
//
//                }
//            });
//
//            t3 = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    setCry(specie.getCry());
//                    setHeightAndWeight(specie.getHeight(), specie.getWeight());
//                    setDescription(specie.getDescription());
//
//                }
//            });
//
//            t1.start();
//            t2.start();
//            t3.start();
//
//            try {
//                t2.join();
//                t3.join();
//                t1.join();
//
//            } catch (InterruptedException ex) {
//                Logger.getLogger(PokedexEntryView.class.getName()).log(Level.SEVERE, null, ex);
//            }

            setDescription(this.specie.getDescription());
            setPokedexIdAndName(this.specie.getPokedexID() + "", specie.getName());
            setType(this.specie.getType());
            setSecondaryType(this.specie.getSecondaryType());
            setHeightAndWeight(this.specie.getHeight(), specie.getWeight());
            setSprite(this.specie.getFrontSprite());
            setCry(this.specie.getCry());

            refresh();
        }
    }

    private synchronized void setCry(Sound cry) {

        for (ActionListener al : this.cryButton.getActionListeners()) {
            this.cryButton.removeActionListener(al);
        }

        this.cryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                utilities.sound.SoundPlayer.getInstance().playEffectChannel(cry);
            }
        });
    }

    private void setSprite(Sprite s) {

        BufferedImage animation[] = s.getAnimation();

        for (int i = 0; i < animation.length; i++) {
            animation[i] = utilities.image.ImageUtil.resize(animation[i], (int) this.sprite.getSize().getHeight(), (int) this.sprite.getSize().getWidth());
        }

        try {
            this.sprite.setup(new Sprite(animation, s.getRefreshRate()));

        } catch (Exception ex) {
            Logger.getLogger(PokedexEntryView.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setHeightAndWeight(Float heigt, Float weight) {

        String h = "" + heigt;
        String w = "" + weight;

        Font f = view.components.fonts.PokemonFont.getFont(1);

        float fontSize = utilities.string.StringUtil.preferedFontSizeforLabel(f, w, specieIdAndName.getSize());

        f = view.components.fonts.PokemonFont.getFont((int) fontSize);

        this.height.setFont(f);
        this.weight.setFont(f);

        this.height.setText(h);
        this.weight.setText(w);

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
            Logger.getLogger(PokedexEntryView.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PokedexEntryView.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setPokedexIdAndName(String pokedexId, String name) {

        String text = " " + pokedexId + "   " + name;

        Font f = view.components.fonts.PokemonFont.getFont(1);

        Dimension labelSize = new Dimension((int) (specieIdAndName.getSize().width * 0.80), specieIdAndName.getSize().height);

        float fontSize = utilities.string.StringUtil.preferedFontSizeforLabel(f, text, labelSize);

        f = view.components.fonts.PokemonFont.getFont((int) fontSize);

        this.specieIdAndName.setFont(f);

        this.specieIdAndName.setText(text);

    }

    private void setDescription(String text) {

        Font f = view.components.fonts.PokemonFont.getFont(12);

        int w = this.pokedexEntryBackground.getWidth();

        int h = (int) (this.pokedexEntryBackground.getHeight() * (0.18f));

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

            pokedexEntryBackground = utilities.image.ImageUtil.resizeProportional(pokedexEntryBackground, 0.85f * ((utilities.image.Dimensions.getSelectedResolution().getHeight()) / (this.pokedexEntryBackground.getHeight())));

            this.size = new Dimension(pokedexEntryBackground.getWidth() + offset, pokedexEntryBackground.getHeight() + offset);
            this.setPreferredSize(size);

            JLabel background = new JLabel(new ImageIcon(pokedexEntryBackground));

            background.setSize(this.getPreferredSize());

            setOpaque(true);
            setBackground(new Color(57, 57, 57));

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

            h = ((int) (this.pokedexEntryBackground.getHeight() * (0.45f)));

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

            //HEIGHT AND WEIGHT
            this.height = new JLabel();
            this.weight = new JLabel();
            this.height.setLocation(xGrid * 28, (int) (yGrid * (10)));
            this.weight.setLocation(xGrid * 28, (int) (yGrid * 12));
            this.height.setSize(xGrid * 8, (int) (yGrid * (1.4f)));
            this.weight.setSize(xGrid * 8, (int) (yGrid * (1.4f)));

            // SPRITE
            this.sprite = new SpriteJLabel();
            this.sprite.setLocation(xGrid * 3, yGrid);
            this.sprite.setSize(xGrid * 10, yGrid * 10);

            // CRY BUTTON
            this.cryButton = new JButton();
            this.cryButton.setLocation((int) (xGrid * 13.8), (int) (yGrid * 6.65));
            this.cryButton.setSize(xGrid * 4, yGrid * 4);
            this.cryButton.setOpaque(false);
            this.cryButton.setContentAreaFilled(false);
            this.cryButton.setBorderPainted(false);

            // PANEL LAYERS
            add(background, new Integer(0));
            add(this.jTextAreaDescription, new Integer(1));
            add(this.specieIdAndName, new Integer(2));
            add(this.type, new Integer(3));
            add(this.secondaryType, new Integer(4));
            add(this.height, new Integer(5));
            add(this.weight, new Integer(6));
            add(this.sprite, new Integer(7));
            add(this.cryButton, new Integer(8));

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

    public Specie getSpecie() {
        return specie;
    }

    @Override
    public Dimension getSize() {
        return size;
    }

}
