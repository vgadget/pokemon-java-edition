package controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PokemonModel;
import model.entities.Entity;
import model.entities.Pokemon;
import model.entities.Specie;
import view.View;
import view.pokemon.PokemonView;

/**
 *
 * @author Adrian Vazquez
 */
public class PokemonController extends AbstractController<PokemonModel, PokemonView, Pokemon> {

    @Override
    public Pokemon createEntity(List<Object> data) {

        String nickname = (String) data.get(0);
        Integer level = (Integer) data.get(1);
        Specie specie = (Specie) data.get(2);

        Pokemon newPokemon;

        try {

            newPokemon = new Pokemon(nickname, level, specie);

        } catch (Exception ex) {

            newPokemon = null;
        }

        return newPokemon;
    }

}
