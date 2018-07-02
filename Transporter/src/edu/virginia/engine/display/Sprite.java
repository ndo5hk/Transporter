package edu.virginia.engine.display;

import java.util.ArrayList;

/**
 * Nothing in this class (yet) because there is nothing specific to a Sprite yet that a DisplayObject
 * doesn't already do. Leaving it here for convenience later. you will see!
 * */
public class Sprite extends DisplayObjectContainer {

//	public Sprite(String id) {
//		super(id);
//		hasPhysics = false;
//	}
	
	public Sprite(String id, boolean phys) {
		super(id, phys);
	}

//	public Sprite(String id, String imageFileName) {
//		super(id, imageFileName);
//		hasPhysics = false;
//	}
	
	public Sprite(String id, String imageFileName, boolean phys) {
		super(id, imageFileName, phys);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
	}
}
