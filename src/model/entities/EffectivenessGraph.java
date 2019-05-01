package model.entities;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.persistence.Persistence;
import utilities.DisplayMessage;

public class EffectivenessGraph implements Entity<String> {

    //SINGLETON
    private static EffectivenessGraph instance;

    public static EffectivenessGraph getInstance() {

        if (instance == null) {
            instance = new EffectivenessGraph();
        }

        return instance;
    }

    //END OF SINGLETON
    private Map<Comparable, Effectiveness> graph;

    private EffectivenessGraph() {

        try {
            EffectivenessGraph eg = (EffectivenessGraph) Persistence.getInstance().getDao().get(getPK(), this.getClass());
            this.graph = eg.graph;
        } catch (Exception ex) {
            graph = new TreeMap<>();
        }
    }

    public boolean addEffectiveness(Effectiveness e) {

        boolean containsValue = graph.containsValue(e);

        if (!containsValue) {
            boolean couldBeInserted = graph.put(e.getPK(), e) == null;

            if (couldBeInserted) {
                try {
                    Persistence.getInstance().getDao().save(this);
                } catch (IOException ex) {
                    DisplayMessage.showErrorDialog(ex.getMessage());
                }
            }

            return couldBeInserted;

        }

        return false;
    }

    public float getEffectiveness(Type fromType, Type toType) {

        Effectiveness e = null;
        try {
            e = new Effectiveness(0, fromType, toType);
            e = graph.get(e.getPK());

        } catch (Exception ex) {
        }

        return e == null ? 0 : e.getEffectivity();
    }

    public Iterator<Effectiveness> iterator() {

        return graph.values().iterator();
    }

    @Override
    public String getPK() {
        return "SINGLETON";
    }

    @Override
    public int compareTo(Entity o) {

        if (o instanceof EffectivenessGraph) {
            return this.getPK().compareTo(((EffectivenessGraph) o).getPK());
        }

        return this.getClass().getName().compareTo(o.getClass().getName());

    }

    @Override
    public String toString() {
        
        String s = "";
        
        Set<Comparable> keys = graph.keySet();
        
        for (Comparable key : keys){
            s += key + " --> " + graph.get(key) + "\n";
        }
        
        return s;
    }
    
    

}
