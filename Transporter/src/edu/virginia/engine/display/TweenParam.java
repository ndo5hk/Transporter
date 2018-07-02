package edu.virginia.engine.display;

public class TweenParam {
	
	DisplayObject object;
	double frames;
	double counter;
	TweenableParam param;
	double start;
	double end;
	double increment;
	double cumulated_inc = 0.0;
	boolean started = false;
	

	public TweenParam(DisplayObject o, TweenableParam p, double s, double e, double f) {
		object = o;
		param = p;
		start = s;
		end = e;
		frames = f;
		counter = 0;
		increment = this.getIncrement();
	}
	public void update() {
		if (started == false) {
			if (!this.isComplete()) {
				if (param == TweenableParam.XPOS || param == TweenableParam.YPOS) {
					if ((cumulated_inc >=0.0 && cumulated_inc < 1.0) || (cumulated_inc <=0.0 && cumulated_inc > -1.0)) {
						cumulated_inc+=increment;
					}
					if (param == TweenableParam.XPOS) {
						object.setPosition((int) start, object.getPosition()[1]);
					} else if (param == TweenableParam.YPOS) {
						object.setPosition(object.getPosition()[0], (int) start);
					}
				}
				if (param == TweenableParam.SCALE_X) {
					object.setScaleX(start);
				} else if (param == TweenableParam.SCALE_Y) {
					object.setScaleY(start);
				} else if (param == TweenableParam.ROTATION) {
					object.setRotation(start);
				} else if (param == TweenableParam.ALPHA) {
					object.setAlpha((float) start);
				}
			}
			started = true;
		}
		else if (!this.isComplete()) {
			if (param == TweenableParam.XPOS || param == TweenableParam.YPOS) {
				if ((cumulated_inc >=0.0 && cumulated_inc < 1.0) || (cumulated_inc <=0.0 && cumulated_inc > -1.0)) {
					cumulated_inc+=increment;
				} else if (param == TweenableParam.XPOS) {
					object.setPosition(object.getPosition()[0]+ (int) cumulated_inc, object.getPosition()[1]);
					cumulated_inc = 0.0;
				} else if (param == TweenableParam.YPOS) {
					object.setPosition(object.getPosition()[0], object.getPosition()[1] + (int) cumulated_inc);
					cumulated_inc = 0.0;
				}
			}
			if (param == TweenableParam.SCALE_X) {
				object.setScaleX(object.getScaleX()+increment);
			} else if (param == TweenableParam.SCALE_Y) {
				object.setScaleY(object.getScaleY()+increment);
			} else if (param == TweenableParam.ROTATION) {
				object.setRotation(object.getRotation() + increment);
			} else if (param == TweenableParam.ALPHA) {
				object.setAlpha(object.getAlpha() + (float) increment);
			}
		}
		counter++;
		if (this.isComplete() && param == TweenableParam.ALPHA) {
			object.setAccelX(0.0);
		}
	}
	
	public boolean isComplete() {
		if (counter == frames) {
			return true;
		} return false;
	}
	
	public double getStartVal() {
		return start;
	}
	
	public double getEndVal() {
		return end;
	}
	
	public double getTweenTime() {
		return frames;
	}
	
	private double getIncrement() {
		return (end - start)/frames;
	}
}
