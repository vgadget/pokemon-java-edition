package model.entities;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Adrian Vazquez
 */
public class Sprite implements Serializable {

    private List<Image> animation;
    private int refreshRate;
    int animationLength;

    public Sprite(BufferedImage[] animation, int refreshRate) throws Exception {
        setAnimation(animation);
        setRefreshRate(refreshRate);
    }

    public BufferedImage[] getAnimation() {

        BufferedImage[] imgs = new BufferedImage[animation.size()];

        for (int i = 0; i < animation.size(); i++) {

            imgs[i] = animation.get(i).getImage();

        }

        return imgs;
    }
    

    public void setAnimation(BufferedImage[] animation) {

        this.animation = new LinkedList<>();

        for (BufferedImage img : animation) {
            this.animation.add(new Image(img));
        }

        this.animationLength = this.animation.size();
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) throws Exception {
        if (refreshRate > 0) {
            this.refreshRate = refreshRate;
        } else {
            throw new Exception("REFRESH RATE MUST BE GREATER THAN 0");
        }
    }

//    private void writeObject(ObjectOutputStream out) throws IOException {
//        out.defaultWriteObject(); // All fields will be saved normally.
//
//
////        for (BufferedImage sprite : animation) { // Serialze every image
////            ImageIO.write(sprite, "png", out);
////        }
//    }
//
//    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//        in.defaultReadObject();
//       
//
////        animation = new BufferedImage[animationLength];
////        for (int i = 0; i < animationLength; i++) {
////            animation[i] = ImageIO.read(in); // read image.
////            System.out.println(animation[i]);
////        }
//    }
}
