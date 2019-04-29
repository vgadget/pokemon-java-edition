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
public class Sprite implements Serializable {

    private transient BufferedImage[] animation;
    private int refreshRate;

    public Sprite(BufferedImage[] animation, int refreshRate) throws Exception {
        setAnimation(animation);
        setRefreshRate(refreshRate);
    }

    public BufferedImage[] getAnimation() {
        return animation;
    }

    public void setAnimation(BufferedImage[] animation) {
        this.animation = animation;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) throws Exception {
        if (refreshRate > 0){
            this.refreshRate = refreshRate;
        } else {
            throw new Exception("REFRESH RATE MUST BE GREATER THAN 0");
        }
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
        for (int i = 0; i < size; i++) {
            animation[i] = ImageIO.read(in); // read image.
        }
    }
}
