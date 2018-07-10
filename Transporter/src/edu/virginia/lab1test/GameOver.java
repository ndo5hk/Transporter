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
public class GameOver  extends TransporterGame implements MouseListener{
    Sprite startbutton;
    Sprite instructionsbutton;
    Sprite InstructionsButton;
     Font Title;
    Sprite background;
    ArrayList<Sprite> spritelist;
    Font start;
    Font instructions;
    SoundManager sound;
    
    GameOver(){
        super("startscreen",1900,1000);
        
         sound = new SoundManager();
            sound.PlayMusic("song1");
              this.background = new Sprite("background1","startScreen.png",false);
        this.startbutton = new Sprite("startbutton","button.png",false);
        this.instructionsbutton=new Sprite("instructionsbutton","button.png",false);
        startbutton.setScaleX(.5);
         startbutton.setScaleY(.5);
         startbutton.setPosition((int)(2100*.5)-(int)(startbutton.getUnscaledWidth()*.5)+50,400);
          instructionsbutton.setScaleX(.5);
         instructionsbutton.setScaleY(.5);
         instructionsbutton.setPosition((int)(2100*.5)-(int)(startbutton.getUnscaledWidth()*.5)+50,530);
         background.setScaleX(3);
              background.setScaleY(3);
        start=new Font("sansserif",1,50);
        instructions=new Font("sansserif",1,40);
       this.Title = new Font("sansserif",3,100);
         super.addChild(background);
       // super.addChild(startbutton);
       // super.addChild(instructionsbutton);
       
    this.getMainFrame().addMouseListener(this);

    }
   @Override
	public void update(ArrayList<Integer> pressedKeys){}
    @Override
    public void draw(Graphics g){
    super.draw(g);
    g.setFont(Title);
    g.drawString("Game Over",(int)(2100*.5)-(int)(startbutton.getUnscaledWidth()*.5)-125,300);
  
    g.setFont(instructions);
    g.drawString("Score:",(int)(2100*.5)-(int)(startbutton.getUnscaledWidth()*.5)+100,400);
    g.drawString("Credit:",(int)(2100*.5)-(int)(startbutton.getUnscaledWidth()*.5)+100,660);
   g.drawString("Nicholas Onley",(int)(2100*.5)-(int)(startbutton.getUnscaledWidth()*.5)+20,740);
    g.drawString("Aiden Smith",(int)(2100*.5)-(int)(startbutton.getUnscaledWidth()*.5)+50,820);
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
					if (x.getId().equals("LevelOne")) {
                                            System.out.println("levelone");}
                                        if (x.getId().equals("LevelTwo")) {
                                            System.out.println("leveltwo");}
                                        if (x.getId().equals("LevelThree")) {
                                            System.out.println("levelthree");}
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
		GameOver start = new GameOver();
		start.start();
//		game.closeGame();
//		TrampolineTester tramp_game = new TrampolineTester();
//		tramp_game.start();
	}
}
   /*
    print points per level
    totalpoints
    credits
    
    
    */