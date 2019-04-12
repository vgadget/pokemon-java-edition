package view.menu;

import javax.swing.JFrame;
import languajes.ButtonText;
import utilities.Dimensions;
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
    private static final int START_POINT = -Dimensions.getMainMenuResolution().height / 6;

    private AidPanel panel = new AnimatedBackgroundPanel(Dimensions.getMainMenuResolution());
    private CustomButton singlePlayer = ButtonFactory.menuButton(ButtonText.singlePlayerButton());
    private CustomButton multiPlayer = ButtonFactory.menuButton(ButtonText.multiPlayerButton());
    private CustomButton settings = ButtonFactory.menuButton(ButtonText.settingsButton());
    private CustomButton credits = ButtonFactory.menuButton(ButtonText.creditsButton());
    private CustomButton saveAndExit = ButtonFactory.menuButton(ButtonText.saveAndExit());

    public MainMenu() {
        initComponents();
    }

    public void initComponents() {

        panel.setLayout(null);

        setPosition(singlePlayer, 0, START_POINT);
        panel.add(singlePlayer);

        setPosition(multiPlayer, 0, START_POINT + singlePlayer.getBounds().height);
        panel.add(multiPlayer);

        setPosition(settings, 0, START_POINT + 2 * singlePlayer.getBounds().height);
        panel.add(settings);

        setPosition(credits, 0, START_POINT + 3 * singlePlayer.getBounds().height);
        panel.add(credits);

        setPosition(saveAndExit, 0, START_POINT + 4 * singlePlayer.getBounds().height);
        panel.add(saveAndExit);

        add(panel);
        setSize(Dimensions.getMainMenuResolution());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setPosition(CustomButton cb, int x, int y) {

        x += Dimensions.getSelectedResolution().width / 2;
        y += Dimensions.getSelectedResolution().height / 2;

        int width = (int) cb.getBounds().getWidth();
        int height = (int) cb.getBounds().getHeight();

        int newX = (x - (width / 2));
        int newY = (y - (height / 2));

        cb.setBounds(newX, newY, width, height);

    }

}
