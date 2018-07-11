
package edu.virginia.lab1test;

/**
 *
 * @author owner
 */
/**
 *
 * @author owner
 */import java.io.File;
 import java.io.IOException;
 import java.util.Scanner;

 import javax.sound.sampled.AudioInputStream;
 import javax.sound.sampled.AudioSystem;
 import javax.sound.sampled.Clip;
 import edu.virginia.engine.util.GameClock;

 public class MusicManager {
	 Clip music;
	 Clip soundeffect1;
	 Clip soundeffect2;
	 AudioInputStream musicInputStream;
	 AudioInputStream audioInputStream;
	 GameClock clock;


	 public MusicManager(){
		 clock = new GameClock();
                  System.out.print("CREATED");
	 }

	
	 public void updateClock(){
		 clock.resetGameClock();
	 }
	
	 public void PlayMusic(String id){
              System.out.print("PLAYING");
     
     		try {
			if (AudioSystem.getClip() != null) {
				AudioSystem.getClip().close();
			 }
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
                    
			 if(0==id.compareTo("1"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/song1.wav").getAbsoluteFile());
			 if(0==id.compareTo("2"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/song2.wav").getAbsoluteFile());
                         if(0==id.compareTo("3"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/song3.wav").getAbsoluteFile());
                         if(0==id.compareTo("4"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/song4.wav").getAbsoluteFile());
                         if(0==id.compareTo("5"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/song5.wav").getAbsoluteFile());
			 // if(0==id.compareTo("tramp"))
			 Clip clip = AudioSystem.getClip();
			 clip.open(musicInputStream);
			 clip.start();
		 } catch(Exception ex) {
			 System.out.println("Error with playing sound.");
			 ex.printStackTrace();
		 }
		 //if(this.audioInputStream.)
                 
	 }
 }