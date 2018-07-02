package edu.virginia.engine.events;

import java.util.ArrayList;
import java.util.HashMap;

public class EventDispatcher implements IEventDispatcher {
	
	HashMap<String,ArrayList<IEventListener>> listeners;

	public EventDispatcher() {
		listeners = new HashMap<String, ArrayList<IEventListener>>(); 
	}
	
	@Override
	public void addEventListener(IEventListener listener, String eventType) {
		if (listeners.containsKey(eventType)) {
			if (!listeners.get(eventType).contains(listener)) {
				listeners.get(eventType).add(listener);
			}
		} else {
			ArrayList<IEventListener> list = new ArrayList<IEventListener>();
			list.add(listener);
			listeners.put(eventType,list);
		}
	}

	@Override
	public void removeEventListener(IEventListener listener, String eventType) {
		if (listeners.containsKey(eventType)) {
			if (listeners.get(eventType).contains(listener)) {
				listeners.get(eventType).remove(listener);
			}
		}
	}

	@Override
	public void dispatchEvent(Event event) {
		try {
			for (IEventListener l : listeners.get(event.getEventType())) {
				l.handleEvent(event);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean hasEventListener(IEventListener listener, String eventType) {
		return listeners.containsKey(eventType);
	}

}
