package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import utilities.sound.Sound;
import view.Pokedex.CreateSpecie;

/**
 *
 * @author Adrian Vazquez
 */
public class Main {

    public static void main(String[] args) throws Exception {
        
        
        JFrame frame = new JFrame("Create new specie");
        
        frame.add(new CreateSpecie());
        
        frame.setSize(1050, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //new MainMenu();
        //debug.ModelDebug.testTypeModel();
        //debug.ModelDebug.testSpecie();
        //debug.PokemonBattleViewDebug.test();
        
        
        
//        Sound s;

//        s = new Sound(new File("test.wav"));
//
//        //Saving of object in a file 
//        FileOutputStream file = new FileOutputStream("soundClassTest");
//        ObjectOutputStream out = new ObjectOutputStream(file);
//
//        // Method for serialization of object 
//        out.writeObject(s);
//        out.close();
//        file.close();
//
//        System.out.println("Object has been serialized");

//        s = null;
//
//        // Reading the object from a file 
//        FileInputStream file2 = new FileInputStream("soundClassTest");
//        ObjectInputStream in = new ObjectInputStream(file2);

//        // Method for deserialization of object 
//        s = (Sound) in.readObject();
//
//        in.close();
//        file2.close();
//
//        System.out.println("Object has been deserialized ");
//        
//        System.out.println(""+s.getFramesLength());
//
//        s.start(14442441/2, 0);

    }

}
