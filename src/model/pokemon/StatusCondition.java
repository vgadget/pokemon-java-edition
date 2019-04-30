package model.pokemon;

import model.Entity;

/**
 *
 * @author Adrian Vazquez
 */
public interface StatusCondition extends Entity<String> {

    public String getName();

    boolean makeEffect(Pokemon p);

    int getRemainingTurns();

}
