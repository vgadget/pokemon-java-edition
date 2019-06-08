package view.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
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

    ;
    private static int START_POINT_Y; // Position in Y coordinate where components start being placed.
    private static int START_POINT_X; // Position in X coordinate where components are aligned.

    private AidPanel panel = new AnimatedBackgroundPanel(Dimensions.getMainMenuResolution());
    private CustomButton singlePlayer = ButtonFactory.menuButton(ButtonTexts.getInstance().singlePlayerButton());
    private CustomButton multiPlayer = ButtonFactory.menuButton(ButtonTexts.getInstance().multiPlayerButton());
    private CustomButton settings = ButtonFactory.menuButton(ButtonTexts.getInstance().settingsButton());
    private CustomButton credits = ButtonFactory.menuButton(ButtonTexts.getInstance().creditsButton());
    private CustomButton saveAndExit = ButtonFactory.menuButton(ButtonTexts.getInstance().saveAndExit());

    public MainMenu() {
        try {
            initComponents();
        } catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initComponents() throws Exception {

        panel.setLayout(null);

        //Button placing
        Dimension buttonSize = singlePlayer.getSize();

        START_POINT_Y = (int) (buttonSize.height / 2);
        START_POINT_X = (int) (utilities.image.Dimensions.getMainMenuResolution().getWidth() - (buttonSize.width + buttonSize.width / 6));

        setPosition(singlePlayer, START_POINT_X, START_POINT_Y);
        panel.add(singlePlayer);

        setPosition(multiPlayer, START_POINT_X, START_POINT_Y + singlePlayer.getBounds().height);
        panel.add(multiPlayer);

        setPosition(settings, START_POINT_X, START_POINT_Y + 2 * singlePlayer.getBounds().height);
        panel.add(settings);

        setPosition(credits, START_POINT_X, START_POINT_Y + 3 * singlePlayer.getBounds().height);
        panel.add(credits);

        setPosition(saveAndExit, START_POINT_X, START_POINT_Y + 4 * singlePlayer.getBounds().height);
        panel.add(saveAndExit);

        //Author text
        Font f = view.components.fonts.PokemonFont.getFont(16);
        
        JLabel developedByAdrianVazquezBarreraLabel = new JLabel(LabelTexts.getInstance().developedByAdrianVazquezBarrera());
        developedByAdrianVazquezBarreraLabel.setFont(f);
        developedByAdrianVazquezBarreraLabel.setForeground(Color.WHITE);
        
        FontMetrics fm = developedByAdrianVazquezBarreraLabel.getFontMetrics(developedByAdrianVazquezBarreraLabel.getFont());
        developedByAdrianVazquezBarreraLabel.setLocation(0, Dimensions.getMainMenuResolution().height - 3*fm.getHeight());
        developedByAdrianVazquezBarreraLabel.setSize(fm.stringWidth(LabelTexts.getInstance().developedByAdrianVazquezBarrera()), fm.getHeight());
        panel.add(developedByAdrianVazquezBarreraLabel);
    
        //Pokemon logo
        JLabel pokemonLogo = new JLabel(new ImageIcon(ImageIO.read(new File("Resources/MainTitle/pokemonLogo/mainTitleLogo.png"))));
        pokemonLogo.setSize(448, 258);
        panel.add(pokemonLogo);
        
        //Panel and frame settings
        add(panel);
        setSize(Dimensions.getMainMenuResolution());
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setPosition(CustomButton cb, int x, int y) {

        int width = (int) cb.getBounds().getWidth();
        int height = (int) cb.getBounds().getHeight();

        cb.setBounds(x, y, width, height);

    }

}
