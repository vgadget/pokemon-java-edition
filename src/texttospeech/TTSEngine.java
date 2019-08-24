package texttospeech;

import java.util.Collection;

import javax.sound.sampled.AudioInputStream;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.modules.synthesis.Voice;

class TTSEngine {

    private VoicePlayer audioPlayer;
    private MaryInterface maryTTS;

    private int volume;

    public TTSEngine() {
        try {
            maryTTS = new LocalMaryInterface();
            volume = 10;
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public synchronized void speak(String text, float gain, boolean sync, boolean join) {

        try {

            stopSpeaking();

            volume = utilities.sound.SoundPlayer.getInstance().getMusicVolume();
            utilities.sound.SoundPlayer.getInstance().setMusicVolume(10);

            AudioInputStream audio = maryTTS.generateAudio(text);
            audioPlayer = new VoicePlayer();
            audioPlayer.setAudio(audio);
            audioPlayer.setGain(gain);
            audioPlayer.setDaemon(sync);
            audioPlayer.start();
            if (join) {
                audioPlayer.join();
            }

        } catch (Exception e) {
            System.err.println(text);
        } finally {
            utilities.sound.SoundPlayer.getInstance().setMusicVolume(volume);
        }
    }

    public void stopSpeaking() {

        if (audioPlayer != null) {

            audioPlayer.cancel();
            audioPlayer.interrupt();
            utilities.sound.SoundPlayer.getInstance().setMusicVolume(volume);
        }
    }

    public Collection<Voice> getAvailableVoices() {
        return Voice.getAvailableVoices();
    }

    public void setVoice(String voice) {
        maryTTS.setVoice(voice);
    }

}
