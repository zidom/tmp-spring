package org.demo;

import reactor.event.Event;

// Use a POJO as an event handler
class Service {
	public <T> void handleEvent(Event<T> ev) {
		// handle the event data
	}
}