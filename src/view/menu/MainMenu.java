package view.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import languajes.ButtonTexts;
import languajes.LabelTexts;
import utilities.image.Dimensions;
import view.components.ButtonFactory;
import view.components.AidPanel;
import view.components.CustomButton;
import view.menu.components.AnimatedBackgroundPanel;

/**
 *
 * @author Adrian Vazquez
 */
public class MainMenu extends JFrame {

    private static int grid;

    private AidPanel panel;
    private CustomButton singlePlayer;
    private CustomButton multiPlayer;
    private CustomButton settings;
    private CustomButton credits;
    private CustomButton saveAndExit;

    private boolean resizing = false;

    public MainMenu() {
        try {
            initComponents();

        } catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initComponents() throws Exception {

        if (panel != null) {
            remove(panel);
        }

        panel = new AnimatedBackgroundPanel(Dimensions.getSelectedResolution());

        singlePlayer = ButtonFactory.menuButton(ButtonTexts.getInstance().singlePlayerButton());
        multiPlayer = ButtonFactory.menuButton(ButtonTexts.getInstance().multiPlayerButton());
        settings = ButtonFactory.menuButton(ButtonTexts.getInstance().settingsButton());
        credits = ButtonFactory.menuButton(ButtonTexts.getInstance().creditsButton());
        saveAndExit = ButtonFactory.menuButton(ButtonTexts.getInstance().saveAndExit());

        grid = (int) (Dimensions.getSelectedResolution().getWidth() * 0.03f);

        if (grid == 0) {
            grid = 1;
        }

        //Button actions
        //Button placing
        panel.setLayout(null);

        setMainMenuButtons();

        panel.add(singlePlayer);

        panel.add(multiPlayer);

        panel.add(settings);

        panel.add(credits);

        panel.add(saveAndExit);

        //Author text
        Dimension d = new Dimension(18 * grid, 2 * grid);
        Font f = view.components.fonts.PokemonFont.getFont(16);
        int fontSize = (int) utilities.string.StringUtil.preferedFontSizeforLabel(f, LabelTexts.getInstance().developedByAdrianVazquezBarrera(), d);

        f = f.deriveFont(fontSize);

        JLabel developedByAdrianVazquezBarreraLabel = new JLabel(LabelTexts.getInstance().developedByAdrianVazquezBarrera());

        developedByAdrianVazquezBarreraLabel.setFont(f);

        developedByAdrianVazquezBarreraLabel.setForeground(Color.WHITE);

        developedByAdrianVazquezBarreraLabel.setLocation(0, 16 * grid);
        developedByAdrianVazquezBarreraLabel.setSize(d.width, d.height);

        panel.add(developedByAdrianVazquezBarreraLabel);

        //Pokemon logo
        BufferedImage logo = ImageIO.read(new File("Resources/MainTitle/pokemonLogo/mainTitleLogo.png"));
        logo = utilities.image.ImageUtil.resize(logo, 12 * grid, 7 * grid);
        JLabel pokemonLogo = new JLabel(new ImageIcon(logo));

        pokemonLogo.setSize(12 * grid, 7 * grid);
        panel.add(pokemonLogo);

        //Panel and frame settings
        add(panel);

        setSize(Dimensions.getSelectedResolution());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

    private void setMainMenuButtons() {

        setPosition(singlePlayer, 22 * grid, (grid));

        setPosition(multiPlayer, 22 * grid, 4 * grid);

        setPosition(settings, 22 * grid, 7 * grid);

        setPosition(credits, 22 * grid, 10 * grid);

        setPosition(saveAndExit, 22 * grid, 13 * grid);
    }

    private void setPosition(CustomButton cb, int x, int y) {

        int width = (int) cb.getBounds().getWidth();
        int height = (int) cb.getBounds().getHeight();

        cb.setBounds(x, y, width, height);

    }

}
