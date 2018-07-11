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
import javax.sound.sampled.LineUnavailableException;

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
	 }

	
	 public void updateClock(){
		 clock.resetGameClock();
	 }
	
	 public void PlayMusic(String id){
		try {
			if (AudioSystem.getClip() != null) {
				AudioSystem.getClip().close();
			 }
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			 if(0==id.compareTo("song1"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("resources/song1.wav").getAbsoluteFile());
			 if(0==id.compareTo("song2"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("resources/song2.wav").getAbsoluteFile());
                         if(0==id.compareTo("song3"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("resources/song3.wav").getAbsoluteFile());
                         if(0==id.compareTo("song4"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("resources/song4.wav").getAbsoluteFile());
                         if(0==id.compareTo("song5"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("resources/song5.wav").getAbsoluteFile());
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
