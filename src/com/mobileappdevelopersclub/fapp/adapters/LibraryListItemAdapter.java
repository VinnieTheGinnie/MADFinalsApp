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
import com.mobileappdevelopersclub.fapp.models.FoodItem;
import com.mobileappdevelopersclub.fapp.models.Library;

public class LibraryListItemAdapter extends ArrayAdapter<Library>{
	
	private Context context;
	private List<Library> mLibraries;
	
	
	@InjectView(R.id.libraryName) TextView libraryName;
	@InjectView(R.id.libraryLocation) TextView libraryLocation;
	@InjectView(R.id.libraryHours) TextView libraryHours;
	
	public LibraryListItemAdapter(Context context, int resource, List<Library> objects) {
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
		} else {
			convertView.getTag();
		}
		
		//Inject views (See android butterknife)
		Views.inject(this, convertView);
		
		Library currLib = mLibraries.get(position);
		
		libraryName.setText(currLib.getName());
		libraryLocation.setText(currLib.getLocation());
		
		String formattedLibHours = currLib.getHourOpen() + " - " + currLib.getHourClose();
		libraryHours.setText(formattedLibHours);
		
		return convertView;
	}
	
}
