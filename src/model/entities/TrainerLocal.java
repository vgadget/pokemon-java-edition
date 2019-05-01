package model.entities;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import model.persistence.Persistence;
import utilities.DisplayMessage;

/**
 *
 * @author Adrian Vazquez
 */
public class TrainerLocal implements Trainer {

    //SINGLETON
    private static TrainerLocal instance;

    public static TrainerLocal getInstance() {

        if (instance == null) {
            try {
                instance = new TrainerLocal();
            } catch (Exception ex) {
                instance = null;
            }
        }

        return instance;
    }

    public static TrainerLocal getInstance(String name, Sprite sprite, Pokemon initialPokemon) {

        instance = new TrainerLocal(name, sprite, initialPokemon);

        return instance;
    }

    // END OF SINGLETON
    private String name;
    private List<Pokemon> team;
    private List<Pokemon> box;
    private Pokemon selectedPokemon;
    private Sprite sprite;

    private TrainerLocal() throws IOException, ClassNotFoundException {
        TrainerLocal tl = (TrainerLocal) Persistence.getInstance().getDao().get(getPK(), TrainerLocal.class);

        setName(tl.getName());
        setTeam(tl.team);
        setBox(tl.box);
        setSelectedPokemon(tl.getSelectedPokemon());
        setSprite(tl.getSprite());
    }

    public TrainerLocal(String name, Sprite sprite, Pokemon initialPokemon) {

        this(name, sprite, new LinkedList<Pokemon>(), new LinkedList<Pokemon>(), initialPokemon);

        List<Pokemon> team = new LinkedList<Pokemon>();

        team.add(initialPokemon);

        setTeam(team);
    }

    private TrainerLocal(String name, Sprite sprite, List<Pokemon> team, List<Pokemon> box, Pokemon selectedPokemon) {
        setName(name);
        setTeam(team);
        setBox(box);
        setSelectedPokemon(selectedPokemon);
        setSprite(sprite);
    }

    public void setName(String name) {
        this.name = name;
        try {
            Persistence.getInstance().getDao().save(this);
        } catch (IOException ex) {
            DisplayMessage.showErrorDialog(ex.toString());
        }
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
        try {
            Persistence.getInstance().getDao().save(this);
        } catch (IOException ex) {
            DisplayMessage.showErrorDialog(ex.toString());
        }
    }

    public void setBox(List<Pokemon> box) {
        this.box = box;
        try {
            Persistence.getInstance().getDao().save(this);
        } catch (IOException ex) {
            DisplayMessage.showErrorDialog(ex.toString());
        }
    }

    public void setSelectedPokemon(Pokemon selectedPokemon) {
        this.selectedPokemon = selectedPokemon;
        try {
            Persistence.getInstance().getDao().save(this);
        } catch (IOException ex) {
            DisplayMessage.showErrorDialog(ex.toString());
        }
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        try {
            Persistence.getInstance().getDao().save(this);
        } catch (IOException ex) {
            DisplayMessage.showErrorDialog(ex.toString());
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public Pokemon getSelectedPokemon() {
        return selectedPokemon;
    }

    @Override
    public Iterator<Pokemon> getTeam() {
        return team.iterator();
    }

    @Override
    public Iterator<Pokemon> getBox() {
        return box.iterator();
    }

    @Override
    public String getPK() {
        return "SINGLETON";
    }

    @Override
    public int compareTo(Entity o) {
        if (o instanceof Trainer) {
            return this.getPK().compareTo(((Trainer) o).getPK());
        }

        return this.getClass().getName().compareTo(o.getClass().getName());
    }

}
