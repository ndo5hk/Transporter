package edu.virginia.engine.display;

import java.awt.Rectangle;
import java.awt.Shape;

public class ReverseTreadMill extends AnimatedSprite {

	public ReverseTreadMill() {
		super("treadmill_reverse_", 24, false);
		this.addActionAnimation("run", 0, 23,1);
	}
	
	public Shape getGlobalHitbox(){
		return getGlobalTransform().createTransformedShape(new Rectangle(15, 18, getUnscaledWidth()-30, getUnscaledHeight()-18));
	}
}
