package utilities.sound;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.FloatControl;

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

    private float musicChannelGain = 1;
    private float effectChannelGain = 1;

    private int musicVolume = 100;
    private int effectVolume = 100;

    private SoundPlayer() {
        musicChannel = null;
    }

    public synchronized void playMusicChannel(Sound s, boolean loop) { // SYNC

        if (musicChannel != null) {

            if (!musicChannel.isInterrupted()) {
                musicChannel.interrupt();
            }

            try {
                if (music != null) {
                    music.stop();
                }
            } catch (Exception ex) {
            }

            musicChannel = null;
        }

        music = s;

        setMusicVolume(musicVolume);

        musicChannel = new Thread(() -> {

            try {

                if (loop) {

                    music.start(0, Integer.MAX_VALUE);
                    music.stop();

                } else {
                    music.start(0, 0);
                    music.stop();
                }

            } catch (Exception ex) {

            }

        });

        musicChannel.start();
    }

    public synchronized void stopMusic() {

        new Thread(() -> {

            if (musicChannel != null) {

                try {
                    music.stop();
                    music = null;
                } catch (Exception ex) {
                }
                
                musicChannel.interrupt();
                musicChannel = null;

            }

        }).start();

    }

    public synchronized void setMusicVolume(int percentage) {

        if (percentage >= 0 && percentage <= 100) {

            musicChannelGain = (percentage / 100f);

            if (music != null) {
                try {

                    music.pause();

                    int framePosition = music.getClip().getFramePosition();

                    FloatControl gainControl = (FloatControl) music.getClip().getControl(FloatControl.Type.MASTER_GAIN);
                    float dB = (float) (Math.log(musicChannelGain) / Math.log(10.0) * 20.0);
                    gainControl.setValue(dB);

                    music.getClip().setFramePosition(framePosition);

                    music.resume();

                } catch (Exception ex) {
                }
            }

        } else {
            throw new IllegalArgumentException("Volume have to be between 0 and 100");
        }

    }

    public int getMusicVolume() {
        return musicVolume;
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
