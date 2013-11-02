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
import com.mobileappdevelopersclub.fapp.models.FoodItem;
import com.squareup.picasso.Picasso;

public class FoodItemAdapter extends ArrayAdapter<FoodItem>{

	private Context context;
	private List<FoodItem> mFoodItems;

//
	@InjectView(R.id.resterauntName) TextView resterauntName;
	@InjectView(R.id.resterauntSpecial) TextView resterauntSpecial;

	@InjectView(R.id.resterauntImage) ImageView resterauntImage;
	
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

		if(position % 2 == 0 ) {
			Picasso.with(context).load(Constants.BAGEL_PLACE).into(resterauntImage);
		} else {
			Picasso.with(context).load(Constants.PIZZA_KINGDOM).into(resterauntImage);
		}
		
		resterauntName.setText(mFoodItems.get(position).getResteraunt());
		resterauntSpecial.setText(mFoodItems.get(position).getDescription());

		return convertView;
	}

}
