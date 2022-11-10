/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JSlider;
import java.io.File;

public class MySoundEffect
{
    private Clip clip;
    FloatControl fc;
    float currentVolume = -37f;

    public MySoundEffect(String filename) {
		try {
			File file = new File(filename);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			fc.setValue(currentVolume);
		}
		catch (Exception e) { System.err.println("Audio '" + filename + "' cannot be opened. " + e); clip = null; }
    }
    public void playOnce()   {
		if (clip != null) {
			clip.setMicrosecondPosition(0);
			clip.start();
		}
	}
    public void playLoop()   { if (clip != null) clip.loop(Clip.LOOP_CONTINUOUSLY); }
    public void stop()       { if (clip != null) clip.stop(); }

    public void setCurrentVolume(float cv) { currentVolume = cv; if (clip != null) fc.setValue(currentVolume); }
   
}
