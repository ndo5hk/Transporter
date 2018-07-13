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
import edu.virginia.engine.display.Tween;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Point;
import java.awt.MouseInfo;
import java.awt.PointerInfo;

/**
 *
 * @author owner
 */
public class StartScreen  extends Level implements MouseListener{
	Tween startbutton;
	Sprite InstructionsButton;
	Sprite levelone;
	Sprite leveltwo;
	Sprite levelthree;
	Sprite levelfour;
	Sprite levelfive;
	Sprite levelsix;
         Sprite checkL1;
    Sprite checkL2;
    Sprite checkL3;
    Sprite checkL4;
    Sprite checkL5;
    Sprite checkL6;
    ArrayList<String> completedLevels;
    ArrayList<Sprite> checks;
	Sprite lock;
	Sprite background;
	ArrayList<Sprite> spritelist;
	Font stuff;
	SoundManager sound;
	int selected_level = 0;

	StartScreen(HashMap<String, Integer> map, int width, int height){

		super("startscreen",width,height, map);
		stuff=new Font("sansserif",1,50);
		sound = new SoundManager();
		checks = new ArrayList();
    
        
                
		this.background = new Sprite("background1","startScreen.png",false);
		background.setScaleX(3);
		background.setScaleY(3);
		super.addChild(this.background);
		levelone=new Sprite("LevelOne","levelone.png",false);
		leveltwo=new Sprite("LevelTwo","leveltwo.png",false);
		levelthree=new Sprite("LevelThree","levelthree.png",false);
		levelfour = new Sprite("LevelFour","levelfour.png",false);
		levelfive=new Sprite("LevelFive","levelfive.png",false);
		levelsix=new Sprite("LevelSix","levelsix.png",false);
		lock = new Sprite("Lock","lock.png",false);
		lock.setPosition(1200,400);
		lock.setAlpha(.8f);
		lock.setRotation(0);
		lock.setScaleX(.165);
		lock.setScaleY(.243);
		levelone.setPosition(200,300 );
		levelone.setScaleX(.2);
		levelone.setScaleY(.2);
		leveltwo.setPosition(500,300 );
		leveltwo.setScaleX(.2);
		leveltwo.setScaleY(.2);
		levelthree.setPosition(800,300 );
		levelthree.setScaleX(.2);
		levelthree.setScaleY(.2);
		levelsix.setPosition(200,550 );
		levelsix.setScaleX(.2);
		levelsix.setScaleY(.2);
		levelfour.setPosition(500,550 );
		levelfour.setScaleX(.2);
		levelfour.setScaleY(.2);
		levelfive.setPosition(800,550 );
		levelfive.setScaleX(.2);
		levelfive.setScaleY(.2);
		this.addChild(background);
		this.addChild(levelone);
		this.addChild(leveltwo);
		this.addChild(levelthree);
		this.addChild(levelfour);
		this.addChild(levelfive);
		this.addChild(levelsix);
		this.addChild(lock);

		spritelist=new ArrayList<Sprite>();
		spritelist.add(levelone);
		spritelist.add(leveltwo);
		spritelist.add(levelthree);
		spritelist.add(levelfour);
		spritelist.add(levelfive);
		spritelist.add(levelsix);
                for(Sprite x : spritelist){
                x.setPivotPoint((int)(x.getUnscaledWidth()*.5), (int)(x.getUnscaledHeight()*.5));
                }
               
                
                            System.out.print("LOL");
                
                   checkL1 = new Sprite("checkL1","check.png",false);
                   checkL2 = new Sprite("checkL2","check.png",false);
                   checkL3 = new Sprite("checkL3","check.png",false);
                   checkL4 = new Sprite("checkL4","check.png",false);
                   checkL5 = new Sprite("checkL5","check.png",false);
                   checkL6 = new Sprite("checkL6","check.png",false);
                   checks.add(checkL1);
                   checks.add(checkL2);
                   checks.add(checkL3);
                   checks.add(checkL4);
                   checks.add(checkL5);
                   checks.add(checkL6);
                   for(Sprite x: checks){
                         x.setScaleX(.2);
                   x.setScaleY(.2);
                   }
          
      
        
        checkL1.setPosition((int)((levelone.getUnscaledWidth())*.5)-120,-350);
        checkL2.setPosition((int)(leveltwo.getUnscaledWidth())-150,50);
        checkL3.setPosition((int)(levelthree.getUnscaledWidth())-150,50);
        checkL4.setPosition((int)(levelfour.getUnscaledWidth())-150,50);
        checkL5.setPosition((int)(levelfive.getUnscaledWidth())-150,50);
        checkL6.setPosition((int)(levelsix.getUnscaledWidth())-150,50);
        for(Sprite x: checks){
        x.setAlpha(1f);
        x.setVisible(false);
        }
       levelone.addChild(checkL1);
       leveltwo.addChild(checkL2);
       levelthree.addChild(checkL3);
       levelfour.addChild(checkL4);
       levelfive.addChild(checkL5);
       levelsix.addChild(checkL6);   
       
       
      
        

	}
         public void setChecks(ArrayList<String> completed){
            this.completedLevels = completed;
            
        
        }
        public void resize(Sprite x){
            if(x.getScaleX()>.2){
        x.setScaleX(x.getScaleX()-.01);
        }
            if(x.getScaleY()>.2){
        x.setScaleY(x.getScaleY()-.01);
        }
      
        }
	@Override
	public void update(ArrayList<Integer> pressedKeys){
          this.mouseIsOverStuff();
          for(Sprite x : spritelist){
          resize(x);
          }
        }
	@Override
	public void draw(Graphics g){
		super.draw(g);
		g.setFont(stuff);
		g.drawString("Pick a Level",370,100);
		Graphics2D g2d = (Graphics2D)g;
		for(Sprite x : spritelist){ 
			g2d.draw(x.getGlobalHitbox().get(0));}
                if(this.completedLevels!=null){

  if(completedLevels.contains("l1")){
this.levelone.setAlpha(.7f);
  this.checkL1.setAlpha(1f);
  }else {this.checkL1.setVisible(false);
   this.levelone.setAlpha(1f);
     }
  
   if(completedLevels.contains("l2")){
  this.checkL2.setVisible(true);
  this.leveltwo.setAlpha(.7f);
  this.checkL2.setAlpha(1f);
  }else  {this.checkL2.setVisible(false);
   this.leveltwo.setAlpha(1f);
     }
   
    if(completedLevels.contains("l3")){
  this.checkL3.setVisible(true);
  this.levelthree.setAlpha(.7f);
  this.checkL3.setAlpha(1f);
  
  }else{this.checkL3.setVisible(false);
   this.levelthree.setAlpha(1f);
     }
  
   if(completedLevels.contains("l4")){
  this.checkL4.setVisible(true);
  this.levelfour.setAlpha(.7f);
  this.checkL4.setAlpha(1f);
  }else{this.checkL4.setVisible(false);
   this.levelfour.setAlpha(1f);
     }
   
    if(completedLevels.contains("l5")){
  this.checkL5.setVisible(true);
  this.levelfive.setAlpha(.7f);
  this.checkL5.setAlpha(1f);
  }else  {this.checkL5.setVisible(false);
   this.levelfive.setAlpha(1f);
     }
    
     if(completedLevels.contains("l6")){
  this.checkL6.setVisible(true);
  this.levelsix.setAlpha(.7f);
  this.checkL6.setAlpha(1f);
  }else  {this.checkL6.setVisible(false);
   this.levelsix.setAlpha(1f);
     }
     }
                this.mouseIsOverStuff();
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
					if (x.getId().equals("LevelOne")) {selected_level = 1;}
					if (x.getId().equals("LevelTwo")) {selected_level = 2;}
					if (x.getId().equals("LevelThree")) {selected_level = 3;}
					if (x.getId().equals("LevelFour")) {selected_level = 4;}
					if (x.getId().equals("LevelFive")) {selected_level = 5;}
					if (x.getId().equals("LevelSix")) {selected_level = 6;}

				}
			}
		}
	}
	
	public int getSelectedLevel() {
		return this.selected_level;
	}
	public void setSelectedLevel(int l) {
		this.selected_level = l;
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

	}
      
        
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
   
        
       private void mouseIsOverStuff(){
            	Area hover = new Area(new Rectangle2D.Double(MouseInfo.getPointerInfo().getLocation().x+this.getPosition()[0], MouseInfo.getPointerInfo().getLocation().y+this.getPosition()[1]-25, 1, 1));
       for(Sprite x: spritelist){
			Area icon = new Area(x.getGlobalHitbox().get(0));
			icon.intersect(hover);

			if (!icon.isEmpty()) {
                           if(x.getId().equals("LevelOne")){
                             levelone.setScaleX(.24);
                levelone.setScaleY(.24);
                
                           }
                            if(x.getId().equals("LevelTwo")){
                            leveltwo.setScaleX(.24);
                leveltwo.setScaleY(.24);
                           }
                           if(x.getId().equals("LevelThree")){
                            levelthree.setScaleX(.24);
                levelthree.setScaleY(.24);
                           }
                        if(x.getId().equals("LevelFour")){
                            levelfour.setScaleX(.24);
                             levelfour.setScaleY(.24);
                           }
                         if(x.getId().equals("LevelFive")){
                           levelfive.setScaleX(.24);
                             levelfive.setScaleY(.24);
                           }
                         if(x.getId().equals("LevelSix")){
                           levelsix.setScaleX(.24);
                             levelsix.setScaleY(.24);
                           }
                         
        }
        
       }
//	public static void main(String[] args) {
//		StartScreen start = new StartScreen();
//		start.start();
//		//		game.closeGame();
//		//		TrampolineTester tramp_game = new TrampolineTester();
//		//		tramp_game.start();
//	}
}}

