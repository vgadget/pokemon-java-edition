package languajes;

/**
 *
 * @author Adrian Vazquez
 */
public class NarratorTexts {

    /*
        KEYWORDS - ARGUMENTS
            - {key} --> KEY TO PRESS
            - {action} --> ACTION 
     
        ----------------------------------------------------------------------
       |                String multilingual resouces                          |
        ----------------------------------------------------------------------
     */
    //Notifies the users. If they press some key, an action will be performed.
    private static String pressKey = "Press {key} to {action}";

    public static String pressKeyTo(String key, String action) {

        String res;
        res = pressKey.replace("{key}", key);
        res = res.replace("{action}", action);

        return res;
    }

    //Notifies the users. If they press space, the Narrator start to speak.
    private static String pressSpaceToRepeat = "Press Space to repeat. ";

    public static String pressSpaceToRepeat() {
        return pressSpaceToRepeat;
    }

    //Notifies the users. If they press space, the Narrator start to speak.
    private static String pressSpaceToGetAudibleDescriptions = "Press Space to get audible descriptions. ";

    public static String pressSpaceToGetAudibleDescriptions() {
        return pressSpaceToGetAudibleDescriptions;
    }

    private static String pressUpOrDownArrowToNavigateBetweenMoreOptions = "Press UP or DOWN key to navigate between more options.";

    public static String pressUpOrDownArrowToNavigateBetweenMoreOptions() {
        return pressUpOrDownArrowToNavigateBetweenMoreOptions;
    }
}
