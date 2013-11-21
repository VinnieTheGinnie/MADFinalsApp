package com.mobileappdevelopersclub.fapp.ui;
import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.adapters.LibraryListItemAdapter;
import com.mobileappdevelopersclub.fapp.models.Library;

public class LibraryListFragment extends FappFragment {
	
	private View mView;
	private Context context;
	private ListView mList;
	private LibraryListItemAdapter mAdapter;
	
	public static LibraryListFragment newInstance() {
		LibraryListFragment fragment = new LibraryListFragment();
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
		
		mView = inflater.inflate(R.layout.list_layout , null);
		
		mAdapter = new LibraryListItemAdapter(getActivity(), 0, new ArrayList<Library>());	
		mList = (ListView) mView.findViewById(R.id.mList);
		mList.setAdapter(mAdapter);
		
		createTestLibraries();
		
		return mView;
	}
	
	
	/**
	 * Test function to test listView
	 * 
	 */
	
	
	private void createTestLibraries() {
		
		for(int i= 0; i < Constants.UMDLIBS.length; i++) {
			String currInt =  Integer.toString(i);
			mAdapter.add(new Library("Library " + currInt, "Location " + currInt, "10:00", "10:00", new ArrayList<String>()));
		}
		
		mAdapter.notifyDataSetChanged();
	}
	
	
}
