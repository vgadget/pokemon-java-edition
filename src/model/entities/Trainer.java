package model.entities;

import java.util.Iterator;

/**
 *
 * @author Adrian Vazquez
 */
public interface Trainer extends Entity<String>{

    
    String getName();
    Sprite getSprite();
    Pokemon getSelectedPokemon();
    Iterator<Pokemon> getTeam();
    Iterator<Pokemon> getBox();      
    
    
}
