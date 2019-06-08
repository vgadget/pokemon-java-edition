package utilities.sound;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Adrian Vazquez
 */
public class SoundPlayer {

    //SINGLETON
    private static SoundPlayer instance;

    public static SoundPlayer getInstance() {

        if (instance == null) {

            instance = new SoundPlayer();
        }

        return instance;
    }

    // END OF SINGLETON
    private Sound music;

    private Thread musicChannel;

    private SoundPlayer() {
        musicChannel = null;
    }

    public synchronized void playMusicChannel(Sound s) { // SYNC

        if (musicChannel != null) {

            try {
                if (music != null) {
                    music.stop();
                }
            } catch (Exception ex) {
                Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }

            musicChannel.interrupt();
            musicChannel = null;
        }

        music = s;

        musicChannel = new Thread(() -> {

            try {
                music.start(0, 0);
                music.stop();
            } catch (Exception ex) {
                Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        musicChannel.start();
    }

    public synchronized void playEffectChannel(Sound s) { // SYNC

        new Thread(() -> {

            try {

                Sound sound = s;
                sound = new Sound(s.getResourceFile());

                sound.start(0, 0);
                sound.stop();
            } catch (Exception ex) {
                Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }).start();
    }
}
