package com.mobileappdevelopersclub.fapp.util;

import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.models.ScheduleItem;

public class ScheduleItemUtil {
	
	private final static int START_FLAG = 0;
	private final static int EIGHT_AM  = 800;
	private final static int END_FLAG = 1;
	
	public static String parseTime(int flag, String timeString) {
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
	
	private static String handleMeridiem(String time) {
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
	

	public static String getClassTimeLabel(String day, ScheduleItem f) {
		StringBuilder sb = new StringBuilder();
		sb.append(day);
		sb.append(" ");
		sb.append(getTimeFormat(getTimeStart(f)));
		sb.append(" - ");
		sb.append(getTimeFormat(getTimeEnd(f)));
		
		return sb.toString();
	}
	
	public static String getTimeFormat(String time) {
		
		String newTime = "";
		
		if(time.length() > 3) {
			newTime = time.substring(0, 2) + ":" + time.substring(2);
		} else {
			newTime = time.substring(0, 1) + ":" + time.substring(1);
		}
		
		return newTime;
	}
	
	public static int getClassLength(ScheduleItem f) {
		int timeDifference =
				Integer.parseInt(getTimeEnd(f)) - 
					Integer.parseInt(getTimeStart(f));

		return convertTimeToPixels(timeDifference);
	}
	
	public static int classStartTime(ScheduleItem f) {
		int timeDifference = Integer.parseInt(getTimeStart(f)) - EIGHT_AM;

		return convertTimeToPixels(timeDifference);
	}
	
	public static String getTimeStart(ScheduleItem f) {
		return ScheduleItemUtil.parseTime(START_FLAG, f.getTime());
	}
	
	public static String getTimeEnd(ScheduleItem f) {
		return ScheduleItemUtil.parseTime(END_FLAG, f.getTime());
	}
	
	private static int convertTimeToPixels(int timeDifference) {
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
