package edu.virginia.engine.display;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Ball extends Sprite {
	
	private int radius;
	private int diameter;

	public Ball(String id, boolean phys) {
		super(id, "ball.png", phys);
		radius = 25;
		diameter = 50;
		super.setPivotPoint(25, 25);
	}

	public Ball(String id, String imageFileName, boolean phys) {
		super(id, imageFileName, phys);
		radius = super.getUnscaledWidth()/2;
		diameter = super.getUnscaledHeight();
		super.setPivotPoint(radius, radius);
	}

	
	public int getRadius() {
		return radius;
	}
	
	@Override
	public Ellipse2D getGlobalHitbox(){
	AffineTransform at = this.getGlobalTransform();
	Ellipse2D ball = new Ellipse2D.Double(at.getTranslateX(),at.getTranslateY(), diameter,diameter);
	return ball;
	}
}


