package com.mobileappdevelopersclub.fapp.adapters;

import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.Views;

import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.models.Hours;
import com.mobileappdevelopersclub.fapp.models.Library;
import com.squareup.picasso.Picasso;

public class EventsListitemAdapter extends ArrayAdapter<Library>{
	
	private Context context;
	private List<Library> mLibraries;
	
	@InjectView(R.id.libraryImage) ImageView libraryImage;
	@InjectView(R.id.libraryName) TextView libraryName;
	@InjectView(R.id.libraryLocation) TextView libraryLocation;
	@InjectView(R.id.libraryHours) TextView libraryHours;
	
	public EventsListitemAdapter(Context context, int resource, List<Library> objects) {
		super(context, resource, objects);
		this.context = context;
		this.mLibraries = objects;
	}


	@Override
	public int getCount() {
		return mLibraries == null ? 0 : mLibraries.size();
	}
	


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
	
		if(convertView == null) {
			convertView = View.inflate(context, R.layout.library_list_item, null);
		} 
		
		//Inject views (See android butterknife)
		Views.inject(this, convertView);
		
		Library currLib = mLibraries.get(position);		
		
		libraryName.setText(currLib.getName());
		Picasso.with(context).load(currLib.getPhoto()).into(libraryImage);
		
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		
		//TODO: change for production 
//		int hoursPosition = 0;
//		
//		for(int i=9; i < 22;i++) {
//			if(day == i) {
//				break;
//			} 
//			
//			++hoursPosition;
//		}
//		Hours hours = currLib.getHours().get(hoursPosition);
		
		
		
		Hours hours = currLib.getHours().get(0);
		String formattedLibHours = hours.getHourOpen() + 
				" - " + hours.getHourClose();
		
		libraryHours.setText(formattedLibHours);
		
		return convertView;
	}
	
}
