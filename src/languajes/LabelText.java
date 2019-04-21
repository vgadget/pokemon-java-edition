package languajes;

/**
 *
 * @author Adrian Vazquez
 */
public class LabelText {

    private static LabelText instance = null;

    public static LabelText getInstance() {

        if (instance == null) {
            instance = new LabelText();
        }

        return instance;
    }

    private LabelText() {
        
    }

    //Main menu - Text for single player button.
    private String developedByAdrianVazquezBarrera = " Developed by Adrián Vázquez Barrera.";

    public String developedByAdrianVazquezBarrera() {
        return developedByAdrianVazquezBarrera;
    }

}
