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
public interface Model<C extends Controller, E extends Entity, PK extends Comparable> {

    List<E> getAll();

    List<PK> getAllPk();
    
    C getController();

    E getEntity(PK pk);

    void newEntity(E entity);

    void removeEntity(E entity);

    void setController(C controller);

    void updateEntity(E entity);
    
}
