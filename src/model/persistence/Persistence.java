package model.persistence;

import java.io.IOException;

/**
 *
 * @author Adrian Vazquez
 */
public class Persistence {

    //SINGLETON
    private static Persistence instance;

    public static Persistence getInstance() {

        if (instance == null) {
            try {
                instance = new Persistence(new FileDao());
            } catch (IOException ex) {
            }
        }

        return instance;

    }

    private Persistence(Dao dao) throws NullPointerException {
        setDao(dao);
    }

    public Dao getDao() {
        return dao;
    }

    private void setDao(Dao dao) throws NullPointerException {
        if (dao != null) {
            this.dao = dao;
        } else {
            throw new NullPointerException("DAO CANNOT BE NULL");
        }
    }

    private Dao dao;

}
