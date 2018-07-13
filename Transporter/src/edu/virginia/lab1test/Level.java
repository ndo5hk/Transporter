package edu.virginia.lab1test;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.TransporterGame;

public class Level extends DisplayObjectContainer implements MouseListener {

	SoundManager sound_manager;
	static HashMap<String, Integer> items;
	boolean completed = false;
	int height;
	int width;
	boolean exitbool;
	String state = "";

	public Level(String gameId, int w, int h, HashMap<String, Integer> map) {
		super(gameId);
		init(map, w, h);
	}
	public void setExit(boolean what){
		this.exitbool = what;
	}
	public boolean getExit(){
		return exitbool;
	}
	
	public String getState(){
		return state;
	}
	
	public void setState(String s) {
		this.state = s;
	}
	
	public boolean isComplete() {
		return this.completed;
	}

	public void setCompleted(boolean b) {
		this.completed = b;
	}

	private void init(HashMap<String, Integer> map, int w, int h) {
		sound_manager = super.getSoundManager();
		items = map;
		height = h;
		width = w;
	}

	public static HashMap<String, Integer> getAvailableItems() {
		return items;
	}

	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}

	protected void removeObject(String type, DisplayObject obj) {
		//		this.removeChild(obj);
		if (type.equalsIgnoreCase("platform")) {
			items.put("platforms", items.get("platforms")+1);
		} else if (type.equalsIgnoreCase("treadmill")) {
			items.put(type+"s", items.get(type+"s")+1);
		} else if (type.equalsIgnoreCase("reverseTreadmill")) {
			items.put(type+"s", items.get(type+"s")+1);
		} else if (type.equalsIgnoreCase("fan")) {
			items.put(type+"s", items.get(type+"s")+1);
		}  else if (type.equalsIgnoreCase("trampoline")) {
			items.put(type+"s", items.get(type+"s")+1);
		} else {
			System.out.println("No object "+type+" is exists.");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
