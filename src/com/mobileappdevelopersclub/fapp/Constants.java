package com.mobileappdevelopersclub.fapp;


import java.util.ArrayList;

import android.content.Context;

import com.mobileappdevelopersclub.fapp.models.ScheduleItem;


public class Constants {
	
	public static final String MONDAY = "Monday";
	public static final String TUESDAY = "Tuesday";
	public static final String WEDNESDAY = "Wednesday";
	public static final String THURSDAY = "Thursday";
	public static final String FRIDAY = "Friday";
	
	public static final String DATABASE_NAME = "schedule-items-db";
	
	
	//Hardcoded final dates for testing 
	//TODO: GET request for dates that the final will fall on 
	public static final String[] FINAL_DATES = {"8", "9" , "10", 
			"11" , "12" , "13" , "14", "15", "16", "17", "18" ,
				"19" , "20" , "21"};
	
	public static int pixelsPerHour;
	public static int pixelsPerTenMin;
	public static int pixelsPerFifteenMin;
	private static final float DPS = 16.0f;  // Constant for calculating dp programaticaly 
	
	//NavigationDrawer Option Constants (positions in array)
	public static final int SCHEDULE_OPTION = 0;
	public static final int FOOD_OPTION = 1;
	public static final int EVENTS_OPTION = 2;
	public static final int LIBRARY_OPTION = 3;
	public static final int TWEETS_OPTION = 4;
	public static final int TRANS_OPTION = 5;
	public static final int HEALTH_OPTION = 6;
	public static final int EXTRAS_OPTION = 7;
	
	//hardcoded image urls 
	public static final String PIZZA_KINGDOM = "http://media-cdn.tripadvisor.com/media/photo-s/03/37/fc/fa/pizza-kingdom.jpg";
	public static final String BAGEL_PLACE = "http://mw2.google.com/mw-panoramio/photos/medium/51118223.jpg";
	public static final String RJ_BENTS = "http://shopcollegepark.org/wp-content/dg_images/cc58d4b17e899526fd24be5ac1afacc8-642_428.jpg";
	public static final String DP_DOUGH = "http://www.dpdough.com/images/cuts/circle-logo.png";
	public static final String TEN_RENS = "http://www.wusa9.com/images/640/360/2/assetpool/photogallery/267963/7.28.jpg";
	public static final String STAMP = "http://www.mdturfcouncil.org/admin/uploadfiles/stamp-student-union.jpg";
	public static final String AANNES = "https://fordham.sodexomyway.com/Images/Auntie_Annes_Hand-Rolled_Soft_Pretzels.gif";

	
	
	public static final String TWITTER_LOGO = "https://g.twimg.com/Twitter_logo_blue.png";

	public static final String SHADY_GROVE = "http://www.umbc.edu/shadygrove/features/umbc-shady-grove-header2.jpg";
		
	public static ArrayList<ScheduleItem> broadcastItems;
	
	public static String[] cuteAnimalPhotos = 
		{"http://i.imgur.com/IFD14.jpg",  "http://i.imgur.com/YqlLn.jpg" , 
			"http://i.imgur.com/rnrLzGM.jpg" , "http://i.imgur.com/fPhWx.jpg" , 
			"http://i.imgur.com/6G0JhxF.jpg" , "http://i.imgur.com/yhmkZ.png" ,
			"http://i.imgur.com/ylksC.jpg", "http://i.imgur.com/JvEaFeo.jpg" , 
			"http://i.imgur.com/LZJ1UIK.jpg" , "http://i.imgur.com/l2s1529.jpg" ,
			"http://i.imgur.com/rSf6qRk.jpg" };
	
	public static void initConstants(Context context) {
		
		final float scale = context.getResources().
				getDimension(R.dimen.one_hour_increment);
		//final float scale = 6;
		pixelsPerHour = (int) (DPS * scale + 0.5f);
		pixelsPerTenMin = pixelsPerHour / 6;
		pixelsPerFifteenMin = pixelsPerHour / 4;
		
		broadcastItems = new ArrayList<ScheduleItem>();
	
	}

}
