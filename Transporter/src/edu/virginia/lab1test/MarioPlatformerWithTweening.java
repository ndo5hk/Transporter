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
import edu.virginia.engine.display.Platform;
import edu.virginia.engine.display.QuestManager;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.Tween;
import edu.virginia.engine.display.TweenJuggler;
import edu.virginia.engine.display.TweenableParam;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.util.GameClock;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class MarioPlatformerWithTweening extends Game implements IEventListener {

	/* Create a sprite object for our game. We'll use mario */
	static AnimatedSprite mario = new AnimatedSprite("mario_", 6, true);
	static Coin coin = new Coin("coin1", true);
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	static QuestManager qm = new QuestManager();
	boolean mousePressed = false;
	boolean mouseOverMario = false;
	static GameClock clock = new GameClock();
	int points = 10;
	ArrayList<Platform> collisions = new ArrayList<Platform>();
	
	boolean started = false;
	
	public MarioPlatformerWithTweening() {
		super("Lab Two Test Game", 800, 600);
		mario.setPosition(50, 100);
		coin.setPosition(700, 250);
		mario.addActionAnimation("run", 0, 1,17);
		mario.addActionAnimation("stand", 0, 0,20);
		this.addEventListener(this, "Start");
		this.addEventListener(this, "CoinCollected");
		this.addEventListener(this, "VanishCoin");
		
		
	//creating platforms
		//ground
		for (int i=0; i<15; i++) {
			platforms.add(new Platform("platform_"+Integer.toString(i)));
			platforms.get(i).setPosition(i*50,500);
		}
		//step1
		for (int i=5; i<=7; i++) {
			platforms.add(new Platform("step1_"+Integer.toString(i-3)));
			platforms.get(platforms.size()-1).setPosition(i*50,400);
		}
		//step2
		for (int i=10; i<=14; i++) {
			platforms.add(new Platform("step2_"+Integer.toString(i-9)));
			platforms.get(platforms.size()-1).setPosition(i*50,300);
		}
		
	}
	
	//tweening
	@Override
	public void handleEvent(Event event) throws Exception {
		if (event.getEventType() == "CoinCollected") {
			Tween coinTween = new Tween(coin);
			coinTween.animate(TweenableParam.XPOS, coin.getPosition()[0], coin.getPosition()[0]-700, 50);
			coinTween.animate(TweenableParam.YPOS, coin.getPosition()[1], coin.getPosition()[1]-30, 50);
			coinTween.animate(TweenableParam.SCALE_X, 1, 2, 100);
			coinTween.animate(TweenableParam.SCALE_Y, 1, 2, 100);
			//coinTween.animate(TweenableParam.ALPHA, 2.0, 0.0, 100);
			TweenJuggler.getInstance().add(coinTween);
		} else if (event.getEventType() == "Start") {
			Tween marioTween = new Tween(mario);
			marioTween.animate(TweenableParam.ALPHA, 0.0, 1.0, 100);
			TweenJuggler.getInstance().add(marioTween);
		} else if (event.getEventType() == "VanishCoin") {
			Tween coinTween = new Tween(coin);
			coinTween.animate(TweenableParam.ALPHA, coin.getAlpha(), 0.0, 75);
			TweenJuggler.getInstance().add(coinTween);
		}
	}
	
	private int getNumCollisions() {
		try {
			for (Platform p : platforms) {
				if (mario.collidesWith(p)) {
					this.collisions.add(p);
				}
			}
			return collisions.size();
		} catch(NullPointerException ConcurrentModificationException) {
			return 0;
		}
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * @throws Exception 
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		if (started == false) {
			this.dispatchEvent(new Event("Start"));
			started = true;
		}
		this.collisions.clear();
		try {
			TweenJuggler.getInstance().nextFrame();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(pressedKeys.contains(37)){
            //left arrow
			mario.setVelX(-100);
		}
		else if(pressedKeys.contains(39)){
	        //right arrow
			mario.setVelX(175);
		}
		else {
			if (mario.getVelX() >5) {
				mario.setAccelX(-200);
			} else if (mario.getVelX() <5) {
				mario.setAccelX(200);
			} else {
				mario.setAccelX(0);
			}

		}
		if (this.getNumCollisions() > 0) {
			mario.setVelY(0);
			mario.setAccelY(0);
			//super.setVelocity();
			super.setDisplacement();
	
			if(pressedKeys.contains(38)){
		        //up arrow
					if (mario.getPosition()[1] >= 4) {
						mario.setVelY(-200);
					}
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
        
        if (mario.collidesWith(coin) && !coin.isCollected()) {
        	//coin.setVisible(false);
        	coin.collect();
        	this.dispatchEvent(new Event("CoinCollected"));
        }
        
        try {
			if (coin.isCollected() && TweenJuggler.getInstance().isEmpty()) {
				this.dispatchEvent(new Event("VanishCoin"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		try {
			if (platforms != null) {
				for (Platform p : platforms) {
					if (p != null) {
						p.draw(g);
					}
				}
			}
		} catch (Exception ConcurrentModificationException) {
			
		}
		if(mario != null) mario.draw(g);
		if(coin != null) coin.draw(g);
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		MarioPlatformerWithTweening game = new MarioPlatformerWithTweening();
		clock.resetGameClock();
		game.start();
	}


}
