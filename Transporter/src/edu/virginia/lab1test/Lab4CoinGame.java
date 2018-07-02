package edu.virginia.lab1test;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Coin;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.QuestManager;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class Lab4CoinGame extends Game{

	/* Create a sprite object for our game. We'll use mario */
	static AnimatedSprite mario = new AnimatedSprite("mario_", 6, false);
	static Coin coin = new Coin("coin1", false);
	static QuestManager qm = new QuestManager();
	boolean mousePressed = false;
	boolean mouseOverMario = false;
	static GameClock clock = new GameClock();
	int points = 10;
	
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public Lab4CoinGame() {
		super("Lab Two Test Game", 800, 600);
		mario.setPosition(100, 450);
		coin.setPosition(450, 200);
		mario.addActionAnimation("run", 0, 1,17);
		mario.addActionAnimation("stand", 0, 0,20);
		this.addEventListener(qm, "Quest");
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
			if (mario.getPosition()[1] <= 448) {
				mario.getPosition()[1] += 4;
			}
        }
        if (pressedKeys.contains(65)) {
        	mario.setRotation(mario.getRotation()+0.05);
        }
        
        if (pressedKeys.contains(39) || pressedKeys.contains(37) || pressedKeys.contains(38) || pressedKeys.contains(40)) {
    	  //left, right, up, down arrows
        	mario.animate("run");
        	} else {
        		mario.animate("stand");
        	}
        
        if (mario.collidesWith(coin) && coin.isVisible()) {
        	coin.setVisible(false);
        	this.dispatchEvent(new Event("Quest"));
        }
        
        super.update(pressedKeys);
		
		/* Make sure Mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if(mario != null) mario.update(pressedKeys);
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);

		if(mario != null) mario.draw(g);
		if(coin != null) coin.draw(g);
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		Lab4CoinGame game = new Lab4CoinGame();
		clock.resetGameClock();
		game.start();
	}


}
