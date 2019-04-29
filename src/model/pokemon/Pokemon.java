package model.pokemon;

import java.io.Serializable;
import model.Entity;

/**
 *
 * @author Adrian Vazquez
 */
public class Pokemon implements Entity<String> {

    private String nickname;
    private Specie specie;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) throws Exception {
        if (specie != null) {
            this.specie = specie;
        } else {
            throw new Exception("NULL SPECIE");
        }
    }

    @Override
    public String getPK() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Entity o) {
        
        if (o instanceof Pokemon) {
            return this.getNickname().compareTo(((Pokemon) o).getNickname());
        }

        return this.getClass().getName().compareTo(o.getClass().getName());
    }

}
