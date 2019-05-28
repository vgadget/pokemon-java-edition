package utilities.image;

import java.awt.image.BufferedImage;

/**
 *
 * @author Adrian Vazquez
 */
public class RGB {

    private int red;
    private int green;
    private int blue;
    private int alpha;

    public RGB(int red, int green, int blue, int alhpa) throws Exception {
        setRed(red);
        setGreen(green);
        setBlue(blue);
        setAlpha(alpha);
    }

    public RGB(int red, int green, int blue) throws Exception {
        this(red, green, blue, 0);
    }

    public RGB() throws Exception {
        this(0, 0, 0, 0);
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) throws Exception {
        if (red >= 0) {
            this.red = red;
        } else {
            throw new Exception("RED VALUE UNDER 0");
        }
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) throws Exception {
        if (green >= 0) {
            this.green = green;
        } else {
            throw new Exception("GREEN VALUE UNDER 0");
        }
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) throws Exception {
        if (blue >= 0) {
            this.blue = blue;
        } else {
            throw new Exception("BLUE VALUE UNDER 0");
        }
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) throws Exception {
        if (alpha >= 0) {
            this.alpha = alpha;
        } else {
            throw new Exception("ALPHA VALUE UNDER 0");
        }
    }

    public static RGB getRGB(int red, int green, int blue) throws Exception {
        return new RGB(red, green, blue);
    }

    public static RGB getRGB(int red, int green, int blue, int alpha) throws Exception {
        return new RGB(red, green, blue, alpha);
    }
    
    public static RGB getRGB(BufferedImage img, int x, int y) throws Exception{
        
        int[] pixel = img.getRaster().getPixel(x, y, (int[]) null);
               
        return RGB.getRGB(pixel[0], pixel[1], pixel[2]);
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
        final RGB other = (RGB) obj;
        if (this.red != other.red) {
            return false;
        }
        if (this.green != other.green) {
            return false;
        }
        if (this.blue != other.blue) {
            return false;
        }
        if (this.alpha != other.alpha) {
            return false;
        }
        return true;
    }
    
        
    

}
