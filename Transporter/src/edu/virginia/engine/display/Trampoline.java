package edu.virginia.engine.display;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

public class Trampoline extends Sprite{

	public Trampoline(String id) {
		super(id, "trampoline.png", false);
	}
	
	
	public ArrayList<Shape> getGlobalHitbox(){
		ArrayList<Shape> list = new ArrayList<Shape>();
		//adding top hitbox as index 0
		list.add(getGlobalTransform().createTransformedShape(new Rectangle(9, 10, getUnscaledWidth()-18, getUnscaledHeight()-60)));
		//adding bottom hitbox as index 1
		list.add(getGlobalTransform().createTransformedShape(new Rectangle(9, 40, getUnscaledWidth()-18, getUnscaledHeight()-70)));
		return list;
	}
	
//	public ArrayList<Shape> getLocalHitbox(){
//		ArrayList<Shape> list = new ArrayList<Shape>();
//		list.add(getLocalTransform().createTransformedShape(new Rectangle(9, 10, getUnscaledWidth()-18, getUnscaledHeight()-60)));
//		return list;
//	}
	
}

