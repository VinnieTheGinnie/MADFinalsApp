package com.mobileappdevelopersclub.fapp.ui;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.adapters.FoodItemAdapter;
import com.mobileappdevelopersclub.fapp.models.FoodItem;
import com.squareup.picasso.Picasso;

public class FoodSpecialsFragment extends FappFragment {
	
	public static final String TAG = "FoodSpecialsFragment";
	
	private Activity mActivity;
	private LayoutInflater mInflater;
	private View mView;
	private ListView mList;
	private FoodItemAdapter mAdapter;
	
	
	public static FoodSpecialsFragment newInstance() {
		FoodSpecialsFragment fragment = new FoodSpecialsFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}


	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		this.mInflater = inflater;

		mAdapter = new FoodItemAdapter(getActivity(), 0, new ArrayList<FoodItem>());
		
		mView = inflater.inflate(R.layout.list_layout, null);	
		mList = (ListView) mView.findViewById(R.id.mList);
		mList.addFooterView(getHeaderView());
		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				FoodCouponDialogFragment.newInstance(pos)
					.show(getFragmentManager(), "Showing Coupon");
			}
			
		});
		
		
		return mView;
	}
	
	private View getHeaderView() {
		TextView tv = (TextView) 
				mInflater.inflate(R.layout.list_view_header, null);
		
		tv.setText(mActivity.getResources().getString(R.string.food_list_header));
		
		return tv;
	}
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		createTestList();
	}

	private void createTestList() {
		
		for(int i= 0 ; i < 7; i++) {
			FoodItem item = null; 
			switch(i) {
			case(0):
				item = new FoodItem("Bagel Place ", 
						"Get a free cup of coffee with any purchase over $5", 
							Constants.BAGEL_PLACE);
				break;
			case(1):
				item = new FoodItem("Pizza Kingdom",
						"Get $1 Off a jumbo slice of pizza!",
							Constants.PIZZA_KINGDOM);
				break;
			case(2):
				item = new FoodItem("R.J. Bently's",
						"Get $2 off Philly steak & cheese \n " +
						"Get 1lb of wings with fries for $6.50",
							Constants.RJ_BENTS);
				break;
			case(3):
				item = new FoodItem("D.P. Dough", 
						"Normal $5 zones every day! \n $5 Breakfast zones",
							Constants.DP_DOUGH);
				break;
			case(4):
				item = new FoodItem("Ten Ren's Tea Time ", 
						"Get $2 off bubble tea!",
							Constants.TEN_RENS);
				break;
			case(5):
				item = new FoodItem("Auntie Anne's ", 
						"Enjoy a new deal every day during finals week!",
							Constants.AANNES);
				break;
			case(6):
				item = new FoodItem("Stamp ", 
						"Enjoy different deals from Chick-fil-a, Sbarro," +
								" Subway and Taco Bell ",
							Constants.STAMP);
				break;
			}			
			
			mAdapter.add(item);
		}
		
		mAdapter.notifyDataSetChanged();
	}
	
	public static class FoodCouponDialogFragment extends DialogFragment {

		int couponPos = 0;
		
		public static FoodCouponDialogFragment newInstance(int couponPos) {
			FoodCouponDialogFragment frag = new FoodCouponDialogFragment();
			frag.couponPos = couponPos;
			return frag;
		}
		
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = 
					new AlertDialog.Builder(getActivity());
			// Get the layout inflater
			LayoutInflater inflater = getActivity().getLayoutInflater();
			
			View view = inflater.inflate(R.layout.coupon_image, null);
			
			Log.e(TAG, "couponPos " + couponPos);
			switch(couponPos) {
			case(0):
				Picasso.with(this.getActivity()).load(R.drawable.bagel_place)
					.into((ImageView)view.findViewById(R.id.image));
				break;
			case(1):
				Picasso.with(this.getActivity()).load(R.drawable.pizza_kingdom)
					.into((ImageView)view.findViewById(R.id.image));
				break;
			case(2):
				Picasso.with(this.getActivity()).load(R.drawable.bentz)
					.into((ImageView)view.findViewById(R.id.image));
				break;
			case(3):
				Picasso.with(this.getActivity()).load(R.drawable.dp_dough)
					.into((ImageView)view.findViewById(R.id.image));
				break;
			case(4):
				Picasso.with(this.getActivity()).load(R.drawable.ten_rens)
					.into((ImageView)view.findViewById(R.id.image));
				break;
			case(5):
				Picasso.with(this.getActivity()).load(R.drawable.auntie_annes)
					.into((ImageView)view.findViewById(R.id.image));
				break;
			case(6):
				Picasso.with(this.getActivity()).load(R.drawable.stamp)
					.into((ImageView)view.findViewById(R.id.image));
				break;
			}		

			// Inflate and set the layout for the dialog
			// Pass null as the parent view because its going in the dialog layout
			builder.setView(view);
			
			
			return builder.create();
		}


	}

	

}
