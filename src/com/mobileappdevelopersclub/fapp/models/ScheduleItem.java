package com.mobileappdevelopersclub.fapp.models;

import org.ektorp.support.CouchDbDocument;

import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.util.ScheduleItemUtil;


public class ScheduleItem extends CouchDbDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int EIGHT_AM  = 800;
	private final int START_FLAG = 0;
	private final int END_FLAG = 1;
	
	
	private String title;
	private String timeStart;
	private String time;
	private String timeEnd;
	private String day;
	private String location;
	private String instructor;
	private String section;
	
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
		this.timeStart = ScheduleItemUtil.parseTime(START_FLAG, time);
		this.timeEnd = ScheduleItemUtil.parseTime(END_FLAG, time);
		
		
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getTimeStart() {
		return timeStart;
	}


	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}


	public String getTimeEnd() {
		return timeEnd;
	}


	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
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
	
}
