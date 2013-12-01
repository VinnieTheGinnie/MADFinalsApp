package com.mobileappdevelopersclub.fapp.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.Views;

import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.models.Final;
import com.mobileappdevelopersclub.fapp.models.Library;
import com.squareup.picasso.Picasso;

public class FinalsResponseAdapter extends ArrayAdapter<Final>{
	
	private Context context;
	private List<Final> mFinals;
	
	@InjectView(R.id.className) TextView className;
	@InjectView(R.id.examDate) TextView examDate;
	@InjectView(R.id.examTime) TextView examTime;
	@InjectView(R.id.examLocation) TextView examLocation;
	@InjectView(R.id.examInstructor) TextView examInstructor;
	@InjectView(R.id.classSection) TextView classSection;
	
	
	
	public FinalsResponseAdapter(Context context, int resource, List<Final> objects) {
		super(context, resource, objects);
		this.context = context;
		this.mFinals = objects;
	}


	@Override
	public int getCount() {
		return mFinals == null ? 0 : mFinals.size();
	}
	


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
	
		if(convertView == null) {
			convertView = View.inflate(context, R.layout.finals_resp_list_item, null);
		} else {
			convertView.getTag();
		}
		
		//Inject views (See android butterknife)
		Views.inject(this, convertView);
		
		Final currFinal = mFinals.get(position);
		
		className.setText(currFinal.getTitle());
		examDate.setText(currFinal.getDay());
		examTime.setText(currFinal.getTime());
		examLocation.setText(currFinal.getLocation());
		examInstructor.setText(currFinal.getInstructor());
		classSection.setText(currFinal.getSection());
		
		return convertView;
	}
	
}
