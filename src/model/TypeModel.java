package model;

import controller.Controller;
import model.entities.EffectivenessGraph;
import model.entities.Type;

/**
 *
 * @author Adrian Vazquez
 */
public class TypeModel extends EntityModelImpl<Controller, Type, Comparable>{
    
    public TypeModel(Controller controller) {
        super(controller, Type.class);
    }
    
    public EffectivenessGraph getEffectivenessGraph(){
        return EffectivenessGraph.getInstance();
    }
    
}
