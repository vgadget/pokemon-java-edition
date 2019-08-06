package model;

import java.util.Stack;
import model.entities.Modifier;
import model.entities.Pokemon;
import model.entities.Trainer;
import utilities.image.Image;
import utilities.patterns.observer.Subject;

/**
 *
 * @author Adrian Vazquez
 */
public interface BattleModel extends Subject<Modifier> {
    
    public void setup(Trainer p1, Trainer p2);
    public Trainer getTrainerP1();
    public Trainer getTrainerP2();
    public void addGraphicLayer(Image img);
    public Stack<Image> getGraphics();
    public void updateGraphics();
    public Double calculateDamage(Pokemon p1, Pokemon p2);
    public Integer getCurrentTurn();
    

}
