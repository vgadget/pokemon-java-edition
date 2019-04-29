package model.pokemon;

import java.io.Serializable;
import model.Entity;

/**
 *
 * @author Adrian Vazquez
 */
public class Effectivity implements Entity<String> {

    private float effectivity;
    private Type fromType;
    private Type toType;

    public Effectivity(float effectivity, Type fromType, Type toType) throws Exception {
        setEffectivity(effectivity);
        if (fromType != null && toType != null) {
            this.fromType = fromType;
            this.toType = toType;
        } else {
            throw new Exception("TYPE: INVALID TYPE COMBINATION");
        }
    }

    public float getEffectivity() {
        return effectivity;
    }

    public void setEffectivity(float effectivity) throws Exception {
        if (effectivity >= 0) {
            this.effectivity = effectivity;
        } else {
            throw new Exception("Invalid effectivity");
        }
    }

    public Type getFromType() {
        return fromType;
    }

    public Type getToType() {
        return toType;
    }

    @Override
    public String toString() {
        return "Effectivity{" + "effectivity=" + effectivity + ", fromType=" + fromType + ", toType=" + toType + '}';
    }

    @Override
    public String getPK() {
        return this.toString();
    }

    @Override
    public int compareTo(Entity o) {

        if (o instanceof Effectivity) {
            return this.getPK().compareTo(((Effectivity) o).getPK());
        }

        return this.getClass().getName().compareTo(o.getClass().getName());
    }

}
