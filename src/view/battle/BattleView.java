package view.battle;

import java.awt.image.BufferedImage;
import model.entities.Pokemon;
import view.components.AidPanel;

/**
 *
 * @author Adrian Vazquez
 */
public abstract class BattleView extends AidPanel {

    public abstract void setPokemonPlayer1(Pokemon p);

    public abstract void setPokemonPlayer2(Pokemon p);

    public abstract void pokemonPlayer1PausePokemonSprite();

    public abstract void pokemonPlayer2PausePokemonSprite();

    public abstract void pokemonPlayer1PlayPokemonSprite();

    public abstract void pokemonPlayer2PlayPokemonSprite();

    public abstract void pokemonPlayer1Appear();

    public abstract void pokemonPlayer2Appear();

    public abstract void pokemonPlayer1Withdraw();

    public abstract void pokemonPlayer2Withdraw();

    public abstract void pokemonPlayer1Blink();

    public abstract void pokemonPlayer2Blink();

    public abstract void pokemonPlayer1Dead();

    public abstract void pokemonPlayer2Dead();

    public abstract void pokemonPlayer1LetsGo();

    public abstract void pokemonPlayer2LetsGo();

    public abstract void pokemonPlayer1LaunchPokeball();

    public abstract void pokemonPlayer2LaunchPokeball();

    public abstract void pokemonPlayer1SetNonVolatileStatus(BufferedImage sprites[]);

    public abstract void pokemonPlayer2SetNonVolatileStatus(BufferedImage sprites[]);

    public abstract void pokemonPlayer1RemoveNonVolatileStatus();

    public abstract void pokemonPlayer2RemoveNonVolatileStatus();

    public abstract void pokemonPlayer1SetVolatileStatus(BufferedImage sprites[]);

    public abstract void pokemonPlayer2SetVolatileStatus(BufferedImage sprites[]);

    public abstract void pokemonPlayer1RemoveVolatileStatus();

    public abstract void pokemonPlayer2RemoveVolatileStatus();

    public abstract void pokemonPlayer1StatsUP();

    public abstract void pokemonPlayer2StatsUP();

    public abstract void pokemonPlayer1StatsDown();

    public abstract void pokemonPlayer2StatsDown();

    // Health bar
    public abstract void pokemonPlayer1UpdateData(String name, int level, int sex);

    public abstract void pokemonPlayer2UpdateData(String name, int level, int sex, boolean isCatched);

    public abstract void pokemonPlayer1UpdateHP(int currentHP, int maxHP);

    public abstract void pokemonPlayer2UpdateHP(int currentHP, int maxHP);

    // Battleground
    public abstract void setWeather(BufferedImage[] weather);

    public abstract void removeWeather();

    public abstract void setAttackAnimation(BufferedImage sprites[]);

    public abstract void doAttackAnimation();

    public abstract void pokemonPlayer1LoadDefaultAttackAnimation();

    public abstract void pokemonPlayer2LoadDefaultAttackAnimation();

    public abstract boolean attackAnimationIsPplaying();

    public abstract void startEarthquake();

    public abstract void stopEarthquake();

    //TextBox
    public abstract void addText(String t);

    public abstract void nextText();

}
