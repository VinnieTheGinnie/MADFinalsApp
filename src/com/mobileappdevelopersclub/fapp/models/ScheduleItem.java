

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
	private String desctiption;
	
	public ScheduleItem() {
		//Stub
	}
		
	
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


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDay() {
		return day;
	}


	public void setDate(String day) {
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
		return desctiption;
	}


	public void setDesctiption(String desctiption) {
		this.desctiption = desctiption;
	}
	
	
	
}
