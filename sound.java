import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class sound
{
    Clip clip;
    URL soundURL[]= new URL[30];
    public sound()
    {
        soundURL[0]= getClass().getResource("/music/Mainmusic.wav");
        soundURL[1]= getClass().getResource("/music/hitmonster.wav");
        soundURL[2]= getClass().getResource("/music/gameover.mp3");
        soundURL[3]= getClass().getResource("/music/receivedamage.wav");

    }
    public void setFile(int i)
    {
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
        }
        catch(Exception e)
        {

        }
    }
    public void play()
    {
        clip.start();
    }

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void stop( )
    {
        clip.stop();
    }
}
