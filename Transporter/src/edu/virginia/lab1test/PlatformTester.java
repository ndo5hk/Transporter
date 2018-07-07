package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Ball;
import edu.virginia.engine.display.DisplayObject;
//import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Platform;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.TransporterGame;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class PlatformTester extends TransporterGame{

    private Platform platform;
    private Platform plat2;
    private Ball ball;

	public PlatformTester() {
        super("platformTester", 500,500);
        init();
	}
	
	public void init() {
		this.platform = new Platform("platform_1");  //172x32px
		this.plat2 = new Platform("plat2");
		platform.setPivotPoint(86, 16);
		platform.setPosition(200, 400);
		platform.setRotation(340);
		plat2.setPivotPoint(86, 16);
		plat2.setPosition(450, 215);
		plat2.setRotation(300);
		//platform.setRotation(270);
		ball = new Ball("ball", "ball.png");
		ball.setPivotPoint(25, 25);
		ball.setPosition(200, 50);
		super.addChild(platform);
		super.addChild(plat2);
		super.addChild(ball);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		
		int old_x = ball.getPosition()[0];
		int old_y = ball.getPosition()[1];
        super.update(pressedKeys);
		//System.out.println(Double.toString(ball.getVelX()));
		//System.out.println(Double.toString(ball.getVelY()));
		
		//Transforming platform based on user input
		if(pressedKeys.contains(37)){
            //left arrow
			if (platform.getPosition()[0] >= 4) {
				platform.getPosition()[0] -= 4;
			}
		}
        if(pressedKeys.contains(38)){
        //up arrow
			if (platform.getPosition()[1] >= 4) {
				platform.getPosition()[1] -= 4;
			}
        }
        if(pressedKeys.contains(39)){
        //right arrow
			if (platform.getPosition()[0] <= 480) {
				platform.getPosition()[0] += 4;
			}
        }
        if(pressedKeys.contains(40)){
        //down arrow
			if (platform.getPosition()[1] <= 448) {
				platform.getPosition()[1] += 4;
			}
        }
        if (pressedKeys.contains(65)) {
        	platform.setRotation(platform.getRotation()+5);
        }
        if (pressedKeys.contains(83)) {
        	platform.setRotation(platform.getRotation()-5);
        	//System.out.println(Double.toString(platform.getRotation()));
        }
        //System.out.println(Double.toString(platform.getRotation()));
   
        if (ball.collidesWith(platform)) {
        	ball.setPosition(old_x, old_y);
        	handleCollision(ball, platform);
        }
        if (ball.collidesWith(plat2)) {
        	ball.setPosition(old_x, old_y);
        	handleCollision(ball, plat2);
        }
		
        /* Make sure platform is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		//if(platform != null) platform.update(pressedKeys);
		//if(ball != null) ball.update(pressedKeys);
	}
	
	private void handleCollision(Ball a, Platform b) {
		ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
		a.setVelX(vels.get(0));
		a.setVelY(vels.get(1));
	}
	
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
//		Graphics2D g2d =  (Graphics2D)g;
//		g2d.draw(this.ball.getGlobalHitbox());
//		g2d.draw(this.platform.getGlobalHitbox());
//		if(platform != null) platform.draw(g);
//		if(ball != null) ball.draw(g);
	}
	
	public static void main(String[] args) {
		PlatformTester game = new PlatformTester();
		game.start();
//		game.closeGame();
//		TrampolineTester tramp_game = new TrampolineTester();
//		tramp_game.start();
	}
	

}
