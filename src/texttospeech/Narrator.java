package texttospeech;

/**
 *
 * @author Adrian Vazquez
 */
public class Narrator {

    //Singleton
    private static Narrator instance;

    //Avaliable languages
    public static enum Language {
        SPANISH, ENGLISH, ITALIAN, FRENCH
    }

    //TTS
    private TTSEngine tts;
    private Language selectedLanguage;

    private boolean enabled;
   
    private Narrator() {
        
        enabled = false;
        
        tts = new TTSEngine();

        setLanguage(Language.ENGLISH);

//        tts.getAvailableVoices()
//                .stream()
//                .forEach(voice -> System.out.println("Voice: " + voice)); // Debug
    }

    public synchronized static Narrator getInstance() {

        if (instance == null) {
            instance = new Narrator();
        }

        return instance;
    }

    public void setLanguage(Language l) {

        selectedLanguage = l;

        switch (selectedLanguage) {

            case SPANISH:
                tts.setVoice("istc-lucia-hsmm");
                break;
            case ITALIAN:
                tts.setVoice("istc-lucia-hsmm");
                break;
            case ENGLISH:
                tts.setVoice("cmu-rms-hsmm");
                break;
            case FRENCH:

                break;
            default:
        }

    }

    public void speak(String s) {

        if (isEnabled()) {
            
                        
            String output;

            if (this.selectedLanguage.equals(Language.SPANISH)) {
                output = spanishAdaptor(s);
            } else {
                output = s;
            }

            tts.stopSpeaking();
    
            tts.speak(output + " ", 1.5f, false, true);
                        
        }
    }

    public synchronized boolean isEnabled() {
        return enabled;
    }

    public synchronized void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private String spanishAdaptor(String s) {

        String output = s + "";

        output = NumbersToSpanishWordsConverter.substituteNumbers(output);

        output = output.toLowerCase();

        output = output.replace("ce", "se");
        output = output.replace("ci", "si");
        output = output.replace("za", "sa");
        output = output.replace("zo", "so");
        output = output.replace("zu", "su");
        output = output.replace("ñ", "ny");
        output = output.replace("que", "ke");
        output = output.replace("qui", "ki");
        output = output.replace("qué", "ké");
        output = output.replace("quí", "kí");
        output = output.replace("chi", "ci");
        output = output.replace("che", "ce");
        output = output.replace("ch", "ts");
        output = output.replace(" r", "rr");
        output = output.replace("ll", "y");
        output = output.replace("gué", "gé");
        output = output.replace("guí", "gí");
        output = output.replace("gí", "jí");
        output = output.replace("que", "qé");
        output = output.replace("w", "gu");

        return output;
    }

}
