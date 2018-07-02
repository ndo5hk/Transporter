package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.GameClock;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class PlatformTester extends Game implements MouseListener{

	/* Create a sprite object for our game. We'll use mario */
	static AnimatedSprite mario = new AnimatedSprite("mario_", 6, false);
	static int points = 50;
	boolean mousePressed = false;
	boolean mouseOverMario = false;
	static GameClock clock = new GameClock();
	
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public PlatformTester() {
		super("Lab Two Test Game", 800, 600);
		super.getMainFrame().addMouseListener(this);
		mario.addActionAnimation("run", 0, 1,17);
		mario.addActionAnimation("roll", 2, 5,12);
		mario.addActionAnimation("stand", 0, 0,20);
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		if(pressedKeys.contains(37)){
            //left arrow
			if (mario.getPosition()[0] >= 4) {
				mario.getPosition()[0] -= 4;
			}
		}
        if(pressedKeys.contains(38)){
        //up arrow
			if (mario.getPosition()[1] >= 4) {
				mario.getPosition()[1] -= 4;
			}
        }
        if(pressedKeys.contains(39)){
        //right arrow
			if (mario.getPosition()[0] <= 680) {
				mario.getPosition()[0] += 4;
			}
        }
        if(pressedKeys.contains(40)){
        //down arrow
			if (mario.getPosition()[1] <= 460) {
				mario.getPosition()[1] += 4;
			}
        }
      if(pressedKeys.contains(38) || pressedKeys.contains(40)){
      //up and down arrows
    	  mario.animate("roll");
      } else if (pressedKeys.contains(39) || pressedKeys.contains(37)) {
    	  //left and right arrows
		mario.animate("run");
      } else {
    	  mario.animate("stand");
      }
		super.update(pressedKeys);
		
		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if(mario != null) mario.update(pressedKeys);
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		
		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
//		if(mario != null) mario.draw(g);

		g.drawString("Life: " + Integer.toString(points), 18, 20);
		
		if (points <= 0) {
			g.drawString("Mario Lost!", 450, 300);
		} else if (60-(int)clock.getElapsedTime()/1000 >= 0){
			g.drawString("Time: " + Integer.toString(60-(int)clock.getElapsedTime()/1000), 725, 20);
		}
		
		if (60-(int)clock.getElapsedTime()/1000 < 0) {
			g.drawString("Clicker Lost!", 350, 230);
		}
		if(mario != null) mario.draw(g);
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		Lab2Game game = new Lab2Game();
		clock.resetGameClock();
		game.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (points > 0 && 60-(int)clock.getElapsedTime()/1000 >= 0) {
			if (e.getX() > mario.getPosition()[0]+15 && e.getX() < mario.getPosition()[0]+108 && e.getY() > mario.getPosition()[1]+20 && e.getY() < mario.getPosition()[1]+153) {
				points--;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
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
}
