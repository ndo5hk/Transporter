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

public class SwingingPlatformTester extends TransporterGame{

    private SwingPlatform platformstuff;
    private Ball ball;
   
   // private TranpolineTest tramp;
    //Sprite platformstuff;
   
Sprite platform;
	public SwingingPlatformTester() {
        super("swingingplatformTester", 1000,500);
        init();
	}
	
	public void init() {
           
            
                this.ball = new Ball("ball");
                this.ball.setPosition(300, -500);
                
		this.platformstuff = new SwingPlatform();  //172x32px
             //  this.platformstuff=platform.getSprite();
		this.platformstuff.swing();
		super.addChild(platformstuff);
                super.addChild(ball);
                this.ball.setVelY(500);
		
              //   System.out.print(fan.getUnscaledHeight()*.2);
             
            
       //   this.tramp.setScaleY(.2);
       //         this.tramp.setScaleX(.2);
       //   this.tramp.setPivotPoint(this.tramp.getPosition()[0]+16, this.tramp.getPosition()[1]+16);
             // this.fan.
              //this.fan.setRotation(270);

	}
	

	public void update(ArrayList<Integer> pressedKeys){
        if(this.ball.collidesWith(platformstuff)){
        this.handleCollision(ball, platformstuff);
        }
        platformstuff.swing();
       // System.out.println("Ball rotation is: " + this.ball.getRotation());
       // this.ball.setVelY(500);
       
       //System.out.println(Double.toString(platform.getRotation()));
      //  if (ball.collidesWith(platform)) {
     // 	handleCollision(ball, platform);
       // }
        
        super.update(pressedKeys);
		
		/* Make sure platform is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		//if(platform != null) platform.update(pressedKeys);
		//if(ball != null) ball.update(pressedKeys);
	}
	
	private void handleCollision(Ball a, SwingPlatform b) {
            System.out.print(this.ball.getVelY()+ "  ");
		if (b.getRotation() == 0 || b.getRotation() == 360.0) {
			a.setPosition(a.getPosition()[0], b.getPosition()[1]-40); //41 is half the platform width + half the ball width
			if (a.getVelY() > 100) {
				a.setVelY(a.getVelY()*-0.5);
				//System.out.println(Double.toString(a.getVelY()));
			} else {
				a.setVelY(0);
				a.setAccelY(0);
			}
		} else {
			double slope = Math.tan(b.getRotation());
			if (a.getVelY()<0) {
				if ((b.getRotation() > 270 && b.getRotation() < 360) || (b.getRotation() < 180 && b.getRotation() > 90)) {
                                    System.out.print("IF STATEMENT ONE");
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
                                     System.out.print("IF STATEMENT TWO");
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
           //  ball.draw(g);
		super.draw(g);
               
		Graphics2D g2d =  (Graphics2D)g;
               
//		g2d.draw(this.platform.getGlobalHitbox());
//		if(platform != null) platform.draw(g);
//		if(ball != null) ball.draw(g);
	}
	
	public static void main(String[] args) {
		PlatformTester game = new PlatformTester();
		game.start();
	}
	

}
