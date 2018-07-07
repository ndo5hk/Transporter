package edu.virginia.engine.display;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.virginia.engine.events.EventDispatcher;
import edu.virginia.engine.util.GameClock;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject extends EventDispatcher {
ArrayList<Shape> hitboxes;
	/* All DisplayObject have a unique id */
	private String id;
	private boolean visible = true;
	protected int[] position;
	protected int[] pivotPoint;
	protected double scaleX;
	protected double scaleY;
	protected double rotation;
	private float alpha;
	int frameCounter = 0;
	int frameCounter2 = 0;
	DisplayObject parent;
//	private int frameCounter;
	/* The image that is displayed by this object */
	private BufferedImage displayImage;
	
	//physics
	GameClock clock = new GameClock();
	boolean hasPhysics = false;
	private double accelX = 0;
	private double accelY = 0;
	private double max_velocity = 2;
	private double gravity = 1200;
	private double velX = 0;
	private double velY = 0;
	private double deltaT = 0;
	private double previousT = 0;
	private double currentT = 0;
	

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObject(String id) {
                        this.hitboxes= new ArrayList();

		this.setId(id);
		visible = true;
		position = new int[2];
		position[0] = 0;
		position[1] = 0;
		pivotPoint = new int[2];
		pivotPoint[0] = 0;
		pivotPoint[1] = 0;
		scaleX = (float) 1.0;
		scaleY = (float) 1.0;
		rotation = 0.0;
		alpha = 1.0f;
		parent = null;
	}
	
	public DisplayObject(String id, boolean phys) {
                        this.hitboxes= new ArrayList();

		this.setId(id);
		visible = true;
		position = new int[2];
		position[0] = 0;
		position[1] = 0;
		pivotPoint = new int[2];
		pivotPoint[0] = 0;
		pivotPoint[1] = 0;
		scaleX = (float) 1.0;
		scaleY = (float) 1.0;
		rotation = 0.0;
		alpha = 1.0f;
		parent = null;
		hasPhysics = phys;
	}

	public DisplayObject(String id, String fileName) {
                        this.hitboxes= new ArrayList();

		this.setId(id);
		this.setImage(fileName);
		visible = true;
		position = new int[2];
		position[0] = 0;
		position[1] = 0;
		pivotPoint = new int[2];
		pivotPoint[0] = 0;
		pivotPoint[1] = 0;
		scaleX = (float) 1.0;
		scaleY = (float) 1.0;
		rotation = 0.0;
		alpha = 1.0f;
		parent = null;
	}
	
	public DisplayObject(String id, String fileName, boolean phys) {
                        this.hitboxes= new ArrayList();

		this.setId(id);
		this.setImage(fileName);
		visible = true;
		position = new int[2];
		position[0] = 0;
		position[1] = 0;
		pivotPoint = new int[2];
		pivotPoint[0] = 0;
		pivotPoint[1] = 0;
		scaleX = (float) 1.0;
		scaleY = (float) 1.0;
		rotation = 0.0;
		alpha = 1.0f;
		parent = null;
		hasPhysics = phys;
	}
	
	public void setParent(DisplayObject parent) {
		this.parent = parent;
	}
	
	public DisplayObject getParent() {
		return this.parent;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int x, int y) {
		this.position[0] = x;
		this.position[1] = y;
	}

	public int[] getPivotPoint() {
		return pivotPoint;
	}

	public void setPivotPoint(int x, int y) {
		this.pivotPoint[0] = x;
		this.pivotPoint[1] = y;
	}

	public double getScaleX() {
		return scaleX;
	}

	public void setScaleX(double d) {
		this.scaleX = d;
	}

	public double getScaleY() {
		return scaleY;
	}

	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}

	public double getRotation() {
		return 360 - rotation%360;
	}

	public void setRotation(double rotation) {
		this.rotation = 360 - rotation%360;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float d) {
		if (d >0.0) {
			this.alpha = d;
			this.visible = true;
		} else {
			this.alpha = (float) 0.1;
			this.setVisible(false);
		}
		if (d >1.0) {
			this.alpha = (float) 1.0;
			this.visible = true;
		} 
	}
public void setPhysics(boolean stuff){
this.hasPhysics=stuff;
 this.setAccelY(0);
}
public boolean getPhysics(){
return this.hasPhysics;
}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void setAccelX(double a) {
		this.accelX = a;
	}
	
	public void setAccelY(double a) {
		this.accelY = a;
	}
	
	public double getAccelX() {
		return this.accelX;
	}
	
	public double getAccelY() {
		return this.accelY;
	}
	
	public double getVelX() {
		return this.velX;
	}
	
	public double getVelY() {
		return this.velY;
	}
	
	public void setVelX(double v) {
		this.velX = v;
	}
	
	public void setVelY(double v) {
		this.velY = v;
	}


	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}


	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}


	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * 
	 * */
	protected void update(ArrayList<Integer> pressedKeys) {
		this.setDeltaT();
		this.applyGravity();
		this.setVelocity();
		this.setDisplacement();
	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {
		
		if (displayImage != null) {
			
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);
			//g2d.fillRect(this.pivotPoint[0], this.pivotPoint[1], 3, 3);

			/* Actually draw the image, perform the pivot point translation here */
			if (visible) {
				g2d.drawImage(displayImage, (int)pivotPoint[0]*-1, (int)pivotPoint[1]*-1,
						(int) (getUnscaledWidth()),
						(int) (getUnscaledHeight()), null);
			}
			
			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);
		}
	}
	
	//Physics
	protected void setDeltaT() {
		currentT = clock.getElapsedTime()/1000;
		deltaT = this.currentT - this.previousT;
		this.previousT = this.currentT;
	}
	
	protected void applyGravity() {
		if (this.hasPhysics) {
			if (this.accelY <= 0) {
				this.accelY += gravity;
			} else {
				this.accelY = gravity;
			}
		}
	}
	
	protected void setVelocity() {
		velX += this.accelX*this.deltaT;
		velY += this.accelY*this.deltaT;
	}
	
	protected void setDisplacement() {
		this.position[0] += velX*deltaT;
		this.position[1] += velY*deltaT;
	}
	
	//general, should work for any Platform
	public boolean collidesWith(DisplayObject other){
		Area a = new Area(this.getGlobalHitbox().get(0));
		Area b = new Area(other.getGlobalHitbox().get(0));
		a.intersect(b);
		if( !a.isEmpty()){
            return true;
		}
		return false;
	}
	
//	public boolean collidesWith(Platform other){
//		boolean check = false;
//		for(Shape x: other.getLocalHitbox()){
//			Area a = new Area(x);
//			a.intersect(new Area(x));
//			if( !a.isEmpty()){
//                check = true;
//                break;
//			}
//		}
//		return check;
//	}
	
	public String collidesWith(Trampoline other){
		Area a = new Area(this.getGlobalHitbox().get(0));
		for(int i=0; i<other.getGlobalHitbox().size(); i++){
			Area b = new Area(other.getGlobalHitbox().get(i));
			//checking for top hitbox
			if (i == 0) {
				a.intersect(b);
				if( !a.isEmpty()){
	                return "trampoline_top";
				} else {
					a = new Area(this.getGlobalHitbox().get(0));
				}
			} else if (i == 1) {
				a.intersect(b);
				if( !a.isEmpty()){
	                return "trampoline_bottom";
				}
			}
		}
		return null;
	}

//	public boolean collidesWith(TreadMill other){
//		boolean check = false;
//		for(Shape x: other.getLocalHitbox()){
//			Area a = new Area(x);
//			a.intersect(new Area(x));
//			if( !a.isEmpty()){
//                check = true;
//                break;
//			}
//		}
//		return check;
//	}
	
//	public boolean collidesWith(ReverseTreadMill other){
//		boolean check = false;
//		for(Shape x: other.getLocalHitbox()){
//			Area a = new Area(x);
//			a.intersect(new Area(x));
//			if( !a.isEmpty()){
//                check = true;
//                break;
//			}
//		}
//		return check;
//	}
	
	public String collidesWith(Fan other){
		Area a = new Area(this.getGlobalHitbox().get(0));
		for(int i=0; i<other.getGlobalHitbox().size(); i++){
			Area b = new Area(other.getGlobalHitbox().get(i));
			//checking for top hitbox
			if (i == 0) {
				a.intersect(b);
				if( !a.isEmpty()){
	                return "fan_top";
				} else {
					a = new Area(this.getGlobalHitbox().get(0));
				}
			} else if (i == 1) {
				a.intersect(b);
				if( !a.isEmpty()){
	                return "fan_bottom";
				}
			}
		}
		return null;
	}
	public ArrayList<Shape> getGlobalHitbox(){
            ArrayList<Shape> list = new ArrayList<>();
            list.add(getGlobalTransform().createTransformedShape(new Rectangle(0,0,getUnscaledWidth(),getUnscaledHeight())));
            return list;
        }
       
	//public Shape getGlobalHitbox(){
	//return getGlobalTransform().createTransformedShape(new Rectangle(0, 0, getUnscaledWidth(), getUnscaledHeight()));
//}
	
	protected AffineTransform getGlobalTransform(){
		AffineTransform at;
		if(this.parent == null) at = new AffineTransform();
		else at = this.getParent().getGlobalTransform();
		at.concatenate(getLocalTransform());
		return at;
	}
		
	protected AffineTransform getLocalTransform(){
		AffineTransform at = new AffineTransform();
		at.translate(this.position[0], this.position[1]);
		at.rotate(Math.toRadians(this.rotation));
		at.scale(this.scaleX, this.scaleY);
		at.translate(-this.pivotPoint[0], -this.pivotPoint[1]);
		return at;
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		g2d.translate(position[0], position[1]);
		g2d.rotate(Math.toRadians(rotation));
		//g2d.rotate(rotation, pivotPoint[0], pivotPoint[1]);
		g2d.scale((double) scaleX, (double) scaleY);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		g2d.scale(1/this.scaleX, 1/this.scaleY); //FIX THIS LATER
		g2d.rotate(Math.toRadians(rotation*-1));
		g2d.translate(position[0]*-1, position[1]*-1);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	}

}
