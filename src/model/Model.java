package model;

import controller.Controller;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import model.persistence.Persistence;
import model.pokemon.Pokemon;
import utilities.DisplayMessage;

/**
 *
 * @author Adrian Vazquez
 * @param <C> Controller - Extends Controller.
 * @param <E> Entity - Extends Entity.
 * @param <PK> PK - PRYMARY KEY - Extends Comparable.
 */
public abstract class Model<C extends Controller, E extends Entity, PK extends Comparable> {

    private Controller controller;
    private Class c;

    public Model(Controller controller, Class c) {
        setController(controller);
        this.c = c;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return this.controller;
    }

    public void newEntity(E entity) {
        try {
            Persistence.getInstance().getDao().save(entity);
        } catch (IOException ex) {
            DisplayMessage.showErrorDialog(ex.getMessage());
        }
    }

    public E getEntity(PK pk){
        
        E entity = null;

        try {
            entity = (E) Persistence.getInstance().getDao().get(pk, c);
        } catch (Exception ex) {
            DisplayMessage.showErrorDialog(ex.getMessage());
        }

        return entity;
        
    }

    public void removeEntity(E entity) {
        Persistence.getInstance().getDao().delete(entity);
    }

    public void updateEntity(E entity) {
        try {
            Persistence.getInstance().getDao().update(entity);
        } catch (IOException ex) {
            DisplayMessage.showErrorDialog(ex.getMessage());
        }
    }

    public List<E> getAll() {
        
        try {
            return Persistence.getInstance().getDao().getAll(c);
        } catch (Exception ex) {
            DisplayMessage.showErrorDialog(ex.getMessage());
        }

        return new LinkedList();
    }
    
    protected void fireModelChanged(){
        this.controller.fireDataModelChanged();
    }

}
