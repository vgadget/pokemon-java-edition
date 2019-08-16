package main;

import controller.SpecieController;
import java.awt.Color;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import languajes.StringResourceMultilingualManager;
import model.MoveModel;
import model.SpecieModel;
import model.TypeModel;
import model.entities.Movements.Move;
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
import view.components.Notification;
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
        //languajes.StringResourceMultilingualManager.getInstance().setDefaultLanguage("ESPAÑOL");

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
//        CreateSpecieView view = new CreateSpecieView(typeModel, specieController);
//        
//        frame.add(view);
//        

       new Thread(()->{
           
           new MainMenu();
           
       }).start();


        while (true) {
            

            Notification.getInstance().displayNotification("こんにちは");
            
        }

    }

}
