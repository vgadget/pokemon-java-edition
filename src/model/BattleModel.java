package model;

import controller.BattleController;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.Queue;
import model.entities.Modifier;
import model.entities.Pokemon;
import model.entities.Trainer;
import utilities.image.Image;
import utilities.patterns.observer.Subject;

/**
 *
 * @author Adrian Vazquez
 */
public interface BattleModel extends Subject<Modifier> {

    public void setup(Trainer p1, Trainer p2, BattleController battleController);

    public Trainer getTrainerP1();

    public Trainer getTrainerP2();

    public Double calculateDamage(Pokemon p1, Pokemon p2);

    public Integer getCurrentTurn();

    public void setActionSelected(Trainer t, Boolean selected);

    public void setActionExecuted(Trainer t, Boolean executed);

    public boolean isActionSelected(Trainer t);

    public boolean isActionExecuted(Trainer t);

// Graphics
    public void addGraphicLayer(Graphic g);

    public void addGraphicLayer(Trainer t, Graphic g);

    public Queue<Graphic> getGraphics(Trainer t);

    public void setGraphicsRefreshRate(Integer ms);

    public Integer getRefreshRate();
    
    public void update();
}

enum ID {
    BACKGROUND, SPRITE, MODIFIER, HUD
}

class Graphic implements Serializable{

    private ID id;
    private int priority;
    private Image img;
    private Dimension size;
    private int x, y;

    public Graphic(ID id, int priority, Image img, Dimension size, int x, int y) {
        this.id = id;
        this.priority = priority;
        this.img = img;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public int getID() {
        return priority;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
