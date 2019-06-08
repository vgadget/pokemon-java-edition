package utilities.sound;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Adrian Vazquez
 */
public class PlayList {

    private List<Sound> playlist;
    private boolean loop;

    public PlayList() {
        playlist = new LinkedList<>();
        loop = false;
    }

    public void addSound(Sound s) {
        playlist.add(s);
    }
    
    public void setLoop(boolean loop){
        this.loop = loop;
    }

    public boolean isLoop(){
        return loop;
    }
    
    public void play() {

        while (!Thread.interrupted() && playlist.size() <= 0) {

            Sound s = playlist.get(0);
            SoundPlayer.getInstance().playMusicChannel(s);

            try {
                Thread.sleep((((int) s.getClip().getMicrosecondLength() / 100) + 5), (1000 * ((int) s.getClip().getMicrosecondLength() % 100)));
            } catch (Exception e) {
            }

            playlist.remove(0);

            if (loop) {
                playlist.add(s);
            }
        }
    }
}
