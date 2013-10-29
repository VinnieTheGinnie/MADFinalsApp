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

public class FoodItemAdapter extends ArrayAdapter<FoodItem>{
	
	private Context context;
	private List<FoodItem> mFoodItems;
	
	
	@InjectView(R.id.resterauntName) TextView resterauntName;
	@InjectView(R.id.resterauntSpecial) TextView resterauntSpecial;
	
	public FoodItemAdapter(Context context, int resource, List<FoodItem> objects) {
		super(context, resource, objects);
		this.context = context;
		this.mFoodItems = objects;
	}


	@Override
	public int getCount() {
		return mFoodItems == null ? 0 : mFoodItems.size();
	}
	


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
	
		if(convertView == null) {
			convertView = View.inflate(context, R.layout.food_list_item, null);
		} else {
			convertView.getTag();
		}
		
		//Inject views (See android butterknife)
		Views.inject(this, convertView);
		
		resterauntName.setText(mFoodItems.get(position).getResteraunt());
		resterauntSpecial.setText(mFoodItems.get(position).getDescription());
		
		return convertView;
	}
	
}
