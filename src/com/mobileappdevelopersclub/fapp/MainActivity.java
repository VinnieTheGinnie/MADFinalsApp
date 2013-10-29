package com.mobileappdevelopersclub.fapp;

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
import com.mobileappdevelopersclub.fapp.ui.FoodSpecialsFragment;
import com.mobileappdevelopersclub.fapp.ui.ScheduleFragment;

public class MainActivity extends FappActivity {

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	String[] subtitle;
	int[] icon;
	private ActionBarDrawerToggle mDrawerToggle;

	public static String[] mDrawerOptions = {"Schedule" , "Food" , "Events" 
		, "Libraries" , "Transportation", "Extras"};
	public static ListView mDrawerList;
	public static DrawerLayout mDrawerLayout;
	static MenuListAdapter mMenuAdapter;
	
	//Option Fragments to Navigate to through drawer 
	ScheduleFragment mScheduleFragment;
	FoodSpecialsFragment mFoodSpecialsFragment;
	

	{
		CBLURLStreamHandlerFactory.registerSelfIgnoreError();
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_layout_main);
		Constants.initConstants(this);

		mTitle = mDrawerTitle = "Fapp";
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
		} else {
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
