package graphiccontroller.battle;

import utilities.Dimensions;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import utilities.ImageUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian Vazquez
 */
public class PokemonOpponentHealthHud extends JPanel {

    public static final int MALE = 0, FEMALE = 1, NONE = 2;
    private static final int MAX_CHAR = 10;

    private static final String URI_HP_HUD = "Resources/BattleHUD/HpBar/OpponentHpBar/HealthBar.png";
    private static final String URI_IS_CATCHED_ICON = "Resources/BattleHUD/HpBar/OpponentHpBar/Captured.png";
    private static final String URI_SEX_FEMALE = "Resources/BattleHUD/HpBar/Common/Sex/Female.png";
    private static final String URI_SEX_MALE = "Resources/BattleHUD/HpBar/Common/Sex/Male.png";
    private static final String URI_SEX_NONE = "Resources/BattleHUD/HpBar/Common/Sex/None.png";

    private static final String URI_GREEN_HP_BAR = "Resources/BattleHUD/HpBar/Common/Bars/GreenBar.png";
    private static final String URI_YELLOW_HP_BAR = "Resources/BattleHUD/HpBar/Common/Bars/YellowBar.png";
    private static final String URI_RED_HP_BAR = "Resources/BattleHUD/HpBar/Common/Bars/RedBar.png";
    private static final int BAR_UPDATE_SPEED = 10;

    private BattleGraphicController mainControler;

    private BufferedImage hud;
    private int hudLocationX, hudLocationY;

    private BufferedImage hpBar, hpGreenBar, hpYellowBar, hpRedBar;
    private int hpBarLocationX, hpBarLocationY;

    private BufferedImage isCatchedIcon;
    private int isCatchedIconLocationX, isCatchedIconLocationY;

    private BufferedImage sexIcon;
    private int sexIconLocationX, sexIconLocationY;

    private final Dimension frameDimension;

    protected JLabel pokemonName;
    protected JLabel pokemonLevel;

    private int percentage;
    private boolean isCatched;
    private int sex;

    private boolean withdraw;

    public PokemonOpponentHealthHud(Dimension frameDimension, String name, int level, int sex, int currentHp, int maxHp, boolean isCatched, BattleGraphicController mainControler) {

        //Variables that can be modified
        this.percentage = 100;
        this.isCatched = isCatched;
        this.sex = sex;
        this.withdraw = false;

        //SetUp the Class
        this.mainControler = mainControler;
        setLayout(null);
        this.frameDimension = frameDimension;
        try {
            setUpResources();
        } catch (IOException ex) {
            new Thread(() -> {
                JOptionPane.showMessageDialog(null, "ERROR 404: RESOURCES NOT FOUND", "FATAL ERROR", 0);
            }).start();
        }

        this.setPercentage(percentage);
        this.setUpPokemonName(name);
        this.setUpPokemonLevel(level);
        this.updateHP(currentHp, maxHp);

    }

    public PokemonOpponentHealthHud(Dimension frameDimension, BattleGraphicController mainControler) {
        this(frameDimension, "MISSINGNO", 100, NONE, 999, 999, false, mainControler);
    }

    private void setUpPokemonName(String name) {

        if (name.length() > MAX_CHAR) {
            name = name.substring(0, MAX_CHAR);
            name = name + "...";
        }

        pokemonName = new JLabel(name);
        pokemonName.setText(name);

        try {

            //Cargar la fuente
            InputStream is = getClass().getResourceAsStream("HUD_NAME_FONT.ttf");
            Font f = Font.createFont(Font.TRUETYPE_FONT, is);
            f = f.deriveFont(0, 18);
            pokemonName.setFont(f);

        } catch (Exception e) {

            new Thread(() -> {
                JOptionPane.showMessageDialog(null, "ERROR 001: MISSING FONT", "ERROR", 0);
            }).start();

        }

        setPokemonName(name);

        pokemonName.setForeground(Color.WHITE);
        this.add(pokemonName);
    }

    public void setPokemonName(String name) {

        if (name.length() > MAX_CHAR) {
            name = name.substring(0, MAX_CHAR - 1);
            name = name + "...";
        }

        pokemonName.setText(name);

        //Dimensions and Locations
        if (frameDimension.equals(Dimensions.frameDimension1080p)) {

            pokemonName.setBounds((int) (hpBarLocationX / 2.5f), (int) (hpBarLocationY * (1f / 2.5f)), pokemonName.getPreferredSize().width, pokemonName.getPreferredSize().height);

        } else if (frameDimension.equals(Dimensions.frameDimension720p)) {
            pokemonName.setBounds((int) (hpBarLocationX / 2.5f), (int) (hpBarLocationY * (1f / 2.5f)), pokemonName.getPreferredSize().width, pokemonName.getPreferredSize().height);

        }

        mainControler.repaint();
        repaint();
    }

    private void setUpPokemonLevel(int level) {

        if (level > 0 && level <= 100) {

            pokemonLevel = new JLabel(level + "");

            try {

                //Cargar la fuente
                InputStream is = getClass().getResourceAsStream("HUD_NAME_FONT.ttf");
                Font f = Font.createFont(Font.TRUETYPE_FONT, is);
                f = f.deriveFont(0, 24);
                pokemonLevel.setFont(f);

            } catch (Exception e) {

                new Thread(() -> {
                    JOptionPane.showMessageDialog(null, "ERROR 001: MISSING FONT", "ERROR", 0);
                }).start();

            }

            setPokemonLevel(level);
            pokemonLevel.setForeground(Color.WHITE);
            this.add(pokemonLevel);
        }
    }

    public void setPokemonLevel(int level) {
        if (level > 0 && level <= 100) {
            this.pokemonLevel.setText(level + "");

            //Dimensions and Locations
            if (frameDimension.equals(Dimensions.frameDimension1080p)) {

                pokemonLevel.setBounds((int) (hpBarLocationX * 2.15f), (int) (hpBarLocationY * (1f / 3.5f)), pokemonLevel.getPreferredSize().width, pokemonLevel.getPreferredSize().height);

            } else if (frameDimension.equals(Dimensions.frameDimension720p)) {
                pokemonLevel.setBounds((int) (hpBarLocationX * 2.15f), (int) (hpBarLocationY * (1f / 3.5f)), pokemonLevel.getPreferredSize().width, pokemonLevel.getPreferredSize().height);

            }

        }
    }

    public void setPokemonSex(int sex) throws IOException {

        this.sex = sex;

        switch (this.sex) {
            case MALE:
                sexIcon = ImageIO.read(new File(URI_SEX_MALE));
                break;
            case FEMALE:
                sexIcon = ImageIO.read(new File(URI_SEX_FEMALE));
                break;
            case NONE:
                sexIcon = ImageIO.read(new File(URI_SEX_NONE));
                break;
        }

        if (sexIcon != null) {
            sexIcon = ImageUtil.resizeProportional(sexIcon, ImageUtil.getProportion(frameDimension) * 2);
        }

        mainControler.repaint();
        repaint();
    }

    public void setPokemonCatched(boolean b) throws IOException {

        this.isCatched = b;

        if (isCatched) {
            isCatchedIcon = ImageIO.read(new File(URI_IS_CATCHED_ICON));
            isCatchedIcon = ImageUtil.resizeProportional(isCatchedIcon, ImageUtil.getProportion(frameDimension) * 2);
        } else {
            isCatchedIcon = null;
        }

        mainControler.repaint();
        repaint();
    }

    private void setUpResources() throws IOException {
        hud = ImageIO.read(new File(URI_HP_HUD));
        hud = ImageUtil.resizeProportional(hud, ImageUtil.getProportion(frameDimension) * 2);

        hpGreenBar = ImageIO.read(new File(URI_GREEN_HP_BAR));
        hpGreenBar = ImageUtil.resizeProportional(hpGreenBar, ImageUtil.getProportion(frameDimension) * 2);

        hpYellowBar = ImageIO.read(new File(URI_YELLOW_HP_BAR));
        hpYellowBar = ImageUtil.resizeProportional(hpYellowBar, ImageUtil.getProportion(frameDimension) * 2);

        hpRedBar = ImageIO.read(new File(URI_RED_HP_BAR));
        hpRedBar = ImageUtil.resizeProportional(hpRedBar, ImageUtil.getProportion(frameDimension) * 2);

        hpBar = ImageUtil.deepCopy(hpGreenBar);

        setPokemonCatched(isCatched);

        setPokemonSex(sex);

        //Dimensions and Locations
        if (frameDimension.equals(Dimensions.frameDimension1080p)) {

            hudLocationX = 0;
            hudLocationY = 0;

            isCatchedIconLocationX = 0;
            isCatchedIconLocationY = 0;

            hpBarLocationX = (int) (frameDimension.width / 9.42);
            hpBarLocationY = (int) (frameDimension.height / 12.9);

            sexIconLocationX = (int) (frameDimension.width / 5.45f);
            sexIconLocationY = (int) (frameDimension.width / 65f);

        } else if (frameDimension.equals(Dimensions.frameDimension720p)) {
            hudLocationX = 0;
            hudLocationY = 0;

            isCatchedIconLocationX = 0;
            isCatchedIconLocationY = 0;

            hpBarLocationX = (int) (frameDimension.width / 6.25f);
            hpBarLocationY = (int) (frameDimension.height / 8.6f);

            sexIconLocationX = (int) (frameDimension.width / 3.6f);
            sexIconLocationY = (int) (frameDimension.width / 45f);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {

        if (!withdraw) {
            super.paintComponent(g);

            g.drawImage(hud, hudLocationX, hudLocationY, this);
            g.drawImage(hpBar, hpBarLocationX, hpBarLocationY, this);
            g.drawImage(isCatchedIcon, isCatchedIconLocationX, isCatchedIconLocationY, this);
            g.drawImage(sexIcon, sexIconLocationX, sexIconLocationY, this);
        }
    }

    private void reducce(int p) {
        hpBar = ImageUtil.cropImage(hpBar, hpBar.getWidth() - p, hpBar.getHeight());
    }

    private void setPercentage(int p) {
        if (p >= 0 && p <= 100) {
            if (p >= 65 && p <= 100) {
                hpBar = ImageUtil.deepCopy(hpGreenBar);
            } else if (p >= 30) {
                hpBar = ImageUtil.deepCopy(hpYellowBar);
            } else if (p > 0) {
                hpBar = ImageUtil.deepCopy(hpRedBar);
            }

            if (p > 0 && p < 100) {
                int newWith = (p * hpBar.getWidth() / 100);

                reducce(hpBar.getWidth() - newWith);
            }

            if (p == 0) {
                hpBar = null;
            }

            mainControler.repaint();
            repaint();
        }
    }

    public void updateHP(int currentHP, int maxHp) {

        if (currentHP > maxHp) {
            currentHP = maxHp;
        } else if (currentHP < 0) {
            currentHP = 0;
        }

        int newPercentage = (int) ((currentHP * 100f) / maxHp);

        if (currentHP > 0 && newPercentage < 1) {
            newPercentage = 1;
        }

        if (newPercentage < percentage) {
            while (newPercentage != percentage) {

                percentage--;
                setPercentage(percentage);
                try {
                    Thread.sleep(BAR_UPDATE_SPEED);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PokemonOpponentHealthHud.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else if (newPercentage > percentage) {
            while (newPercentage != percentage) {
                percentage++;
                setPercentage(percentage);
                try {
                    Thread.sleep(BAR_UPDATE_SPEED);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PokemonOpponentHealthHud.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public int getPokemonSex() {
        return sex;
    }

    public boolean pokemonIsCatched() {
        return isCatched;
    }

    public void withdraw() {
        this.withdraw = true;
        pokemonName.setVisible(false);
        pokemonLevel.setVisible(false);
        repaint();
    }

    public void appear() {
        this.withdraw = false;
        pokemonName.setVisible(true);
        pokemonLevel.setVisible(true);
    }

}
