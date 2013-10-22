package com.mobileappdevelopersclub.fapp.models;

import org.ektorp.support.CouchDbDocument;

import com.mobileappdevelopersclub.fapp.Constants;


public class ScheduleItem extends CouchDbDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int EIGHT_AM  = 800;
	
	private String title;
	private String timeStart;
	private String timeEnd;
	private String date;
	
	
	public ScheduleItem(String title, String timeStart, String timeEnd,
			String date) {
		super();
		this.title = title;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.date = date;
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


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
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
	
	public int getClassStartTime() {
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
