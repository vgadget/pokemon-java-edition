package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.pokemon.Move;
import model.pokemon.MoveSet;
import model.pokemon.Type;
import view.menu.MainMenu;

/**
 *
 * @author Adrian Vazquez
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        
//        BufferedImage tag = ImageIO.read(new File("example.png"));
//        
//        Type fire = new Type("FIRE", tag, Color.RED);
//        Type water = new Type("WATER", tag, Color.BLUE);
//        
//        BufferedImage[] animaton = new BufferedImage[1];
//        animaton[0] = tag;
//        
//        Move one = new Move("firstMove", 10, 10, 10, null, fire, animaton);
//        Move two = new Move("secondMove", 20, 20, 20, null, fire, animaton);
//
//        MoveSet moveSet = new MoveSet();
//        moveSet.add(one);
//        moveSet.add(two);
//        
//        System.out.println(moveSet);
        

       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                try {
                    //new MainMenu();
                    debug.ViewDebug.test();
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });

               
    }

}
