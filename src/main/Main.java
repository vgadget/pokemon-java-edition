package main;

import controller.SpecieController;
import java.awt.Dimension;
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
import view.menu.MainMenu;
import view.pokedex.PokedexEntryView;
import view.pokedex.PokedexSpecieListView;
import view.pokedex.PokedexView;
import view.pokedex.PokedexViewImpl;

/**
 *
 * @author Adrian Vazquez
 */
public class Main {

    public static void main(String[] args) throws Exception {
        
        
        //StringResourceMultilingualManager.getInstance().setDefaultLanguage("日本語");
        
        
        SpecieModel specieModel;
        TypeModel typeModel;
        CreateSpecieView createSpecieView;
        SpecieController specieController;
        
        specieModel = new SpecieModel();
        typeModel = new TypeModel();
        
        specieController = new SpecieController();
        
        specieModel.setController(specieController);
        specieController.setModel(specieModel);
        
        PokedexView view = new PokedexViewImpl(specieController, typeModel);
        
        List<View> views = new ArrayList<>();
        views.add(view);
        
        
        specieController.setup(specieModel, views);
        
        
        JFrame frame = new JFrame("Create new specie");
        
       
        
        frame.add(view);
        
        frame.setSize(view.getPreferredSize());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true); 

        //new MainMenu();
        
        
        
        
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
