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
import com.mobileappdevelopersclub.fapp.models.HealthResource;

public class HealthResourceAdapter extends ArrayAdapter<HealthResource>{

	private Context context;
	private List<HealthResource> mResources;

	@InjectView(R.id.resourceTitle) TextView resourceTitle;
	@InjectView(R.id.resourceContact) TextView resourceContact;
	@InjectView(R.id.resourceDescription) TextView resourceDescription;

	public HealthResourceAdapter(Context context, int resource,
			List<HealthResource> objects) {
		super(context, resource, objects);
		this.context = context;
		this.mResources = objects;
	}


	@Override
	public int getCount() {
		return mResources == null ? 0 : mResources.size();
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {


		if(convertView == null) {
			convertView = View.inflate(context, R.layout.resource_list_item, null);
		} 

		//Inject views (See android butterknife)
		Views.inject(this, convertView);

		HealthResource currResource = mResources.get(position);	
		
		resourceTitle.setText(currResource.getTitle());
		
		resourceContact.setText(currResource.getContact());
		
		resourceDescription.setText(currResource.getDescription());

		return convertView;
	}
}
