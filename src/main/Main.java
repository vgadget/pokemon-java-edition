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

    //Models
    public static final TypeModel typeModel = new TypeModel();
    public static final SpecieModel specieModel = new SpecieModel();

    //Controllers
    public static final SpecieController specieController = new SpecieController();

    public static void main(String[] args) throws Exception {

        Narrator.getInstance().setEnabled(false);

        //Link specie model, specie controller and view.
        specieModel.setController(specieController);
        specieController.setModel(specieModel);
        PokedexView pokedexView = new PokedexViewImpl(specieController, typeModel);
        specieController.addView(pokedexView);

        // Set Narrator 
        Narrator.getInstance().setLanguage(Narrator.Language.ENGLISH);

        Narrator.getInstance().setEnabled(true);

//        specieController.start();
        new Thread(() -> {

            new MainMenu();

        }).start();

    }

}
