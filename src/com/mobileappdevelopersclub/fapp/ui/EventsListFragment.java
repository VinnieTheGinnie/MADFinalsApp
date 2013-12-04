package com.mobileappdevelopersclub.fapp.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.google.gson.Gson;
import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.adapters.LibraryListItemAdapter;
import com.mobileappdevelopersclub.fapp.models.Library;
import com.mobileappdevelopersclub.fapp.models.LibraryResponse;

public class EventsListFragment extends FappFragment {

	private View mView;
	private static Context context;
	private ListView mList;
	private LibraryListItemAdapter mAdapter;

	public static EventsListFragment newInstance() {
		EventsListFragment fragment = new EventsListFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this.getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);

		mView = inflater.inflate(R.layout.list_layout , null);

		mAdapter = new LibraryListItemAdapter(getActivity(), 0,
				new ArrayList<Library>());	
		mList = (ListView) mView.findViewById(R.id.mList);
		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				showLibraryHoursDialog(mAdapter.getItem(pos));

			}

		});

		fetchLibraryData();

		//		createTestLibraries();

		return mView;
	}

	private void showLibraryHoursDialog(Library lib) {
		
		LibraryHoursDialogFragment.newInstance(lib)
		.show(getFragmentManager(), "Showing Hours");
		
	}

	private void fetchLibraryData() {

		Gson gson = new Gson();
		String json = null;
		try {
			json = parseAsString("libraries.json");
		} catch (IOException e) {
			Toast.makeText(this.getActivity(), 
					"Could not parse JSON to String", Toast.LENGTH_SHORT).show();
		}

		LibraryResponse response = gson.fromJson(json, LibraryResponse.class);

		addToLibraryList(response.getLibraries());
	}

	private void addToLibraryList(List<Library> libraries) {

		mAdapter.addAll(libraries);
		mAdapter.notifyDataSetChanged();

	}


	public String parseAsString(String filename) throws IOException {

		AssetManager assetManager = this.getActivity().getAssets();

		InputStream in = assetManager.open(filename);

		int size = in.available();
		byte[] buffer = new byte[size];

		in.read(buffer);
		in.close();

		String fileAsString = new String(buffer);

		return fileAsString;

	}

	public static class LibraryHoursDialogFragment extends DialogFragment {

		Library lib;
		
		public static LibraryHoursDialogFragment newInstance(Library lib) {
			LibraryHoursDialogFragment dialog = new LibraryHoursDialogFragment();
			dialog.lib = lib;
			return dialog;
		}
		

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = 
					new AlertDialog.Builder(getActivity());
			// Get the layout inflater
			LayoutInflater inflater = getActivity().getLayoutInflater();

			View view = inflater.inflate(R.layout.library_hours_dialog, null);
			
			((TextView) view.findViewById(R.id.title)).setText(lib.getName());
			
			Calendar c = Calendar.getInstance();
			int day = c.get(Calendar.DAY_OF_MONTH);
			int dayMax = 14;
			int dayMin = 8;
			
			if(day > 14) {
				dayMin = 15;
				dayMax = 21;
			}
			
			LinearLayout dates = (LinearLayout) view.findViewById(R.id.dates);
			
			for(int i = dayMin; i <= dayMax; i++) {
				TextView t = new TextView(context);
				t.setText(Integer.toString(i));
				dates.addView(t);
			}
			
			
			

			// Inflate and set the layout for the dialog
			// Pass null as the parent view because its going in the dialog layout
			builder.setView(view);


			return builder.create();
		}


	}



	/**
	 * Test function to test listView
	 * 
	 */

	//	private void createTestLibraries() {
	//
	//		for(int i= 0; i < Constants.UMDLIBS.length; i++) {
	//			String currInt =  Integer.toString(i);
	//			mAdapter.add(new Library("Library " + currInt, "Location " + currInt, "10:00", "10:00", new ArrayList<String>()));
	//		}
	//
	//		mAdapter.notifyDataSetChanged();
	//	}


}
