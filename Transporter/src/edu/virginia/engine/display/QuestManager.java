package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class QuestManager implements IEventListener {

	public QuestManager() {
		
	}

	@Override
	public void handleEvent(Event event) {
		System.out.println("Quest Manager handled event of type: " + event.getEventType());
		System.out.println("Quest Complete!");
	}

}
