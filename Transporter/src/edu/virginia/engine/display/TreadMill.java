package edu.virginia.engine.display;

public class TreadMill extends AnimatedSprite {

	public TreadMill() {
		super("treadmill_", 24, false);
		this.addActionAnimation("run", 0, 23,1);
	}

}
