package model.entities;

import model.BattleModel;
import utilities.image.Image;

/**
 *
 * @author Adrian Vazquez
 */
public interface Item extends Entity<String> {

    public String getName();
    
    public Image getSprite();

    public void makeEffect(Pokemon pFrom, Pokemon pTo, BattleModel battleModel);

}
