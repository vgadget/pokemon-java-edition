package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import view.menu.MainMenu;

/**
 *
 * @author Adrian Vazquez
 */
public class Main {

    public static void main(String[] args) throws Exception {
        

       java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                try {
                    new MainMenu();
                    //debug.ViewDebug.test();
                    //debug.PokemonBattleViewDebug.test();
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });

               
    }

}
