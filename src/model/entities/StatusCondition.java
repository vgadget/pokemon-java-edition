package model.entities;

import model.BattleModel;

/**
 *
 * @author Adrian Vazquez
 */
public interface StatusCondition extends Entity<String> {

    String getName();

    Sprite getAnimation();
        
    public void makeEffect(BattleModel battleModel, Pokemon from, Pokemon to);    
}
