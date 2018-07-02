package edu.virginia.engine.display;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class AnimatedSprite extends Sprite {
	private BufferedImage[] images;
	private HashMap<String,int[]> actionIndices;
	private int current;
	private int arrayEnd;
	private int startIndex;
	private int endIndex;
	private int speed;
	private int counter = 0;

	public AnimatedSprite(String imageFileName, int count, boolean phys) {
		super(imageFileName, imageFileName+"0.png", phys);		
		this.current = 0;
		this.arrayEnd = count-1;
		this.startIndex = 0;
		this.endIndex = arrayEnd;
		this.images = new BufferedImage[count];
		this.actionIndices = new HashMap<String, int[]>();
		
		this.loadImages(imageFileName, count);
	}
	
	private void loadImages(String nameBase, int count) {
		String fullName = "";
		for (int i = 0; i < count; i++) {
			fullName = nameBase + Integer.toString(i) + ".png";
			images[i] = super.readImage(fullName);
		}
	}
	
	public void addActionAnimation(String actionName, int start, int end, int speed) {
		int[] indices = new int[3];
		indices[0] = start;
		indices[1] = end;
		indices[2] = speed;
		this.actionIndices.put(actionName, indices);
	}
	
	public void animate(String action) {
		counter++;
		if (counter < 0) {
			counter = 0;
		}
		int[] indices = null;
		try {
			indices = this.actionIndices.get(action);
		} catch (Exception NullPointerException) {
			System.out.println("Please add \"" + action + "\" to the list of supported animations.");
		}
		
		if (indices != null) {
			startIndex = indices[0];
			endIndex = indices[1];
			speed = indices[2];
		}
		if (counter%speed+1 == 0 || counter%speed+1 == speed) {
			if (current < startIndex || current > endIndex) {
				current = startIndex;
			}
			if (current == endIndex) {
				current = startIndex;
				super.setImage(images[endIndex]);
			} else {
				current++;
				super.setImage(images[current-1]);
			}
		}
	}

}
