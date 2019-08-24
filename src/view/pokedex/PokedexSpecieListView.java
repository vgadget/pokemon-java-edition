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
import view.components.AidPanelImpl;
import view.components.ButtonFactory;
import view.components.CustomButton;

/**
 *
 * @author Adrian Vazquez
 */
public class PokedexSpecieListView extends AidPanelImpl {

    private Dimension size;
    private PokedexEntryView entryView;
    private SpecieController specieController;
    private TypeModel typeModel;
    private JFrame frame;

    private PokedexView container;

    public PokedexSpecieListView(Dimension size, PokedexEntryView pokedexEntryView, SpecieController specieController, TypeModel typeModel) {
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

    public synchronized void setup() {

        // REMOVE ALL COMPONENTS 
        for (Component c : this.getComponents()) {
            this.remove(c);
        }

        // COLOR BACGROUND
        setBackground(new Color(57, 57, 57)); // SET BACKGROUND

        // VIEW SIZE
        Dimension frameDimension = new Dimension((int) (size.getWidth() * 1f), (int) (size.getWidth() * 1f));

        List<String> pkList = specieController.getModel().getAllPk();

        while (pkList.contains(null)) {
            pkList = specieController.getModel().getAllPk();
        }

        Collections.sort(pkList, new utilities.string.StringComparator()); // SORT SPECIE LIST.

        pkList.forEach((String s) -> { // FOR EACH SPECIE CREATES A BUTTON.

            try {

                //System.out.println(s.getName());
                Specie specie = specieController.getModel().getEntity(s);

                while (specie == null) {
                    specie = specieController.getModel().getEntity(s);
                }

                CustomButton cb = ButtonFactory.getPokedexSpecieButton(specie, frameDimension);
                cb.setDescription(specie.getName());

                // IF BUTTON PRESSED, SHOW THAT POKEMON | ACTION LISTENER.
                cb.setPressedVoiceFeedback(specie.toString());

                cb.addActionListener(new ActionListener() {

                    private String speciePk = s;

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        new Thread(() -> {

                            Specie specieSelected = specieController.getModel().getEntity(speciePk);

                            entryView.setSpecie(specieSelected);

                            if (isEnabledAudioDescription()) {
                                cb.getPressedVoiceFeedback();

                                String cry = "It_sounds_like";

                                if (!StringResourceMultilingualManager.getInstance().keyExist(cry)) {

                                    StringResourceMultilingualManager.getInstance().addKey(cry);

                                }

                                Narrator.getInstance().speak(StringResourceMultilingualManager.getInstance().getResource(cry));

                                SoundPlayer.getInstance().playEffectChannel(specieSelected.getCry());
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
        if (pkList.size() > 0) {
            entryView.setSpecie(specieController.getModel().getEntity(pkList.get(0)));
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

            CustomButton cb = ButtonFactory.pokedexButton(text, frameDimension);

            cb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    MainFrame.getInstance().hideMainFrame();

                    frame = new JFrame(cb.getDescription());

                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                            MainFrame.getInstance().showMainFrame();
                            MainFrame.getInstance().requestFocusInWindow();
                        }
                    });

                    CreateSpecieView panel = new CreateSpecieView(typeModel, specieController, frame);
                    frame.add(panel);
                    frame.setSize(panel.getPreferredSize());
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setAlwaysOnTop(true);
                    frame.requestFocus();
                }
            });

            add(cb);
        } catch (Exception ex) {
            Logger.getLogger(PokedexSpecieListView.class.getName()).log(Level.SEVERE, null, ex);
        }

        //CREATE EXIT BUTTON
        try {

            CustomButton cb = ButtonFactory.pokedexButton(ButtonTexts.getInstance().goBack(), frameDimension);

            cb.addActionListener((ActionEvent e) -> {

                MainFrame.getInstance().previousView();

            });

            add(cb);

            // SHOW ALL PROPERLY
            setPreferredSize(new Dimension((int) (frameDimension.getWidth() * 0.90f), (int) ((cb.getSize().height * (pkList.size() + 10)))));

        } catch (Exception e) {
            utilities.DisplayMessage.showErrorDialog(e.getMessage());
        }

        repaint();
        
    }

}
