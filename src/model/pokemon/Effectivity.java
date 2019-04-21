package model.pokemon;

import java.io.Serializable;

/**
 *
 * @author Adrian Vazquez
 */
public class Effectivity implements Serializable {

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

}
