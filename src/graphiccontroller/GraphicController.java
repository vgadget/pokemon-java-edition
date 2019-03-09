package graphiccontroller;

import javax.swing.JFrame;
import graphiccontroller.battle.BattleGraphicControllerImpl;

/**
 *
 * @author Adrian Vazquez
 */
public class GraphicController extends JFrame {

    private static GraphicController instance = null;
    
    private BattleGraphicControllerImpl battleControler;
    
    private GraphicController(){}
    
    public static synchronized void createInstance(){
        instance = new GraphicController();
    }
    
    public static GraphicController getInstance(){
        if (instance == null){
            createInstance();
        }
        
        return instance;
    }

}
