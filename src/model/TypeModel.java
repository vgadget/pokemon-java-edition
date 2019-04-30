package model;

import controller.Controller;
import model.pokemon.EffectivityGraph;
import model.pokemon.Type;

/**
 *
 * @author Adrian Vazquez
 */
public class TypeModel extends Model<Controller, Type, Comparable>{
    
    public TypeModel(Controller controller) {
        super(controller, Type.class);
    }
    
    public EffectivityGraph getEffectivityGraph(){
        return EffectivityGraph.getInstance();
    }
    
}
