package edu.virginia.engine.display;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

public class TreadMill extends AnimatedSprite {

	public TreadMill() {
		super("treadmill_", 24, false);
		this.addActionAnimation("run", 0, 23,1);
	}
	
	@Override
	public ArrayList<Shape> getGlobalHitbox(){
		ArrayList<Shape> list = new ArrayList<Shape>();
		list.add(getGlobalTransform().createTransformedShape(new Rectangle(15, 12, getUnscaledWidth()-30, getUnscaledHeight()-18)));
		return list;
	}
	public void handleCollision(Ball a) {
		ArrayList<Double> vels = super.getElasticCollisionVels(a, this);
		if (a.getVelY()<=0) {
			a.setVelX(vels.get(0)*0.1);
			a.setVelY(vels.get(1)*0.1);
		} else {
			a.setVelX(vels.get(0)*0.1+200);
			a.setVelY(vels.get(1)*0.1);
		}
	}
}
