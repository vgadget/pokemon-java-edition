package model;

import controller.Controller;
import model.entities.EffectivenessGraph;
import model.entities.Type;

/**
 *
 * @author Adrian Vazquez
 */
public class TypeModel extends AbstractModel<Controller, Type, Comparable>{
    
    public TypeModel() {
        super(Type.class);
    }
    
    public EffectivenessGraph getEffectivenessGraph(){
        return EffectivenessGraph.getInstance();
    }
    
}
