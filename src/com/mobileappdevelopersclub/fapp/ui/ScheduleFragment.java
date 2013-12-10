package com.mobileappdevelopersclub.fapp.ui;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.adapters.FinalsResponseAdapter;
import com.mobileappdevelopersclub.fapp.models.Final;
import com.mobileappdevelopersclub.fapp.models.FinalResponse;
import com.mobileappdevelopersclub.fapp.models.ScheduleItem;
import com.mobileappdevelopersclub.fapp.models.ScheduleItemRepository;
import com.mobileappdevelopersclub.fapp.transactions.AbsHttpTask;
import com.mobileappdevelopersclub.fapp.util.ScheduleItemUtil;

public class ScheduleFragment extends FappFragment implements OnItemSelectedListener {

	public static final String PREFS_NAME = "UserCASLogin";
	public static final String USERNAME = "Username";
	public static final String PASSWORD = "Password";
	public static final String NOT_SET = "Not Set";

	public static final String TAG = "ScheduleFragment";

	private final String[] SPINNER_DATES = {"December 8" , "December 9" , 
			"December 10" , "December 11" , "December 12" , "December 13",
			"December 14", "December 15", "December 16", "December 17", 
			"December 18" , "December 19" , "December 20" , "December 21"};

	static int mCurrentPosition = 0;

	private String GET_FINAL_URL = "http://mobileappdevelopersclub.com/shellp/ShelLp_Final/";

	static ScheduleFragment context;
	View view; 
	private Activity mActivity;
	ArrayAdapter<String> mSpinnerAdapter;
	private Spinner mSpinner;
	SharedPreferences userInfo;
	LayoutInflater inflater;
	private static List<ScheduleItem> mScheduleItems;
	private static String mCurrentClass;
	private static FragmentManager fm;

	@Inject CouchDbInstance dbInstance;

	public static ScheduleFragment newInstance() {
		ScheduleFragment fragment = new ScheduleFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
		userInfo = getActivity().getSharedPreferences(PREFS_NAME, 0);
		mScheduleItems = new ArrayList<ScheduleItem>();
		context = this;
		//		generateTestClasses();

	}


	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		this.inflater = inflater;
		fm = getFragmentManager();
		setHasOptionsMenu(true);
		view = inflater.inflate(R.layout.schedulefragment_main, null);	

		mSpinner = (Spinner) view.findViewById(R.id.daysOfWeek);
		mSpinnerAdapter = new ArrayAdapter<String>(mActivity ,
				android.R.layout.simple_dropdown_item_1line, android.R.id.text1, 
				SPINNER_DATES);
		mSpinner.setAdapter(mSpinnerAdapter);
		mSpinner.setOnItemSelectedListener(this);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		//Pull in saved schedule items to be displayed
		new CouchDbPullTask().execute();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.main , menu);
		MenuItem addClass = menu.findItem(R.id.action_add_class);
		addClass.setOnMenuItemClickListener(new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				new EnterClassDialogFragment().show(getFragmentManager(), "Entering a new Class");
				return false;
			}

		});

		super.onCreateOptionsMenu(menu, inflater);
	}



	public void fetchClasses(String className) {
		mCurrentClass = className;
		String url = buildFinalGetterUrl(className, "");
		new FetchClassesTask("GET", url).execute();
	}

	private class FetchClassesTask extends AbsHttpTask {

		public FetchClassesTask(String verb, String url) {
			super(verb, url);
		}

		@Override
		protected void onError(String error) {
			// TODO Show dialog/message
			Log.e(TAG, "error on get");
		}

		@Override
		protected void onSuccess(String response) {

			String header = "{ \"finals\":";

			String resWHead = header.concat(response);
			String newResponse = resWHead.concat(" }");

			FinalResponse finalResponse = new Gson().fromJson(
					newResponse, FinalResponse.class);		
			onClassesFound(finalResponse.getFinals());
		}

	}

	private void onClassesFound(List<Final> finals) {

		for(int i = 0 ; i < finals.size(); i++) {
			finals.get(i).setTitle(mCurrentClass);
		}


		FinalResponseDialogFragment.newInstance(finals)
		.show(getFragmentManager(), "Entering a new Class");

	}



	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {

		prepareDailyFragment(position);

	}

	private void prepareDailyFragment(int position){
		mCurrentPosition = position;
		ArrayList<ScheduleItem> todaysList = getTodaysList(position);	

		fm.beginTransaction().replace(R.id.schedule, 
				DailyScheduleFragment.newInstance(position, todaysList)).
				commit();
	}

	private static void refreshDailyFragment(){
		ArrayList<ScheduleItem> todaysList = getTodaysList(mCurrentPosition);	

		fm.beginTransaction().replace(R.id.schedule, 
				DailyScheduleFragment.newInstance(mCurrentPosition, todaysList)).
				commit();
	}


	private static ArrayList<ScheduleItem> getTodaysList(int day) {
		ArrayList<ScheduleItem> todaysItems = new ArrayList<ScheduleItem>();
		String today = Constants.FINAL_DATES[day];

		for(int i=0; i < mScheduleItems.size(); i++) {
			String currDay = ScheduleItemUtil.
					parseScheduleItemDayString(mScheduleItems.get(i));
			if(currDay.equalsIgnoreCase(today)) {
				todaysItems.add(mScheduleItems.get(i));
			}
		}

		return todaysItems;
	}

	private static boolean belongsToCurrentVisibleDay(ScheduleItem item) {
		String today = Constants.FINAL_DATES[mCurrentPosition];

		String currDay = ScheduleItemUtil.
				parseScheduleItemDayString(item);
		if(currDay.equalsIgnoreCase(today)) {
			return true;
		}

		return false;
	}

	private void generateTestClasses() {

		ScheduleItem meeting = new ScheduleItem("It worked!",
				"10:00 pm - 11:15 pm", 
				"Sun, Dec 10" , "The Library" , 
				"Testudo", "group study room");

		ScheduleItem meeting1 = new ScheduleItem("It worked!", "14-Dec" ,
				"10:00 pm - 11:15 pm", "The Library" , 
				"Testudo", "group study room", "Event");


		String tes1 = ScheduleItemUtil.parseScheduleItemDayString(meeting1);
		String tes2 = ScheduleItemUtil.parseScheduleItemDayString(meeting);

		mScheduleItems.add(meeting);
		Constants.broadcastItems.add(meeting);

		for(int i =0 ; i < mScheduleItems.size(); i++){
			new CouchDbCommitTask().execute(mScheduleItems.get(i));
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}


	@SuppressLint("ValidFragment")
	public class EnterClassDialogFragment extends DialogFragment {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder =
					new AlertDialog.Builder(getActivity());
			// Get the layout inflater
			LayoutInflater inflater = getActivity().getLayoutInflater();

			final View view = inflater.inflate(R.layout.add_class_dialog,
					null);
			// Inflate and set the layout for the dialog
			// Pass null as the parent view because its going in the dialog layout
			builder.setView(view)
			// Add action buttons
			.setPositiveButton(R.string.add_class, 
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					EditText className = 
							(EditText) view.findViewById(R.id.className);
					fetchClasses(className.getText().toString());					
				}
			})
			.setNegativeButton(R.string.cancel,
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					EnterClassDialogFragment.this.getDialog().cancel();
				}
			});      
			return builder.create();
		}


	}

	public static class FinalResponseDialogFragment extends DialogFragment {

		List<Final> finals;

		public static FinalResponseDialogFragment newInstance(List<Final> finals) {
			FinalResponseDialogFragment fragment =
					new FinalResponseDialogFragment();
			fragment.finals = finals;
			return fragment;
		}


		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = 
					new AlertDialog.Builder(getActivity());
			// Get the layout inflater
			LayoutInflater inflater = getActivity().getLayoutInflater();


			FinalsResponseAdapter adapter 
			= new FinalsResponseAdapter(this.getActivity(), 0, finals);
			View v = inflater.inflate(R.layout.final_resp_dialog, null);

			ListView mList = (ListView)v.findViewById(R.id.mList);
			mList.setAdapter(adapter);
			mList.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int pos, long arg3) {
					Log.d(TAG, "Adding " + finals.get(pos).getTitle());
					context.new CouchDbCommitTask().execute(finals.get(pos));
					context.mScheduleItems.add(finals.get(pos));
					
					if(belongsToCurrentVisibleDay(finals.get(pos))) {
						refreshDailyFragment();
						FinalResponseDialogFragment.this.getDialog().cancel();
					} else {
						FinalResponseDialogFragment.this.getDialog().cancel();
					}
				}

			});

			// Inflate and set the layout for the dialog
			// Pass null as the parent view because its going in the dialog layout
			builder.setView(v)
			// Add action buttons
			.setTitle("Final dates for " + mCurrentClass)
			//			.setPositiveButton(R.string.add_class, 
			//					new DialogInterface.OnClickListener() {
			//				@Override
			//				public void onClick(DialogInterface dialog, int id) {
			//					//make request for final info
			//				}
			//			})
			.setNegativeButton(R.string.cancel,
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					FinalResponseDialogFragment.this.getDialog().cancel();
				}
			});      
			return builder.create();
		}
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
				if(curr.getTitle().equals(item.getTitle())) {
					exists = true;
				}
			}

			if(!exists) {
				repo.add((ScheduleItem)item);
			}

		}

	}


	private class CouchDbPullTask extends AsyncTask<Void,
	Void, ArrayList<ScheduleItem>> {

		public CouchDbPullTask() {

		}

		@Override
		protected ArrayList<ScheduleItem> doInBackground(Void... params) {

			return getScheduleItems();
		}


		@Override
		protected void onPostExecute(ArrayList<ScheduleItem> result) {
			addToScheduleItems(result);
		}

		private ArrayList<ScheduleItem> getScheduleItems() {

			CouchDbConnector couchDbConnector = dbInstance.createConnector(Constants.DATABASE_NAME, true);
			ScheduleItemRepository repo = new ScheduleItemRepository(couchDbConnector);

			ArrayList<ScheduleItem> items = new ArrayList<ScheduleItem>();


			for(ScheduleItem curr: repo.getAll()) {
				items.add(curr);
			}



			return items;	
		}


	}

	private void addToScheduleItems(List<ScheduleItem> items) {
		mScheduleItems.addAll(items);
	}

	private String buildFinalGetterUrl(String currentClass, String currentSection) {
		StringBuilder sb = new StringBuilder();
		sb.append(GET_FINAL_URL);
		sb.append(currentClass);

		return sb.toString();
	}

}
