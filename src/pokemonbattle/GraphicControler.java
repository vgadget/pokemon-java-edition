package pokemonbattle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import pokemonbattle.graphicscontrolers.battle.PokemonBattleGraphicControler;

/**
 *
 * @author Adrian Vazquez
 */
public class GraphicControler extends JFrame {

    private static GraphicControler instance = null;
    
    private PokemonBattleGraphicControler battleControler;
    
    private GraphicControler(){}
    
    public static synchronized void createInstance(){
        instance = new GraphicControler();
    }
    
    public static GraphicControler getInstance(){
        if (instance == null){
            createInstance();
        }
        
        return instance;
    }

}
