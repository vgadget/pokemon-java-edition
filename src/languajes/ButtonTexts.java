package languajes;

/**
 *
 * @author Adrian Vazquez
 */
public class ButtonTexts {

    private static ButtonTexts instance = null;

    public static ButtonTexts getInstance() {

        if (instance == null) {
            instance = new ButtonTexts();
        }

        return instance;
    }

    private ButtonTexts() {

    }

    //Main menu - Text for single player button.
    private String singlePlayerButton = "Single player.";

    public String singlePlayerButton() {
        return singlePlayerButton;
    }

    //Main menu - Text for multiplayer player button.
    private String multiPlayerButton = "Multiplayer.";

    public String multiPlayerButton() {
        return multiPlayerButton;
    }

    //Main menu - Text for settings button.
    private String settingsButton = "Settings.";

    public String settingsButton() {
        return settingsButton;
    }

    //Main menu - Text for credits button.
    private String creditsButton = "Credits.";

    public String creditsButton() {
        return creditsButton;
    }

    //Main menu - Text for save and exit.
    private String saveAndExit = "Save and exit.";

    public String saveAndExit() {
        return saveAndExit;
    }

    // Main menu -> Single player - Text for Fight.
    private String battle = "Battle.";

    public String battle() {
        return battle;
    }
    // Main menu -> Single player - Text for see pokemon team.
    private String team = "My team.";

    public String team() {
        return team;
    }

    // Main menu -> Single player - Text for catch new pokemon.
    private String catchPokemon = "Catch.";

    public String catchPokemon() {
        return catchPokemon;
    }

    // Main menu -> Single player - Text for catch new pokemon.
    private String pokedex = "Pokédex.";

    public String pokedex() {
        return pokedex;
    }

    // Main menu -> Multiplayer - Text for onlinePlay
    private String localMltiplayer = "Local multiplayer.";

    public String localMultiplayer() {
        return localMltiplayer;
    }

    // Main menu -> Multiplayer - Text for onlinePlay
    private String online = "Online multiplayer.";

    public String online() {
        return online;
    }

    // Main menu -> X - Text for go back to main menu.
    private String goBack = "Go back.";

    public String goBack() {
        return goBack;
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

    private String nextPokemon = "Next pokémon";

    public String nextPokemon() {
        return nextPokemon;
    }
    
    private String previousPokemon = "Previous pokémon";

    public String previousPokemon() {
        return previousPokemon;
    }

}
