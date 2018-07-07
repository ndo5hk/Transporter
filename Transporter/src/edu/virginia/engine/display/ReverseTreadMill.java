package edu.virginia.engine.display;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

public class ReverseTreadMill extends AnimatedSprite {

	public ReverseTreadMill() {
		super("treadmill_reverse_", 24, false);
		this.addActionAnimation("run", 0, 23,1);
	}
	
	@Override
	public ArrayList<Shape> getGlobalHitbox(){
		ArrayList<Shape> list = new ArrayList<Shape>();
		list.add(getGlobalTransform().createTransformedShape(new Rectangle(15, 12, getUnscaledWidth()-30, getUnscaledHeight()-18)));
		return list;
	}
}
