package view.pokemon;

import view.AbstractView;
import controller.PokemonController;
import model.PokemonModel;


/**
 *
 * @author Adrian Vazquez
 */
public class PokemonView extends AbstractView<PokemonModel, PokemonController>{

    
    public void setup(){
        
    }
    
    @Override
    public void refresh() {
        setup();
    }
    
}
