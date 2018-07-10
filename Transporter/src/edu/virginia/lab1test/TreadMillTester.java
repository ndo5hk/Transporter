package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import edu.virginia.engine.display.Ball;
//import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.ReverseTreadMill;
import edu.virginia.engine.display.Trampoline;
import edu.virginia.engine.display.TreadMill;
import edu.virginia.engine.display.TransporterGame;


/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class TreadMillTester extends TransporterGame{

    private TreadMill treadmill;
    private ReverseTreadMill reverse;
    private Ball ball;

	public TreadMillTester() {
        super("TreadMillTester", 1000,500);
        init();
	}
	
	public void init() {
		this.treadmill = new TreadMill();  //172x32px
		treadmill.setPivotPoint(118, 30);
		treadmill.setPosition(150, 250);
		//treadmill.setRotation(50);
		
		this.reverse = new ReverseTreadMill();  //172x32px
		reverse.setPivotPoint(118, 30);
		reverse.setPosition(300, 400);
		//treadmill.setRotation(270);
		ball = new Ball("ball", "ball.png");
		ball.setPivotPoint(25, 25);
		ball.setPosition(100, -200);
		super.addChild(treadmill);
		super.addChild(reverse);
		super.addChild(ball);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		treadmill.animate("run");
		reverse.animate("run");
		
		int old_x = ball.getPosition()[0];
		int old_y = ball.getPosition()[1];
        super.update(pressedKeys);
   
        if (ball.collidesWith(treadmill)) {
        	handleCollision(ball, treadmill);
        	ball.setPosition(old_x, old_y);
        }
        
        if (ball.collidesWith(reverse)) {
        	handleCollision(ball, reverse);
        	ball.setPosition(old_x, old_y);
        }
		
		/* Make sure treadmill is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		//if(treadmill != null) treadmill.update(pressedKeys);
		//if(ball != null) ball.update(pressedKeys);
	}
	
	private void handleCollision(Ball a, TreadMill b) {
		ArrayList<Double> vels = super.getElasticCollisionVels(a, b);
		if (a.getVelY()<=0) {
			a.setVelX(vels.get(0)*0.1);
			a.setVelY(vels.get(1)*0.1);
		} else {
			a.setVelX(vels.get(0)*0.1+200);
			a.setVelY(vels.get(1)*0.1);
		}
	}
	
	private void handleCollision(Ball a, ReverseTreadMill b) {
		ArrayList<Double> vels = super.getElasticCollisionVels(a, b);
		a.setVelX(vels.get(0)*0.2-200);
		a.setVelY(vels.get(1)*0.2);
	}
	
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
//		Graphics2D g2d =  (Graphics2D)g;
//		g2d.draw(this.ball.getGlobalHitbox().get(0));
//		g2d.draw(this.treadmill.getGlobalHitbox().get(0));
//		g2d.draw(this.reverse.getGlobalHitbox());
//		if(treadmill != null) treadmill.draw(g);
//		if(ball != null) ball.draw(g);
	}
	
	public static void main(String[] args) {
		TreadMillTester game = new TreadMillTester();
		game.start();
	}
	

}