package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;

public class TweenEvent extends Event {
	
	private Tween tween;

	public TweenEvent(String eventName, Tween t) {
		super(eventName);
		tween = t;
	}
	
	public Tween getTween() {
		return this.tween;
	}

}
