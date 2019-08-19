package model.entities;

import java.util.List;

/**
 *
 * @author Adrian Vazquez
 */
public interface Trainer extends Entity<String>{

    
    String getName();
    Sprite getSprite();
    Pokemon getSelectedPokemon();
    List<Pokemon> getTeam();   
    
}
