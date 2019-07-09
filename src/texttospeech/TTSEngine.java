package texttospeech;

import java.util.Collection;

import javax.sound.sampled.AudioInputStream;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.modules.synthesis.Voice;

class TTSEngine {

    private VoicePlayer audioPlayer;
    private MaryInterface maryTTS;

    public TTSEngine() {
        try {
            maryTTS = new LocalMaryInterface();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void speak(String text, float gain, boolean sync, boolean join) {

        try {
            stopSpeaking();
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
            System.err.println(e);
        }
    }

    public void stopSpeaking() {

        if (audioPlayer != null) {
            
            audioPlayer.cancel();
            audioPlayer.interrupt();
        }
    }

    public Collection<Voice> getAvailableVoices() {
        return Voice.getAvailableVoices();
    }

    public void setVoice(String voice) {
        maryTTS.setVoice(voice);
    }

}
