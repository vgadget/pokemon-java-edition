package model.pokemon;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 *
 * @author Adrian Vazquez
 */
public class Move implements Serializable, Comparable {

    private String name;
    private int precision;
    private int power;
    private int pp;
    private StatusCondition secondaryEffect;
    private Type type;
    private BufferedImage[] animation;

    public Move(String name, int precision, int power, int pp,
            StatusCondition secondaryEffect, Type type,
            BufferedImage[] animation) throws Exception {

        if (validateFields(name, precision, power, pp, type, animation)) {
            this.name = name;
            this.precision = precision;
            this.power = power;
            this.pp = pp;
            this.secondaryEffect = secondaryEffect;
            this.type = type;
            this.animation = animation;
        } else {
            throw new Exception("INVALID FIELDS");
        }
    }

    private boolean validateFields(String name, int precision, int power,
            int pp, Type type,
            BufferedImage[] animation) {

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
        } else if (animation == null) {
            return false;
        }

        return true;
    }
    
        private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // All fields will be saved normally.
        out.writeInt(animation.length); //How many images will be serialized

        for (BufferedImage sprite : animation) { // Serialze every image
            ImageIO.write(sprite, "png", out);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int size = in.readInt(); // How many images will be read
        animation = new BufferedImage[size];
        for (int i = 0; i < size; i++){
            animation[i] = ImageIO.read(in); // read image.
        }
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

    public BufferedImage[] getAnimation() {
        return animation;
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
    public int compareTo(Object o) {
        if (equals(o)){
            return 0;
        } else{
            return ((Move) o).getName().compareToIgnoreCase(getName());
        }
    }

    @Override
    public String toString() {
        return "Move{" + "name=" + name + ", pp=" + pp + ", type=" + type + '}';
    }
    
    
    
   
    
    
    
}
