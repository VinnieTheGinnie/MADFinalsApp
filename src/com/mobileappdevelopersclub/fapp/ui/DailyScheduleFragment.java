package com.mobileappdevelopersclub.fapp.ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.models.ScheduleItem;
import com.mobileappdevelopersclub.fapp.models.ScheduleItemRepository;
import com.mobileappdevelopersclub.fapp.util.ScheduleItemComparator;
import com.mobileappdevelopersclub.fapp.util.ScheduleItemUtil;



public class DailyScheduleFragment extends FappFragment{

	public final static String TAG = "DailyScheduleFragment";

	//constants
	private final String[] hours = {"8am" , "9am" , "10am" , "11am" , "12pm" , "1pm" , "2pm" , "3pm" , "4pm" , "5pm" 
			, "6pm" , "7pm" , "8pm" , "9pm" };

	private ListPopupWindow mPopupWindow;
	private String[] mPopupOptions = {"Add To Calendar"};
	private ArrayAdapter<String> mPopupAdapter;
	private static final String 
	CALENDAR_EVENT_URI = "vnd.android.cursor.item/event",
	TITLE_TAG = "title",
	DESCRIPTION_TAG = "description",
	START_TIME_TAG = "beginTime",
	LOCATION = "eventLocation",
	END_TIME_TAG = "endTime";

	@Inject CouchDbInstance dbInstance;


	int dayOfWeek;
	private String TODAY;
	View view;
	private Context mContext;
	private LinearLayout timeSlots;
	private FrameLayout classes;
	//Maps the name of a class to the list of meetings for the current day
	private ArrayList<ScheduleItem> todaysMeetingsList;   
	private LayoutInflater mInflater;

	public static DailyScheduleFragment newInstance(int day, 
			ArrayList<ScheduleItem> todaysItemsList) {
		DailyScheduleFragment frag = new DailyScheduleFragment();
		frag.dayOfWeek = day;
		frag.todaysMeetingsList = todaysItemsList;

		Collections.sort(frag.todaysMeetingsList, new ScheduleItemComparator());

		return frag;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		TODAY = Constants.FINAL_DATES[dayOfWeek];
		mPopupAdapter = new ArrayAdapter<String>(mContext, R.layout.popup_list, R.id.popup_text, mPopupOptions);
		mPopupWindow = new ListPopupWindow(mContext);
		mPopupWindow.setAdapter(mPopupAdapter);
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

			RelativeLayout classItemLayout =  
					(RelativeLayout) mInflater.inflate(
							R.layout.schedule_list_item , null);

			int height = ScheduleItemUtil.getClassLengthInDps(currItem);

			FrameLayout.LayoutParams params = 
					new FrameLayout.LayoutParams(
							LinearLayout.LayoutParams.MATCH_PARENT , height);

			params.setMargins(0, ScheduleItemUtil.classStartTime(currItem),
					0, 0);
			classItemLayout.setLayoutParams(params);

			if(currItem.getType() == null) {
				addClassInfo(currItem.getTitle(), currItem, classItemLayout );
			} else {
				addEventInfo(currItem.getTitle(), currItem, classItemLayout);
			}



			classes.addView(classItemLayout);
		}

	}

	private void addEventInfo(String eventName, ScheduleItem currEvent, 
			RelativeLayout classItemLayout) {

		((TextView)classItemLayout.findViewById(R.id.className)).
		setText(eventName);

		((TextView)classItemLayout.findViewById(R.id.date)).
		setText(currEvent.getDay());

		String time = currEvent.getStartTime() + " - " + currEvent.getEndTime();

		((TextView)classItemLayout.findViewById(R.id.time)).
		setText(time);

		((TextView)classItemLayout.findViewById(R.id.location)).
		setText(currEvent.getLocation());

		((TextView)classItemLayout.findViewById(R.id.instructor)).
		setText(currEvent.getDesctiption());

	}

	private void addClassInfo(String className,
			final ScheduleItem currMeeting, RelativeLayout classItemLayout) { 

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

		final ImageView overflowLocation = 
				(ImageView) classItemLayout.findViewById(R.id.actionOverflow);


		ImageView overflow = 
				(ImageView)classItemLayout.findViewById(R.id.actionOverflow);


		Drawable overflowButton = mContext.getResources().
				getDrawable(R.drawable.ic_action_overflow);
		overflowButton.setColorFilter( R.color.lightgray, Mode.MULTIPLY );
		overflow.setImageDrawable(overflowButton);
		overflow.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPopupWindow.setAnchorView(overflowLocation);
				mPopupWindow.setModal(true);
				mPopupWindow.setContentWidth(430);
				mPopupWindow.show();
				mPopupWindow.getListView().setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {

						addToUserCalendar(mContext, currMeeting);	

					}

				});
			}
		});

	}

	private class CouchDbDeleteTask extends AsyncTask<ScheduleItem, Void, Void> {


		public CouchDbDeleteTask() {
		}

		@Override
		protected Void doInBackground(ScheduleItem... params) {

			ScheduleItem item = params[0];
			deleteItem(item.getTitle());
			return null;

		}

		private void deleteItem(String mTitle) {

			CouchDbConnector couchDbConnector =
					dbInstance.createConnector(Constants.DATABASE_NAME, true);
			ScheduleItemRepository repo =
					new ScheduleItemRepository(couchDbConnector);
			List<ScheduleItem> list = repo.getAll();
			int length = list.size();


			for(int i = 0; i < length; i++) {
				ScheduleItem item = list.get(i);
				if(item.getTitle().equals(mTitle)) {
					repo.remove(list.get(i));
					length--;
				}
			}
		}
	}


	public void addToUserCalendar(final Context context, ScheduleItem item) {
		final Intent intent = new Intent(Intent.ACTION_EDIT);
		intent.setType(CALENDAR_EVENT_URI);
		intent.putExtra(TITLE_TAG, item.getTitle());
		intent.putExtra(DESCRIPTION_TAG, item.getDesctiption());
		intent.putExtra(LOCATION, item.getLocation());
		try {
			intent.putExtra(START_TIME_TAG, ScheduleItemUtil.getMilliSeconds(item));
		} catch (ParseException e) {
			Log.e("EventsListItemAdapter", "Error Parsing start time");
		}
		context.startActivity(intent);
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
