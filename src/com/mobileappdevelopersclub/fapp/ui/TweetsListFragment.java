package com.mobileappdevelopersclub.fapp.ui;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.adapters.TweetsListItemAdapter;
import com.mobileappdevelopersclub.fapp.models.Tweet;

public class TweetsListFragment extends FappFragment {
	
	private View mView;
	private Context context;
	private ListView mList;
	private TweetsListItemAdapter mAdapter;
	
	public static TweetsListFragment newInstance() {
		TweetsListFragment fragment = new TweetsListFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		
		mView = inflater.inflate(R.layout.list_layout, null);
		
		mAdapter = new TweetsListItemAdapter(getActivity(), 0, new ArrayList<Tweet>());
		mList = (ListView) mView.findViewById(R.id.mList);
		mList.setAdapter(mAdapter);
		
		createTestTweets();
		
		return mView;
	}
	
	/** Test for Tweets list view
	 **/
	
	private void createTestTweets() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("#UMDfinals");
		list.add("#UMDgoTerps");
		
		for (int i = 0; i < 20; i++) {
			String currInt = Integer.toString(i);
			mAdapter.add(new Tweet("twitter_user_" + currInt, "Wow finals are hard, im hungry #UMDfinals", list) );
			
		}
		
	}
	
	
	
	
	
	
	
	
	
}
