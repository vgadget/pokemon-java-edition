package model.entities;

import java.awt.Color;

/**
 *
 * @author Adrian Vazquez
 */
public class Type implements Entity<String> {

    private String name;
    private Color color;

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Type(String name, Color color) throws Exception {
        if (name != null && !name.equalsIgnoreCase("") && color != null) {
            this.name = name;
            this.color = color;
        } else {
            throw new Exception("TYPE: INVALID FIELDS");
        }
    }

    @Override
    public String toString() {
        return "Type{" + "name=" + name + ", color=" + color + '}';
    }

    @Override
    public String getPK() {
        return this.getName();
    }

    @Override
    public int compareTo(Entity o) {
        
        if (o instanceof Type) {
                       
            return this.getPK().compareTo(((Type) o).getPK());
        }

        return this.getClass().getName().compareTo(o.getClass().getName());
    }

}
