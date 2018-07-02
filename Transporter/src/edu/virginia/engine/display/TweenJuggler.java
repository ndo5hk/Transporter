package edu.virginia.engine.display;

import java.util.ArrayList;

public class TweenJuggler {
	
	ArrayList<Tween> tweens;
	public static TweenJuggler juggler;
	
	public TweenJuggler() throws Exception {
		tweens = new ArrayList<Tween>();
		if (juggler != null) {
			throw new Exception();
		}
	}
	
	public static TweenJuggler getInstance() throws Exception {
		if (juggler == null) {
			juggler = new TweenJuggler();
		}
		return juggler;
	}
	
	public void add(Tween t) {
		tweens.add(t);
	}
	
	public void nextFrame() {
		ArrayList<Tween> toRemove = new ArrayList<Tween>();
		if (tweens != null || tweens.size()>0) {
			for (Tween t : tweens) {
				if (!t.isComplete()) {
					t.update();
				} else {
					toRemove.add(t);
				}
			}
			if (toRemove.size() > 0) {
				for (Tween t : toRemove) {
					tweens.remove(t);
				}
			}
		}
	}
	public boolean isEmpty() {
		if (tweens.size() == 0) {
			return true;
		} return false;
	}
}
