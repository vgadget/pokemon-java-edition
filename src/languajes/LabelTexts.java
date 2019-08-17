package languajes;

/**
 *
 * @author Adrian Vazquez
 */
public class LabelTexts {

    private static LabelTexts instance = null;

    public static LabelTexts getInstance() {

        if (instance == null) {
            instance = new LabelTexts();
        }

        return instance;
    }

    private LabelTexts() {

    }

    //Main menu - Text for single player button.
    private String developedByAdrianVazquezBarrera = " Developed by Adrián Vázquez Barrera.";

    public String developedByAdrianVazquezBarrera() {
        return developedByAdrianVazquezBarrera;
    }
    
    private String pleaseWait = "Please, wait.";

    public String pleaseWait() {
        return pleaseWait;
    }
}
