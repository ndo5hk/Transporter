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

/**
 *
 * @author owner
 */
public class Instructions  extends Level implements MouseListener{
    Sprite exit;
    boolean exitbool;
	Sprite startbutton;
	Sprite instructionsbutton;
	Sprite InstructionsButton;
	Font Title;
	Sprite background;
	ArrayList<DisplayObject> spritelist;
	Font start;
	Font instructions;
	SoundManager sound;
      

	Instructions(HashMap<String, Integer> map, int width, int height){
		super("startscreen",width,height,map);

		sound = new SoundManager();
		
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
		instructions=new Font("sansserif",1,15);
		this.Title = new Font("sansserif",1,30);
		super.addChild(background);
                this.exit = new Sprite("exit","cancel.png",false);
		exit.setPosition(960,10);
		exit.setScaleX(.1);
		exit.setScaleY(.1);
                this.addChild(exit);
                spritelist = new ArrayList();
                spritelist.add(exit);
		// super.addChild(startbutton);
		// super.addChild(instructionsbutton);


	}
	@Override
	public void update(ArrayList<Integer> pressedKeys){}
	@Override
	public void draw(Graphics g){
		super.draw(g);
		g.setFont(Title);
		// g.drawString("Game Over",(int)(1000*.5)-(int)(startbutton.getUnscaledWidth()*.5)+130,300);
		g.drawString("Instructions:",(int)(1000*.5)-(int)(startbutton.getUnscaledWidth()*.5)+240,200);
		g.setFont(instructions);

		g.drawString("Click an object to create it. You can now move it with WASD.",(int)(1000*.5)-(int)(startbutton.getUnscaledWidth()*.5)+70,250);
		g.drawString("Rotate the selected item with your left and right arrow keys.",(int)(1000*.5)-(int)(startbutton.getUnscaledWidth()*.5)+70,300);
		g.drawString("Switch between created items with Q, and E.",(int)(1000*.5)-(int)(startbutton.getUnscaledWidth()*.5)+70,350);
		g.drawString("Delete the selected object with R.",(int)(1000*.5)-(int)(startbutton.getUnscaledWidth()*.5)+70,400);
		g.drawString("Start your run by hitting the Spacebar",(int)(1000*.5)-(int)(startbutton.getUnscaledWidth()*.5)+70,450);

	}
          public void setExit(boolean what){
        this.exitbool = what;
        }
        public boolean getExit(){
        return exitbool;
        }
        
	@Override
	public void mouseClicked(MouseEvent e) {
		Area click = new Area(new Rectangle2D.Double(e.getX()+this.getPosition()[0], e.getY()+this.getPosition()[1]-25, 1, 1));
		//		System.out.println(Integer.toString(e.getX()));
		//		System.out.println(Integer.toString(e.getY()));
		for(DisplayObject x: spritelist){
			Area icon = new Area(x.getGlobalHitbox().get(0));
			icon.intersect(click);

			if (!icon.isEmpty()) {
				if (x.getId().equals("exit")) { 
                                    super.setExit(true);
                                            this.setExit(true);}
				
				
	}}}
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
	//		Instructions start = new Instructions();
	//		start.start();
	////		game.closeGame();
	////		TrampolineTester tramp_game = new TrampolineTester();
	////		tramp_game.start();
	//	}
}
/*
    print points per level
    totalpoints
    credits


    */