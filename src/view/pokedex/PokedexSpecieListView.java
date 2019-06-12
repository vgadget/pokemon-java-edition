package view.pokedex;

import controller.SpecieController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.TypeModel;
import model.entities.Specie;
import view.components.ButtonFactory;
import view.components.CustomButton;

/**
 *
 * @author Adrian Vazquez
 */
public class PokedexSpecieListView extends JPanel {

    private List<Specie> specieList;
    private Dimension size;
    private PokedexEntryView ev;
    private SpecieController specieController;
    private TypeModel typeModel;
    private JFrame frame;

    public void setUp() {
        
        for (Component c : this.getComponents()){
            this.remove(c);
        }

        setBackground(Color.LIGHT_GRAY);

        Dimension buttonSize = new Dimension((int) (size.getWidth() * 0.80f), (int) (size.getWidth() * 0.80f));
        specieList.forEach((s) -> {
            try {
                //System.out.println(s.getName());
                CustomButton cb = ButtonFactory.getPokedexSpecieButton(s, buttonSize);

                cb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ev.setSpecie(s);
                            }
                        }).start();
                    }
                });

                add(cb);

            } catch (Exception ex) {
                Logger.getLogger(PokedexSpecieListView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

       String text = "Create specie.";
//        if (languajes.StringResourceMultilingualManager.getInstance().keyExist(getClass().getName() + "-CREATE_NEW_SPECIE")) {
//
//            text = languajes.StringResourceMultilingualManager.getInstance().getResource(getClass().getName() + "-CREATE_NEW_SPECIE");
//
//        } else {
//
//            languajes.StringResourceMultilingualManager.getInstance().addKey(getClass().getName() + "-CREATE_NEW_SPECIE");
//            languajes.StringResourceMultilingualManager.getInstance().setResource(getClass().getName() + "-CREATE_NEW_SPECIE", text);
//        }

        try {
            CustomButton cb = ButtonFactory.getPokedexCreateSpecie(text, buttonSize);
            frame = new JFrame(text);

            cb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CreateSpecieView panel = new CreateSpecieView(typeModel, specieController);
                    frame.add(panel);
                    frame.setSize(panel.getPreferredSize());
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setAlwaysOnTop(true);
                    frame.setVisible(true);
                    setUp();
                    
                }
            });

            add(cb);
        } catch (Exception ex) {
            Logger.getLogger(PokedexSpecieListView.class.getName()).log(Level.SEVERE, null, ex);
        }

        repaint();
        setPreferredSize(new Dimension((int) buttonSize.getWidth(), (int) ((buttonSize.getHeight() * this.specieList.size()) + size.getHeight())));

    }

    public PokedexSpecieListView(List<Specie> specieList, Dimension size, PokedexEntryView pokedexEntryView, SpecieController specieController, TypeModel typeModel) {
        this.specieList = specieList;
        this.size = size;
        this.ev = pokedexEntryView;
        this.specieController = specieController;
        this.typeModel = typeModel;

        setUp();
    }

}
