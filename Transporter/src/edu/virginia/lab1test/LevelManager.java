package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.TransporterGame;

public class LevelManager extends TransporterGame {
	private MusicManager music_manager;
	public static SoundManager sound_manager;
	ArrayList<String> completed_levels;
	ArrayList<Level> levels;
	static HashMap<String,Integer> available_items;
	String current_level;
	final static int width = 1000;
	final static int height = 800;
	String currentsong;

	//Screens
	InitializeScreen start;
	StartScreen menu;
	Instructions instructions;
	LevelOne l1;
	LevelTwo l2;
	LevelThree l3;
	LevelFour l4;
	LevelFive l5;
	LevelSix l6;
	GameOver end;


	public LevelManager(String gameId) {
		super(gameId, width, height);
		init();
	}

	@SuppressWarnings("static-access")
	private void init() {
		music_manager = new MusicManager();
		currentsong = "ll";
		music_manager.PlayMusic("l1");
		sound_manager = super.getSoundManager();
		available_items = new HashMap<String,Integer>();
		current_level = "start";
		levels = new ArrayList<Level>();
		available_items.put("platforms", 3);
		available_items.put("trampolines", 1);
		available_items.put("treadmills", 1);
		available_items.put("fans", 0);
		available_items.put("reverseTreadmills", 2);

		start = new InitializeScreen(this.available_items, width, height);
		menu = new StartScreen(this.available_items, width, height);
		instructions = new Instructions(this.available_items, width, height);
		//start = new InitializeScreen();
		l1 = new LevelOne(this.available_items, width, height);
		levels.add((Level)l1);
		l2 = new LevelTwo(this.available_items, width, height);
		levels.add((Level)l2);
		l3 = new LevelThree(this.available_items, width, height);
		levels.add((Level)l3);
		l4 = new LevelFour(this.available_items, width, height);
		levels.add((Level)l4);
		l5 = new LevelFive(this.available_items, width, height);
		levels.add((Level)l5);
		l6 = new LevelSix(this.available_items, width, height);
		levels.add((Level)l6);

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
			if (start.isStarted()) {
				start.setStartSelected(false);
				this.openScreen(menu, "menu");
			} else if (start.instructionsSelected()) {
				start.setInstructionsSelected(false);
				this.openScreen(instructions, "instructions");

			}
		} else if (current_level.equals("menu")) {
			if (!this.hasChild(menu)) {
				this.openScreen(menu, "menu");
			}
			if (menu.getSelectedLevel() != 0) {
				this.openScreen(this.levels.get(menu.getSelectedLevel()-1), "l"+Integer.toString(menu.getSelectedLevel()));
				this.menu.setSelectedLevel(0);

			}
		} 
		else if (current_level.equals("instructions")) {
			if (!this.hasChild(instructions)) {
				this.openScreen(instructions, "instructions");
			} else {
				if(instructions.getExit()==true){ 
					instructions.setExit(false);
					//  System.out.print("STUFF IS HAPPENING?");
					this.openScreen(start, "start");

				}
			}
		} 
		else if (current_level.equals("end")) {
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
							//System.out.println("got here");
							this.openScreen(menu, "menu");

						}
						if(temp_current.getExit()==true){ 
							temp_current.setExit(false);
							//  System.out.print("STUFF IS HAPPENING?");
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
			if(id!=current_level){
				//      System.out.print("CHANGING");


				if(id.equals("start")||id.equals("instructions")||id.equals("menu")){
					if(this.music_manager.playing==false)  this.music_manager.PlayMusic("l1");

				}else{
					if(this.music_manager.playing==true) this.music_manager.stopmusic();

					if(id.equals("l1")){
						this.music_manager.PlayMusic("l1");
						currentsong = "l1";
					}
					if(id.equals("l2")){
						this.music_manager.PlayMusic("l2");
						currentsong = "l2";
					}
					if(id.equals("l3")){
						this.music_manager.PlayMusic("l3");
						currentsong = "l3";
					}
					if(id.equals("l4")){
						this.music_manager.PlayMusic("l4");
						currentsong = "l4";
					}
					if(id.equals("l5")){
						this.music_manager.PlayMusic("l5");
						currentsong = "l5";
					}
					if(id.equals("l6")){
						this.music_manager.PlayMusic("l2");
						currentsong = "l2";
					}
				}
			}
			// if(&&this.music_manager.playing==true){
			//  this.music_manager.stopmusic();
			//  }

			//                if(id != this.currentsong){
			//                    System.out.println("New music"+id);
			//                    this.currentsong = id;
			//                this.music_manager.PlayMusic(id);
			// this.music_manager.stopmusic();

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
