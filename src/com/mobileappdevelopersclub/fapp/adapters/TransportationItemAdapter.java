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
import com.mobileappdevelopersclub.fapp.models.BusRoute;


public class TransportationItemAdapter extends ArrayAdapter<BusRoute>{

	private Context context;
	private List<BusRoute> mRoutes;
	
	@InjectView(R.id.busRouteTitle) TextView busRouteTitle;
	
	public TransportationItemAdapter(Context context, int resource,
			List<BusRoute> objects) {
		super(context, resource, objects);
		this.context = context;
		this.mRoutes = objects;
	}


	@Override
	public int getCount() {
		return mRoutes == null ? 0 : mRoutes.size();
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {


		if(convertView == null) {
			convertView = View.inflate(context, R.layout.transportation_list_item, null);
		} 

		//Inject views (See android butterknife)
		Views.inject(this, convertView);

		BusRoute currRoute = mRoutes.get(position);
		
		busRouteTitle.setText(currRoute.getTitle());

		return convertView;
	}
}
