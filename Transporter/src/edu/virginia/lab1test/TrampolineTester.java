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
import edu.virginia.engine.display.Trampoline;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.TransporterGame;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */

public class TrampolineTester extends TransporterGame{

    private Trampoline trampoline;
    private Ball ball;

	public TrampolineTester() {
        super("trampolineTester", 500,500);
        init();
	}
	
	public void init() {
		this.trampoline = new Trampoline("trampoline_1");  //172x32px
		trampoline.setPivotPoint(86, 16);
		trampoline.setPosition(200, 400);
		//trampoline.setRotation(270);
		ball = new Ball("ball", "ball.png");
		ball.setPivotPoint(25, 25);
		ball.setPosition(200, 50);
		super.addChild(trampoline);
		super.addChild(ball);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		
		//Transforming trampoline based on user input
		if(pressedKeys.contains(37)){
            //left arrow
			if (trampoline.getPosition()[0] >= 4) {
				trampoline.getPosition()[0] -= 4;
			}
		}
        if(pressedKeys.contains(38)){
        //up arrow
			if (trampoline.getPosition()[1] >= 4) {
				trampoline.getPosition()[1] -= 4;
			}
        }
        if(pressedKeys.contains(39)){
        //right arrow
			if (trampoline.getPosition()[0] <= 480) {
				trampoline.getPosition()[0] += 4;
			}
        }
        if(pressedKeys.contains(40)){
        //down arrow
			if (trampoline.getPosition()[1] <= 448) {
				trampoline.getPosition()[1] += 4;
			}
        }
        if (pressedKeys.contains(65)) {
        	trampoline.setRotation(trampoline.getRotation()+5);
        }
        //System.out.println(Double.toString(trampoline.getRotation()));
   
        if (ball.collidesWith(trampoline)) {
        	handleCollision(ball, trampoline);
        }
        
        super.update(pressedKeys);
		
		/* Make sure trampoline is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		//if(trampoline != null) trampoline.update(pressedKeys);
		//if(ball != null) ball.update(pressedKeys);
	}
	
	private void handleCollision(Ball a, Trampoline b) {
		if (b.getRotation() == 0 || b.getRotation() == 360.0) {
			a.setPosition(a.getPosition()[0], b.getPosition()[1]-25); //41 is half the trampoline width + half the ball width
			if (a.getVelY() > 50) {
				a.setVelY(a.getVelY()*-1);
				//System.out.println(Double.toString(a.getVelY()));
			} else {
				a.setVelY(0);
				a.setAccelY(0);
			}
		} else {
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
//		g2d.draw(this.trampoline.getGlobalHitbox());
//		if(trampoline != null) trampoline.draw(g);
//		if(ball != null) ball.draw(g);
	}
	
	public static void main(String[] args) {
		TrampolineTester game = new TrampolineTester();
		game.start();
	}
	

}
