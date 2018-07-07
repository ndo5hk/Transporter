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
import edu.virginia.engine.display.Fan;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.TransporterGame;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class FanTester extends TransporterGame{

    private Fan fan;
    private Ball ball;

	public FanTester() {
        super("fanTester", 1500,1000);
        init();
	}
	
	public void init() {
		this.fan = new Fan("fan_1");  //172x32px
		fan.setPivotPoint(86, 16);
		fan.setPosition(200, 350);
		fan.setRotation(0);
		ball = new Ball("ball", "ball.png");
		ball.setPivotPoint(25, 25);
		ball.setPosition(200, 100);
		super.addChild(fan);
		super.addChild(ball);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		
		int old_x = ball.getPosition()[0];
		int old_y = ball.getPosition()[1];
        super.update(pressedKeys);
		
		//Transforming fan based on user input
		if(pressedKeys.contains(37)){
            //left arrow
			if (fan.getPosition()[0] >= 4) {
				fan.getPosition()[0] -= 4;
			}
		}
        if(pressedKeys.contains(38)){
        //up arrow
			if (fan.getPosition()[1] >= 4) {
				fan.getPosition()[1] -= 4;
			}
        }
        if(pressedKeys.contains(39)){
        //right arrow
			if (fan.getPosition()[0] <= 480) {
				fan.getPosition()[0] += 4;
			}
        }
        if(pressedKeys.contains(40)){
        //down arrow
			if (fan.getPosition()[1] <= 448) {
				fan.getPosition()[1] += 4;
			}
        }
        if (pressedKeys.contains(81)) {
        	fan.setRotation(fan.getRotation()+5);
        }
        if (pressedKeys.contains(87)) {
        	fan.setRotation(fan.getRotation()-5);
        }
        //System.out.println(Double.toString(fan.getRotation()));
   
        if (ball.collidesWith(fan) != null) {
        	String hitboxID = ball.collidesWith(fan);
        	handleCollision(ball, fan, ball.collidesWith(fan));
        	if (hitboxID.equals("fan_bottom")) {
        		ball.setPosition(old_x, old_y);
        	}
        }
        
        super.update(pressedKeys);
		
		/* Make sure fan is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		//if(fan != null) fan.update(pressedKeys);
		//if(ball != null) ball.update(pressedKeys);
	}
	
	private void handleCollision(Ball a, Fan b, String hitbox_id) {
		if (hitbox_id.equals("fan_top")) {
			System.out.println("Top"+(this.ball.getPosition()[1]+700*Math.cos(Math.toRadians(b.getNormal())))+" "+this.fan.getPosition()[1]);
                    //   if(>this.fan.getPosition()[1]){
                       System.out.println("stuff");
                     //  }
			a.setVelX(a.getVelX()+100*Math.cos(Math.toRadians(b.getNormal())));
			a.setVelY(a.getVelY()-100*Math.sin(Math.toRadians(b.getNormal())));
                        
		} else {
			System.out.println("Bottom");
			ArrayList<Double> vels = super.getElasticCollisionVels(a, b, true);
			a.setVelX(vels.get(0)*0.2);
			a.setVelY(vels.get(1)*0.2);
		}
	}
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
		Graphics2D g2d =  (Graphics2D)g;
		//g2d.draw(this.ball.getGlobalHitbox().get(0));
		g2d.draw(this.fan.getGlobalHitbox().get(0));
		g2d.draw(this.fan.getGlobalHitbox().get(1));
//		if(fan != null) fan.draw(g);
//		if(ball != null) ball.draw(g);
	}
	
	public static void main(String[] args) {
		FanTester game = new FanTester();
		game.start();
	}
	

}