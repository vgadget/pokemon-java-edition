package languajes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.CSVManager;

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
    private CSVManager csvManager;
    private String selectedLanguage;

    private StringResourceMultilingualManager() throws FileNotFoundException {
        csvManager = new CSVManager(new File("Resources\\languages\\translations.csv"));
        selectedLanguage = getAvailableLanguages().get(1);
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
        try {
            List<List<String>> data = csvManager.getAll();

            data.forEach((line) -> {
                line.add(" ");
            });

            csvManager.setData(data);
            csvManager.save();

        } catch (Exception ex) {
            Logger.getLogger(StringResourceMultilingualManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> getAvailableLanguages() {

        try {
            return csvManager.getAll().get(0);
        } catch (Exception ex) {
            Logger.getLogger(StringResourceMultilingualManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new LinkedList<>();
    }

    public String getResource(String key, String language) {

        String resource = key;

        int languageIndex = getLanguageIndex(language);

        if (languageIndex < 0) {
            languageIndex = 1;
        }

        try {
            List<List<String>> data = this.csvManager.getAll();

            for (List<String> line : data) {

                if (line.get(0).equalsIgnoreCase(key)) {
                    String r = line.get(languageIndex);

                    int i = 1;
                    while (i < getAvailableLanguages().size() && (r == null || r.equals("") || r.equals(" "))) {
                        r = line.get(i);
                        i++;
                    }

                    if (r != null && !r.equals("") && !r.equals(" ")) {
                        resource = r;
                    }
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(StringResourceMultilingualManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resource;
    }

    private int getLanguageIndex(String language) {

        int i = 0;

        if (this.getAvailableLanguages().contains(language)) {
            i = this.getAvailableLanguages().indexOf(language);
        }

        return i;
    }

    private void setResource(String key, String language, String resource) {

        try {

            List<List<String>> data = csvManager.getAll();

            int languageIndex = getAvailableLanguages().indexOf(language);
            
            if (languageIndex >= 1) {
                for (List<String> line : data) {
                    
                    if (line.get(0).equalsIgnoreCase(key)) {
                        line.set(languageIndex, resource);
                        
                    }
                }
                csvManager.setData(data);
                csvManager.save();
            }

        } catch (Exception ex) {
            Logger.getLogger(StringResourceMultilingualManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addKey(String key) {

        try {
            List<List<String>> data = csvManager.getAll();
            List<String> line = new LinkedList<>();

            line.add(key);

            for (int i = 1; i < getAvailableLanguages().size(); i++) {
                line.add(" ");
            }

            data.add(line);

            csvManager.setData(data);
            csvManager.save();

        } catch (Exception ex) {
            Logger.getLogger(StringResourceMultilingualManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> getAvailableKeys() throws FileNotFoundException {

        List<String> keys = new LinkedList<>();

        try {
            for (List<String> s : csvManager.getAll()) {
                keys.add(s.get(0));
            }
        } catch (Exception ex) {
            Logger.getLogger(StringResourceMultilingualManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return keys;
    }

    private boolean languageExist(String language) {

        return getAvailableLanguages().contains(language);
    }

    public boolean keyExist(String key) {

        try {
            for (List<String> line : csvManager.getAll()) {

                if (line.get(0).equalsIgnoreCase(key)) {
                    return true;
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(StringResourceMultilingualManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
