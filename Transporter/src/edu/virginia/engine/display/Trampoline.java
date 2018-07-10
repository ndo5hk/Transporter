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
	
	public void handleCollision(Ball a, String hitbox_id) {
		if (hitbox_id.equals("trampoline_top")) {
			System.out.println("Top");
			ArrayList<Double> vels = super.getElasticCollisionVels(a, this);
			a.setVelX(vels.get(0));
			a.setVelY(vels.get(1));
		} else {
			System.out.println("Bottom");
			ArrayList<Double> vels = super.getElasticCollisionVels(a, this);
			a.setVelX(vels.get(0)*0.2);
			a.setVelY(vels.get(1)*0.2);
		}
	}
	
//	public ArrayList<Shape> getLocalHitbox(){
//		ArrayList<Shape> list = new ArrayList<Shape>();
//		list.add(getLocalTransform().createTransformedShape(new Rectangle(9, 10, getUnscaledWidth()-18, getUnscaledHeight()-60)));
//		return list;
//	}
	
}

