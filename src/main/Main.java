package main;

import controller.SpecieController;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import languajes.StringResourceMultilingualManager;
import model.MoveModel;
import model.SpecieModel;
import model.TypeModel;
import model.entities.Move;
import model.entities.Specie;
import model.persistence.Persistence;
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
        
        
        
        //System.out.println(new MoveModel().getAll());
        
//        
//        SpecieModel specieModel = new SpecieModel();
//        SpecieController specieController = new SpecieController();
//        
//        specieModel.setController(specieController);
//        specieController.setModel(specieModel);
//        
//        TypeModel typeModel = new TypeModel();
//        
//        JFrame frame = new JFrame();
//        CreateSpecieView view = new CreateSpecieView(typeModel, specieController);
//        
//        frame.add(view);
//        
//        frame.setSize(view.getPreferredSize());
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);

       new Thread(()->{
           
           new MainMenu();
           
       }).start();
    }

}
