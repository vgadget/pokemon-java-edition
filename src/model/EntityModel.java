package model;

import model.entities.Entity;
import controller.Controller;
import java.util.List;

/**
 *
 * @author Adrian Vazquez
 * @param <E>
 * @param <PK>
 */
public interface EntityModel<E extends Entity, PK extends Comparable> {

    List<E> getAll();

    Controller getController();

    E getEntity(PK pk);

    void newEntity(E entity);

    void removeEntity(E entity);

    void setController(Controller controller);

    void updateEntity(E entity);
    
}
