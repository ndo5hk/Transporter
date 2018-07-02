package edu.virginia.engine.display;

public class Ball extends Sprite {
	
	private int radius;

	public Ball(String id, boolean phys) {
		super(id, "ball.png", phys);
		radius = 25;
		super.setPivotPoint(25, 25);
	}

	public Ball(String id, String imageFileName, boolean phys) {
		super(id, imageFileName, phys);
		radius = super.getUnscaledWidth()/2;
		super.setPivotPoint(radius, radius);
	}
	
	public int getRadius() {
		return radius;
	}

}
