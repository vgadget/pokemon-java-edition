package view;

import controller.Controller;
import model.Model;

/**
 *
 * @author Adrian Vazquez
 */
public interface View<M extends Model, C extends Controller> {

    public C getController();

    public void setController(C controller);

    public M getModel();

    public void setModel(M model);

    public void display();

    public void dataModelChanged();

}
