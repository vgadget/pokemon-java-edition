package model.entities;

/**
 *
 * @author Adrian Vazquez
 */
public interface Item extends Entity<String> {

    public String getName();

    public void makeEffect(Pokemon p);

}
