package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.TransporterGame;

public class LevelManager extends TransporterGame {
	
	public static SoundManager sound_manager;
	ArrayList<String> completed_levels;
	ArrayList<Level> levels;
	static HashMap<String,Integer> available_items;
	String current_level;
	final static int width = 1000;
	final static int height = 800;
	
	//Screens
	InitializeScreen start;
	StartScreen menu;
	LevelOne l1;
	LevelTwo l2;
	LevelThree l3;
	LevelFour l4;
//	LevelFive l5;
//	LevelSix l6;
	GameOver end;
	
	

	public LevelManager(String gameId) {
		super(gameId, width, height);
		init();
	}
	
	@SuppressWarnings("static-access")
	private void init() {
		sound_manager = super.getSoundManager();
		available_items = new HashMap<String,Integer>();
		available_items.put("platforms", 0);
		current_level = "l1";
		levels = new ArrayList<Level>();
//		available_items.put("trampolines", 0);
//		available_items.put("treadmills", 0);
//		available_items.put("fans", 0);
//		available_items.put("reverseTreadmills", 0);
		
		start = new InitializeScreen(this.available_items, width, height);
		menu = new StartScreen(this.available_items, width, height);
		//start = new InitializeScreen();
		l1 = new LevelOne(this.available_items, width, height);
		levels.add((Level)l1);
		l2 = new LevelTwo(this.available_items, width, height);
		levels.add((Level)l2);
		l3 = new LevelThree(this.available_items, width, height);
		levels.add((Level)l3);
		l4 = new LevelFour(this.available_items, width, height);
		levels.add((Level)l4);
//		l5 = new LevelFive(this.available_items, width, height);
//		levels.add((Level)l5);
//		l6 = new LevelSix(this.available_items, width, height);
//		levels.add((Level)l6);
		//start.start();
		this.removeAll();
		
	}
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
		
		if (current_level.equals("start")) {
			if (!this.hasChild(start)) {
				this.openScreen(start, "start");
			}
		} else if (current_level.equals("menu")) {
			if (!this.hasChild(menu)) {
				this.openScreen(menu, "menu");
			}
		} else if (current_level.equals("end")) {
			if (!this.hasChild(end)) {
				this.openScreen(end, "end");
			}
		} else {
			for (int i=1; i<=levels.size(); i++) {
				if (current_level.equalsIgnoreCase("l"+Integer.toString(i))) {
					Level temp_current = levels.get(i-1);
					if (!this.hasChild(temp_current)) {
						this.openScreen(temp_current, "l"+Integer.toString(i));
					} else {
						if (temp_current.isComplete()) {
							System.out.println("got here");
							this.openScreen(menu, "menu");
						}
					}
				}
			}
		}
	}
		
		
//		else if (LevelManager.current_level.equals("l1")) {
//			if (!this.hasChild(l1)) {
//				this.removeAll();
//;				this.addChild(l1);
//			}
//		} else if (LevelManager.current_level.equals("l2")) {
//			if (!this.hasChild(l2)) {
//				this.removeAll();
//;				this.addChild(l2);
//			}
//		} else if (LevelManager.current_level.equals("l3")) {
//			if (!this.hasChild(l3)) {
//				this.removeAll();
//;				this.addChild(l3);
//			}
//		} else if (LevelManager.current_level.equals("l4")) {
//			if (!this.hasChild(l4)) {
//				this.removeAll();
//;				this.addChild(l4);
//			}
//		}
	
//		} else if (LevelManager.current_level.equals("l5")) {
//			if (!this.hasChild(l5)) {
//				this.removeAll();
//;				this.addChild(l5);
//			}
//		} else if (LevelManager.current_level.equals("l6")) {
//			if (!this.hasChild(l6)) {
//				this.removeAll();
//;				this.addChild(l6);
//			}
//		}
	
	private void openScreen(DisplayObject obj, String id) {
		current_level = id;
		this.removeAll();
		this.addChild(obj);
		for (MouseListener l : super.getMainFrame().getMouseListeners()) {
			super.getMainFrame().removeMouseListener(l);
		}
		super.getMainFrame().addMouseListener((MouseListener) obj);
	}
	
	public static void main(String[] args) {
		LevelManager man = new LevelManager("game");
		man.start();
	}

}
