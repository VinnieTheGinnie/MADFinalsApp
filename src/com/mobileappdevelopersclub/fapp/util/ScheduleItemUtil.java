package com.mobileappdevelopersclub.fapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.models.ScheduleItem;

public class ScheduleItemUtil {

	private final static int START_FLAG = 0;
	private final static int EIGHT_AM  = 800;
	private final static int END_FLAG = 1;

	public static String parseTime(int flag, String timeString) {
		String time = timeString.replace(":", "");

		if(time.contains("-")) {
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
		} else {
			return time;
		}
	}

	private static String handleMeridiem(String time) {
		if(time.contains("am") || time.contains("AM")) {
			String newTime = time.replace("am", "").replace("AM", "");
			int time_num = Integer.parseInt(newTime);
			if(time_num >= 1200) {
				time_num -= 1200;
			}

			return Integer.toString(time_num);
		} else {
			String newTime = time.replace("pm", "").replace("PM", "");
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


	/**
	 * 
	 * @param f
	 * @return length of the class in density pixels
	 */

	public static int getClassLengthInDps(ScheduleItem f) {
		int timeDifference =
				Integer.parseInt(getTimeEnd(f)) - 
				Integer.parseInt(getTimeStart(f));

		return convertTimeToPixels(timeDifference);
	}

	/**
	 * 
	 * @param f
	 * @return int length of class, ex. 12:00 - 2:00 = 200
	 */

	public static int getClassLength(ScheduleItem f) {
		int timeDifference =
				Integer.parseInt(getTimeEnd(f)) - 
				Integer.parseInt(getTimeStart(f));

		return timeDifference;
	}


	public static int classStartTime(ScheduleItem f) {
		int timeDifference = Integer.parseInt(getTimeStart(f)) - EIGHT_AM;

		return convertTimeToPixels(timeDifference);
	}

	public static String getTimeStart(ScheduleItem f) {
		if(f.getType() == null) {
			return ScheduleItemUtil.parseTime(START_FLAG, f.getTime());
		} else {
			return ScheduleItemUtil.parseTime(START_FLAG, f.getStartTime());
		}
	}

	public static String getTimeEnd(ScheduleItem f) {

		if(f.getType() == null) {
			return ScheduleItemUtil.parseTime(END_FLAG, f.getTime());
		} else {
			return ScheduleItemUtil.parseTime(END_FLAG, f.getEndTime());
		}

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

	public static String parseHourDateString(String date) {
		return date.substring(date.length() - 2).replace(" ", "");
	}

	public static String parseScheduleItemDayString(ScheduleItem item) {
		String day = item.getDay();
		if(item.getType() == null) {
			return day.substring(day.length() -2).replace(" ", "");
		} else {
			int indOfDash = day.indexOf("-");
			return day.substring(0, indOfDash);
		}
	}

	public static long getMilliSeconds(ScheduleItem item) throws ParseException{

		Date dateSample = null ; 
		String day = formatDateForCalIntent(item);

		if(item.getType() == null) {
			try {  
				SimpleDateFormat formatter;
				//		    	Fri, Dec 20 2013 1:30 pm - 3:30 pm
				formatter = new SimpleDateFormat("EEE, MMM dd yyyy hh:mma");
				dateSample = (Date) formatter.parse(day);  
			} catch (Exception e) {
			}  
		} else {
			try {  
				SimpleDateFormat formatter;
				//		    	9-Dec 2013 1:30 - 3:30
				formatter = new SimpleDateFormat("dd-MMM yyyy hh:mm");
				dateSample = (Date) formatter.parse(day);  
			} catch (Exception e) {
			}  
		}



		return dateSample.getTime();
	}

	private static String formatDateForCalIntent(ScheduleItem item) {
		StringBuilder sb = new StringBuilder();

		if(item.getType() == null) {
			int breakInd = item.getTime().indexOf("-");
			String newTime = item.getTime().substring(0, breakInd).replace(" ", "");
			sb.append(item.getDay());
			sb.append(" 2013 ");
			sb.append(newTime);
		} else {
			sb.append(item.getDay());
			sb.append(" 2013 ");
			sb.append(item.getStartTime());
		}

		return sb.toString();
	}


}
