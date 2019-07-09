package view.pokedex;

import controller.SpecieController;
import model.SpecieModel;
import view.AbstractView;

/**
 *
 * @author Adrian Vazquez
 */
public abstract class PokedexView extends AbstractView<SpecieModel, SpecieController> {


    public PokedexView(SpecieModel specieModel, SpecieController specieController) {
        
        setModel(specieModel);
        setController(specieController);
        
        
    }


    
    

}
