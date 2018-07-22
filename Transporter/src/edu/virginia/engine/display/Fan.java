package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.GameClock;
import edu.virginia.lab1test.SoundManager;

import java.awt.Rectangle;
import java.awt.Shape;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class Fan extends Sprite {
        
	int angle;
    boolean swing = true;
    SoundManager sound_manager;
        
	public Fan(String id) {
		super(id,"fan.png",false);
        this.setScaleX(.2);  
        this.setScaleY(.2); 
        this.setPivotPoint((int)(this.getUnscaledWidth()*.5), (int)(this.getUnscaledHeight()*.5));
        sound_manager = super.getSoundManager();
	}
	
	
	
	@Override
	public ArrayList<Shape> getGlobalHitbox(){
		ArrayList<Shape> list = new ArrayList<Shape>();
		list.add(getGlobalTransform().createTransformedShape(new Rectangle(20, -155,(int) (getUnscaledWidth()*.25), 150)));
		list.add(getGlobalTransform().createTransformedShape(new Rectangle(50, -155,(int) (getUnscaledWidth()*.5), 150)));
		list.add(getGlobalTransform().createTransformedShape(new Rectangle(105, -155,(int) (getUnscaledWidth()*.25), 150)));
		list.add(getGlobalTransform().createTransformedShape(new Rectangle(0, 0, getUnscaledWidth(), getUnscaledHeight())));
		return list;
	}
	
	public double getNormal() {
		if (this.getRotation()>270) {
			return (this.getRotation()+90)%360;
		} else {
			return this.getRotation()+90;
		}
	}
	
	public void handleCollision(Ball a, String hitbox_id) {
        if (hitbox_id.equals("fan_top_middle")) {
		//System.out.println("Top"+(this.ball.getPosition()[1])+" "b.getPosition()[1]);
                //  if(>this.fan.getPosition()[1]){
                //   System.out.println("stuff "+Math.sin(Math.toRadians(b.getNormal())));
               
		a.setVelX(a.getVelX()+200*Math.cos(Math.toRadians(this.getNormal())));
		a.setVelY(a.getVelY()-200*Math.sin(Math.toRadians(this.getNormal())));
                   //
                   //
	} else if (hitbox_id.equals("fan_top_right")) {
//		System.out.println("Top"+(this.ball.getPosition()[1])+" "+this.fan.getPosition()[1]);
                //  if(>this.fan.getPosition()[1]){
//                     System.out.println("stuff "+Math.sin(Math.toRadians(b.getNormal())));
               
		a.setVelX(a.getVelX()+50*Math.cos(Math.toRadians(this.getNormal()-45)));
		a.setVelY(a.getVelY()-50*Math.sin(Math.toRadians(this.getNormal())));//left = -cos, right = cos(normal)
                    
	}
           else if (hitbox_id.equals("fan_top_left")) {
//		System.out.println("Top"+(this.ball.getPosition()[1])+" "+this.fan.getPosition()[1]);
                //  if(>this.fan.getPosition()[1]){
            //       System.out.println("stuff "+Math.sin(Math.toRadians(b.getNormal())));
            //   System.out.print(a.getVelX());
		a.setVelX(a.getVelX()+50*Math.cos(Math.toRadians(this.getNormal()-45)));
		a.setVelY(a.getVelY()-100*Math.sin(Math.toRadians(this.getNormal())));
                    
	} 
            if (hitbox_id.equals("fan_bottom")) {
	//	System.out.println("Bottom");
		ArrayList<Double> vels = super.getElasticCollisionVels(a, this);
		a.setVelX(vels.get(0));
		a.setVelY(vels.get(1));
	}
}
}
