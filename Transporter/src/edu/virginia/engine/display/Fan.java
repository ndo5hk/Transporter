package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.GameClock;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class Fan extends Sprite {

	/* Create a sprite object for our game. We'll use mario */
	//
    
   // static AnimatedSprite mario = new AnimatedSprite("mario_", 6, false);
	static int points = 50;
	boolean mousePressed = false;
	boolean mouseOverMario = false;
	static GameClock clock = new GameClock();
        
	int angle;
        boolean swing = true;
        
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public Fan() {
		super("fan","fan.png",false);
        this.setScaleX(.2);  
        this.setScaleY(.2); 
	}
       
        
     
     
       
         }
