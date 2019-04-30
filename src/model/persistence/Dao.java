/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.persistence;

import java.io.IOException;
import java.util.List;
import model.Entity;

/**
 *
 * @author Adrian Vazquez
 * @param <E> Entity Object
 * @param <PK> Primary key
 */
public interface Dao<E extends Entity, PK extends Comparable> {

    E get(PK pk, Class c) throws IOException, ClassNotFoundException;

    List<E> getAll() throws IOException, ClassNotFoundException;
    
    List<E> getAll(Class c) throws IOException, ClassNotFoundException;

    void save(E t) throws IOException;

    void update(E t) throws IOException;

    void delete(E t);

}
