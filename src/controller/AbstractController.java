package controller;

import java.util.LinkedList;
import java.util.List;
import model.Model;
import model.entities.Entity;
import view.View;

/**
 *
 * @author Adrian Vazquez
 * @param <M> Model
 * @param <V> View
 * @param <E> Entity
 */
public abstract class AbstractController<M extends Model, V extends View, E extends Entity> implements Controller<M, V, E> {

    private M model;
    private List<V> views = new LinkedList<>();

    @Override
    public void setup(M model, List<V> views) {
        this.model = model;
        this.addViews(views);
        this.model.setController(this);
    }

    @Override
    public void start(){
        views.forEach((view) -> {
            view.display();
        });
    }

    @Override
    public void addView(V view) {
        this.views.add(view);
    }
    
    @Override
    public void addViews(List<V> views){
        this.views.addAll(views);
    }

    @Override
    public void removeView(V view) {
        this.views.remove(view);
    }

    @Override
    public M getModel() {
        return model;
    }

    @Override
    public void setModel(M model) {
        this.model = model;
    }

    @Override
    public void newEntityGesture(E e) {
        getModel().newEntity(e);
    }

    @Override
    public void removeEntityGesture(E e) {

        getModel().removeEntity(e);
    }

    @Override
    public void updateEntityGesture(E e) {
        
        if (getModel().getEntity(e.getPK()) != null){
            getModel().removeEntity(e);
            newEntityGesture(e);
        }
    }

    @Override
    public abstract E createEntity(List<Object> data);

    
    @Override
    public void fireDataModelChanged() {
        views.forEach((view) -> {
            view.dataModelChanged();
        });
    }
}
