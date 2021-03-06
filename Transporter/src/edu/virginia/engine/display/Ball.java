package edu.virginia.engine.display;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Ball extends Sprite {
	
	private int radius;
	private int diameter;

	public Ball(String id) {
		super(id, "ball.png", true);
		radius = 25;
		diameter = 50;
		super.setPivotPoint(25, 25);
	}

	public Ball(String id, String imageFileName) {
		super(id, imageFileName, true);
		radius = super.getUnscaledWidth()/2;
		diameter = super.getUnscaledHeight();
		super.setPivotPoint(radius, radius);
	}

	
	public int getRadius() {
		return radius;
	}
	
	@Override
	public ArrayList<Shape> getGlobalHitbox(){
	AffineTransform at = this.getGlobalTransform();
	Ellipse2D ball = new Ellipse2D.Double(at.getTranslateX(),at.getTranslateY(), diameter,diameter);
	ArrayList<Shape> list = new ArrayList<Shape>();
	list.add(ball);
	return list;
	}
}


