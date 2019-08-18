package view.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import languajes.ButtonTexts;
import languajes.LabelTexts;
import texttospeech.Narrator;
import utilities.image.Dimensions;
import view.MainFrame;
import view.components.ButtonFactory;
import view.components.AidPanel;
import view.components.CustomButton;
import view.menu.components.AnimatedBackgroundPanel;

/**
 *
 * @author Adrian Vazquez
 */
public class MainMenu {

    private static int grid;

    private AidPanel panel;

    // Main menu buttons.
    private CustomButton singlePlayerMenuButton;
    private CustomButton multiPlayerMenuButton;
    private CustomButton settingsMenuButton;
    private CustomButton creditsMenuButton;
    private CustomButton saveAndExitMenuButton;

    //Single player menu buttons.
    private CustomButton singlePlayerBattle;
    private CustomButton team;
    private CustomButton catchPokemon;
    private CustomButton pokedex;
    private CustomButton goBackFromSinglePlayerMenuToMainMenu;

    //Multiplayer menu buttons.
    private CustomButton localMultiplayer;
    private CustomButton onlineMultiplayer;
    private CustomButton goBackFromMultiPlayerMenuToMainMenu;
    
    
    //Know if main buttons are ready
    private boolean mainButtonsReady;

    //Semaphore
    private Semaphore mutex; //Mutex that control the synchronization of the buttons when is on animation

    public MainMenu() {

        mutex = new Semaphore(1);

        try {

            initComponents();

        } catch (Exception ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initComponents() throws Exception {

        panel = new AnimatedBackgroundPanel(Dimensions.getSelectedResolution());

        panel.setLayout(null);

        panel.setPreferredSize(Dimensions.getSelectedResolution());

        grid = (int) (Dimensions.getSelectedResolution().getWidth() * 0.03f);

        if (grid == 0) {
            grid = 1;
        }

        //Set buttons
        setMainMenuButtons();
        setSinglePlayerMenuButtons();
        setMultiplayerMenuButtons();

        //Author text
        Dimension d = new Dimension(Dimensions.getSelectedResolution().width, 1 * grid);
        Font f = view.components.fonts.PokemonFont.getFont(18);
        int fontSize = (int) utilities.string.StringUtil.preferedFontSizeforLabel(f, LabelTexts.getInstance().developedByAdrianVazquezBarrera(), d);

        f = f.deriveFont(fontSize);

        JLabel developedByAdrianVazquezBarreraLabel = new JLabel(LabelTexts.getInstance().developedByAdrianVazquezBarrera());

        developedByAdrianVazquezBarreraLabel.setFont(f);

        developedByAdrianVazquezBarreraLabel.setForeground(Color.WHITE);

        developedByAdrianVazquezBarreraLabel.setLocation(0, Dimensions.getSelectedResolution().height - d.height);
        developedByAdrianVazquezBarreraLabel.setSize(d.width, d.height);

        developedByAdrianVazquezBarreraLabel.setOpaque(true);
        developedByAdrianVazquezBarreraLabel.setBackground(new Color(0, 0, 0, 200));

        panel.add(developedByAdrianVazquezBarreraLabel);

        //Pokemon logo
        BufferedImage logo = ImageIO.read(new File("Resources/MainTitle/pokemonLogo/mainTitleLogo.png"));
        logo = utilities.image.ImageUtil.resize(logo, 12 * grid, 7 * grid);
        JLabel pokemonLogo = new JLabel(new ImageIcon(logo));

        pokemonLogo.setSize(12 * grid, 7 * grid);
        panel.add(pokemonLogo);

        
        mainButtonsReady = false;
        
        showMainMenuButtons();
        
        while(!mainButtonsReady){
            Thread.sleep(1);
        }
        
        //Panel and frame settings
        MainFrame.getInstance().showView(panel);

    }

    private void setMainMenuButtons() {

        singlePlayerMenuButton = ButtonFactory.menuButton(ButtonTexts.getInstance().singlePlayerButton());
        multiPlayerMenuButton = ButtonFactory.menuButton(ButtonTexts.getInstance().multiPlayerButton());
        settingsMenuButton = ButtonFactory.menuButton(ButtonTexts.getInstance().settingsButton());
        creditsMenuButton = ButtonFactory.menuButton(ButtonTexts.getInstance().creditsButton());
        saveAndExitMenuButton = ButtonFactory.menuButton(ButtonTexts.getInstance().saveAndExit());

        // Position
        setPosition(singlePlayerMenuButton, 22 * grid, (grid));

        setPosition(multiPlayerMenuButton, 22 * grid, 4 * grid);

        setPosition(settingsMenuButton, 22 * grid, 7 * grid);

        setPosition(creditsMenuButton, 22 * grid, 10 * grid);

        setPosition(saveAndExitMenuButton, 22 * grid, 13 * grid);

        //Set actions
        singlePlayerMenuButton.addActionListener((ActionEvent e) -> {

            hideMainMenuButtons();

            showSinglePlayerMenuButtons();

        });

        multiPlayerMenuButton.addActionListener((ActionEvent e) -> {

            hideMainMenuButtons();
            showMultiPlayerMenuButtons();
        });

        settingsMenuButton.addActionListener((ActionEvent e) -> {

            //hideMainMenuButtons();
        });

        creditsMenuButton.addActionListener((ActionEvent e) -> {

            //hideMainMenuButtons();
        });
        saveAndExitMenuButton.addActionListener((ActionEvent e) -> {

            hideMainMenuButtons();
            System.exit(0);
        });

    }

    private void setSinglePlayerMenuButtons() {

        singlePlayerBattle = ButtonFactory.menuButton(ButtonTexts.getInstance().battle());
        team = ButtonFactory.menuButton(ButtonTexts.getInstance().team());
        catchPokemon = ButtonFactory.menuButton(ButtonTexts.getInstance().catchPokemon());
        pokedex = ButtonFactory.menuButton(ButtonTexts.getInstance().pokedex());
        goBackFromSinglePlayerMenuToMainMenu = ButtonFactory.menuButton(ButtonTexts.getInstance().goBack());

        // Position
        setPosition(singlePlayerBattle, 22 * grid, (grid));

        setPosition(team, 22 * grid, 4 * grid);

        setPosition(catchPokemon, 22 * grid, 7 * grid);

        setPosition(pokedex, 22 * grid, 10 * grid);

        setPosition(goBackFromSinglePlayerMenuToMainMenu, 22 * grid, 13 * grid);

        //Actions
        pokedex.addActionListener((ActionEvent e) -> {
            main.Main.specieController.start();

        });

        goBackFromSinglePlayerMenuToMainMenu.addActionListener((ActionEvent e) -> {

            hideSinglePlayerMenuButtons();
            showMainMenuButtons();
        });

    }

    private void setMultiplayerMenuButtons() {

        localMultiplayer = ButtonFactory.menuButton(ButtonTexts.getInstance().localMultiplayer());
        onlineMultiplayer = ButtonFactory.menuButton(ButtonTexts.getInstance().online());
        goBackFromMultiPlayerMenuToMainMenu = ButtonFactory.menuButton(ButtonTexts.getInstance().goBack());

        // Position
        setPosition(localMultiplayer, 22 * grid, (grid));

        setPosition(onlineMultiplayer, 22 * grid, 4 * grid);

        setPosition(goBackFromMultiPlayerMenuToMainMenu, 22 * grid, 7 * grid);

        //Actions
        goBackFromMultiPlayerMenuToMainMenu.addActionListener((ActionEvent e) -> {
            hideMultiPlayerMenuButtons();
            showMainMenuButtons();
        });
    }

    private void hideMultiPlayerMenuButtons() {

        new Thread(() -> {

            for (int i = 22; i < 40; i++) {

                setPosition(localMultiplayer, i * grid, (grid));

                setPosition(onlineMultiplayer, i * grid, 4 * grid);

                setPosition(pokedex, i * grid, 7 * grid);

                setPosition(goBackFromMultiPlayerMenuToMainMenu, i * grid, 7 * grid);

                try {
                    Thread.sleep(20);
                    panel.repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            panel.remove(localMultiplayer);
            panel.remove(onlineMultiplayer);
            panel.remove(goBackFromMultiPlayerMenuToMainMenu);
            mutex.release();

        }).start();
    }

    private void showMultiPlayerMenuButtons() {

        new Thread(() -> {

            try {
                mutex.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

            panel.add(localMultiplayer);
            panel.add(onlineMultiplayer);
            panel.add(goBackFromMultiPlayerMenuToMainMenu);

            for (int i = 40; i >= 22; i--) {

                setPosition(localMultiplayer, i * grid, (grid));

                setPosition(onlineMultiplayer, i * grid, 4 * grid);

                setPosition(pokedex, i * grid, 7 * grid);

                setPosition(goBackFromMultiPlayerMenuToMainMenu, i * grid, 7 * grid);

                try {
                    Thread.sleep(20);
                    panel.repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }).start();
    }

    private void showSinglePlayerMenuButtons() {

        new Thread(() -> {

            try {
                mutex.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

            panel.add(singlePlayerBattle);
            panel.add(team);
            panel.add(catchPokemon);
            panel.add(pokedex);
            panel.add(goBackFromSinglePlayerMenuToMainMenu);

            for (int i = 40; i >= 22; i--) {

                setPosition(singlePlayerBattle, i * grid, (grid));

                setPosition(team, i * grid, 4 * grid);

                setPosition(catchPokemon, i * grid, 7 * grid);

                setPosition(pokedex, i * grid, 10 * grid);

                setPosition(goBackFromSinglePlayerMenuToMainMenu, i * grid, 13 * grid);

                try {
                    Thread.sleep(20);
                    panel.repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

    }

    private void hideSinglePlayerMenuButtons() {

        new Thread(() -> {

            for (int i = 22; i < 40; i++) {

                setPosition(singlePlayerBattle, i * grid, (grid));

                setPosition(team, i * grid, 4 * (grid));

                setPosition(catchPokemon, i * grid, 7 * grid);

                setPosition(pokedex, i * grid, 10 * grid);

                setPosition(goBackFromSinglePlayerMenuToMainMenu, i * grid, 13 * grid);

                try {
                    Thread.sleep(20);
                    panel.repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            panel.remove(singlePlayerBattle);
            panel.remove(catchPokemon);
            panel.remove(pokedex);
            panel.remove(team);
            panel.remove(goBackFromSinglePlayerMenuToMainMenu);

            mutex.release();

        }).start();

    }

    private void showMainMenuButtons() {

        new Thread(() -> {

            try {
                mutex.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

            panel.add(singlePlayerMenuButton);
            panel.add(multiPlayerMenuButton);
            panel.add(settingsMenuButton);
            panel.add(creditsMenuButton);
            panel.add(saveAndExitMenuButton);

            for (int i = 40; i >= 22; i--) {

                setPosition(singlePlayerMenuButton, i * grid, (grid));

                setPosition(multiPlayerMenuButton, i * grid, 4 * grid);

                setPosition(settingsMenuButton, i * grid, 7 * grid);

                setPosition(creditsMenuButton, i * grid, 10 * grid);

                setPosition(saveAndExitMenuButton, i * grid, 13 * grid);

                try {
                    Thread.sleep(20);
                    panel.repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            mainButtonsReady = true;
        }).start();

    }

    private void hideMainMenuButtons() {

        new Thread(() -> {

            for (int i = 22; i < 40; i++) {

                setPosition(singlePlayerMenuButton, i * grid, (grid));

                setPosition(multiPlayerMenuButton, i * grid, 4 * grid);

                setPosition(settingsMenuButton, i * grid, 7 * grid);

                setPosition(creditsMenuButton, i * grid, 10 * grid);

                setPosition(saveAndExitMenuButton, i * grid, 13 * grid);

                try {
                    Thread.sleep(20);
                    panel.repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            panel.remove(singlePlayerMenuButton);
            panel.remove(multiPlayerMenuButton);
            panel.remove(settingsMenuButton);
            panel.remove(creditsMenuButton);
            panel.remove(saveAndExitMenuButton);

            mutex.release();

        }).start();
    }

    private void setPosition(CustomButton cb, int x, int y) {

        int width = (int) cb.getBounds().getWidth();
        int height = (int) cb.getBounds().getHeight();

        cb.setBounds(x, y, width, height);

    }

}
