package moon_lander;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.IOException;
import java.io.File;
public class Audio {
    private Clip clip;
    private File audioFile;
    private AudioInputStream audioInputStream;
    private boolean isLoop;
    public boolean isPlaying = true;
    private long clipTime;

    public Audio(String pathname,boolean isLoop)
    {
        try
        {
            clip=AudioSystem.getClip();
            audioFile = new File(pathname);
            audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip.open(audioInputStream);

        } catch(LineUnavailableException e) {
            e.printStackTrace();

        } catch(IOException e) {
            e.printStackTrace();
        } catch(UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        clip.setFramePosition(0);
        clip.start();
        if(isLoop) clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public boolean audioPlayingTrue()
    {
        return isPlaying = true;
    }
    public void stop()
    {
        clip.stop();
    }
    public void mute()
    {
        clipTime= clip.getMicrosecondPosition();
        clip.stop();

    }
    public void resume()
    {
        clip.setMicrosecondPosition(clipTime);

        clip.start();
    }

    public void musicPause()
    {
        isPlaying = false;
        mute();
    }

    public void musicResume()
    {
        isPlaying = true;
        resume();
    }

}