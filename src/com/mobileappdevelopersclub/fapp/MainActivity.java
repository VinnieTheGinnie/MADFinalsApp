package com.mobileappdevelopersclub.fapp;

import javax.inject.Inject;

import org.ektorp.CouchDbInstance;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.couchbase.cblite.router.CBLURLStreamHandlerFactory;
import com.mobileappdevelopersclub.fapp.adapters.MenuListAdapter;
import com.mobileappdevelopersclub.fapp.ui.EventsListFragment;
import com.mobileappdevelopersclub.fapp.ui.FoodSpecialsFragment;
import com.mobileappdevelopersclub.fapp.ui.GpaCalculatorFragment;
import com.mobileappdevelopersclub.fapp.ui.HealthResourcesFragment;
import com.mobileappdevelopersclub.fapp.ui.LibraryListFragment;
import com.mobileappdevelopersclub.fapp.ui.ScheduleFragment;
import com.mobileappdevelopersclub.fapp.ui.TransportationListFragment;
import com.mobileappdevelopersclub.fapp.ui.TweetsListFragment;

public class MainActivity extends FappActivity {

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	String[] subtitle;
	int[] icon;
	private ActionBarDrawerToggle mDrawerToggle;

	public static String[] mDrawerOptions = {"My Finals Schedule" , "Food Specials" ,
		"Events", "Library Information" , "Testudo's Nose", "Bus Routes" , "Mental Health Resources" , "Extras" , "GPA Calculator"};
	public static ListView mDrawerList;
	public static DrawerLayout mDrawerLayout;
	static MenuListAdapter mMenuAdapter; 

	//Option Fragments to Navigate to through drawer 
	ScheduleFragment mScheduleFragment;
	FoodSpecialsFragment mFoodSpecialsFragment;
	LibraryListFragment mLibraryListFragment;
	TweetsListFragment mTweetsListFragment;
	EventsListFragment mEventsListFragment;
	TransportationListFragment mTransportationListFrag;
	HealthResourcesFragment mHealthResourceFragment;
	GpaCalculatorFragment mGpaCalculatorFragment;

	@Inject CouchDbInstance dbInstance;


	{
		CBLURLStreamHandlerFactory.registerSelfIgnoreError();
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_layout_main);
		Constants.initConstants(this);

		//set broadcast receiver
		//		MotivationalMessageService bR = new MotivationalMessageService();
		//		bR.setAlarm(this);

		mTitle = mDrawerTitle = getResources().getString(R.string.app_name);
		getActionBar().setTitle(mTitle);


		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// set a custom shadow that overlays the main content when the drawer opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		// set up the drawer's list view with items and click listener


		subtitle = new String[] { "Subtitle Fragment 2", 
				"Subtitle Fragment 3" , };
		icon = new int[] {0, 0};

		mMenuAdapter = new MenuListAdapter(this, mDrawerOptions, subtitle, icon);

		//  Globals.mDrawerList.setAdapter(new ArrayAdapter<String>(this,
		// 	R.layout.drawer_list_item, Globals.mDrawerOptions));

		mDrawerList.setAdapter(mMenuAdapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


		getActionBar().setDisplayHomeAsUpEnabled(true);
		//		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(
				this,                  /* host Activity */
				mDrawerLayout,         /* DrawerLayout object */
				R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
				R.string.drawer_open,  /* "open drawer" description for accessibility */
				R.string.drawer_close  /* "close drawer" description for accessibility */
				) {
			public void onDrawerClosed(View view) {
				//				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				//				getSupportActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
		};



		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			//
			mScheduleFragment = ScheduleFragment.newInstance();
			getFragmentManager().beginTransaction().replace(R.id.main_frame, mScheduleFragment).commit();
			//			mDrawerList.setItemChecked(Constants.FRAT_DRAWER_POSITION, true);
		}

	}


	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {


		// update the main content by replacing fragments
		if(position == Constants.SCHEDULE_OPTION) {

			mScheduleFragment = ScheduleFragment.newInstance();
			getFragmentManager().beginTransaction().replace(R.id.main_frame, mScheduleFragment).commit();
			mDrawerList.setItemChecked(position, false);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else if(position == Constants.FOOD_OPTION) {

			mFoodSpecialsFragment = FoodSpecialsFragment.newInstance();
			getFragmentManager().beginTransaction().replace(R.id.main_frame, mFoodSpecialsFragment).commit();
			mDrawerList.setItemChecked(position, false);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else if (position == Constants.EVENTS_OPTION) { 

			mEventsListFragment = EventsListFragment.newInstance();
			getFragmentManager().beginTransaction().replace(R.id.main_frame, mEventsListFragment).commit();
			mDrawerList.setItemChecked(position, false);
			mDrawerLayout.closeDrawer(mDrawerList);

		}else if (position == Constants.LIBRARY_OPTION ){

			mLibraryListFragment = LibraryListFragment.newInstance();
			getFragmentManager().beginTransaction().replace(R.id.main_frame, mLibraryListFragment).commit();
			mDrawerList.setItemChecked(position, false);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else if (position == Constants.TWEETS_OPTION ){

			mTweetsListFragment = TweetsListFragment.newInstance();
			getFragmentManager().beginTransaction().replace(R.id.main_frame, mTweetsListFragment).commit();
			mDrawerList.setItemChecked(position, false);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else if(position == Constants.TRANS_OPTION){
			
			mTransportationListFrag = TransportationListFragment.newInstance();
			getFragmentManager().beginTransaction().replace(R.id.main_frame, mTransportationListFrag).commit();
			mDrawerList.setItemChecked(position, false);
			mDrawerLayout.closeDrawer(mDrawerList);
			
		} else if (position == Constants.HEALTH_OPTION ) {
			
			mHealthResourceFragment = HealthResourcesFragment.newInstance();
			getFragmentManager().beginTransaction().replace(R.id.main_frame, mHealthResourceFragment).commit();
			mDrawerList.setItemChecked(position, false);
			mDrawerLayout.closeDrawer(mDrawerList);

		} else if (position == Constants.GPA_OPTION ) {
			
			mGpaCalculatorFragment = GpaCalculatorFragment.newInstance();
			getFragmentManager().beginTransaction().replace(R.id.main_frame, mGpaCalculatorFragment).commit();
			mDrawerList.setItemChecked(position, false);
			mDrawerLayout.closeDrawer(mDrawerList);

		} else if(position == Constants.EXTRAS_OPTION) { 
			
			Intent extrasIntent = new Intent(MainActivity.this, ExtrasActivity.class);
			startActivity(extrasIntent);
			
		}else {
			//do nothing
			mDrawerList.setItemChecked(position, false);
			mDrawerLayout.closeDrawer(mDrawerList);
		}

	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {

			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
