package edu.virginia.engine.display;

import java.util.ArrayList;

public class TransporterGame extends Game {
	
	protected Ball ball;

	public TransporterGame(String gameId, int width, int height) {
		super(gameId, width, height);
		init();
	}
	
	private void init() {
		ball = new Ball("ball", true);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
	}
	
	

}
