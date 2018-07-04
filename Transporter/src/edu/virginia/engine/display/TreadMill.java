package edu.virginia.engine.display;

import java.awt.Rectangle;
import java.awt.Shape;

public class TreadMill extends AnimatedSprite {

	public TreadMill() {
		super("treadmill_", 24, false);
		this.addActionAnimation("run", 0, 23,1);
	}
	
	public Shape getGlobalHitbox(){
		return getGlobalTransform().createTransformedShape(new Rectangle(15, 18, getUnscaledWidth()-30, getUnscaledHeight()-18));
	}
}
