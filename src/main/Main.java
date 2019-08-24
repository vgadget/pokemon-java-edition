package main;

import controller.PokemonController;
import controller.SpecieController;
import model.PokemonModel;
import model.SpecieModel;
import model.TypeModel;
import model.entities.Pokemon;
import texttospeech.Narrator;
import view.components.AnimatedBackgroundPanel;
import view.menu.MainMenu;
import view.pokedex.PokedexView;
import view.pokedex.PokedexViewImpl;
import view.pokemon.PokemonView;

/**
 *
 * @author Adrian Vazquez
 */
public class Main {

    //Models
    public static final TypeModel TYPE_MODEL = new TypeModel();
    public static final SpecieModel SPECIE_MODEL = new SpecieModel();
    public static final PokemonModel POKEMON_MODEL = new PokemonModel();

    //Controllers
    public static final SpecieController SPECIE_CONTROLLER = new SpecieController();
    public static final PokemonController POKEMON_CONTROLLER = new PokemonController();

    public static void main(String[] args) throws Exception {
        
// Set Narrator 
        Narrator.getInstance().setEnabled(true);
        Narrator.getInstance().setLanguage(Narrator.Language.ENGLISH);

        //Link specie model, specie controller and specie view.
        SPECIE_MODEL.setController(SPECIE_CONTROLLER);
        SPECIE_CONTROLLER.setModel(SPECIE_MODEL);
        PokedexView pokedexView = new PokedexViewImpl(SPECIE_CONTROLLER, TYPE_MODEL);
        SPECIE_CONTROLLER.addView(pokedexView);

        //Link pokemon model, pokemon controller and pokemon view.
        POKEMON_MODEL.setController(POKEMON_CONTROLLER);
        POKEMON_CONTROLLER.setModel(POKEMON_MODEL);
        PokemonView pokemonView = new PokemonView(POKEMON_CONTROLLER);
        POKEMON_CONTROLLER.addView(pokemonView);

        POKEMON_CONTROLLER.start();

//
//        new Thread(() -> {
//
//            new MainMenu();
//
//        }).start();
    }

}
