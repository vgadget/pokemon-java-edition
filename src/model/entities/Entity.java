package model.entities;

import java.io.Serializable;

/**
 *
 * @author Adrian Vazquez
 * @param <PK> Primary key
 */
public interface Entity<PK extends Comparable> extends Serializable, Comparable<Entity>{
    
    PK getPK();
    
    
}
