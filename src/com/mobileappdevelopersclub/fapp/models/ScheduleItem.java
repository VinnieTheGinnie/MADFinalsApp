package com.mobileappdevelopersclub.fapp.models;

import org.ektorp.support.CouchDbDocument;

import com.mobileappdevelopersclub.fapp.Constants;


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
		this.timeStart = parseTime(START_FLAG, time);
		this.timeEnd = parseTime(END_FLAG, time);
		
		
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
	
	//1:30 pm - 3:30 pm
	// 10:30 am - 11:30 am
	// 9:30 am - 10:30 am 
	private String parseTime(int flag, String timeString) {
		String time = timeString.replace(":", "");
		String[] arr = time.split("-");
		if(flag == START_FLAG) {
			String startTime = arr[0].replace(" ", "");
			startTime = handleMeridiem(startTime);
			return startTime;
		} else {
			String endTime = arr[1].replace(" ", "");
			endTime = handleMeridiem(endTime);
			return endTime;
		}		
	}
	
	private String handleMeridiem(String time) {
		if(time.contains("am")) {
			String newTime = time.replace("am", "");
			int time_num = Integer.parseInt(newTime);
			if(time_num >= 1200) {
				time_num -= 1200;
			}
			
			return Integer.toString(time_num);
		} else {
			String newTime = time.replace("pm", "");
			int time_num = Integer.parseInt(newTime);
			if(time_num < 1200) {
				time_num += 1200;
			}
			
			return Integer.toString(time_num);
		}
	}
	

	public String getClassTimeLabel(String day) {
		StringBuilder sb = new StringBuilder();
		sb.append(day);
		sb.append(" ");
		sb.append(getTimeFormat(timeStart));
		sb.append(" - ");
		sb.append(getTimeFormat(timeEnd));
		
		return sb.toString();
	}
	
	private String getTimeFormat(String time) {
		
		String newTime = "";
		
		if(time.length() > 3) {
			newTime = time.substring(0, 2) + ":" + time.substring(2);
		} else {
			newTime = time.substring(0, 1) + ":" + time.substring(1);
		}
		
		return newTime;
	}
	
	public int getClassLength() {
		int timeDifference = Integer.parseInt(timeEnd) - Integer.parseInt(timeStart);

		return convertTimeToPixels(timeDifference);
	}
	
	public int classStartTime() {
		int timeDifference = Integer.parseInt(timeStart) - EIGHT_AM;

		return convertTimeToPixels(timeDifference);
	}
	
	private int convertTimeToPixels(int timeDifference) {
		int timeInPixels = 0;
		
		while(timeDifference > 0) {
			if(timeDifference >= 60) {
				timeInPixels += Constants.pixelsPerHour;
				timeDifference -= 100;
			} else if(timeDifference % 15 == 0) {
				timeInPixels += Constants.pixelsPerFifteenMin;
				timeDifference -= 15;
			} else {
				timeInPixels += Constants.pixelsPerTenMin;
				timeDifference -= 10;
			}
		}
		
		return timeInPixels;
	}

}
