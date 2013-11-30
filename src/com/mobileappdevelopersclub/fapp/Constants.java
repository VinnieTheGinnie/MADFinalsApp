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
	public static final String[] FINAL_DATES = {"14", "15", "16", "17", "18" ,
														"19" , "20" , "21"};
	
	public static int pixelsPerHour;
	public static int pixelsPerTenMin;
	public static int pixelsPerFifteenMin;
	private static final float DPS = 16.0f;  // Constant for calculating dp programaticaly 
	
	//NavigationDrawer Option Constants (positions in array)
	public static final int SCHEDULE_OPTION = 0;
	public static final int FOOD_OPTION = 1;
	public static final int LIBRARY_OPTION = 3;
	public static final int TWEETS_OPTION = 5;
	
	//hardcoded image urls 
	public static final String PIZZA_KINGDOM = "http://media-cdn.tripadvisor.com/media/photo-s/03/37/fc/fa/pizza-kingdom.jpg";
	public static final String BAGEL_PLACE = "http://mw2.google.com/mw-panoramio/photos/medium/51118223.jpg";
	
	public static final String MCKELDIN = "http://www.gazette.net/storyimage/PN/20111216/NEWS/712169648/EP/1/7/EP-712169648.jpg";
	public static final String TWITTER_LOGO = "https://g.twimg.com/Twitter_logo_blue.png";

	public static final String ART_LIB = "http://www.umd.edu/CampusMaps/pics/146.JPG";
	public static final String ARCHITECTURE_LIB = "http://www.lib.umd.edu/binaries/content/gallery/public/architecturelibrary/arch-library.jpg";
	public static final String CHEM_LIB = "http://www.chem.umd.edu/wp-content/uploads/2009/02/201022_274971239281872_483430466_o-704x3181.jpg";
	public static final String EPSL_LIB = "http://farm8.staticflickr.com/7381/8718560452_54c143cff4_z.jpg";
	public static final String HORNBAKE = "http://www.lib.umd.edu/blogs/special/wp-content/uploads/special/spring.jpg";
	public static final String MSPAL_LIB = "http://www.umd.edu/CampusMaps/pics/386-0.JPG";
	public static final String SHADY_GROVE = "http://www.umbc.edu/shadygrove/features/umbc-shady-grove-header2.jpg";
	public static final String[] UMDLIBS = {MCKELDIN, ART_LIB, ARCHITECTURE_LIB, 
												CHEM_LIB, EPSL_LIB, HORNBAKE, MSPAL_LIB, SHADY_GROVE};
	

	
	public static void initConstants(Context context) {
		
		final float scale = context.getResources().getDimension(R.dimen.one_hour_increment);
		//final float scale = 6;
		pixelsPerHour = (int) (DPS * scale + 0.5f);
		pixelsPerTenMin = pixelsPerHour / 6;
		pixelsPerFifteenMin = pixelsPerHour / 4;
		
		
	
	}

}
