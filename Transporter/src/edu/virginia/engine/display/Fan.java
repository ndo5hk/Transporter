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
import java.awt.Rectangle;
import java.awt.Shape;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class Fan extends Sprite {
        
	int angle;
    boolean swing = true;
        
	public Fan(String id) {
		super(id,"fan.png",false);
        this.setScaleX(.2);  
        this.setScaleY(.2); 
        this.setPivotPoint((int)(this.getUnscaledWidth()*.5), (int)(this.getUnscaledHeight()*.5));
	}
	
	@Override
	public ArrayList<Shape> getGlobalHitbox(){
		ArrayList<Shape> list = new ArrayList<Shape>();
		list.add(getGlobalTransform().createTransformedShape(new Rectangle(100, -700,(int) (getUnscaledWidth()*.25), 700)));
                list.add(getGlobalTransform().createTransformedShape(new Rectangle(250, -700,(int) (getUnscaledWidth()*.5), 700)));
                list.add(getGlobalTransform().createTransformedShape(new Rectangle(530, -700,(int) (getUnscaledWidth()*.25), 700)));
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
}
