package main;

import controller.SpecieController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import languajes.StringResourceMultilingualManager;
import model.SpecieModel;
import model.TypeModel;
import utilities.CSVReader;
import utilities.sound.Sound;
import view.Pokedex.CreateSpecieView;
import view.View;

/**
 *
 * @author Adrian Vazquez
 */
public class Main {

    public static void main(String[] args) throws Exception {
        
        
     //   CSVReader reader = new CSVReader(new File("Resources\\languages\\translations.csv"));
        
        StringResourceMultilingualManager s = StringResourceMultilingualManager.getInstance();
        
        //System.out.println(s.getAvailableLanguages());
        
        s.addKey("SALUDO2");
        
        s.setDefaultLanguage("ENGLISH");
        
        s.setResource("SALUDO2", "Bye");
        
        System.out.println(s.getAvailableKeys());
        
        //System.out.println(s.getResource("SALUDO", "ESPAÑOL"));
        
        
        
        /*SpecieModel specieModel = new SpecieModel();
        TypeModel typeModel = new TypeModel();
        
        CreateSpecieView p = new CreateSpecieView(specieModel, typeModel);
        
        List<View> views = new ArrayList<>();
        views.add(p);
        
        SpecieController specieController = new SpecieController();
        specieController.setup(specieModel, views);
        
        
        JFrame frame = new JFrame("Create new specie");
        
        
        
        
        
        frame.add(p);
        
        frame.setSize(p.getPreferredSize());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); */

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
