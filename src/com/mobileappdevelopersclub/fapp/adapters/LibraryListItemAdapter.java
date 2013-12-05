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
import com.mobileappdevelopersclub.fapp.util.ScheduleItemUtil;
import com.squareup.picasso.Picasso;

public class LibraryListItemAdapter extends ArrayAdapter<Library>{

	private Context context;
	private List<Library> mLibraries;

	@InjectView(R.id.libraryImage) ImageView libraryImage;
	@InjectView(R.id.libraryName) TextView libraryName;
	@InjectView(R.id.currentDate) TextView currentDate;
	@InjectView(R.id.libraryHours) TextView libraryHours;

	public LibraryListItemAdapter(Context context, int resource,
			List<Library> objects) {
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

		int hourInd = 0;

		List<Hours> hrsList = currLib.getHours();

		for(int i=0; i < hrsList.size(); i++) {
			String date = ScheduleItemUtil.parseHourDateString(hrsList.get(i).getDate());
			if(date.equalsIgnoreCase(Integer.toString(day))) {
				hourInd = i;
				break;
			}
		}



		Hours hours = hrsList.get(hourInd);

		currentDate.setText(hours.getDate());

		String formattedLibHours;

		if(hours.getHourOpen().equalsIgnoreCase(hours.getHourClose())) {
			formattedLibHours = "Open 24 Hours";
		} else {
			formattedLibHours = hours.getHourOpen() + 
					" - " + hours.getHourClose();
		}

		libraryHours.setText(formattedLibHours);

		return convertView;
	}
}
