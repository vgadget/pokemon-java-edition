package model.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import model.entities.Entity;

/**
 *
 * @author Adrian Vazquez
 * @param <E> Entity Object
 * @param <PK> Primary key
 */
public class FileDao<E extends Entity, PK extends Comparable> implements Dao<E, PK> {

    private final String URI_SAVEGAME_FOLDER = "saves/";

    public FileDao() throws IOException {

        new File(URI_SAVEGAME_FOLDER).mkdir();

    }

    @Override
    public List<E> getAll() throws IOException, ClassNotFoundException {

        Runtime.getRuntime().gc();

        List<E> allElements = new LinkedList<>();
        List<String> pathnames = listOfFiles();

        for (String pathname : pathnames) {
            allElements.add(readObject(pathname));
        }

        return allElements;
    }

    @Override
    public List<E> getAll(Class c) throws IOException, ClassNotFoundException {

        Runtime.getRuntime().gc();

        List<E> allElements = new LinkedList<>();
        List<String> pathnames = listOfFiles();

        for (String pathname : pathnames) {
            if (pathname.contains(c.getName())) {
                allElements.add(readObject(pathname));
            }
        }

        return allElements;
    }

    @Override
    public List<Comparable> getAllPK(Class c) throws IOException, ClassNotFoundException {

        Runtime.getRuntime().gc();

        List<String> pathnames = listOfFiles();

        List<Comparable> allPK = new LinkedList<>();

        pathnames.parallelStream()
                .filter((file) -> (file.contains(c.getName()))).map((file) -> file).map((pk) -> pk.substring(pk.indexOf(c.getName()))).map((pk) -> pk.replace(c.getName() + ".", "")).map((pk) -> pk.replace(getFileExtension(), "")).forEachOrdered((pk) -> {
            allPK.add(pk);
        });

        return allPK;
    }

    @Override
    public E get(PK pk, Class c) throws IOException, ClassNotFoundException {

        Runtime.getRuntime().gc();

        String file = URI_SAVEGAME_FOLDER + c.getName() + "." + pk + getFileExtension();

        return readObject(file);
    }

    private E readObject(String pathname) throws IOException, ClassNotFoundException {

        E object = null;

        FileInputStream fileInputStream = new FileInputStream(pathname);

        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        object = (E) objectInputStream.readObject();

        objectInputStream.close();

        fileInputStream.close();

        return object;
    }

    @Override
    public void save(E t) throws IOException {

        String file = URI_SAVEGAME_FOLDER + t.getClass().getName() + "." + t.getPK() + getFileExtension();

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(t);
        objectOutputStream.close();

        fileOutputStream.close();

        Runtime.getRuntime().gc();
    }

    @Override
    public void update(E t) throws IOException {

        delete(t);
        save(t);
        Runtime.getRuntime().gc(); 
    }

    @Override
    public void delete(E t) {

        String file = URI_SAVEGAME_FOLDER + t.getClass().getName() + "." + t.getPK() + getFileExtension();

        File f = new File(file);

        f.delete();
        
        Runtime.getRuntime().gc(); 

    }

    private List<String> listOfFiles() {

        List<String> fileList = new LinkedList<>();

        File files[] = new File(URI_SAVEGAME_FOLDER).listFiles();

        for (File f : files) {
            fileList.add(f.getAbsolutePath());
        }

        return fileList;
    }

    public static String getFileExtension() {
        return ".sav";
    }

}
