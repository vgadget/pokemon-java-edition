package utilities.DataStructure;

import java.util.LinkedList;
import java.util.List;
import model.entities.Entity;

/**
 *
 * @author Adrian Vazquez
 * @param <E>
 * @param <PK>
 */
public class DataPool<E extends Entity, PK extends Comparable> {

    final int MAX_SIZE;
    private List<E> data;

    public DataPool(int size) {
        this.MAX_SIZE = size;
        this.data = new LinkedList<>();
    }

    public void add(E element) {

        while (this.data.size() > MAX_SIZE) {
            this.data.remove(0);
        }

        if (!this.data.contains(element)) {
            this.data.add(element);
        }
    }

    public List<E> getAll() {
        return data;
    }

    public E get(PK pk) {

        for (E element : this.data) {
            if (element.getPK().equals(pk)) {
                return element;
            }
        }

        return null;
    }

    public boolean contains(PK pk) {

        return this.data.stream().anyMatch((element) -> (element.getPK().equals(pk)));
    }

}
