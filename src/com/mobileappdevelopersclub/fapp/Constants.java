package com.mobileappdevelopersclub.fapp;

import android.content.Context;


public class Constants {
	
	public static final String MONDAY = "Monday";
	public static final String TUESDAY = "Tuesday";
	public static final String WEDNESDAY = "Wednesday";
	public static final String THURSDAY = "Thursday";
	public static final String FRIDAY = "Friday";
	
	public static final String DATABASE_NAME = "schedule-items-db";
	
	
	//Hardcoded final dates for testing 
	//TODO: GET request for dates that the final will fall on 
	public static final String[] FINAL_DATES = {"December 14", "December 15", "December 16", "December 17", "December 18" ,
														"December 19" , "December 20" , "December 21"};
	
	public static int pixelsPerHour;
	public static int pixelsPerTenMin;
	public static int pixelsPerFifteenMin;
	private static final float DPS = 16.0f;  // Constant for calculating dp programaticaly 
	
	//NavigationDrawer Option Constants 
	public static final int SCHEDULE_OPTION = 0;
	public static final int FOOD_OPTION = 1;
	public static final int LIBRARY_OPTION = 3;
	
	//hardcoded image urls 
	public static final String PIZZA_KINGDOM = "http://media-cdn.tripadvisor.com/media/photo-s/03/37/fc/fa/pizza-kingdom.jpg";
	public static final String BAGEL_PLACE = "http://mw2.google.com/mw-panoramio/photos/medium/51118223.jpg";
	
	public static final String MCKELDIN = "http://www.gazette.net/storyimage/PN/20111216/NEWS/712169648/EP/1/7/EP-712169648.jpg";
	
	public static void initConstants(Context context) {
		final float scale = context.getResources().getDimension(R.dimen.one_hour_increment);
		
		pixelsPerHour = (int) (DPS * scale + 0.5f);
		pixelsPerTenMin = pixelsPerHour / 6;
		pixelsPerFifteenMin = pixelsPerHour / 4;
	}

}
