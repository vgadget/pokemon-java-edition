package view.pokedex;

import controller.SpecieController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import languajes.ButtonTexts;
import languajes.StringResourceMultilingualManager;
import model.TypeModel;
import model.entities.Specie;
import texttospeech.Narrator;
import utilities.sound.SoundPlayer;
import view.MainFrame;
import view.components.AidPanel;
import view.components.ButtonFactory;
import view.components.CustomButton;

/**
 *
 * @author Adrian Vazquez
 */
public class PokedexSpecieListView extends AidPanel {

    private List<Specie> specieList;
    private Dimension size;
    private PokedexEntryView entryView;
    private SpecieController specieController;
    private TypeModel typeModel;
    private JFrame frame;

    private PokedexView container;

    public PokedexSpecieListView(List<Specie> specieList, Dimension size, PokedexEntryView pokedexEntryView, SpecieController specieController, TypeModel typeModel) {
        this.specieList = specieList;
        this.size = size;
        this.entryView = pokedexEntryView;
        this.specieController = specieController;
        this.typeModel = typeModel;

        setup();
    }

    public PokedexView getContainer() {
        return container;
    }

    public void setContainer(PokedexView container) {
        this.container = container;
    }

    public void setup() {

        // REMOVE ALL COMPONENTS 
        for (Component c : this.getComponents()) {
            this.remove(c);
        }

        // COLOR BACGROUND
        setBackground(new Color(0, 132, 99)); // SET BACKGROUND

        // VIEW SIZE
        Dimension buttonSize = new Dimension((int) (size.getWidth() * 1f), (int) (size.getWidth() * 1f));

        Collections.sort(specieList); // SORT SPECIE LIST.

        specieList.forEach((s) -> { // FOR EACH SPECIE CREATES A BUTTON.
            try {
                //System.out.println(s.getName());
                CustomButton cb = ButtonFactory.getPokedexSpecieButton(s, buttonSize);
                cb.setDescription(s.getName());

                // IF BUTTON PRESSED, SHOW THAT POKEMON | ACTION LISTENER.
                cb.setPressedVoiceFeedback(s.toString());

                cb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                entryView.setSpecie(s);
                                cb.getPressedVoiceFeedback();

                                String cry = "It_sounds_like";

                                if (!StringResourceMultilingualManager.getInstance().keyExist(cry)) {

                                    StringResourceMultilingualManager.getInstance().addKey(cry);

                                }

                                Narrator.getInstance().speak(StringResourceMultilingualManager.getInstance().getResource(cry));

                                SoundPlayer.getInstance().playEffectChannel(s.getCry());
                            }
                        }).start();
                    }
                });

                add(cb); // ADD BUTTON TO PANEL.

            } catch (Exception ex) {
                Logger.getLogger(PokedexSpecieListView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // START SHOWING FIRST SPECIE
        if (specieList.size() > 0) {
            entryView.setSpecie(specieList.get(0));
        }

        // CREATE SPECIE BUTTON
        try {

            String text = "Create specie.";

            if (languajes.StringResourceMultilingualManager.getInstance().keyExist(getClass().getName() + "-CREATE_NEW_SPECIE")) {

                text = languajes.StringResourceMultilingualManager.getInstance().getResource(getClass().getName() + "-CREATE_NEW_SPECIE");

            } else {

                languajes.StringResourceMultilingualManager.getInstance().addKey(getClass().getName() + "-CREATE_NEW_SPECIE");
                languajes.StringResourceMultilingualManager.getInstance().setResource(getClass().getName() + "-CREATE_NEW_SPECIE", text);
            }

            CustomButton cb = ButtonFactory.pokedexButton(text, buttonSize);

            cb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    frame = new JFrame(cb.getDescription());

                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                            container.refresh();
                        }
                    });

                    CreateSpecieView panel = new CreateSpecieView(typeModel, specieController);
                    frame.add(panel);
                    frame.setSize(panel.getPreferredSize());
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            });

            add(cb);
        } catch (Exception ex) {
            Logger.getLogger(PokedexSpecieListView.class.getName()).log(Level.SEVERE, null, ex);
        }

        //CREATE EXIT BUTTON
        try {

            CustomButton cb = ButtonFactory.pokedexButton(ButtonTexts.getInstance().goBack(), buttonSize);
            
            cb.addActionListener((ActionEvent e) -> {
                
                MainFrame.getInstance().previousView();
                
            });

            add(cb);
            
            
        } catch (Exception e) {

        }

        // SHOW ALL PROPERLY
        repaint();
        setPreferredSize(new Dimension((int) buttonSize.getWidth(), (int) ((buttonSize.getHeight() * this.specieList.size()) + size.getHeight())));

        requestFocusInWindow();
    }

}
