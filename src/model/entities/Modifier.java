package model.entities;

import utilities.patterns.observer.Observer;
import model.BattleModel;

/**
 *
 * @author Adrian Vazquez
 */
public interface Modifier extends Observer<BattleModel>, Entity<String>{
    public String getName();
    public void makeEffect(BattleModel battleModel);
    
}
