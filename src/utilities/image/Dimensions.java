package utilities.image;

import java.awt.Dimension;

/**
 *
 * @author Adrian Vazquez
 */
public class Dimensions {

    public static final Dimension frameDimension1080p = new Dimension((int) (1920 / 1.3), (int) (1080 / 1.3));
    public static final Dimension frameDimension720p = new Dimension((int) (1280 / 1.3), (int) (720 / 1.3));
    public static final Dimension frameDimension2k =  new Dimension((int) (1280*2 / 1.3), (int) (720*2 / 1.3));
    
    private static Dimension selectedResolution = frameDimension2k;
    
    public static Dimension getSelectedResolution(){
        return selectedResolution;
    }
    
    public static void setSelectedResolution(Dimension resolution){
        selectedResolution = resolution;
    }
   

}
