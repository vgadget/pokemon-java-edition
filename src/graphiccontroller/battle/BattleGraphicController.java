package graphiccontroller.battle;

import Util.Dimensions;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Adrian Vazquez
 */
public class BattleGraphicController extends JPanel {

    private static final String URI_BATTLE_BACKGROUND = "Resources/BattleHUD/BackGround/battleBackground.png";
    private static final String URI_CROPPED_BATTLE_BACKGROUND = "Resources/BattleHUD/BackGround/croppedbattleBackground.png";

    private static final int FPS = 80;

    private static final String URI_BATTLE_GROUND = "Resources/BattleHUD/BackGround/groundBattle.png";

    private Dimension resolution;
    private BufferedImage background, ground, croppedBackground;
    private int groundLocationX, groundLocationY;

    private boolean setEnabledPlay;

    //Pokemon player1
    private PokemonMainCharacterHealthHud player1HUD;
    private PokemonMainCharacterSprite player1Sprite;

    //Pokemon player2
    private PokemonOpponentHealthHud player2HUD;
    private PokemonOpponentSprite player2Sprite;

    //Pokeball
    private PokeballAnimation pokeballP1;

    //Weather
    private Weather weatherAnimation;

    //TextBox
    private TextBox textBox;
    
    //AttackAnimation
    private AttackAnimation attackAnimation;

    public BattleGraphicController(Dimension resolution) {

        this.resolution = resolution;
        this.setEnabledPlay = true;

        setLayout(null);
        
        try {
            this.background = ImageIO.read(new File(URI_BATTLE_BACKGROUND));
            this.croppedBackground = ImageIO.read(new File(URI_CROPPED_BATTLE_BACKGROUND));

            this.ground = ImageIO.read(new File(URI_BATTLE_GROUND));

            if (this.resolution.equals(Dimensions.frameDimension1080p)) {

                this.background = Util.ImageUtil.resizeProportional(background, 6);
                this.croppedBackground = Util.ImageUtil.resizeProportional(croppedBackground, 6);

                this.ground = Util.ImageUtil.resize(ground, (int) (313 * 4.6f), 168 * 4);

                this.groundLocationX = 0;
                this.groundLocationY = 130;

            } else if (this.resolution.equals(Dimensions.frameDimension720p)) {

                this.background = Util.ImageUtil.resizeProportional(background, 4);

                this.croppedBackground = Util.ImageUtil.resizeProportional(croppedBackground, 4);

                this.ground = Util.ImageUtil.resizeProportional(ground, 3);

                this.groundLocationX = 0;
                this.groundLocationY = 20;

            }

        } catch (IOException ex) {
            Logger.getLogger(BattleGraphicController.class.getName()).log(Level.SEVERE, null, ex);
        }

        player1Sprite = new PokemonMainCharacterSprite(resolution, null);

        player2Sprite = new PokemonOpponentSprite(resolution, null);

        player1HUD = new PokemonMainCharacterHealthHud(resolution, this);

        player2HUD = new PokemonOpponentHealthHud(resolution, this);

        new Thread(() -> {
            while (true) {

                if (setEnabledPlay) {
                    repaint();
                }
                try {
                    Thread.sleep(FPS);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PokemonMainCharacterSprite.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

        try {
            pokeballP1 = new PokeballAnimation(resolution);
        } catch (IOException ex) {
            Logger.getLogger(BattleGraphicController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.weatherAnimation = new Weather(resolution, null);
        
        this.attackAnimation = new AttackAnimation(resolution);

        textBox = new TextBox(resolution);
        this.add(textBox.text);
        this.add(this.textBox.inputDetector);

        player1Appear();

        player2Appear();

    }

    public void pausePokemonSprites() {

        this.setEnabledPlay = false;

        if (player1Sprite != null) {
            player1Sprite.pause();
        }

        if (player2Sprite != null) {
            player2Sprite.pause();
        }

    }

    public void playPokemonSprites() {

        this.setEnabledPlay = true;

        if (player1Sprite != null) {
            player1Sprite.play();
        }

        if (player2Sprite != null) {
            player2Sprite.play();
        }

    }

    public void player1SetSprites(BufferedImage s[]) {
        this.player1Sprite.setUpSprites(s);
    }

    public void player2SetSprites(BufferedImage s[]) {
        this.player2Sprite.setUpSprites(s);

    }

    public void player1UpdateData(String name, int level, int sex) {

        if (player1HUD != null) {

            try {
                player1HUD.setPokemonName(name);
                player1HUD.setPokemonLevel(level);
                player1HUD.setPokemonSex(sex);
            } catch (IOException ex) {
                System.out.println("Error en jugador 1");
            }
        }
    }

    public void player2UpdateData(String name, int level, int sex, boolean isCatched) {

        if (player2HUD != null) {

            try {
                player2HUD.setPokemonName(name);
                player2HUD.setPokemonLevel(level);
                player2HUD.setPokemonSex(sex);
                player2HUD.setPokemonCatched(isCatched);
            } catch (IOException ex) {
                System.out.println("Error en jugador 2");
            }
        }
    }

    public void player1UpdateHP(int currentHP, int maxHP) {

        if (player1HUD != null) {
            player1HUD.updateHP(currentHP, maxHP);
        }

    }

    public void player2UpdateHP(int currentHP, int maxHP) {

        if (player2HUD != null) {
            player2HUD.updateHP(currentHP, maxHP);
        }

    }

    public void player1Appear() {

        player1Sprite.appear();
        player1HUD.appear();
        add(player1HUD.pokemonName);
        add(player1HUD.pokemonLevel);
        add(player1HUD.pokemonCurrentHp);
        add(player1HUD.pokemonMaxHp);

    }

    public void player2Appear() {

        player2Sprite.appear();
        player2HUD.appear();
        add(player2HUD.pokemonName);
        add(player2HUD.pokemonLevel);

    }

    public void player1Withdraw() {

        player1HUD.withdraw();
        player1Sprite.withdraw();

        repaint();
    }

    public void player2Withdraw() {

        player2HUD.withdraw();
        player2Sprite.withdraw();

        repaint();
    }

    public void player1Blink() {
        player1Sprite.blink();
    }

    public void player2Blink() {
        player2Sprite.blink();
    }

    public void player1Dead() {
        this.player1Sprite.dead();
    }

    public void player2Dead() {
        this.player2Sprite.dead();
    }

    public void player1LetsGo() {
        player1LaunchPokeball();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BattleGraphicController.class.getName()).log(Level.SEVERE, null, ex);
                }
                player1Appear();
            }
        }).start();
    }

    public void player2LetsGo() {
        player2LaunchPokeball();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BattleGraphicController.class.getName()).log(Level.SEVERE, null, ex);
                }
                player2Appear();
            }
        }).start();

    }

    public void player1Fly() {
        player1Sprite.fly();
    }

    public void player2Fly() {
        player2Sprite.fly();
    }

    public void player1LaunchPokeball() {
        if (resolution.equals(Dimensions.frameDimension720p)) {
            pokeballP1.doAnimation(0, 200);
        } else if (resolution.equals(Dimensions.frameDimension1080p)) {
            pokeballP1.doAnimation(50, 400);
        }
    }

    public void player2LaunchPokeball() {
        if (resolution.equals(Dimensions.frameDimension720p)) {
            pokeballP1.doAnimation(500, 0);
        } else if (resolution.equals(Dimensions.frameDimension1080p)) {
            pokeballP1.doAnimation(900, 40);
        }
    }

    public void player1SetAlteredState(BufferedImage sprites[]) {
        this.player1Sprite.setAlteredState(sprites);
    }

    public void player1RemoveAlteredState() {
        this.player1Sprite.removeAlteredState();
    }

    public void player1LoadDefaultAlteredState() {
        this.player1Sprite.loadDefaultsetAlteredState();
    }

    public void player2SetAlteredState(BufferedImage sprites[]) {
        this.player2Sprite.setAlteredState(sprites);
    }

    public void player2RemoveAlteredState() {
        this.player2Sprite.removeAlteredState();
    }

    public void player2LoadDefaultAlteredState() {
        this.player2Sprite.loadDefaultsetAlteredState();
    }

    public void player1StatsUP() {
        this.player1Sprite.statsUpAnimation();
    }

    public void player1StatsDown() {
        this.player1Sprite.statsDownAnimation();
    }

    public void player2StatsUP() {
        this.player2Sprite.statsUpAnimation();
    }

    public void player2StatsDown() {
        this.player2Sprite.statsDownAnimation();
    }

    public void setWeather(BufferedImage[] weather) {
        this.weatherAnimation.changeWeather(weather);
    }

    public void removeWeather() {

        this.weatherAnimation.removeWeather();
    }

    public void addText(String t) {
        textBox.enqueueText(t);
    }

    public void nextText() {

        textBox.dequeueText();

    }
    
    public void setAttackAnimation( BufferedImage sprites[]){
        this.attackAnimation.setAttack(sprites);
    }
    
    public void doAttackAnimation(){
        this.attackAnimation.doAnimation();
    }
    
    public void player1LoadDefaultAttackAnimation(){
        try {
            this.attackAnimation.loadDefaultAttackAnimationMainCharacter();
        } catch (IOException ex) {
            Logger.getLogger(BattleGraphicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void player2LoadDefaultAttackAnimation(){
        try {
            this.attackAnimation.loadDefaultAttackAnimationOpponent();
        } catch (IOException ex) {
            Logger.getLogger(BattleGraphicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public boolean attackAnimationIsPplaying(){
        return this.attackAnimation.isPlaying();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (background != null) {
            g.drawImage(background, 0, 0, this);
        }

        if (this.ground != null) {
            g.drawImage(ground, this.groundLocationX, this.groundLocationY, this);
        }

        //Player 2-Sprite
        if (player2Sprite != null) {
            player2Sprite.paintComponent(g);
        }

        if (croppedBackground != null) {
            g.drawImage(croppedBackground, 0, 0, this);
        }

        //Player 1-HUD
        if (player1HUD != null) {

            player1HUD.paintComponent(g);
        }

        //Player 1-Sprite
        if (player1Sprite != null) {
            player1Sprite.paintComponent(g);
        }

        //Player 2-HUD
        if (player2HUD != null) {
            player2HUD.paintComponent(g);
        }

        //Pokeball
        pokeballP1.paintComponent(g);

        weatherAnimation.paintComponent(g);

        //TextBox
        textBox.paintComponent(g);
        
        //Attack animation
        attackAnimation.paintComponent(g);

    }

}
