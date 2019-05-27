package view;

import controller.Controller;
import model.Model;

/**
 *
 * @author Adrian Vazquez
 */
public interface View<M extends Model, C extends Controller> {
    public void display();
    public void dataModelChanged();
}
