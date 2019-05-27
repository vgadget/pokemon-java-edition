package controller;

import java.util.List;
import model.Model;
import model.entities.Entity;
import view.View;

/**
 *
 * @author Adrian Vazquez
 * @param <M> Model
 * @param <V> View
 */
public interface Controller<M extends Model, V extends View, E extends Entity> {

    public void setup(M model, List<V> views);

    public void start();

    public void addView(V view);
    
    public void addViews(List<V> views);

    public void removeView(V view);

    public M getModel();

    public void setModel(M model);

    public void newEntityGesture(E e);

    public void removeEntityGesture(E e);

    public void updateEntityGesture(E e);

    public Entity createEntity(List<Object> data);

    public void fireDataModelChanged();

}
