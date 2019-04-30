package model.pokemon;

import model.Entity;

/**
 *
 * @author Adrian Vazquez
 */
public interface Item extends Entity<String> {

    public String getName();

    public void makeEffect(Pokemon p);

}
