

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
     boolean playing;
	 Clip music;
	 Clip soundeffect1;
	 Clip soundeffect2;
	 AudioInputStream musicInputStream;
	 AudioInputStream audioInputStream;
	 GameClock clock;

 Clip clip;
	 public MusicManager(){
		 clock = new GameClock();
                  System.out.print("CREATED");
	 }

	
	 public void updateClock(){
		 clock.resetGameClock();
	 }
	
	 public void PlayMusic(String id){
              System.out.print("PLAYING");
     
     	/*	try {
                   
			if (AudioSystem.getClip() != null) {
                            System.out.println("TRYING");     
				AudioSystem.getClip().stop();
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
     
		 try {
                  //   System.out.println("music");  
                   
			 if(0==id.compareTo("l1"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/song1.wav").getAbsoluteFile());
			 if(0==id.compareTo("l2"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/song2.wav").getAbsoluteFile());
                         if(0==id.compareTo("l3"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/song3.wav").getAbsoluteFile());
                         if(0==id.compareTo("l4"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/song4.wav").getAbsoluteFile());
                         if(0==id.compareTo("l5"))
				 this.musicInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/song5.wav").getAbsoluteFile());
			 // if(0==id.compareTo("tramp"))
			this.clip = AudioSystem.getClip();
			 this.clip.open(musicInputStream);
			 this.clip.start();
                         playing=true;
                            
		 } catch(Exception ex) {
			 System.out.println("Error with playing sound.");
			 ex.printStackTrace();
		 }
		 //if(this.audioInputStream.)
                 
	 }
         public void stopmusic(){
            
         this.clip.stop();
        // System.out.print("stopping");
         }
 }