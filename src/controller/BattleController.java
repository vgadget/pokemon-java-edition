package controller;

import model.BattleModel;
import model.entities.Trainer;
import view.battle.BattleView;

/**
 *
 * @author Adrian Vazquez
 */
public interface BattleController {

    public void setup(BattleModel battleModel);

    public BattleView getBattleView(Trainer t);

    public void fireDataModelChanged();

    public void start();

}
