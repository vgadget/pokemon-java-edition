package model.pokemon;

import java.io.Serializable;

/**
 *
 * @author Adrian Vazquez
 */
public class Pokemon implements Serializable{
    
    private Specie specie;

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) throws Exception {
        if (specie != null){
            this.specie = specie;
        } else {
            throw new Exception("NULL SPECIE");
        }
    }
    
    
    
}
