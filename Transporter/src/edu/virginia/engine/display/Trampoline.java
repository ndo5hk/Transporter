package edu.virginia.engine.display;

import java.awt.Rectangle;
import java.awt.Shape;

public class Trampoline extends Sprite{

	public Trampoline(String id) {
		super(id, "trampoline.png", false);
	}
	
	public Shape getGlobalHitbox(){
		return getGlobalTransform().createTransformedShape(new Rectangle(9, 10, getUnscaledWidth()-18, getUnscaledHeight()-60));
	}
}

