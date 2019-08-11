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
                
        Narrator.getInstance().setLanguage(Narrator.Language.ENGLISH);
        
        
        //debug.PokemonBattleViewDebug.test();
        
        //StringResourceMultilingualManager.getInstance().setDefaultLanguage("ESPAÑOL");
//        
//        SpecieModel specieModel;
//        TypeModel typeModel;
//        CreateSpecieView createSpecieView;
//        SpecieController specieController;
//        
//        specieModel = new SpecieModel();
//        typeModel = new TypeModel();
//        
//        specieController = new SpecieController();
//        
//        specieModel.setController(specieController);
//        specieController.setModel(specieModel);
//        
//        PokedexView view = new PokedexViewImpl(specieController, typeModel);
//        
//        List<View> views = new ArrayList<>();
//        views.add(view);
//        
//        
//        specieController.setup(specieModel, views);
//        
//        
//        JFrame frame = new JFrame("Create new specie");
//        
//       
//        
//        frame.add(view);
//        
//        frame.setSize(view.getPreferredSize());
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(true);
//        frame.setVisible(true);

       new Thread(()->{
           
           new MainMenu();
           
       }).start();
      

    }

}
