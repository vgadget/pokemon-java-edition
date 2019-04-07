package controller.texttospeech;


import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.SourceDataLine;

class VoicePlayer extends Thread {

    public enum PlayerStatus {
        WAITING, PLAYING;
    }

    private static final int AUDIO_BUFFER_SIZE = 65532;

    private AudioInputStream audioInputStream;
    private LineListener lineListener;
    private SourceDataLine sourceDataLine;

    private PlayerStatus playerStatus = PlayerStatus.WAITING;

    private boolean requestStop = false;
    private float gain = 1.0f;

    public void setAudio(AudioInputStream audioInputStream) {
        if (playerStatus != PlayerStatus.PLAYING) {
            this.audioInputStream = audioInputStream;
        }
    }

    public void cancel() {
        if (sourceDataLine != null) {
            sourceDataLine.stop();
        }
        requestStop = true;
    }

    public SourceDataLine getLine() {
        return sourceDataLine;
    }

    public float getGainValue() {
        return gain;
    }

    public void setGain(float gain) {

        this.gain = gain;

        if (sourceDataLine != null && sourceDataLine.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            ((FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN)).setValue((float) (20 * Math.log10(gain <= 0.0 ? 0.0000 : gain)));
        }

    }

    public PlayerStatus getPlayerStatus(){
        return playerStatus;
    }
    
    @Override
    public void run() {

        playerStatus = PlayerStatus.PLAYING;
        AudioFormat audioFormat = audioInputStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

        try {
            if (sourceDataLine == null) {

                if (!AudioSystem.isLineSupported(info)) {
                    AudioFormat sourceFormat = audioFormat;
                    AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(), sourceFormat.getSampleSizeInBits(),
                            sourceFormat.getChannels(), sourceFormat.getChannels() * (sourceFormat.getSampleSizeInBits() / 8), sourceFormat.getSampleRate(),
                            sourceFormat.isBigEndian());

                    audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
                    audioFormat = audioInputStream.getFormat();
                }

                sourceDataLine = (SourceDataLine) AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, audioFormat));
            }

            if (lineListener != null) {
                sourceDataLine.addLineListener(lineListener);
            }

            sourceDataLine.open(audioFormat);

        } catch (Exception e) {
            System.err.println(e);
        }

        sourceDataLine.start();
        setGain(getGainValue());

        int n = 0;
        byte[] buffer = new byte[AUDIO_BUFFER_SIZE];

        while (n >= 0 && !requestStop) {

            try {
                n = audioInputStream.read(buffer, 0, buffer.length);
            } catch (Exception e) {
                System.err.println(e);
            } finally {

                if (n >= 0) {
                    sourceDataLine.write(buffer, 0, n);
                }
            }
        }

        if (!requestStop) {
            sourceDataLine.drain();
        }

        playerStatus = PlayerStatus.WAITING;
        sourceDataLine.close();
    }

}
