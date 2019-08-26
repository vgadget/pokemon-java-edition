package model.entities;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import model.persistence.Persistence;
import utilities.displaymessage.DisplayMessage;

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
    private Pokemon selectedPokemon;
    private Sprite sprite;

    private TrainerLocal() throws IOException, ClassNotFoundException {

        TrainerLocal tl = (TrainerLocal) Persistence.getInstance().getDao().get(getPK(), TrainerLocal.class);

        setName(tl.getName());
        setTeam(tl.team);
        setSelectedPokemon(tl.getSelectedPokemon());
        setSprite(tl.getSprite());
    }

    private TrainerLocal(String name, Sprite sprite, Pokemon initialPokemon) {

        this(name, sprite, new LinkedList<>(), initialPokemon);

        List<Pokemon> team = new LinkedList<>();

        team.add(initialPokemon);

        setTeam(team);
    }

    private TrainerLocal(String name, Sprite sprite, List<Pokemon> team, Pokemon selectedPokemon) {
        setName(name);
        setTeam(team);
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

        if (team == null || team.size() <= 0) {
            throw new IllegalArgumentException("Pokemon team has to be at least one pokemon");
        }

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
    public List<Pokemon> getTeam() {
        return team;
    }

    @Override
    public String getPK() {
        return "SINGLETON-TRAINER";
    }

    @Override
    public int compareTo(Entity o) {

        if (o instanceof Trainer) {
            return this.getPK().compareTo(((Trainer) o).getPK());
        }

        return this.getClass().getName().compareTo(o.getClass().getName());
    }

}
