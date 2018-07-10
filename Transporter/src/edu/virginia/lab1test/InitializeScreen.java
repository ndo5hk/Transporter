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
public class InitializeScreen  extends TransporterGame implements MouseListener{
    Sprite startbutton;
    Sprite instructionsbutton;
    Sprite InstructionsButton;
     Font Title;
    Sprite background;
    ArrayList<Sprite> spritelist;
    Font start;
    Font instructions;
    SoundManager sound;
    
    InitializeScreen(){
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
        super.addChild(startbutton);
        super.addChild(instructionsbutton);
        spritelist = new ArrayList();
        spritelist.add(startbutton);
        spritelist.add(instructionsbutton);
       
    this.getMainFrame().addMouseListener(this);

    }
   @Override
	public void update(ArrayList<Integer> pressedKeys){}
    @Override
    public void draw(Graphics g){
    super.draw(g);
    g.setFont(Title);
    g.drawString("Transporters",(int)(2100*.5)-(int)(startbutton.getUnscaledWidth()*.5)-125,300);
    g.setFont(start);
    g.drawString("Start",(int)(2100*.5)-(int)(startbutton.getUnscaledWidth()*.5)+150,465);
    g.setFont(instructions);
    g.drawString("Instructions",(int)(2100*.5)-(int)(startbutton.getUnscaledWidth()*.5)+100,595);
  
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
                                    
					if (x.getId().equals("startbutton")) {System.out.println("start");}
                                       	if (x.getId().equals("instructionsbutton")) {System.out.println("instructions");}
                                        
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
		InitializeScreen start = new InitializeScreen();
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