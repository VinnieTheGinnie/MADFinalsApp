package com.mobileappdevelopersclub.fapp.util;

import java.util.Comparator;

import com.mobileappdevelopersclub.fapp.models.ScheduleItem;

public class ScheduleItemComparator implements Comparator<ScheduleItem> {

	@Override
	public int compare(ScheduleItem item0, ScheduleItem item1) {
		int time0Length = getItemLength(item0);
		int time1Length = getItemLength(item1);
		
		if(time0Length > time1Length) {
			return -1;
		} else if(time0Length < time1Length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	private int getItemLength(ScheduleItem item) {		
		return ScheduleItemUtil.getClassLength(item);
	}
	
	

}
