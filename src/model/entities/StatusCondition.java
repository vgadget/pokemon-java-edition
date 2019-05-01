package model.entities;

/**
 *
 * @author Adrian Vazquez
 */
public interface StatusCondition extends Entity<String> {

    String getName();

    Sprite getAnimation();
    
    Type getType();
    
    boolean makeEffect(Pokemon p);

    int getRemainingTurns();

    
}
