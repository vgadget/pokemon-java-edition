package languajes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.CSVReader;

/**
 *
 * @author Adrian Vazquez
 */
public class StringResourceMultilingualManager {

    //SINGLETON
    private static StringResourceMultilingualManager srmm;

    public static StringResourceMultilingualManager getInstance() {

        if (srmm == null) {
            try {
                srmm = new StringResourceMultilingualManager();
            } catch (FileNotFoundException ex) {
                utilities.DisplayMessage.showErrorDialog(ex.getMessage());
            }
        }

        return srmm;
    }

    //END OF SINGLETON
    private CSVReader reader;
    private String selectedLanguage;

    private StringResourceMultilingualManager() throws FileNotFoundException {
        reader = new CSVReader(new File("Resources\\languages\\translations.csv"));
        selectedLanguage = reader.getColums().get(1);
    }

    public void setDefaultLanguage(String laguage) {

        this.selectedLanguage = laguage;
    }

    public String getDefaultLanguage() {
        return this.selectedLanguage;
    }

    public String getResource(String key) {
        return getResource(key, getDefaultLanguage());
    }

    public void setResource(String key, String resource) {
        setResource(key, getDefaultLanguage(), resource);
    }

    public void addLanguage(String language) {

        if (!languageExist(language)) {
            try {
                reader.addColumn(language.toUpperCase());
            } catch (IOException ex) {
                utilities.DisplayMessage.showErrorDialog(ex.getMessage());
            }
        }
    }

    public List<String> getAvailableLanguages() {
        try {
            return this.reader.getColums();
        } catch (FileNotFoundException ex) {
            utilities.DisplayMessage.showErrorDialog(ex.getMessage());
        }

        return null;
    }

    private String getResource(String key, String language) {

        String resource = "";

        try {

            List<List<String>> data = reader.getAll();

            for (List<String> line : data) {

                if (line.get(0).equalsIgnoreCase(key)) {

                    resource = line.get(getLanguageIndex(language));

                    int i = 1;
                    while ((resource.equalsIgnoreCase(key) || resource.isEmpty()) && i < line.size()) {
                        resource = line.get(i);
                        i++;
                    }

                    return resource;
                }

            }

        } catch (Exception ex) {
            //utilities.DisplayMessage.showErrorDialog(ex.getMessage());
        }

        return key;
    }

    private int getLanguageIndex(String language) throws FileNotFoundException {

        int i = 0;

        List<String> lang = this.getAvailableLanguages();

        boolean found = false;
        while (i < lang.size() && !found) {

            if (lang.get(i).contains(language)) {
                found = true;

            } else {
                i++;
            }
        }

        if (!found) {
            i = 0;
        }

        return i;
    }

    private void setResource(String key, String language, String resource) {

        resource = "\"" + resource + "\"";

        if (keyExist(key) && languageExist(language)) {

            try {

                List<List<String>> data = new LinkedList<>();

                data.add(reader.getColums());
                data.addAll(reader.getAll());

                int i = 0;
                boolean found = false;
                while (i < data.size() && !found) {

                    if (data.get(i).get(0).contains(key)) {

                        data.get(i).set(getLanguageIndex(language), resource);
                        reader.setData(data);
                        found = true;
                    } else {
                        i++;
                    }

                }

            } catch (Exception ex) {
                utilities.DisplayMessage.showErrorDialog(ex.getMessage());
            }
        }

    }

    public void addKey(String key) {

        if (!keyExist(key)) {

            try {

                int columnCount = reader.getColumCount();

                List<List<String>> data = new LinkedList<>();

                data.add(reader.getColums());
                data.addAll(reader.getAll());

                List<String> line = new ArrayList<>();

                line.add(key);

                while (line.size() < columnCount) {
                    line.add(" ");
                }

                data.add(line);

                reader.setData(data);

            } catch (Exception ex) {
                utilities.DisplayMessage.showErrorDialog(ex.getMessage());
            }
        }
    }

    public List<String> getAvailableKeys() throws FileNotFoundException {

        List<String> keys = new LinkedList<>();
        List<List<String>> data = reader.getAll();

        data
                .forEach((line) -> {
                    keys.add(line.get(0));
                });

        return keys;
    }

    private boolean languageExist(String language) {

        try {

            List<String> lan = reader.getColums();

            if (lan.stream().anyMatch((l) -> (l.equalsIgnoreCase(language)))) {
                return true;
            }

        } catch (FileNotFoundException ex) {
            utilities.DisplayMessage.showErrorDialog(ex.getMessage());
        }

        return false;
    }

    private boolean keyExist(String key) {

        try {

            List<List<String>> data = reader.getAll();

            if (data.stream().anyMatch((line) -> (line.get(0).equals(key)))) {
                return true;
            }

        } catch (FileNotFoundException ex) {
            utilities.DisplayMessage.showErrorDialog(ex.getMessage());
        }

        return false;
    }

}
