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
import edu.virginia.engine.display.TreadMill;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.TransporterGame;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class TreadMillTester extends TransporterGame{

    private TreadMill treadmill;
    private Ball ball;

	public TreadMillTester() {
        super("TreadMillTester", 500,500);
        init();
	}
	
	public void init() {
		this.treadmill = new TreadMill();  //172x32px
		treadmill.setPivotPoint(86, 16);
		treadmill.setPosition(200, 400);
		//treadmill.setRotation(270);
		ball = new Ball("ball", "ball.png");
		ball.setPivotPoint(25, 25);
		ball.setPosition(150, 50);
		super.addChild(treadmill);
		super.addChild(ball);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		treadmill.animate("run");
		
		//Transforming treadmill based on user input
		if(pressedKeys.contains(37)){
            //left arrow
			if (treadmill.getPosition()[0] >= 4) {
				treadmill.getPosition()[0] -= 4;
			}
		}
        if(pressedKeys.contains(38)){
        //up arrow
			if (treadmill.getPosition()[1] >= 4) {
				treadmill.getPosition()[1] -= 4;
			}
        }
        if(pressedKeys.contains(39)){
        //right arrow
			if (treadmill.getPosition()[0] <= 480) {
				treadmill.getPosition()[0] += 4;
			}
        }
        if(pressedKeys.contains(40)){
        //down arrow
			if (treadmill.getPosition()[1] <= 448) {
				treadmill.getPosition()[1] += 4;
			}
        }
        if (pressedKeys.contains(65)) {
        	treadmill.setRotation(treadmill.getRotation()+5);
        }
        //System.out.println(Double.toString(treadmill.getRotation()));
   
        if (ball.collidesWith(treadmill)) {
        	handleCollision(ball, treadmill);
        }
        
        super.update(pressedKeys);
		
		/* Make sure treadmill is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		//if(treadmill != null) treadmill.update(pressedKeys);
		//if(ball != null) ball.update(pressedKeys);
	}
	
	private void handleCollision(Ball a, TreadMill b) {
		if (b.getRotation() == 0 || b.getRotation() == 360.0) {
			a.setPosition(a.getPosition()[0], b.getPosition()[1]-40); //41 is half the treadmill width + half the ball width
			if (a.getVelY() > 100) {
				a.setVelX(a.getVelX()+300);
				a.setVelY(a.getVelY()*-0.5);
				//System.out.println(Double.toString(a.getVelY()));
			} else {
				a.setVelY(0);
				a.setAccelY(0);
			}
		} else {
			//CHANGE THIS ONCE THE ROTATION BUG IS FIXED
			double slope = Math.tan(b.getRotation());
			if (a.getVelY()<0) {
				if ((b.getRotation() > 270 && b.getRotation() < 360) || (b.getRotation() < 180 && b.getRotation() > 90)) {
					while (a.collidesWith(b)) {
						double accum = slope;
						double new_x = 1;
						while (accum<1) {
							accum+=slope;
							new_x+=1;
						}
						a.setPosition(a.getPosition()[0]+ (int)new_x, a.getPosition()[1]+(int)accum);
					}
				} else if ((b.getRotation() > 0 && b.getRotation() < 90) || (b.getRotation() > 180 && b.getRotation() < 270)) {
					while (a.collidesWith(b)) {
						double accum = slope;
						double new_x = 1;
						while (accum<1) {
							accum+=slope;
							new_x+=1;
						}
						a.setPosition(a.getPosition()[0]- (int)new_x, a.getPosition()[1]+(int)accum);
					}
				}
			}
			double angle = Math.toDegrees(Math.atan2(a.getVelY(), a.getVelX()));
			double normalAngle = b.getRotation()-90;
			angle = 2*normalAngle - 180 - angle;
			double mag = Math.hypot(b.getVelX(), b.getVelY());
			a.setVelX(Math.cos(Math.toRadians(angle) * mag));
			a.setVelY(Math.sin(Math.toRadians(angle) * mag));
		}
	}
	
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
//		Graphics2D g2d =  (Graphics2D)g;
//		g2d.draw(this.ball.getGlobalHitbox());
//		g2d.draw(this.treadmill.getGlobalHitbox());
//		if(treadmill != null) treadmill.draw(g);
//		if(ball != null) ball.draw(g);
	}
	
	public static void main(String[] args) {
		TreadMillTester game = new TreadMillTester();
		game.start();
	}
	

}