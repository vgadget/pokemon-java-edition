/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package languajes;

/**
 *
 * @author Adrian Vazquez
 */
public class ButtonText {

    //Main menu - Text for single player button.
    private static String singlePlayerButton = "   Single player.";

    public static String singlePlayerButton() {
        return singlePlayerButton;
    }

    //Main menu - Text for multiplayer player button.
    private static String multiPlayerButton = "Multiplayer.";

    public static String multiPlayerButton() {
        return multiPlayerButton;
    }

    //Main menu - Text for settings button.
    private static String settingsButton = "Settings.     ";

    public static String settingsButton() {
        return settingsButton;
    }

    //Main menu - Text for credits button.
    private static String creditsButton = "Credits.      ";

    public static String creditsButton() {
        return creditsButton;
    }

    //Main menu - Text for save and exit.
    private static String saveAndExit = "     Save and exit.";

    public static String saveAndExit() {
        return saveAndExit;
    }
    
    // Attack button description
       private static String attackButtonDescription = "Attack: {name}, Type: {type}, with {remainingPP} over {maximunPP} PP.";

    public static String attackButtonDescription(String name, String type, int remainingPP, int maximunPP) {
        
        String s = attackButtonDescription.replace("{name}", name);
        s = s.replace("{type}", type);
        s = s.replace("{remainingPP}", remainingPP+"");
        s = s.replace("{maximunPP}", maximunPP+"");
        
        return s;
    }

}
