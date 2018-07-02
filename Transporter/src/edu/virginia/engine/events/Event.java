package edu.virginia.engine.events;

public class Event {
	
	String eventType;

	public Event(String eventName) {
		eventType = eventName;
	}
	
	public void setEventType(String eventName) {
		eventType = eventName;
	}
	
	public String getEventType() {
		return eventType;
	}
}
