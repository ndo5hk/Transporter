package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.GameClock;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class TranpolineTest extends Sprite {

	/* Create a sprite object for our game. We'll use mario */

    
   // static AnimatedSprite mario = new AnimatedSprite("mario_", 6, false);
    boolean hi = true;
	static int points = 50;
	boolean mousePressed = false;
	boolean mouseOverMario = false;
	static GameClock clock = new GameClock();
        Sprite sprite;
	int angle;
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public TranpolineTest() {
           super("platform","trampoline.png",false);
		this.sprite= new Sprite("platform","trampoline.png",false);
		this.sprite.setScaleY(.1);
                this.sprite.setScaleX(.1);
                
                this.angle = 0;
                
	}
        public Sprite getSprite(){
        return this.sprite;
        }
	

}
