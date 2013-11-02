package com.mobileappdevelopersclub.fapp.ui;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.adapters.FoodItemAdapter;
import com.mobileappdevelopersclub.fapp.models.FoodItem;

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
	

}
