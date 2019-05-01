package model.entities;

/**
 *
 * @author Adrian Vazquez
 */
public class Move implements Entity<String> {

    private String name;
    private int precision;
    private int power;
    private int pp;
    private StatusCondition secondaryEffect;
    private Type type;
    private Sprite frontAnimation;
    private Sprite backAnimation;

    public Move(String name, int precision, int power, int pp,
            StatusCondition secondaryEffect, Type type,
            Sprite frontAnimation, Sprite backAnimation) throws Exception {

        if (validateFields(name, precision, power, pp, type, frontAnimation, backAnimation)) {
            this.name = name;
            this.precision = precision;
            this.power = power;
            this.pp = pp;
            this.secondaryEffect = secondaryEffect;
            this.type = type;
            this.frontAnimation = frontAnimation;
            this.backAnimation = backAnimation;
        } else {
            throw new Exception("INVALID FIELDS");
        }
    }

    private boolean validateFields(String name, int precision, int power,
            int pp, Type type,
            Sprite frontAnimation, Sprite backAnimation) {

        if (name == null || name.equals("")) {
            return false;
        } else if (precision <= 0) {
            return false;
        } else if (power < 0) {
            return false;
        } else if (pp <= 0) {
            return false;
        } else if (type == null) {
            return false;
        } else if (this.frontAnimation == null || backAnimation == null) {
            return false;
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public int getPrecision() {
        return precision;
    }

    public int getPower() {
        return power;
    }

    public int getPp() {
        return pp;
    }

    public StatusCondition getSecondaryEffect() {
        return secondaryEffect;
    }

    public Type getType() {
        return type;
    }

    public Sprite getFrontAnimation() {
        return frontAnimation;
    }

    public Sprite getBackAnimation() {
        return backAnimation;
    }
    
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Move m = (Move) obj;

        return m.getName().equalsIgnoreCase(getName());
    }

    @Override
    public String toString() {
        return "Move{" + "name=" + name + ", pp=" + pp + ", type=" + type + '}';
    }

    @Override
    public String getPK() {
        return this.getName();
    }

    @Override
    public int compareTo(Entity o) {

        if (o instanceof Move) {

            if (this.getType().compareTo(((Move) o).getType()) != 0) {
                return this.getType().compareTo(((Move) o).getType());
            }

            return this.getPK().compareTo(((Move) o).getPK());
        }

        return this.getClass().getName().compareTo(o.getClass().getName());
    }

}
