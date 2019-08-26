package view.pokemon;

import view.AbstractView;
import controller.PokemonController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import languajes.LabelTexts;
import model.PokemonModel;
import model.entities.Pokemon;
import model.entities.Sprite;
import texttospeech.Narrator;
import utilities.datastructures.DataPool;
import utilities.image.Image;
import utilities.string.StringComparator;
import view.components.AidPanelImpl;
import view.components.AidView;
import view.components.AnimatedBackgroundPanel;
import view.components.ButtonFactory;
import view.components.CustomButton;
import view.components.Musical;

/**
 *
 * @author Adrian Vazquez
 */
public class PokemonView extends AbstractView<PokemonModel, PokemonController> implements AidView, Musical {

    private static final String URI_POKEMON_BOX = "Resources/Pokemon/PokemonBox/pokemonBox.png";
    private static final String URI_LOADING = "Resources/Pokemon/PokemonBox/LoadingAnimation/PNG";

    private Integer grid;

    //Loading animation
    private Dimension frameSize;
    private BufferedImage boxLayout;
    private Sprite loadingAnimation;
    private int currentLoadingSpriteAnimation;
    private int loadingAnimationPosX, loadingAnimationPosY;
    private boolean showLoadingAnimation;

    //Current showing pokemon
    private Pokemon pokemon;
    private int currentPokemonSpriteAnimation;
    private Dimension pokemonSpriteDimension;
    private int pokemonSpriteLocationX, pokemonSpriteLocationY;
    private JLabel pokemonNameLabel;

    // Pre-Loaded pokemons
    private DataPool<Pokemon, String> preloadedPokemonsPool;
    private final int MAX_PRELOAD = 5;
    private List<String> allPokemonPk;

    // Allow button action Button delay.
    private boolean action;

    //Aids
    private AidPanelImpl aidPanel;

    //Semaphore
    private Semaphore mutex; // Binary mutex to controll preloads

    //Repainter    
    private Thread imageUpdater;

    //Background
    private AnimatedBackgroundPanel animatedBackgroundPanel;

    public PokemonView(PokemonController pokemonController) {

        setController(pokemonController);
        setModel(pokemonController.getModel());

        setup();
    }

    private void setup() {

        this.setLayout(null);

        for (Component c : getComponents()) {
            remove(c);
        }

        this.allPokemonPk = getModel().getAllPk();

        Collections.sort(allPokemonPk, new utilities.string.StringComparator());

        boolean aidsActivated = Narrator.getInstance().isEnabled();
        Narrator.getInstance().setEnabled(false);
        this.aidPanel = new AidPanelImpl();
        this.add(getInputDetector());
        animatedBackgroundPanel = new AnimatedBackgroundPanel();
        animatedBackgroundPanel.setEnableAudioDescriptions(false);
        Narrator.getInstance().setEnabled(aidsActivated);

        if (imageUpdater != null) {
            imageUpdater.interrupt();
        }

        frameSize = utilities.image.Dimensions.getSelectedResolution();

        grid = (int) (frameSize.getWidth() * 0.003f);

        this.pokemonSpriteDimension = new Dimension(grid * 80, grid * 80);
        this.pokemonSpriteLocationX = ((frameSize.width - pokemonSpriteDimension.width) / 2);
        this.pokemonSpriteLocationY = ((frameSize.height - pokemonSpriteDimension.height) / 2);

        try {
            boxLayout = utilities.image.ImageUtil.resize((ImageIO.read(new File(URI_POKEMON_BOX))), frameSize.width, frameSize.height);
        } catch (IOException ex) {
        }

        // SET LOADING POKEBALL
        currentLoadingSpriteAnimation = 0;
        List<File> files = Arrays.asList(new File(URI_LOADING)
                .listFiles((File pathname) -> {

                    return pathname.isFile() && pathname.getName().toLowerCase().endsWith("png");
                }));

        StringComparator comp = new StringComparator();

        Collections.sort(files, (File o1, File o2) -> {

            return comp.compare(o1.getName(), o2.getName());

        });

        List<Image> animation = new LinkedList<>();

        files.forEach((File f) -> {

            try {
                BufferedImage img = ImageIO.read(f);
                img = utilities.image.ImageUtil.resize(img, grid * 12, grid * 12);
                animation.add(new Image(img));
            } catch (IOException ex) {
                utilities.displaymessage.DisplayMessage.showErrorDialog(ex.getLocalizedMessage());
            }
        });

        try {

            loadingAnimation = new Sprite(animation, 10);

            loadingAnimationPosX = (int) ((frameSize.width - loadingAnimation.getImages().get(0).getImage().getWidth()) / 2);
            loadingAnimationPosY = (int) ((frameSize.height - loadingAnimation.getImages().get(0).getImage().getHeight()) / 2);
        } catch (Exception ex) {
            utilities.displaymessage.DisplayMessage.showErrorDialog(ex.getLocalizedMessage());
        }

        // Set the image updater
        imageUpdater = new Thread(() -> {

            Thread pokemonSpriteAnimationThread = new Thread(() -> {

                boolean backwards = false;

                while (!Thread.interrupted()) {

                    try {
                        if (pokemon != null) {

                            if (backwards) {

                                if (currentPokemonSpriteAnimation > 0) {
                                    currentPokemonSpriteAnimation--;
                                } else {
                                    backwards = false;
                                }

                            }

                            if (!backwards) {

                                if (currentPokemonSpriteAnimation < pokemon.getSpecie().getFrontSprite().getImages().size() - 1) {

                                    currentPokemonSpriteAnimation++;
                                } else {
                                    backwards = true;
                                }
                            }
                            Thread.sleep(pokemon.getSpecie().getFrontSprite().getRefreshRate());
                        }

                    } catch (Exception e) {

                    }

                }

            });

            Thread loadingAnimationThread = new Thread(() -> {

                while (!Thread.interrupted()) {

                    try {

                        if (currentLoadingSpriteAnimation < loadingAnimation.getImages().size() - 1) {
                            currentLoadingSpriteAnimation++;
                        } else {
                            currentLoadingSpriteAnimation = 0;
                        }

                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PokemonView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

            loadingAnimationThread.start();
            pokemonSpriteAnimationThread.start();

            while (!Thread.interrupted()) {

                try {
                    repaint();
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
            }

            loadingAnimationThread.interrupt();
            pokemonSpriteAnimationThread.interrupt();
        });

        imageUpdater.start();

        //Pokemon name JLabel
        this.pokemonNameLabel = new JLabel();
        this.pokemonNameLabel.setForeground(Color.WHITE);
        this.pokemonNameLabel.setSize(grid * 150, grid * 27);
        this.pokemonNameLabel.setLocation(grid * 15, 0);
        add(this.pokemonNameLabel);
        this.aidPanel.addAidComponent(this.pokemonNameLabel);

        try {

            //Navigation arrrows
            Dimension navigationButtonSize = new Dimension(grid * 25, grid * 25);

            CustomButton nextPokemonButton = ButtonFactory.rightArrowButton(navigationButtonSize);

            CustomButton previousPokemonButton = ButtonFactory.leftArrowButton(navigationButtonSize);

            nextPokemonButton.setLocation(grid * 250, grid * 95);
            previousPokemonButton.setLocation(grid * 112, grid * 95);

            nextPokemonButton.addActionListener((ActionEvent e) -> {
                new Thread(() -> {

                    if (action) {

                        nextPokemon();

                        new Thread(() -> {

                            action = false;

                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException ex) {
                            }

                            action = true;

                        }).start();

                    }

                }).start();
            });

            previousPokemonButton.addActionListener((ActionEvent e) -> {
                new Thread(() -> {

                    if (action) {

                        previousPokemon();

                        new Thread(() -> {

                            action = false;

                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException ex) {
                            }

                            action = true;

                        }).start();

                    }

                }).start();
            });

            add(nextPokemonButton);
            add(previousPokemonButton);
            this.aidPanel.addAidComponent(previousPokemonButton);
            this.aidPanel.addAidComponent(nextPokemonButton);

            this.action = true;

        } catch (Exception ex) {
        }

        //Pokemon management
        this.mutex = new Semaphore(1);
        this.preloadedPokemonsPool = new DataPool<>(2 * MAX_PRELOAD);
        
        Thread t = setPokemon(getModel().getEntity(getModel().getAllPk().get(0)));
        
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
        }

    }

    @Override
    public void paintComponent(Graphics g) {

        animatedBackgroundPanel.paintComponent(g);

        g.drawImage(boxLayout, 0, 0, this);

        if (pokemon != null && !showLoadingAnimation) {
            g.drawImage(pokemon.getSpecie().getFrontSprite().getImages().get(currentPokemonSpriteAnimation).getImage(),
                    pokemonSpriteLocationX, pokemonSpriteLocationY, pokemonSpriteDimension.width, pokemonSpriteDimension.height, this);

            //g.drawRect(pokemonSpriteLocationX, pokemonSpriteLocationY, pokemonSpriteDimension.width, pokemonSpriteDimension.height);
        }

        if (showLoadingAnimation) {

            if (loadingAnimation != null && loadingAnimation.getImages().size() > 0) {
                g.drawImage(loadingAnimation.getImages().get(currentLoadingSpriteAnimation).getImage(), loadingAnimationPosX, loadingAnimationPosY, this);
            }
        }

        super.paintComponent(g);
    }

    @Override
    public void refresh() {
        setup();
    }

    private Thread setPokemon(Pokemon pokemon) {

        return new Thread(() -> {

            this.showLoadingAnimation = true;

            try {
                this.mutex.acquire();
            } catch (InterruptedException ex) {
            }

            this.currentPokemonSpriteAnimation = 0;
            this.pokemon = pokemon;

            String text = this.pokemon.getNickname();

            Font f = view.components.fonts.PokemonFont.getFont(1);
            f = f.deriveFont((float) utilities.string.StringUtil.preferedFontSizeforLabel(f, text, this.pokemonNameLabel.getSize()));
            this.pokemonNameLabel.setFont(f);
            this.pokemonNameLabel.setText(text);

            this.mutex.release();

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
            this.showLoadingAnimation = false;

            this.aidPanel.removeAudibleDescription();
            this.aidPanel.addAudibleDescription(LabelTexts.getInstance().selectedPokemon() + text);
            getAudibleDescription();
            updatePool();
        });
    }

    public synchronized void nextPokemon() {

        try {
            this.mutex.acquire();
        } catch (InterruptedException ex) {
        }

        int index = this.allPokemonPk.indexOf(this.pokemon.getPK());

        if (index < this.allPokemonPk.size() - 1 && index >= 0) {

            Pokemon p = this.preloadedPokemonsPool.get(this.allPokemonPk.get(index + 1));
            if (p != null) {
                setPokemon(p).start();
            }
        }

        this.mutex.release();
    }

    public synchronized void previousPokemon() {

        try {
            this.mutex.acquire();
        } catch (InterruptedException ex) {
        }

        int index = this.allPokemonPk.indexOf(this.pokemon.getPK());

        if (index > 0) {

            Pokemon p = this.preloadedPokemonsPool.get(this.allPokemonPk.get(index - 1));

            if (p != null) {
                setPokemon(p).start();
            }
        }

        this.mutex.release();
    }

    private synchronized void updatePool() {

        try {
            this.mutex.acquire();
        } catch (InterruptedException ex) {
        }

        int currentPokemonIndex = this.allPokemonPk.indexOf(this.pokemon.getPK());

        Thread t1, t2;

        t1 = new Thread(() -> {

            for (int i = currentPokemonIndex; i < currentPokemonIndex + MAX_PRELOAD && i < this.allPokemonPk.size(); i++) {
                if (!this.preloadedPokemonsPool.contains(this.allPokemonPk.get(i))) {
                    this.preloadedPokemonsPool.add(getModel().getEntity(this.allPokemonPk.get(i)));
                }
            }

        });

        t2 = new Thread(() -> {

            for (int i = currentPokemonIndex; i >= currentPokemonIndex - MAX_PRELOAD && i >= 0; i--) {
                if (!this.preloadedPokemonsPool.contains(this.allPokemonPk.get(i))) {
                    this.preloadedPokemonsPool.add(getModel().getEntity(this.allPokemonPk.get(i)));
                }
            }

        });

        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (Exception ex) {

        }

        this.mutex.release();
    }

    @Override
    public void playBackgroundMusic() {

        if (this.animatedBackgroundPanel != null) {
            this.animatedBackgroundPanel.playBackgroundMusic();
        }

    }

    @Override
    public void getAudibleDescription() {
        this.aidPanel.getAudibleDescription();
    }

    @Override
    public JLabel getInputDetector() {
        return this.aidPanel.getInputDetector();
    }

    @Override
    public boolean isEnabledAudioDescription() {
        return this.aidPanel.isEnabledAudioDescription();
    }

    @Override
    public void setEnableAudioDescriptions(boolean enableAudioDescriptions) {
        this.aidPanel.setEnableAudioDescriptions(enableAudioDescriptions);
    }

}
