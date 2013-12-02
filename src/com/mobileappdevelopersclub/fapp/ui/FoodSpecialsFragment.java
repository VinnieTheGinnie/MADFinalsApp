package com.mobileappdevelopersclub.fapp.ui;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.adapters.FoodItemAdapter;
import com.mobileappdevelopersclub.fapp.models.Final;
import com.mobileappdevelopersclub.fapp.models.FoodItem;
import com.mobileappdevelopersclub.fapp.ui.ScheduleFragment.FinalResponseDialogFragment;
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
		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				new FoodCouponDialogFragment()
					.show(getFragmentManager(), "Showing Coupon");
			}
			
		});
		
		
		return mView;
	}
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		createTestList();
	}

	private void createTestList() {
		
		for(int i= 0 ; i < 20; i++) {
			FoodItem item = new FoodItem("Sample Resteraunt " + Integer.toString(i), "Save 10 % on your purchase during finals!");
			mAdapter.add(item);
		}
		
		mAdapter.notifyDataSetChanged();
	}
	
	public static class FoodCouponDialogFragment extends DialogFragment {

	
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = 
					new AlertDialog.Builder(getActivity());
			// Get the layout inflater
			LayoutInflater inflater = getActivity().getLayoutInflater();
			
			View view = inflater.inflate(R.layout.coupon_image, null);
			Picasso.with(this.getActivity()).load(Constants.BAGEL_PLACE)
			.into((ImageView)view.findViewById(R.id.image));

			// Inflate and set the layout for the dialog
			// Pass null as the parent view because its going in the dialog layout
			builder.setView(view);
			
			
			return builder.create();
		}


	}

	

}
