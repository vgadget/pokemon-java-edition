package view;

import controller.Controller;
import javax.swing.JPanel;
import model.Model;

/**
 *
 * @author Adrian Vazquez
 */
public abstract class AbstractView<M extends Model, C extends Controller> extends JPanel implements View<M, C>{

    private M model;
    private C controller;
    
    @Override
    public C getController() {
        return this.controller;
    }

    @Override
    public void setController(C controller) {
        this.controller = controller;
    }

    @Override
    public M getModel() {
        return this.model;
    }

    @Override
    public void setModel(M model) {
        this.model = model;
    }

    @Override
    public void display(){
        
        java.awt.EventQueue.invokeLater(() -> {
            
            setVisible(true);
            refresh();
            
        });
    }
   
    @Override
    public void dataModelChanged(){
        refresh();
    }
        
    public abstract void refresh();
    
    
}
