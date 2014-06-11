package org.demo;

import static reactor.event.selector.Selectors.$;

import javax.inject.Inject;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.event.Event;
import reactor.function.Consumer;

public class Main {
	
	@Inject
	Service service;

	public static void main(String[] args) {
		Environment env = new Environment();

		// This factory call creates a Reactor.
		Reactor reactor = Reactors.reactor().env(env) // our current Environment
				.dispatcher(Environment.EVENT_LOOP) // use one of the
													// BlockingQueueDispatchers
				.get(); // get the object when finished configuring

		// Register a consumer to handle events sent with a key that matches
		// "parse"
		reactor.on($("parse"), new Consumer<Event<String>>() {
			@Override
			public void accept(Event<String> ev) {
				System.out.println("Received event with data: " + ev.getData());
			}
		});

		// Send an event to this Reactor and trigger all actions that match the
		// given key
		reactor.notify("parse", Event.wrap("data"));

	}

}
