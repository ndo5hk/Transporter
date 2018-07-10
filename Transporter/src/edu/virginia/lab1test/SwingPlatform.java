package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import edu.virginia.engine.display.Ball;
//import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.GameClock;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class SwingPlatform extends Sprite {

	/* Create a sprite object for our game. We'll use mario */
	//
    
   // static AnimatedSprite mario = new AnimatedSprite("mario_", 6, false);
	static int points = 50;
	boolean mousePressed = false;
	boolean mouseOverMario = false;
	static GameClock clock = new GameClock();
       // private Sprite sprite;
	int angle;
        boolean swing = true;
        
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public SwingPlatform() {
           super("swingingplatform","swingingplatform.png",false);
		
          //      this.sprite.setScaleX();
          this.setPivotPoint(this.getPosition()[0]+16, this.getPosition()[1]+16);
          this.setPosition(200,200);
                this.angle = 0;
                
	}
    
    public void swing(){
    // System.out.println(this.getRotation());
	if(this.swing){
                if(this.getRotation()<315 ){
                this.setRotation(this.getRotation()+2);
                }
                else this.swing = false;
            }
            if(this.swing==false){
             if(this.getRotation()>225){
                this.setRotation(this.getRotation()-2);
                }else this.swing = true;
            }
    }
    
	public void handleCollision(Ball a) {
//		System.out.println(Double.toString(ball.getVelX()));
		ArrayList<Double> vels = super.getElasticCollisionVels(a, this);
		a.setVelX(vels.get(0)*0.9);
//		if (vels.get(0)*0.8 >= -50 && vels.get(0)*0.8 < 0) {
//			a.setVelX(150);
//		} else if (vels.get(0)*0.8 <= 50 && vels.get(0)*0.8 > 0) {
//			a.setVelX(-150);
//			//System.out.println("got here");
//		}
		a.setVelY(vels.get(1)*0.3);
	}
	

}
