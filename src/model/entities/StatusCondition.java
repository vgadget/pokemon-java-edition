package model.entities;

import model.BattleModel;

/**
 *
 * @author Adrian Vazquez
 */
public interface StatusCondition extends Entity<String> {

    String getName();

    Sprite getAnimation();
        
    public void makeEffect(Pokemon pFrom, Pokemon pTo, BattleModel battleModel);

    int getRemainingTurns();

    
}
