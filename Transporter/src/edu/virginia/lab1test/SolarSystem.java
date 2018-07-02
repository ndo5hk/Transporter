package edu.virginia.lab1test;

import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;


public class SolarSystem extends Game {
	Sprite sun = new Sprite("sun", "sun.png", false); //150x150
	Sprite sun_dummy = new Sprite("sun_dummy", "dummy_pixel.png",false); //150x150
	Sprite sun_dummy1 = new Sprite("sun_dummy1", "dummy_pixel.png",false); //150x150
	Sprite sun_dummy2 = new Sprite("sun_dummy2", "dummy_pixel.png",false); //150x150
	Sprite earth = new Sprite("earth", "earth.png",false);//100x100
	Sprite jupiter = new Sprite("jupiter", "jupiter.png",false);//100x100
	Sprite mars = new Sprite("mars", "mars.png",false);//50x50
	Sprite moon_earth = new Sprite("moon_earth", "moon.png",false);//100x100
	Sprite moon_jupiter = new Sprite("moon_jupiter", "moon.png",false);//100x100
	//Sprite earth = new Sprite("earth", "earth.png");//100x100
	
	public SolarSystem() {
		super("Solar System", 1300, 800);
		
		this.sun.setPosition(600,400);
		this.sun.setPivotPoint(sun.getUnscaledWidth()/2,sun.getUnscaledHeight()/2);
		//this.sun_dummy.setPosition(600-75, 400-75);
		this.sun_dummy.setPivotPoint(sun_dummy.getUnscaledWidth()/2,sun_dummy.getUnscaledHeight()/2);
		this.sun_dummy1.setPivotPoint(sun_dummy1.getUnscaledWidth()/2,sun_dummy1.getUnscaledHeight()/2);
		this.sun_dummy2.setPivotPoint(sun_dummy2.getUnscaledWidth()/2,sun_dummy2.getUnscaledHeight()/2);

		this.earth.setPosition(0, 200);
		this.earth.setPivotPoint(earth.getUnscaledWidth()/2,earth.getUnscaledHeight()/2);
		this.mars.setPosition(0, 100);
		this.jupiter.setPosition(0, 300);
		this.jupiter.setPivotPoint(jupiter.getUnscaledWidth()/2,jupiter.getUnscaledHeight()/2);
		this.moon_earth.setPosition(0, 50);
		this.moon_jupiter.setPosition(0, 75);
		//this.earth.setPivotPoint(earth.getUnscaledHeight()/2, earth.getUnscaledWidth()/2);
//		this.moon_earth.setPosition(-moon_earth.getUnscaledHeight()/2, 150- moon_earth.getUnscaledHeight()/2);
		
		super.addChild(sun);
		sun.addChild(sun_dummy);
		sun.addChild(sun_dummy1);
		sun.addChild(sun_dummy2);
		sun_dummy.addChild(earth);
		sun_dummy1.addChild(jupiter);
		sun_dummy2.addChild(mars);
		earth.addChild(moon_earth);
		jupiter.addChild(moon_jupiter);
		
		//this.fireball.addChild(mario);
	}

//	public SolarSystem(String gameId, int width, int height) {
//		super(gameId, width, height);
//		this.mario.setPosition(200, 200);
//		this.mario_1.setPosition(300, 200);
//		this.mario.addChild(mario_1);
//	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		
		if(pressedKeys.contains(65)){
			
			double offset = (Math.cos(sun_dummy.getRotation()) + 1)*100;
			double offset1 = (Math.cos(sun_dummy1.getRotation()) + 1)*50;
			double offset2 = (Math.cos(sun_dummy2.getRotation()) + 1)*200;
			
			sun_dummy.setPosition(sun_dummy.getPosition()[0], (int)offset- 200);
			sun_dummy1.setPosition(sun_dummy1.getPosition()[0], (int)offset1- 300);
			sun_dummy2.setPosition(sun_dummy2.getPosition()[0], (int)offset2- 100);
      //a
			sun_dummy.setRotation(sun_dummy.getRotation() - 0.1);
			sun_dummy1.setRotation(sun_dummy1.getRotation() - 0.05);
			sun_dummy2.setRotation(sun_dummy2.getRotation() - 0.2);
			earth.setRotation(earth.getRotation() - 0.2);
			jupiter.setRotation(jupiter.getRotation() - 0.05);
      }
      
      if(pressedKeys.contains(83)){
    	  double offset = (Math.cos(sun_dummy.getRotation()) + 1)*100;
    	  double offset1 = (Math.cos(sun_dummy1.getRotation()) + 1)*50;
    	  double offset2 = (Math.cos(sun_dummy2.getRotation()) + 1)*200;

    	  sun_dummy.setPosition(sun_dummy.getPosition()[0], (int)offset- 200);
    	  sun_dummy1.setPosition(sun_dummy1.getPosition()[0], (int)offset1- 300);
    	  sun_dummy2.setPosition(sun_dummy2.getPosition()[0], (int)offset2- 100);
    	  
      //s
    	  sun_dummy.setRotation(sun_dummy.getRotation() + 0.1);
    	  sun_dummy1.setRotation(sun_dummy1.getRotation() + 0.05);
    	  sun_dummy2.setRotation(sun_dummy2.getRotation() + 0.2);
    	  earth.setRotation(earth.getRotation() + 0.2);
    	  jupiter.setRotation(jupiter.getRotation() + 0.05);
      }
      
      if (pressedKeys.contains(37)) {
    	  //left
    	  sun.setPosition(sun.getPosition()[0] +2, sun.getPosition()[1]);
 
      }
      if (pressedKeys.contains(39)) {
    	  //left
    	  sun.setPosition(sun.getPosition()[0] -2, sun.getPosition()[1]);
 
      }
      if (pressedKeys.contains(38)) {
    	  //left
    	  sun.setPosition(sun.getPosition()[0], sun.getPosition()[1]+2);
 
      }
      if (pressedKeys.contains(40)) {
    	  //left
    	  sun.setPosition(sun.getPosition()[0], sun.getPosition()[1]-2);
 
      }
      if(pressedKeys.contains(81)){
    	  //q
    	  if (sun.getScaleX() > 0.1f) {
    		  sun.setScaleX(sun.getScaleX()-0.1);
    	  }
    	  if (sun.getScaleY() > 0.1f) {
    		  sun.setScaleY(sun.getScaleY()-0.1);
    	  }
  	
      }
      if(pressedKeys.contains(87)){
    	  //w
    	  sun.setScaleX(sun.getScaleX()+0.1);
    	  sun.setScaleY(sun.getScaleY()+0.1);
      }
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);

	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		SolarSystem game = new SolarSystem();
		game.start();
	}

}

