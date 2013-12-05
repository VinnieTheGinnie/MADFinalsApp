package com.mobileappdevelopersclub.fapp.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.Views;

import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.models.Event;

public class EventsListitemAdapter extends ArrayAdapter<Event>{
	
	private Context context;
	private List<Event> mEvents;
	
	@InjectView(R.id.title) TextView title;
	@InjectView(R.id.dayAndTime) TextView dayAndTime;
	@InjectView(R.id.location) TextView location;
	@InjectView(R.id.description) TextView description;
	
	public EventsListitemAdapter(Context context, int resource,
			List<Event> objects) {
		super(context, resource, objects);
		this.context = context;
		this.mEvents = objects;
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
		
		//Inject views (See android butterknife)
		Views.inject(this, convertView);
		
		Event currEvent = mEvents.get(position);
		
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
		
		return convertView;
	}
	
}
