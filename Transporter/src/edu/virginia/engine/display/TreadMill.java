package edu.virginia.engine.display;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import edu.virginia.lab1test.SoundManager;

public class TreadMill extends AnimatedSprite {
	
	private SoundManager sound_manager;

	public TreadMill() {
		super("treadmill_", 24, false);
		this.addActionAnimation("run", 0, 23,1);
		sound_manager = super.getSoundManager();
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
