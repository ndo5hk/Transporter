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

 
public class SoundManager {
    Clip music;
    Clip soundeffect1;
    Clip soundeffect2;
     AudioInputStream musicInputStream;
      AudioInputStream audioInputStream;
    SoundManager(){
    
    
    }
    public void LoadSoundEffect(){}
public void PlaySoundEffect(String id){
 try {
        if(0==id.compareTo("ball"));
         this.audioInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/ballhit.wav").getAbsoluteFile());
        if(0==id.compareTo("tramp"))
             this.audioInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/trampoline.wav").getAbsoluteFile());
        if(0==id.compareTo("win"))
             this.audioInputStream = AudioSystem.getAudioInputStream(new File("Transporter/resources/win.wav").getAbsoluteFile());
        // if(0==id.compareTo("tramp"))
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(Exception ex) {
        System.out.println("Error with playing sound.");
        ex.printStackTrace();
    }
}
//sound	effects	are	short	and	are	removed	once	complete
public void LoadMusic(String id,String filename){
       
 
}
public void PlayMusic(String id){
/*String songName = "HungryKidsofHungary-ScatteredDiamonds.mp3";
String pathToMp3 = System.getProperty("user.dir") +"/"+ songName;
BasicPlayer player = new BasicPlayer();
try {
    player.open(new URL("file:///" + pathToMp3));
    player.play();
} catch (BasicPlayerException | MalformedURLException e) {
    e.printStackTrace();
}*/
}
}
