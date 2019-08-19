package model.entities.modifiers;

import utilities.patterns.observer.Observer;
import model.BattleModel;
import model.entities.Entity;
import model.entities.Pokemon;

/**
 *
 * @author Adrian Vazquez
 */
public interface Modifier extends Observer<BattleModel>, Entity<String>{
    
    public void setUp(BattleModel battleModel, Pokemon from, Pokemon to);   
    
}
