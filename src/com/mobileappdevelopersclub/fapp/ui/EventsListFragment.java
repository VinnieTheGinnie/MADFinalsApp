package com.mobileappdevelopersclub.fapp.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.adapters.EventsListitemAdapter;
import com.mobileappdevelopersclub.fapp.models.Event;
import com.mobileappdevelopersclub.fapp.models.EventResponse;
import com.mobileappdevelopersclub.fapp.models.ScheduleItem;
import com.mobileappdevelopersclub.fapp.models.ScheduleItemRepository;


public class EventsListFragment extends FappFragment {

	private View mView;
	private static EventsListFragment context;
	
	private ListView mList;
	private EventsListitemAdapter mAdapter;
	
	@Inject CouchDbInstance dbInstance;

	public static EventsListFragment newInstance() {
		EventsListFragment fragment = new EventsListFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);

		mView = inflater.inflate(R.layout.list_layout , null);

		mAdapter = new EventsListitemAdapter(getActivity(), 0,
				new ArrayList<Event>());	
		mList = (ListView) mView.findViewById(R.id.mList);
		mList.setAdapter(mAdapter);
		mList.setDividerHeight(1);
		mList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO: Once the schedule layout works nicely with overlapping 
					// items, add this feature back in 
//				showAddToSchedDialog(mAdapter.getItem(pos));

			}

		});

		fetchEventData();

		return mView;
	}

	private void showAddToSchedDialog(Event e) {
		AddToScheduleDialogFragment.newInstance(e).show(getFragmentManager(), 
				"Add To Schedule Dialog Showing");
	}

	private void fetchEventData() {

		Gson gson = new Gson();
		String json = null;
		try {
			json = parseAsString("events_list.json");
		} catch (IOException e) {
			Toast.makeText(this.getActivity(), 
					"Could not parse JSON to String", Toast.LENGTH_SHORT).show();
		}

		EventResponse response = gson.fromJson(json, EventResponse.class);

		addToEventsList(response.getEvents());
	}

	private void addToEventsList(List<Event> events) {

		mAdapter.addAll(events);
		mAdapter.notifyDataSetChanged();

	}
	
	private class CouchDbCommitTask extends AsyncTask<ScheduleItem, Void, Void> {

		public CouchDbCommitTask() {

		}

		@Override
		protected Void doInBackground(ScheduleItem... arg0) {
			commitItem(arg0[0]);
			return null;
		}


		private void commitItem(ScheduleItem item) {

			CouchDbConnector couchDbConnector = dbInstance.createConnector(Constants.DATABASE_NAME, true);
			ScheduleItemRepository repo = new ScheduleItemRepository(couchDbConnector);
			boolean exists = false;

			for(ScheduleItem curr: repo.getAll()) {
				if(curr.getTitle().equals(item.getTime())) {
					exists = true;
				}
			}

			if(!exists) {
				repo.add((ScheduleItem)item);
			}

		}

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

	public static class AddToScheduleDialogFragment extends DialogFragment {
		
		
		Event event;
		
		public static AddToScheduleDialogFragment newInstance(Event e) {
			AddToScheduleDialogFragment frag = new AddToScheduleDialogFragment();
			frag.event = e;
			return frag;
		}
		
		
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder =
					new AlertDialog.Builder(getActivity());
			// Get the layout inflater
			LayoutInflater inflater = getActivity().getLayoutInflater();

			final View view = inflater.inflate(R.layout.add_class_dialog,
					null);
			
			((TextView)view.findViewById(R.id.Title)).setText(context.getResources().getString(R.string.add_to_sched));
			((EditText)view.findViewById(R.id.className)).setVisibility(View.INVISIBLE);

			// Inflate and set the layout for the dialog
			// Pass null as the parent view because its going in the dialog layout
			builder.setView(view)
			// Add action buttons
			.setPositiveButton(R.string.yes, 
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					//TODO: save schedule item
					context.new CouchDbCommitTask().execute(event);
				}
			})
			.setNegativeButton(R.string.no,
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					AddToScheduleDialogFragment.this.getDialog().cancel();
				}
			});      
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
