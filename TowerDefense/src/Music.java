import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music  implements Runnable  {
	private Thread t;
	private File audioFile ;
    private AudioInputStream audioStream;
    private Clip audioClip;
    private String fn;
    
    //CREATED MUSIC CLASS TO ADD SOUND TO SPECIFIC ACTIONS WITHIN THE GAME
	public Music(String fileName, boolean loops) {
		fn = fileName;
		start2();
	}
	
	public void play() {
		start3();
	}
	public void start3() {
	     t = new Thread (this, fn);
	     start2();
	     t.start ();
	}
	
	//START PLAYING THE DIFFERNT SOUNDS
	public void start() {
	     t = new Thread (this, fn);
	     t.start ();
	}
	public void start2() {
		Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
		audioFile= new File(path+"\\bin"+fn);
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
	        DataLine.Info info = new DataLine.Info(Clip.class, format);
	        audioClip = (Clip) AudioSystem.getLine(info);
	        audioClip.open(audioStream);
	        audioClip.start();
	        
	        //catching any potential error exceptions
	        
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		 audioClip.start();
	}
	

}
