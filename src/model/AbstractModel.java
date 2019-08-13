package model;

import model.entities.Entity;
import controller.Controller;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import model.persistence.Persistence;
import utilities.DisplayMessage;

/**
 *
 * @author Adrian Vazquez
 * @param <C> Controller - Extends Controller.
 * @param <E> Entity - Extends Entity.
 * @param <PK> PK - PRYMARY KEY - Extends Comparable.
 */
public abstract class AbstractModel<C extends Controller, E extends Entity, PK extends Comparable> implements Model<C, E, PK> {

    private C controller;
    private Class c;

    public AbstractModel(Class c) {
        this.c = c;
    }

    @Override
    public void setController(C controller) {
        this.controller = controller;
    }

    @Override
    public C getController() {
        return this.controller;
    }

    @Override
    public void newEntity(E entity) {
        try {
            Persistence.getInstance().getDao().save(entity);
            fireModelChanged();
        } catch (IOException ex) {
            DisplayMessage.showErrorDialog(ex.getMessage());
        }
    }

    @Override
    public E getEntity(PK pk){
        
        E entity = null;

        try {
            entity = (E) Persistence.getInstance().getDao().get(pk, c);
            
        } catch (Exception ex) {
            DisplayMessage.showErrorDialog(ex.getMessage());
        }

        return entity;
        
    }

    @Override
    public void removeEntity(E entity) {
        Persistence.getInstance().getDao().delete(entity);
        fireModelChanged();
    }

    @Override
    public void updateEntity(E entity) {
        try {
            Persistence.getInstance().getDao().update(entity);
            fireModelChanged();
        } catch (IOException ex) {
            DisplayMessage.showErrorDialog(ex.getMessage());
        }
    }

    @Override
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
