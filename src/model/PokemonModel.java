package model;

import controller.Controller;
import controller.PokemonController;
import model.entities.Pokemon;

/**
 *
 * @author Adrian Vazquez
 */
public class PokemonModel extends AbstractModel<PokemonController, Pokemon, String> {

    public PokemonModel() {
        super(Pokemon.class);
    }

}
