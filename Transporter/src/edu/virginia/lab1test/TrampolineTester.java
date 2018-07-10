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
		trampoline.setPosition(200, 100);
		trampoline.setRotation(30);
		ball = new Ball("ball", "ball.png");
		ball.setPivotPoint(25, 25);
		ball.setPosition(200, 400);
		ball.setVelY(-1000);
		super.addChild(trampoline);
		super.addChild(ball);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		
		int old_x = ball.getPosition()[0];
		int old_y = ball.getPosition()[1];
        super.update(pressedKeys);
		
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
   
        if (ball.collidesWith(trampoline) != null) {
        	handleCollision(ball, trampoline, ball.collidesWith(trampoline));
        	ball.setPosition(old_x, old_y);
        }
        
        super.update(pressedKeys);
		
		/* Make sure trampoline is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		//if(trampoline != null) trampoline.update(pressedKeys);
		//if(ball != null) ball.update(pressedKeys);
	}
	
	private void handleCollision(Ball a, Trampoline b, String hitbox_id) {
		if (hitbox_id.equals("trampoline_top")) {
			System.out.println("Top");
			ArrayList<Double> vels = super.getElasticCollisionVels(a, b);
			a.setVelX(vels.get(0));
			a.setVelY(vels.get(1));
		} else {
			System.out.println("Bottom");
			ArrayList<Double> vels = super.getElasticCollisionVels(a, b);
			a.setVelX(vels.get(0)*0.2);
			a.setVelY(vels.get(1)*0.2);
		}
	}
	
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
		Graphics2D g2d =  (Graphics2D)g;
		//g2d.draw(this.ball.getGlobalHitbox());
//		g2d.draw(this.trampoline.getGlobalHitbox().get(0));
//		g2d.draw(this.trampoline.getGlobalHitbox().get(1));
//		if(trampoline != null) trampoline.draw(g);
//		if(ball != null) ball.draw(g);
	}
	
	public static void main(String[] args) {
		TrampolineTester game = new TrampolineTester();
		game.start();
	}
	

}
