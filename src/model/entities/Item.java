package model.entities;

import model.BattleModel;

/**
 *
 * @author Adrian Vazquez
 */
public interface Item extends Entity<String> {

    public String getName();

    public void makeEffect(Pokemon pFrom, Pokemon pTo, BattleModel battleModel);

}
