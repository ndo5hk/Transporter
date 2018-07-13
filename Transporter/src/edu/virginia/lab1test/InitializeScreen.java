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
import java.util.HashMap;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.MouseInfo;

/**
 *
 * @author owner
 */
public class InitializeScreen  extends Level implements MouseListener{
    
    Sprite startbutton;
    Sprite instructionsbutton;
    Sprite InstructionsButton;
     Font Title;
    Sprite background;
    ArrayList<Sprite> spritelist;
    Font start;
    Font instructions;
    SoundManager sound;
    boolean start_selected = false;
    boolean instructions_selected = false;
   

    InitializeScreen(HashMap<String, Integer> map, int width, int height){
    	super("startscreen",width,height, map);

    	sound = new SoundManager();
     
        
    	this.background = new Sprite("background1","startScreen.png",false);
    	this.startbutton = new Sprite("startbutton","button.png",false);
    	this.instructionsbutton=new Sprite("instructionsbutton","button.png",false);
    	startbutton.setScaleX(.5);
    	startbutton.setScaleY(.5);
    	startbutton.setPosition(500,350);
        
    	instructionsbutton.setScaleX(.5);
    	instructionsbutton.setScaleY(.5);
    	instructionsbutton.setPosition(500,500);
    	background.setScaleX(3);
    	background.setScaleY(3);
    	start=new Font("sansserif",1,50);
    	instructions=new Font("sansserif",1,40);
    	this.Title = new Font("sansserif",3,100);
    	super.addChild(background);
    	super.addChild(startbutton);
    	super.addChild(instructionsbutton);
    	spritelist = new ArrayList();
    	spritelist.add(startbutton);
    	spritelist.add(instructionsbutton);
        for(Sprite x: spritelist){
        x.setPivotPoint((int)(x.getUnscaledWidth()*.5), (int)(x.getUnscaledHeight()*.5));
        }

    }
    
	public boolean isStarted() {
		return this.start_selected;
	}
	
	public void setStartSelected(boolean s) {
		this.start_selected = s;
	}
	
	public boolean instructionsSelected() {
		return this.instructions_selected;
	}
	
	public void setInstructionsSelected(boolean s) {
		this.instructions_selected = s;
	}
    
 public void resize(Sprite x){
            if(x.getScaleX()>.5){
        x.setScaleX(x.getScaleX()-.01);
        }
            if(x.getScaleY()>.5){
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
    g.setFont(Title);
    g.drawString("Transporters",(int)(1000*.5)-(int)(startbutton.getUnscaledWidth()*.5),210);
    g.setFont(start);
    g.drawString("Start",(int)(1000*.5)-(int)(startbutton.getUnscaledWidth()*.5)+260,365);
    g.setFont(instructions);
    g.drawString("Instructions",390,510);
  
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

    			if (x.getId().equals("startbutton")) {this.start_selected = true;}
    			if (x.getId().equals("instructionsbutton")) {this.instructions_selected = true;}

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
//        public static void main(String[] args) {
//		InitializeScreen start = new InitializeScreen();
//		start.start();
////		game.closeGame();
////		TrampolineTester tramp_game = new TrampolineTester();
////		tramp_game.start();
//	}

  private void mouseIsOverStuff(){
            	Area hover = new Area(new Rectangle2D.Double(MouseInfo.getPointerInfo().getLocation().x+this.getPosition()[0], MouseInfo.getPointerInfo().getLocation().y+this.getPosition()[1]-25, 1, 1));
       for(Sprite x: spritelist){
	Area icon = new Area(x.getGlobalHitbox().get(0));
	icon.intersect(hover);
        if (!icon.isEmpty()) {
            if (x.getId().equals("startbutton")) {
                  startbutton.setScaleX(.52);
                startbutton.setScaleY(.52);
            }
            if (x.getId().equals("instructionsbutton")) {
              instructionsbutton.setScaleX(.52);
              instructionsbutton.setScaleY(.52);
            }
                         }
       }
  }

}
   /*
    print points per level
    totalpoints
    credits
    
    */