/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

 public class SoundManager {
	 Clip music;
	 Clip soundeffect1;
	 Clip soundeffect2;
	 AudioInputStream musicInputStream;
	 AudioInputStream audioInputStream;
	 GameClock clock;


	 public SoundManager(){
		 clock = new GameClock();
	 }

	 public void LoadSoundEffect(){


	 }



	 public void PlaySoundEffect(String id){
		 System.out.print((clock.getElapsedTime()/1000));
		 if((clock.getElapsedTime()/1000) > .2){
                     System.out.println("THE ID DUDE"+id);
			 try {                
				 if(0==id.compareTo("ball")){
				 this.audioInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/ballhit.wav").getAbsoluteFile());
                                  
				 if(0==id.compareTo("tramp")){
					 this.audioInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/trampoline.wav").getAbsoluteFile());
                                         System.out.print("TRAMPSTUFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
                                 }
				 if(0==id.compareTo("win"))
					 this.audioInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/win.wav").getAbsoluteFile());
                                    //   if(this.musicInputStream.available()!=0)  this.musicInputStream.close();
                                 if(0==id.compareTo("fan"))
					 this.audioInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/fan.wav").getAbsoluteFile());
				 // if(0==id.compareTo("tramp"))
                                 
				 Clip clip = AudioSystem.getClip();
				 clip.open(audioInputStream);
				 clip.start();
				 }
			 } catch(Exception ex) {
				 System.out.println("Error with playing sound.");
				 ex.printStackTrace();
			 
		 
	 }
                 }}
	 public void updateClock(){
		 clock.resetGameClock();
	 }}
	