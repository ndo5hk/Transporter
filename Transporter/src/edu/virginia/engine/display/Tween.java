package edu.virginia.engine.display;

import java.util.ArrayList;

public class Tween {
	
	DisplayObject object;
	ArrayList<TweenParam> params;
	//TweenTransitions transition;

	public Tween(DisplayObject o) {
		object = o;
		params = new ArrayList<TweenParam>();
	}
	
	public Tween(DisplayObject o, TweenableParam fieldToAnimate, double	startVal, double endVal, double	frames) {
		object = o;
		params = new ArrayList<TweenParam>();
		this.animate(fieldToAnimate, startVal, endVal, frames);
	}
	
//	public Tween(DisplayObject o, TweenTransitions t) {
//		object = o;
//	}
	
	public void animate(TweenableParam	fieldToAnimate,	double	startVal,	double	endVal,	double	frames) {
		TweenParam p = new TweenParam(object, fieldToAnimate, startVal, endVal, frames);
		params.add(p);
	}
	
	public boolean isComplete() {
		for (TweenParam t : params) {
			if (!t.isComplete()) {
				return false;
			}
		} return true;
	}
	
	public void update() {
		for (TweenParam p : params) {
			if (!p.isComplete()) {
				p.update();
			}
		}
	}
}
