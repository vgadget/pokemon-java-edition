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
import texttospeech.Narrator;
import utilities.CSVManager;
import utilities.image.Dimensions;
import utilities.sound.Sound;
import view.pokedex.CreateSpecieView;
import view.View;
import view.components.AidPanel;
import view.components.ButtonFactory;
import view.components.CustomButton;
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
        
        
       
        /*
        StringResourceMultilingualManager.getInstance().setDefaultLanguage("ESPAÑOL");
        
        JFrame frame = new JFrame();
        JPanel panel = new AidPanel();
        
        CustomButton cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11;
        
        cb1 = ButtonFactory.menuButton("ONE");
        cb2 = ButtonFactory.menuButton("TWO");
        cb3 = ButtonFactory.menuButton("THREE");
        cb4 = ButtonFactory.menuButton("FOUR");
        cb5 = ButtonFactory.menuButton("FIVE");
        cb6 = ButtonFactory.menuButton("SIX");
        cb7 = ButtonFactory.menuButton("SEVEN");
        cb8 = ButtonFactory.menuButton("EIGHT");
        cb9 = ButtonFactory.menuButton("NINE");
        cb10 = ButtonFactory.menuButton("TEN");
        cb11 = ButtonFactory.menuButton("ELEVEN");
        
        panel.add(cb1);
        panel.add(cb2);
        panel.add(cb3);
        panel.add(cb4);
        panel.add(cb5);
        panel.add(cb6);
        panel.add(cb7);
        panel.add(cb8);
        panel.add(cb9);
        panel.add(cb10);
        panel.add(cb11);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Dimensions.frameDimension1080p);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        */
        
        /*
        StringResourceMultilingualManager manager = StringResourceMultilingualManager.getInstance();
        
        manager.setResource("model.entities.Type - Bug", "Bicho");
        */
        
        Narrator.getInstance().setLanguage(Narrator.Language.ENGLISH);
        //StringResourceMultilingualManager.getInstance().setDefaultLanguage("ESPAÑOL");
        
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
