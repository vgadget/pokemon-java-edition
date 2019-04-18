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
    private transient BufferedImage tag;
    private Color color;

    public String getName() {
        return name;
    }

    public BufferedImage getTag() {
        return tag;
    }

    public Color getColor() {
        return color;
    }

    public Type(String name, BufferedImage tag, Color color) throws Exception {
        if (name != null && !name.equalsIgnoreCase("") && tag != null && color != null) {
            this.name = name;
            this.tag = tag;
            this.color = color;
        } else {
            throw new Exception("TYPE: INVALID FIELDS");
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // All fields will be saved normally.
        out.writeInt(1); //One image will be serialized
        ImageIO.write(tag, "png", out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        in.readInt(); // One image will be read
        tag = ImageIO.read(in); // read image.

    }

    @Override
    public String toString() {
        return "Type{" + "name=" + name + ", color=" + color + '}';
    }
    
    

}
