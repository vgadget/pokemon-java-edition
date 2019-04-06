package view.battle;

import view.battle.components.PokeballAnimation;
import view.battle.components.PokemonOpponentSprite;
import view.battle.components.PokemonAttackAnimation;
import view.battle.components.PokemonMainCharacterHealthHud;
import view.battle.components.TextBox;
import view.battle.components.PokemonMainCharacterSprite;
import view.battle.components.Weather;
import view.battle.components.PokemonOpponentHealthHud;
import utilities.Dimensions;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Adrian Vazquez
 */
public class BattleViewImpl extends BattleView {

    private static final String URI_BATTLE_BACKGROUND = "Resources/BattleHUD/BackGround/battleBackground.png";
    private static final String URI_CROPPED_BATTLE_BACKGROUND = "Resources/BattleHUD/BackGround/croppedbattleBackground.png";

    private static final int FPS = 30; //Milliseconds

    private static final String URI_BATTLE_GROUND = "Resources/BattleHUD/BackGround/groundBattle.png";

    private Dimension resolution;
    private BufferedImage background, ground, croppedBackground;
    private int groundLocationX, groundLocationY;
    private int backgroundLocationX, backgroundLocationY;

    private boolean setEnabledPlay;

    private boolean earthquake;

    //Pokemon player1
    private PokemonMainCharacterHealthHud player1HUD;
    private PokemonMainCharacterSprite player1Sprite;

    //Pokemon player2
    private PokemonOpponentHealthHud player2HUD;
    private PokemonOpponentSprite player2Sprite;

    //Pokeball
    private PokeballAnimation pokeballAnimation;

    //Weather
    private Weather weatherAnimation;

    //TextBox
    private TextBox textBox;

    //AttackAnimation
    private PokemonAttackAnimation attackAnimation;

    public BattleViewImpl(Dimension resolution) {

        this.resolution = resolution;
        this.setEnabledPlay = true;
        this.earthquake = false;
        this.backgroundLocationX = this.backgroundLocationY = 0;

        setLayout(null);

        try {
            this.background = ImageIO.read(new File(URI_BATTLE_BACKGROUND));
            this.croppedBackground = ImageIO.read(new File(URI_CROPPED_BATTLE_BACKGROUND));

            this.ground = ImageIO.read(new File(URI_BATTLE_GROUND));

            if (this.resolution.equals(Dimensions.frameDimension1080p)) {

                this.background = utilities.ImageUtil.resizeProportional(background, 6);
                this.croppedBackground = utilities.ImageUtil.resizeProportional(croppedBackground, 6);

                this.ground = utilities.ImageUtil.resize(ground, (int) (313 * 4.6f), 168 * 4);

                this.groundLocationX = 0;
                this.groundLocationY = 130;

            } else if (this.resolution.equals(Dimensions.frameDimension720p)) {

                this.background = utilities.ImageUtil.resizeProportional(background, 4);

                this.croppedBackground = utilities.ImageUtil.resizeProportional(croppedBackground, 4);

                this.ground = utilities.ImageUtil.resizeProportional(ground, 3);

                this.groundLocationX = 0;
                this.groundLocationY = 20;

            }

        } catch (IOException ex) {
            Logger.getLogger(BattleViewImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            pokeballAnimation = new PokeballAnimation(resolution);
        } catch (IOException ex) {
            Logger.getLogger(BattleViewImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.weatherAnimation = new Weather(resolution, null);

        this.attackAnimation = new PokemonAttackAnimation(resolution);

        textBox = new TextBox(resolution);
        this.add(textBox.getTextComponent());
        this.add(this.textBox.getInputDetector());

        player1Appear();

        player2Appear();

    }

    @Override
    public void pausePokemonSprites() {

        this.setEnabledPlay = false;

        if (player1Sprite != null) {
            player1Sprite.pause();
        }

        if (player2Sprite != null) {
            player2Sprite.pause();
        }

    }

    @Override
    public void playPokemonSprites() {

        this.setEnabledPlay = true;

        if (player1Sprite != null) {
            player1Sprite.play();
        }

        if (player2Sprite != null) {
            player2Sprite.play();
        }

    }

    @Override
    public void player1SetSprites(BufferedImage s[]) {
        this.player1Sprite.setUpSprites(s);
    }

    @Override
    public void player2SetSprites(BufferedImage s[]) {
        this.player2Sprite.setUpSprites(s);

    }

    @Override
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

    @Override
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

    @Override
    public void player1UpdateHP(int currentHP, int maxHP) {

        if (player1HUD != null) {
            player1HUD.updateHP(currentHP, maxHP);
        }

    }

    @Override
    public void player2UpdateHP(int currentHP, int maxHP) {

        if (player2HUD != null) {
            player2HUD.updateHP(currentHP, maxHP);
        }

    }

    @Override
    public void player1Appear() {

        player1Sprite.appear();
        player1HUD.appear();
        add(player1HUD.getPokemonName());
        add(player1HUD.getPokemonLevel());
        add(player1HUD.getPokemonCurrentHp());
        add(player1HUD.getPokemonMaxHp());

    }

    @Override
    public void player2Appear() {

        player2Sprite.appear();
        player2HUD.appear();
        add(player2HUD.getPokemonName());
        add(player2HUD.getPokemonLevel());

    }

    @Override
    public void player1Withdraw() {

        player1HUD.withdraw();
        player1Sprite.withdraw();

        repaint();
    }

    @Override
    public void player2Withdraw() {

        player2HUD.withdraw();
        player2Sprite.withdraw();

        repaint();
    }

    @Override
    public void player1Blink() {
        player1Sprite.blink();
    }

    @Override
    public void player2Blink() {
        player2Sprite.blink();
    }

    @Override
    public void player1Dead() {
        this.player1Sprite.dead();
    }

    @Override
    public void player2Dead() {
        this.player2Sprite.dead();
    }

    @Override
    public void player1LetsGo() {
        player1LaunchPokeball();
        new Thread(() -> {
            try {
                Thread.sleep(1200);
            } catch (InterruptedException ex) {
                Logger.getLogger(BattleViewImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            player1Appear();
        }).start();
    }

    @Override
    public void player2LetsGo() {
        player2LaunchPokeball();

        new Thread(() -> {
            try {
                Thread.sleep(1200);
            } catch (InterruptedException ex) {
                Logger.getLogger(BattleViewImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            player2Appear();
        }).start();

    }

    @Override
    public void player1Fly() {
        player1Sprite.fly();
    }

    @Override
    public void player2Fly() {
        player2Sprite.fly();
    }

    @Override
    public void player1LaunchPokeball() {
        if (resolution.equals(Dimensions.frameDimension720p)) {
            pokeballAnimation.doAnimation(0, 200);
        } else if (resolution.equals(Dimensions.frameDimension1080p)) {
            pokeballAnimation.doAnimation(50, 400);
        }
    }

    @Override
    public void player2LaunchPokeball() {
        if (resolution.equals(Dimensions.frameDimension720p)) {
            pokeballAnimation.doAnimation(500, 0);
        } else if (resolution.equals(Dimensions.frameDimension1080p)) {
            pokeballAnimation.doAnimation(900, 40);
        }
    }

    @Override
    public void player1SetNonVolatileStatus(BufferedImage sprites[]) {
        if (sprites != null) {
            this.player1Sprite.setNonVolatileStatus(sprites);
        } else {
            player1LoadDefaultNonVolatileStatus();
        }
    }

    @Override
    public void player1RemoveNonVolatileStatus() {
        this.player1Sprite.removeNonVolatileStatus();
    }

    private void player1LoadDefaultNonVolatileStatus() {
        this.player1Sprite.loadDefaultsetNonVolatileStatus();
    }

    @Override
    public void player2SetNonVolatileStatus(BufferedImage sprites[]) {
        if (sprites != null) {
            this.player2Sprite.setNonVolatileStatus(sprites);
        } else {
            player2LoadDefaultNonVolatileStatus();
        }
    }

    @Override
    public void player2RemoveNonVolatileStatus() {
        this.player2Sprite.removeNonVolatileStatus();
    }

    private void player2LoadDefaultNonVolatileStatus() {
        this.player2Sprite.loadDefaultsetNonVolatileStatus();
    }

    @Override
    public void player1StatsUP() {
        this.player1Sprite.statsUpAnimation();
    }

    @Override
    public void player1StatsDown() {
        this.player1Sprite.statsDownAnimation();
    }

    @Override
    public void player2StatsUP() {
        this.player2Sprite.statsUpAnimation();
    }

    @Override
    public void player2StatsDown() {
        this.player2Sprite.statsDownAnimation();
    }

    @Override
    public void setWeather(BufferedImage[] weather) {
        this.weatherAnimation.changeWeather(weather);
    }

    @Override
    public void removeWeather() {

        this.weatherAnimation.removeWeather();
    }

    @Override
    public void addText(String t) {
        textBox.enqueueText(t);
    }

    @Override
    public void nextText() {

        textBox.dequeueText();

    }

    @Override
    public void setAttackAnimation(BufferedImage sprites[]) {
        this.attackAnimation.setAttack(sprites);
    }

    @Override
    public void doAttackAnimation() {
        this.attackAnimation.doAnimation();
    }

    @Override
    public void player1LoadDefaultAttackAnimation() {
        try {
            this.attackAnimation.loadDefaultAttackAnimationMainCharacter();
        } catch (IOException ex) {
            Logger.getLogger(BattleViewImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void player2LoadDefaultAttackAnimation() {
        try {
            this.attackAnimation.loadDefaultAttackAnimationOpponent();
        } catch (IOException ex) {
            Logger.getLogger(BattleViewImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean attackAnimationIsPplaying() {
        return this.attackAnimation.isPlaying();
    }

    @Override
    public void startEarthquake() {

        this.earthquake = true;

        int copyOfXBackground;
        int copyOfXground;

        copyOfXground = this.groundLocationX;
        copyOfXBackground = this.backgroundLocationX;

        new Thread(() -> {

            int slice = 15;

            int groundBoundStart = copyOfXground - ground.getWidth() / slice, groundBoundEnd = copyOfXground + ground.getWidth() / slice;

            int backgroundBoundStart = copyOfXBackground - background.getWidth() / slice, backgroundBoundEnd = copyOfXBackground + background.getWidth() / slice;

            groundLocationX = groundBoundEnd + 1;
            backgroundLocationX = backgroundBoundStart - 1;

            boolean backwardsGround = false, backwardsBackground = false;

            int speed = ground.getWidth() / (slice * 15);

            while (earthquake) {

                if (groundLocationX < groundBoundStart) {
                    backwardsGround = false;
                } else if (groundLocationX > groundBoundEnd) {
                    backwardsGround = true;
                }

                if (backgroundLocationX < groundBoundStart) {
                    backwardsBackground = false;
                } else if (backgroundLocationX > groundBoundEnd) {
                    backwardsBackground = true;
                }

                if (backwardsGround) {
                    groundLocationX -= speed;

                } else {
                    groundLocationX += speed;

                }

                if (backwardsBackground) {
                    backgroundLocationX -= speed;
                } else {
                    backgroundLocationX += speed;
                }

                try {
                    Thread.sleep(0, 1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BattleViewImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            backgroundLocationX = copyOfXBackground;
            groundLocationX = copyOfXground;

        }).start();

    }

    @Override
    public void stopEarthquake() {

        this.earthquake = false;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (background != null) {
            g.drawImage(background, this.backgroundLocationX, this.backgroundLocationY, this);
        }

        if (this.ground != null) {
            g.drawImage(ground, this.groundLocationX, this.groundLocationY, this);
        }

        //Player 2-Sprite
        if (player2Sprite != null) {
            player2Sprite.paintComponent(g);
        }

        if (croppedBackground != null) {
            g.drawImage(croppedBackground, this.backgroundLocationX, this.backgroundLocationY, this);
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
        pokeballAnimation.paintComponent(g);

        weatherAnimation.paintComponent(g);

        //TextBox
        textBox.paintComponent(g);

        //Attack animation
        attackAnimation.paintComponent(g);

    }

    @Override
    public void player1PausePokemonSprite() {

        this.player1Sprite.pause();

    }

    @Override
    public void player2PausePokemonSprite() {
        this.player2Sprite.pause();
    }

    @Override
    public void player1PlayPokemonSprite() {
        this.player1Sprite.play();

    }

    @Override
    public void player2PlayPokemonSprite() {
        this.player2Sprite.play();
    }

    @Override
    public void player1SetVolatileStatus(BufferedImage[] sprites) {
        if (sprites != null) {
            this.player1Sprite.setVolatileStatus(sprites);
        } else {
            this.player1Sprite.loadDefaultsetVolatileStatus();
        }
    }

    @Override
    public void player2SetVolatileStatus(BufferedImage[] sprites) {
        if (sprites != null) {
            this.player2Sprite.setVolatileStatus(sprites);
        } else {
            this.player2Sprite.loadDefaultsetVolatileStatus();
        }    }

    @Override
    public void player1RemoveVolatileStatus() {
        this.player1Sprite.removeVolatileStatus();
    }

    @Override
    public void player2RemoveVolatileStatus() {
        this.player2Sprite.removeVolatileStatus();
    }

}
