package com.mobileappdevelopersclub.fapp.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.MainActivity;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.models.ScheduleItem;
import com.mobileappdevelopersclub.fapp.models.ScheduleItemRepository;
import com.mobileappdevelopersclub.fapp.util.ScheduleItemUtil;

public class MotivationalMessageService extends BroadcastReceiver{

	
	
	private final static String TAG = "MotivationalMessageService";
	private Context mContext;
	private final long duration = 5*1000*60; //5 mins
//	private long duration = 1000*10; //10 sec
	private static final int NUM_MINS = 1;
	int mId = 0;
			
	@SuppressLint("NewApi")
	@Override
	public void onReceive(Context context, Intent intent) {
		//run to check for updates

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		
		Log.d(TAG, "Year " + year);
		Log.d(TAG, "month " + month);
		Log.d(TAG, "day " + day);
		
		//if current year and current month 
		//TODO: must update for future releases 
		if(year == 2013 && month == Calendar.DECEMBER) {
			
			
			for(int i = 0; i < Constants.broadcastItems.size();i++) {
				ScheduleItem curr = Constants.broadcastItems.get(i);
				
				if(curr.getDay().contains(String.valueOf(day))) {
					Log.d(TAG, "day is same");
					int timeDiff = Integer.parseInt(ScheduleItemUtil.
							getTimeStart(curr)) - ((hour * 100) + min);
					
					Log.d(TAG, "class start time: " + ScheduleItemUtil.
							getTimeStart(curr));
					Log.d(TAG, "hour: " + ((hour * 100) + min));
					Log.d(TAG, "Time diff: " + timeDiff);
					if(timeDiff >= 200 && timeDiff <= 205){
						Log.d(TAG, "Time diff is same");
						//SEND NOTIFICATION
						
						NotificationCompat.Builder mBuilder =
								new NotificationCompat.Builder(context)
						.setSmallIcon(R.drawable.ic_launcher)
						.setContentTitle(curr.getTitle())
						.setContentText(curr.getDay() +  " " + 
								curr.getTime() + " " + curr.getLocation());
						// Creates an explicit intent for an Activity in your app
						Intent resultIntent = new Intent(context,
								MainActivity.class);

						// The stack builder object will 
						//contain an artificial back stack for the
						// started Activity.
						// This ensures that navigating backward 
						// from the Activity leads out of
						// your application to the Home screen.
						TaskStackBuilder stackBuilder = 
								TaskStackBuilder.create(context);
						// Adds the back stack for the Intent
						// (but not the Intent itself)
						stackBuilder.addParentStack(MainActivity.class);
						// Adds the Intent that starts the Activity 
						// to the top of the stack
						stackBuilder.addNextIntent(resultIntent);
						PendingIntent resultPendingIntent =
								stackBuilder.getPendingIntent(
										0,
										PendingIntent.FLAG_UPDATE_CURRENT
										);
						mBuilder.setContentIntent(resultPendingIntent);
						NotificationManager mNotificationManager =
						(NotificationManager)
						context.getSystemService(Context.NOTIFICATION_SERVICE);
						// mId allows you to update the notification later on.

						mNotificationManager.notify(mId, mBuilder.build());
						mId++;
					}
					
				}
			}
		}
	}


	public void setAlarm(Context context){
		mContext = context;
		
		
		if(mContext == null) {
			Log.d("MotivationalMessageService", "Context is null" );
		}

		AlarmManager am = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
		Intent i = new Intent(context, MotivationalMessageService.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
		//TODO: decide the best time interval to check for upcoming finals 

		//		duration = NUM_MINS*1000*60;
		//for testing purposes, set for every 10 secs
		am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, duration, duration, pi);
	}

	public void cancelAlarm(Context context){
		Intent intent = new Intent(context, MotivationalMessageService.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(pi);
	}


}
