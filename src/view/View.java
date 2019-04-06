package view;

import view.battle.BattleView;
import javax.swing.JFrame;

/**
 *
 * @author Adrian Vazquez
 */
public class View extends JFrame {

    private static View instance = null;
    
    private BattleView battleControler;
    
    private View(){}
    
    public static synchronized void createInstance(){
        instance = new View();
    }
    
    public static View getInstance(){
        if (instance == null){
            createInstance();
        }
        
        return instance;
    }

}
