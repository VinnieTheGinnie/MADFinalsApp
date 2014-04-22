package com.mobileappdevelopersclub.fapp.adapters;

import java.text.ParseException;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import butterknife.InjectView;
import butterknife.Views;

import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.models.Event;
import com.mobileappdevelopersclub.fapp.util.ScheduleItemUtil;

public class EventsListitemAdapter extends ArrayAdapter<Event>{

	private Context context;
	private List<Event> mEvents;
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


	@InjectView(R.id.title) TextView title;
	@InjectView(R.id.dayAndTime) TextView dayAndTime;
	@InjectView(R.id.location) TextView location;
	@InjectView(R.id.description) TextView description;
	@InjectView(R.id.overflow) ImageView overflow;

	public EventsListitemAdapter(Context context, int resource,
			List<Event> objects) {
		super(context, resource, objects);
		this.context = context;
		this.mEvents = objects;
		mPopupAdapter = new ArrayAdapter<String>(context, R.layout.popup_list, R.id.popup_text, mPopupOptions);
		mPopupWindow = new ListPopupWindow(context);
		mPopupWindow.setAdapter(mPopupAdapter);
	}


	@Override
	public int getCount() {
		return mEvents == null ? 0 : mEvents.size();
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {


		if(convertView == null) {
			convertView = View.inflate(context, R.layout.events_list_item, null);
		} 
		
		final ImageView overflowLocation = 
				(ImageView) convertView.findViewById(R.id.overflow); 
		
		//Inject views (See android butterknife)
		Views.inject(this, convertView);

		final Event currEvent = mEvents.get(position);

		title.setText(currEvent.getTitle());

		StringBuilder dayTimeBuilder = new StringBuilder();

		dayTimeBuilder.append(currEvent.getDay());
		dayTimeBuilder.append("  ");
		dayTimeBuilder.append(currEvent.getStartTime());
		dayTimeBuilder.append(" - ");
		dayTimeBuilder.append(currEvent.getEndTime());

		dayAndTime.setText(dayTimeBuilder.toString());

		location.setText(currEvent.getLocation());

		description.setText(currEvent.getDesctiption());
		
		Drawable overflowButton = context.getResources().getDrawable(R.drawable.ic_action_overflow);
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
						addToUserCalendar(context, currEvent);
					}

				});
			}
		});

		return convertView;
	}


	public void addToUserCalendar(final Context context, Event item) {
		final Intent intent = new Intent(Intent.ACTION_EDIT);
		intent.setType(CALENDAR_EVENT_URI);
		intent.putExtra(TITLE_TAG, item.getTitle());
		intent.putExtra(DESCRIPTION_TAG, item.getDesctiption());
		try {
			intent.putExtra(START_TIME_TAG, ScheduleItemUtil.getMilliSeconds(item));
		} catch (ParseException e) {
			Log.e("EventsListItemAdapter", "Error Parsing start time");
		}
		intent.putExtra(LOCATION, item.getLocation());
		context.startActivity(intent);
	}

}
