package com.mobileappdevelopersclub.fapp.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.models.ScheduleItem;
import com.mobileappdevelopersclub.fapp.util.ScheduleItemUtil;



public class DailyScheduleFragment extends Fragment{

	public final static String TAG = "DailyScheduleFragment";

	//constants
	private final String[] hours = {"8am" , "9am" , "10am" , "11am" , "12pm" , "1pm" , "2pm" , "3pm" , "4pm" , "5pm" 
			, "6pm" , "7pm" , "8pm" , "9pm" };

	int dayOfWeek;
	private String TODAY;
	View view;
	private Context mContext;
	private LinearLayout timeSlots;
	private FrameLayout classes;
	private ArrayList<ScheduleItem> todaysMeetingsList;  //Maps the name of a class to the list of meetings for the current day 
	private LayoutInflater mInflater;

	public static DailyScheduleFragment newInstance(int day, ArrayList<ScheduleItem> todaysItemsList) {
		DailyScheduleFragment frag = new DailyScheduleFragment();
		frag.dayOfWeek = day;
		frag.todaysMeetingsList = todaysItemsList;
		return frag;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();

		TODAY = Constants.FINAL_DATES[dayOfWeek];
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
		mInflater = inflater;
		view = inflater.inflate(R.layout.schedule_layout, null);
		timeSlots = (LinearLayout) view.findViewById(R.id.timeSlots);
		classes = (FrameLayout) view.findViewById(R.id.classes);
		addTimeSlots();

		return view;

	}
	
	


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		buildSchedule();
	}

	private void buildSchedule() {

		addFinalsToView(todaysMeetingsList);
	}

	private void addFinalsToView(List<ScheduleItem> items) {

		for(int i=0; i < items.size(); i++) {
			ScheduleItem currItem = items.get(i);
			
			LinearLayout classItemLayout =  
					(LinearLayout) mInflater.inflate(
							R.layout.schedule_list_item , null);
			
			int height = ScheduleItemUtil.getClassLength(currItem);
			
			FrameLayout.LayoutParams params = 
					new FrameLayout.LayoutParams(
							LinearLayout.LayoutParams.MATCH_PARENT , height);
			
			params.setMargins(0, ScheduleItemUtil.classStartTime(currItem),
					0, 0);
			classItemLayout.setLayoutParams(params);
			addClassInfo(currItem.getTitle(), currItem, classItemLayout );
			classes.addView(classItemLayout);
		}

	}

	private void addClassInfo(String className,
			ScheduleItem currMeeting, LinearLayout classItemLayout) { 

		((TextView)classItemLayout.findViewById(R.id.className)).
			setText(className);
		
		((TextView)classItemLayout.findViewById(R.id.date)).
		setText(currMeeting.getDay());
		
		((TextView)classItemLayout.findViewById(R.id.time)).
		setText(currMeeting.getTime());
		
		((TextView)classItemLayout.findViewById(R.id.location)).
		setText(currMeeting.getLocation());
		
		((TextView)classItemLayout.findViewById(R.id.instructor)).
		setText(currMeeting.getInstructor());

		
		((TextView)classItemLayout.findViewById(R.id.section)).
		setText(currMeeting.getSection());




	}


	private void addTimeSlots() {
		for(int i = 0; i < hours.length ; i++) {
			Log.d(TAG, "Adding Time Slot");
			TextView time = new TextView(mContext);
			time.setText(hours[i]);
			time.setHeight(Constants.pixelsPerHour);
			timeSlots.addView(time);
		}
	}
}
