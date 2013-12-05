package com.mobileappdevelopersclub.fapp.models;

public class Event extends ScheduleItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Event(String title, String day, String startTime, String endTime,
				String location, String description, String type) {
		super(title, day, startTime, endTime, location, description, type);
	}
	
	

}
