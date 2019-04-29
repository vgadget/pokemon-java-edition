package model.pokemon;

import model.Entity;

/**
 *
 * @author Adrian Vazquez
 */
public interface Item extends Entity<String>{
    
    public void makeEffect(Pokemon p);
    
}
