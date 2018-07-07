package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class DisplayObjectContainer extends DisplayObject {
	
	private ArrayList<DisplayObject> objectList;

	public DisplayObjectContainer(String id) {
		super(id);
		this.objectList = new ArrayList<DisplayObject>();
	}
	
	public DisplayObjectContainer(String id, boolean phys) {
		super(id, phys);
		this.objectList = new ArrayList<DisplayObject>();
	}
	
	public DisplayObjectContainer(String id, String imageFileName) {
		super(id, imageFileName);
		this.objectList = new ArrayList<DisplayObject>();
	}
	
	public DisplayObjectContainer(String id, String imageFileName, boolean phys) {
		super(id, imageFileName, phys);
		this.objectList = new ArrayList<DisplayObject>();
	}
	
	public DisplayObjectContainer(String id, DisplayObject obj) {
		super(id);
		this.objectList = new ArrayList<DisplayObject>();
		this.objectList.add(obj);
	}
	
	public DisplayObjectContainer(String id, DisplayObject obj, boolean phys) {
		super(id, phys);
		this.objectList = new ArrayList<DisplayObject>();
		this.objectList.add(obj);
	}
	
	public void addChild(DisplayObject object) {
		object.setParent(this);
		this.objectList.add(object);
	}
	
	public void addChildAtIndex(int index, DisplayObject object) {
		object.setParent(this);
		this.objectList.add(index, object);
	}
	
	public boolean removeChild(DisplayObject object) {
		if (this.objectList.contains(object)) {
			object.setParent(null);
			this.objectList.remove(object);
			return true;
		}
		return false;	
	}
	
	public boolean removeChildByIndex(int index) {
		if (this.objectList.size() <= index+1) {
			this.objectList.get(index).setParent(null);
			this.objectList.remove(index);
			return true;
		}
		return false;	
	}
	
	public boolean removeAll() {
		for (int i=0; i < this.objectList.size(); i++) {
			this.objectList.get(i).setParent(null);
			this.objectList.remove(i);
		}
		return this.objectList.isEmpty();
	}
	
	public boolean hasChild(DisplayObject obj) {
		return this.objectList.contains(obj);
	}
	
	public ArrayList<DisplayObject> getChildren() {
		return this.objectList;
	}
	
	public DisplayObject getChildByIndex(int index) {
		if (this.objectList.size() <= index+1) {
			return this.objectList.get(index);
		}
		return null;
	}
	
	public DisplayObject getChildByID(String id)  {
		for (DisplayObject obj: this.objectList) {
			if (obj.getId() == id) {
				return obj;
			}
		}
		return null;
	}
	
	@Override
	protected void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		for (DisplayObject obj : this.objectList) {
			obj.update(pressedKeys);
		}
	}
	
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		//this.draw(g);
		super.applyTransformations((Graphics2D) g);
		
		try {
			for (DisplayObject obj : this.objectList) {
				obj.draw(g);
			}
		} catch (Exception ConcurrentModificationException) {
			System.out.println("yerrrr");
		}
		//System.out.println(this.getId());
		super.reverseTransformations((Graphics2D) g);
		
	}
}
