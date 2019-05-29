package view.Pokedex;

import java.util.LinkedList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import model.TypeModel;
import model.entities.Type;

/**
 *
 * @author Adrian Vazquez
 */
public class TypeComboBoxModel implements ComboBoxModel {

    private TypeModel typeModel;
    private Type selectedItem;
    private List<Type> typeList;
    private List<ListDataListener> listeners;

    public TypeComboBoxModel(TypeModel tm) {

        this.typeModel = tm;
        this.typeList = tm.getAll();
        this.selectedItem = typeList.get(0);

        this.listeners = new LinkedList<>();
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem != null) {
            String name = (String) anItem;

            this.selectedItem = null;

            this.typeList
                    .stream()
                    .filter((t)
                            -> (t != null))
                    .filter((t)
                            -> (t.getName().equals(anItem)))
                    .forEachOrdered((t) -> {
                        this.selectedItem = t;
                    });

        }
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem != null ? selectedItem.getName() : "None";
    }

    @Override
    public int getSize() {
        return this.typeList.size();
    }

    @Override
    public String getElementAt(int index) {
        return this.typeList.get(index) != null ? this.typeList.get(index).getName() : "None";
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        this.listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        this.listeners.remove(l);

    }

    protected List<Type> getTypeList() {
        return typeList;
    }

}
