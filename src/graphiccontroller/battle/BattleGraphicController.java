package graphiccontroller.battle;

import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Adrian Vazquez
 */
public abstract class BattleGraphicController extends JPanel {

    //Pokemon sprites
    public abstract void pausePokemonSprites();

    public abstract void playPokemonSprites();

    public abstract void player1SetSprites(BufferedImage s[]);

    public abstract void player2SetSprites(BufferedImage s[]);

    public abstract void player1PausePokemonSprite();

    public abstract void player2PausePokemonSprite();

    public abstract void player1PlayPokemonSprite();

    public abstract void player2PlayPokemonSprite();

    public abstract void player1Appear();

    public abstract void player2Appear();

    public abstract void player1Withdraw();

    public abstract void player2Withdraw();

    public abstract void player1Blink();

    public abstract void player2Blink();

    public abstract void player1Dead();

    public abstract void player2Dead();

    public abstract void player1LetsGo();

    public abstract void player2LetsGo();

    public abstract void player1Fly();

    public abstract void player2Fly();

    public abstract void player1LaunchPokeball();

    public abstract void player2LaunchPokeball();

    public abstract void player1SetStatusCondition(BufferedImage sprites[]);

    public abstract void player2SetStatusCondition(BufferedImage sprites[]);

    public abstract void player1RemoveStatusCondition();

    public abstract void player2RemoveStatusCondition();

    public abstract void player1StatsUP();

    public abstract void player2StatsUP();

    public abstract void player1StatsDown();

    public abstract void player2StatsDown();

    // Health bar
    public abstract void player1UpdateData(String name, int level, int sex);

    public abstract void player2UpdateData(String name, int level, int sex, boolean isCatched);

    public abstract void player1UpdateHP(int currentHP, int maxHP);

    public abstract void player2UpdateHP(int currentHP, int maxHP);

    // Battleground
    public abstract void setWeather(BufferedImage[] weather);

    public abstract void removeWeather();

    public abstract void setAttackAnimation(BufferedImage sprites[]);

    public abstract void doAttackAnimation();

    public abstract void player1LoadDefaultAttackAnimation();

    public abstract void player2LoadDefaultAttackAnimation();

    public abstract boolean attackAnimationIsPplaying();

    public abstract void startEarthquake();

    public abstract void stopEarthquake();

    //TextBox
    public abstract void addText(String t);

    public abstract void nextText();

}
