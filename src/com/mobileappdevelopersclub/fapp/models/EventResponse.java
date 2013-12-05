package com.mobileappdevelopersclub.fapp.models;

import java.util.List;

public class EventResponse {

	private List<Event> events;
	
	public EventResponse(List<Event> events) {
		super();
		this.events = events;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
}	
