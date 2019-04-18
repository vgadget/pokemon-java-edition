package model.pokemon;

import java.io.Serializable;

/**
 *
 * @author Adrian Vazquez
 */
public interface StatusCondition extends Serializable{
    public boolean makeEffect(Pokemon p);
    public int getRemainingTurns();
   
}
