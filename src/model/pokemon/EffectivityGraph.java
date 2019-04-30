package model.pokemon;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Entity;
import model.persistence.Persistence;
import utilities.DisplayMessage;

public class EffectivityGraph implements Entity<String> {

    //SINGLETON
    private static EffectivityGraph instance;

    public static EffectivityGraph getInstance() {

        if (instance == null) {
            instance = new EffectivityGraph();
        }

        return instance;
    }

    //END OF SINGLETON
    private Map<Comparable, Effectivity> graph;

    private EffectivityGraph() {

        try {
            EffectivityGraph eg = (EffectivityGraph) Persistence.getInstance().getDao().get(getPK(), this.getClass());
            this.graph = eg.graph;
        } catch (Exception ex) {
            graph = new TreeMap<>();
        }
    }

    public boolean addEffectivity(Effectivity e) {

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

    public float getEffectivity(Type fromType, Type toType) {

        Effectivity e = null;
        try {
            e = new Effectivity(0, fromType, toType);
            e = graph.get(e.getPK());

        } catch (Exception ex) {
        }

        return e == null ? 0 : e.getEffectivity();
    }

    public Iterator<Effectivity> iterator() {

        return graph.values().iterator();
    }

    @Override
    public String getPK() {
        return "SINGLETON";
    }

    @Override
    public int compareTo(Entity o) {

        if (o instanceof EffectivityGraph) {
            return this.getPK().compareTo(((EffectivityGraph) o).getPK());
        }

        return this.getClass().getName().compareTo(o.getClass().getName());

    }

}
