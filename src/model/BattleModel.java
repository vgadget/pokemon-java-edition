package model;

import controller.BattleController;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.Queue;
import model.entities.modifiers.Modifier;
import model.entities.Pokemon;
import model.entities.Trainer;
import utilities.image.Image;
import utilities.patterns.observer.Subject;

/**
 *
 * @author Adrian Vazquez
 */
public interface BattleModel extends Subject<Modifier> {

    public void setup(Trainer p1, Trainer p2, BattleController battleController);

    public Trainer getTrainerP1();

    public Trainer getTrainerP2();

    public Double calculateDamage(Pokemon p1, Pokemon p2);

    public Integer getCurrentTurn();

    public void setActionSelected(Trainer t, Boolean selected);

    public void setActionExecuted(Trainer t, Boolean executed);

    public boolean isActionSelected(Trainer t);

    public boolean isActionExecuted(Trainer t);

}
