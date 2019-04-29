package model.pokemon;

import model.Entity;

/**
 *
 * @author Adrian Vazquez
 */
public interface StatusCondition extends Entity<String>{
    public boolean makeEffect(Pokemon p);
    public int getRemainingTurns();
   
}
