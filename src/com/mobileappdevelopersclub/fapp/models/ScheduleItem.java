

package com.mobileappdevelopersclub.fapp.models;

import org.ektorp.support.CouchDbDocument;



public class ScheduleItem extends CouchDbDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String time;
	private String day;
	private String location;
	private String instructor;
	private String section;
	private String description;
	private String type;
	private String startTime;
	private String endTime;
	
	public ScheduleItem() {
		//Stub
	}
		
	//Constructor for Final schedule item
	public ScheduleItem(String title, String time, String day,
			String location, String instructor, String section) {
		super();
		this.title = title;
		this.time = time;
		this.day = day;
		this.location = location;
		this.instructor = instructor;
		this.section = section;
	}
	
	//constructor for Event schedule item
	public ScheduleItem(String title, String day, String startTime, 
			String endTime, String location, String description, String type) {
		super();
		this.title = title;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.description = description;
		this.type = type;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}
	
	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getInstructor() {
		return instructor;
	}


	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getSection() {
		return section;
	}


	public void setSection(String section) {
		this.section = section;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getDesctiption() {
		return description;
	}


	public void setDesctiption(String desctiption) {
		this.description = desctiption;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
