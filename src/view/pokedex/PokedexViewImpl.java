package view.pokedex;

import controller.SpecieController;
import model.SpecieModel;


public class PokedexViewImpl extends PokedexView {

    public PokedexViewImpl(SpecieModel specieModel, SpecieController specieController) {
        super(specieModel, specieController);
    }
    
    

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
