package main;

import controller.SpecieController;
import model.SpecieModel;
import model.TypeModel;
import texttospeech.Narrator;
import view.menu.MainMenu;
import view.pokedex.PokedexView;
import view.pokedex.PokedexViewImpl;

/**
 *
 * @author Adrian Vazquez
 */
public class Main {

    //Models
    public static final TypeModel typeModel = new TypeModel();
    public static final SpecieModel specieModel = new SpecieModel();

    //Controllers
    public static final SpecieController specieController = new SpecieController();

    public static void main(String[] args) throws Exception {

        //Link specie model, specie controller and view.
        specieModel.setController(specieController);
        specieController.setModel(specieModel);
        PokedexView pokedexView = new PokedexViewImpl(specieController, typeModel);
        specieController.addView(pokedexView);

        // Set Narrator 
        Narrator.getInstance().setLanguage(Narrator.Language.ENGLISH);

        Narrator.getInstance().setEnabled(true);

        new Thread(() -> {

            new MainMenu();

        }).start();

    }

}
