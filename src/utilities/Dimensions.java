package utilities;

import java.awt.Dimension;

/**
 *
 * @author Adrian Vazquez
 */
public class Dimensions {

    public static final Dimension frameDimension1080p = new Dimension((int) (1920 / 1.3), (int) (1080 / 1.3));
    public static final Dimension frameDimension720p = new Dimension((int) (1280 / 1.3), (int) (720 / 1.3));
    
    public static final Dimension mainMenuResolution =  new Dimension(getSelectedResolution().width, (int) (getSelectedResolution().height*1.3));
    
    public static Dimension getSelectedResolution(){
        return frameDimension720p;
    }
    public static Dimension getMainMenuResolution(){
        
        if (getSelectedResolution().equals(frameDimension1080p)){
            return frameDimension1080p;
        }
        
        return mainMenuResolution;
    }

}
