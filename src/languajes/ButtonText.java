package languajes;

/**
 *
 * @author Adrian Vazquez
 */
public class ButtonText {

    private static ButtonText instance = null;

    public static ButtonText getInstance() {

        if (instance == null) {
            instance = new ButtonText();
        }

        return instance;
    }

    private ButtonText() {

    }

    //Main menu - Text for single player button.
    private String singlePlayerButton = "   Single player.";

    public String singlePlayerButton() {
        return singlePlayerButton;
    }

    //Main menu - Text for multiplayer player button.
    private String multiPlayerButton = "Multiplayer.";

    public String multiPlayerButton() {
        return multiPlayerButton;
    }

    //Main menu - Text for settings button.
    private String settingsButton = "Settings.     ";

    public String settingsButton() {
        return settingsButton;
    }

    //Main menu - Text for credits button.
    private String creditsButton = "Credits.      ";

    public String creditsButton() {
        return creditsButton;
    }

    //Main menu - Text for save and exit.
    private String saveAndExit = "     Save and exit.";

    public String saveAndExit() {
        return saveAndExit;
    }

    // Attack button description
    private String attackButtonDescription = "Attack: {name}, Type: {type}, with {remainingPP} over {maximunPP} PP.";

    public String attackButtonDescription(String name, String type, int remainingPP, int maximunPP) {

        String s = attackButtonDescription.replace("{name}", name);
        s = s.replace("{type}", type);
        s = s.replace("{remainingPP}", remainingPP + "");
        s = s.replace("{maximunPP}", maximunPP + "");

        return s;
    }

}
