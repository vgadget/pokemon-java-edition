package main;

import view.menu.MainMenuView;

/**
 *
 * @author Adrian Vazquez
 */
public class Main {

    public static void main(String[] args) throws Exception {

        //Debug
       //debug.ViewDebug.test();
       //debug.PokemonBattleViewDebug.test();
       
       new MainMenuView(utilities.Dimensions.frameDimension1080p).setVisible(true);
       
//       
//        AnimatedBackground a = new AnimatedBackground(Dimensions.frameDimension720p);
//       
//        
//        JFrame frame = new JFrame("VENTANA");
//        frame.setSize(utilities.Dimensions.frameDimension720p);
//        frame.setLocationRelativeTo(null);
//        frame.add(a);
//        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
    }

}
