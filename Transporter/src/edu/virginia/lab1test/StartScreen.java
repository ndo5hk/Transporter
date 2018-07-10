/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.virginia.lab1test;

import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Trampoline;
import edu.virginia.engine.display.TransporterGame;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Font;

/**
 *
 * @author owner
 */
public class StartScreen  extends TransporterGame implements MouseListener{
    Sprite startbutton;
    Sprite InstructionsButton;
    Sprite levelone;
    Sprite leveltwo;
    Sprite levelthree;
    Sprite levelfour;
    Sprite levelfive;
    Sprite levelsix;
    Sprite lock;
    Sprite background;
    ArrayList<Sprite> spritelist;
    Font stuff;
    SoundManager sound;
    
    StartScreen(){
        
        super("startscreen",1900,1000);
        stuff=new Font("sansserif",1,50);
          sound = new SoundManager();
            sound.PlayMusic("song1");
              this.background = new Sprite("background1","startScreen.png",false);
              background.setScaleX(3);
              background.setScaleY(3);
              super.addChild(this.background);
       levelone=new Sprite("LevelOne","levelone.png",false);
     leveltwo=new Sprite("LevelTwo","levelone.png",false);
    levelthree=new Sprite("LevelThree","levelone.png",false);
    levelfour = new Sprite("LevelFour","levelone.png",false);
    levelfive=new Sprite("LevelFive","levelone.png",false);
    levelsix=new Sprite("LevelSix","levelone.png",false);
    lock = new Sprite("Lock","lock.png",false);
    lock.setPosition(1200,400);
    lock.setAlpha(.8f);
    lock.setScaleX(.165);
     lock.setScaleY(.243);
    levelone.setPosition(400,200 );
    levelone.setScaleX(.2);
    levelone.setScaleY(.2);
    leveltwo.setPosition(800,200 );
    leveltwo.setScaleX(.2);
    leveltwo.setScaleY(.2);
    levelthree.setPosition(1200,200 );
    levelthree.setScaleX(.2);
    levelthree.setScaleY(.2);
     levelsix.setPosition(1200,400 );
    levelsix.setScaleX(.2);
    levelsix.setScaleY(.2);
     levelfour.setPosition(400,400 );
    levelfour.setScaleX(.2);
    levelfour.setScaleY(.2);
    levelfive.setPosition(800,400 );
    levelfive.setScaleX(.2);
    levelfive.setScaleY(.2);
      super.addChild(background);
    super.addChild(levelone);
   super.addChild(leveltwo);
    super.addChild(levelthree);
   super.addChild(levelfour);
    super.addChild(levelfive);
    super.addChild(levelsix);
    super.addChild(lock);
  
    spritelist=new ArrayList();
    spritelist.add(levelone);
    spritelist.add(leveltwo);
    spritelist.add(levelthree);
    spritelist.add(levelfour);
    spritelist.add(levelfive);
    spritelist.add(levelsix);
    this.getMainFrame().addMouseListener(this);

    }
   @Override
	public void update(ArrayList<Integer> pressedKeys){}
    @Override
    public void draw(Graphics g){
    super.draw(g);
    g.setFont(stuff);
    g.drawString("Pick a Level",780,100);
    Graphics2D g2d = (Graphics2D)g;
    for(Sprite x : spritelist){ 
        g2d.draw(x.getGlobalHitbox().get(0));}
    }
    @Override
	public void mouseClicked(MouseEvent e) {
		Area click = new Area(new Rectangle2D.Double(e.getX()+this.getPosition()[0], e.getY()+this.getPosition()[1]-25, 1, 1));
	//		System.out.println(Integer.toString(e.getX()));
	//		System.out.println(Integer.toString(e.getY()));
			for(Sprite x: spritelist){
					Area icon = new Area(x.getGlobalHitbox().get(0));
				icon.intersect(click);
                                
				if (!icon.isEmpty()) {
                                      if (x.getId().equals("lock")) {System.out.println("lock");}
                                      else{
					if (x.getId().equals("LevelOne")) {System.out.println("levelone");}
                                        if (x.getId().equals("LevelTwo")) {System.out.println("leveltwo");}
                                        if (x.getId().equals("LevelThree")) {System.out.println("levelthree");}
                                        if (x.getId().equals("LevelFour")) {System.out.println("levelfour");}
                                        if (x.getId().equals("LevelFive")) {System.out.println("levelfive");}
                                        if (x.getId().equals("LevelSix")) {System.out.println("levelsix");}
                                        
                                      }
                                }
                        }
        }
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
        public static void main(String[] args) {
		StartScreen start = new StartScreen();
		start.start();
//		game.closeGame();
//		TrampolineTester tramp_game = new TrampolineTester();
//		tramp_game.start();
	}
}
