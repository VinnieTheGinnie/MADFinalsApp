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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.models.ScheduleItem;
import com.mobileappdevelopersclub.fapp.models.ScheduleItemRepository;

public class ScheduleFragment extends FappFragment implements OnItemSelectedListener {

	public static final String PREFS_NAME = "UserCASLogin";
	public static final String USERNAME = "Username";
	public static final String PASSWORD = "Password";
	public static final String NOT_SET = "Not Set";
	
	public static final String TAG = "ScheduleFragment";

	//Constants 

	View view; 
	private Activity mActivity;
	ArrayAdapter<String> mSpinnerAdapter;
	private Spinner mSpinner;
	SharedPreferences userInfo;
	LayoutInflater inflater;
	private List<ScheduleItem> mScheduleItems;
	
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
		generateTestClasses();
	}


	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		this.inflater = inflater;
		//		Schedule Feature not yet implemented 
		//		if(userInfo.getString(USERNAME, NOT_SET).equals(NOT_SET) || userInfo.getString(PASSWORD, NOT_SET).equals(NOT_SET) ) {
		//			//This code should only run on 1st run of application 
		//
		//			view = inflater.inflate(R.layout.login_layout, null);
		//			final EditText username = (EditText) view.findViewById(R.id.email_login);
		//			final EditText passWord = (EditText) view.findViewById(R.id.password_login);
		//			view.findViewById(R.id.submit).setOnTouchListener(new OnTouchListener(){
		//
		//				@Override
		//				public boolean onTouch(View v, MotionEvent event) {
		//					String user = username.getText().toString();
		//					String pass = passWord.getText().toString();
		//
		//
		//					if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)) {
		//						SharedPreferences userInfo = getActivity().getSharedPreferences(PREFS_NAME, 0);
		//						SharedPreferences.Editor editor = userInfo.edit();
		//						editor.putString(USERNAME, user);
		//						editor.putString(PASSWORD, pass);
		//
		//						editor.commit();
		//						
		//						testInflate();
		//
		//					} else {
		//						Toast.makeText(mActivity, "Username or Password Not entered", Toast.LENGTH_SHORT).show();
		//					}
		//					return true;
		//					
		//				}});
		//
		//		} else {
		setHasOptionsMenu(true);
		view = inflater.inflate(R.layout.schedulefragment_main, null);	
		
		mSpinner = (Spinner) view.findViewById(R.id.daysOfWeek);
		//TODO: custom spinner adapter 
		mSpinnerAdapter = new ArrayAdapter<String>(mActivity , android.R.layout.simple_dropdown_item_1line, android.R.id.text1, Constants.FINAL_DATES);
		mSpinner.setAdapter(mSpinnerAdapter);
		mSpinner.setOnItemSelectedListener(this);
		//			fetchClasses();
		//		}
		
		return view;
	}
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
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
	
	



	//	private void testInflate(){
	//		Toast.makeText(mActivity, "Trying to inflate", Toast.LENGTH_SHORT).show();
	//		setHasOptionsMenu(true);
	//		view = inflater.inflate(R.layout.schedulefragment_main, null);
	//
	//		mSpinner = (Spinner) view.findViewById(R.id.daysOfWeek);
	//		mSpinnerAdapter = new ArrayAdapter<String>(Globals.actionBar.getThemedContext(),
	//				android.R.layout.simple_spinner_item, android.R.id.text1, daysOfWeek);
	//		mSpinner.setAdapter(mSpinnerAdapter);
	//		mSpinner.setOnItemSelectedListener(this);
	//		view.refreshDrawableState();
	////		fetchClasses();
	//	}

//	public void fetchClasses() {
//		new FetchClassesTask("GET", UMDClassResponse.getUserUrl()).execute();
//	}
//
//	private class FetchClassesTask extends AbsHttpTask {
//
//		public FetchClassesTask(String verb, String url) {
//			super(verb, url);
//		}
//
//		@Override
//		protected void onError(String error) {
//			// TODO Show dialog/message
//
//		}
//
//		@Override
//		protected void onSuccess(String response) {
//
//			UMDClassResponse classResponse = new Gson().fromJson(
//					response, UMDClassResponse.class);
//			onClassesFound(classResponse.getClasses());
//		}
//
//	}
//
//	private void onClassesFound(List<UMDClass> classes) {
//		//TODO: Add classes to the view
//		mClasses = classes;
//	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		//Pull in saved schedule items to be displayed
		new CouchDbPullTask().execute();
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
		FragmentManager fm = getFragmentManager();
		ArrayList<ScheduleItem> todaysList = getTodaysList(position);		
		fm.beginTransaction().replace(R.id.schedule, DailyScheduleFragment.newInstance(position, todaysList)).commit();
	}
	

	private ArrayList<ScheduleItem> getTodaysList(int day) {
		ArrayList<ScheduleItem> todaysItems = new ArrayList<ScheduleItem>();
		String today = Constants.FINAL_DATES[day];

		for(int i=0; i < mScheduleItems.size(); i++) {
			ScheduleItem curr = mScheduleItems.get(i);
			if(today.contains(curr.getDate())) {
				todaysItems.add(curr);
			}
		}
		
		return todaysItems;
	}

	private void generateTestClasses() {

		ScheduleItem meeting = new ScheduleItem("Final 1", "1000", "1050", "December 15");
		
		ScheduleItem meeting1 = new ScheduleItem("Final 2", "1100", "1150", "December 15");
		
		ScheduleItem meeting2 = new ScheduleItem("Final 3", "1200", "1400", "December 15");
		
		ScheduleItem meeting3 = new ScheduleItem("Final 4", "1600", "1700", "December 15");

		mScheduleItems = new ArrayList<ScheduleItem>();
		mScheduleItems.add(meeting);
		mScheduleItems.add(meeting1);
		mScheduleItems.add(meeting2);
		mScheduleItems.add(meeting3);

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
	}
	
	
	public static class EnterClassDialogFragment extends DialogFragment {
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
		    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		    // Get the layout inflater
		    LayoutInflater inflater = getActivity().getLayoutInflater();

		    // Inflate and set the layout for the dialog
		    // Pass null as the parent view because its going in the dialog layout
		    builder.setView(inflater.inflate(R.layout.add_class_dialog, null))
		    // Add action buttons
		           .setPositiveButton(R.string.add_class, new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		                  //make request for final info
		               }
		           })
		           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int id) {
		                   EnterClassDialogFragment.this.getDialog().cancel();
		               }
		           });      
		    return builder.create();
		}

		
	}
	
	private class CouchDbPullTask extends AsyncTask<Void, Void, ArrayList<ScheduleItem>> {

		public CouchDbPullTask() {

		}

		@Override
		protected ArrayList<ScheduleItem> doInBackground(Void... params) {

			return getScheduleItems();
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

}
