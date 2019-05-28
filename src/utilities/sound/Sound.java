package utilities.sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Adrian Vazquez
 */
public class Sound implements Serializable {

    private byte data[]; // Audio file bytes
    private String name;

    private transient Clip clip;
    private transient AudioInputStream audioInputStream;
    private transient File tempFile;

    public Sound(File file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        if (file.exists()) {

            name = file.getName();

            data = new byte[(int) file.length()];

            FileInputStream fis = new FileInputStream(file);

            fis.read(data);

            fis.close();

            clip = getClip();
        }
    }

    public Clip getClip() throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        if (clip == null) {

            tempFile = File.createTempFile(name, null);

            if (tempFile.exists()) {
                tempFile.delete();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);

            fileOutputStream.write(data);

            fileOutputStream.close();

            tempFile.deleteOnExit();

            //Get sound from file
            audioInputStream = AudioSystem.getAudioInputStream(tempFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }

        clip.setFramePosition(0);
        return clip;
    }

    public void start(int framePosition, int loops) throws Exception {

        Clip c = getClip();

        c.setFramePosition(framePosition);
        c.loop(loops);
        c.start();

        Thread.sleep((((int) c.getMicrosecondLength() / 100) + 5), (1000 * ((int) c.getMicrosecondLength() % 100)));
    }

    public void pause() throws Exception {
        getClip().stop();
    }

    public void resume() throws Exception {
        getClip().start();
    }

    public void stop() throws Exception {
        freeMemory();
    }

    public long getFramesLength() throws Exception {
        return getClip().getFrameLength();
    }

    private void freeMemory() throws IOException {

        audioInputStream.close();
        clip.drain();
        clip.stop();
        clip.close();
        clip = null;
        tempFile.delete();
    }

}
