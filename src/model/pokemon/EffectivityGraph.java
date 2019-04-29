package model.pokemon;

import java.io.Serializable;
import java.util.List;
import model.Entity;

public class EffectivityGraph implements Entity<Integer> {

    private static EffectivityGraph instance;

    public static EffectivityGraph getInstance() {

        if (instance == null) {
            instance = new EffectivityGraph();
        }

        return instance;
    }

    private EffectivityGraph() {

    }

    private List<Effectivity> graph;

    @Override
    public Integer getPK() {
        return 1;
    }

    @Override
    public int compareTo(Entity o) {

        if (o instanceof EffectivityGraph) {
            return this.getPK().compareTo(((EffectivityGraph) o).getPK());
        }

        return this.getClass().getName().compareTo(o.getClass().getName());

    }

}
