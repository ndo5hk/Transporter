package edu.virginia.engine.display;

public class Coin extends Sprite {
	
	boolean collected = false;

	public Coin(String id, boolean phys) {
		super(id, "coin.png", phys);
	}
	
	public void collect() {
		collected = true;
	}
	
	public boolean isCollected() {
		return collected;
	}

//	public Coin(String id, String imageFileName) {
//		super(id, imageFileName);
//		
//	}

}
