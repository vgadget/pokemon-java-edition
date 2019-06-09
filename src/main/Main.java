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
import view.pokedex.CreateSpecieView;
import view.View;

/**
 *
 * @author Adrian Vazquez
 */
public class Main {

    public static void main(String[] args) throws Exception {
        
        
        StringResourceMultilingualManager.getInstance().setDefaultLanguage("ESPAÑOL");
        
        
        SpecieModel specieModel;
        TypeModel typeModel;
        CreateSpecieView createSpecieView;
        SpecieController specieController;
        
        specieModel = new SpecieModel();
        typeModel = new TypeModel();
        
        specieController = new SpecieController();
        
        specieModel.setController(specieController);
        specieController.setModel(specieModel);
        
        createSpecieView = new CreateSpecieView(typeModel, specieController);
        
                
        List<View> views = new ArrayList<>();
        views.add(createSpecieView);
        
        
        specieController.setup(specieModel, views);
        
        
        JFrame frame = new JFrame("Create new specie");
        
        
        
        
        
        frame.add(createSpecieView);
        
        frame.setSize(createSpecieView.getPreferredSize());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
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
