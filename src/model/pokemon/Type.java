package model.pokemon;

import java.awt.Color;
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
public class Type implements Serializable {

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
    
    

}
