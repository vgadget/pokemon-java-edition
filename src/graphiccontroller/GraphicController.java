package graphiccontroller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import graphiccontroller.battle.BattleGraphicController;

/**
 *
 * @author Adrian Vazquez
 */
public class GraphicController extends JFrame {

    private static GraphicController instance = null;
    
    private BattleGraphicController battleControler;
    
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
