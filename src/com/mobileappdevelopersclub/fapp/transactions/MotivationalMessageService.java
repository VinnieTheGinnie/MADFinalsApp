package com.mobileappdevelopersclub.fapp.transactions;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.models.ScheduleItem;

public class MotivationalMessageService extends Service {
	  private Looper mServiceLooper;
	  private ServiceHandler mServiceHandler;
	  Service s;

	  // Handler that receives messages from the thread
	  private final class ServiceHandler extends Handler {
	      public ServiceHandler(Looper looper) {
	          super(looper);
	      }
	      @SuppressLint("NewApi")
		@Override
	      public void handleMessage(Message msg) {
	          // Normally we would do some work here, like download a file.
	          // For our sample, we just sleep for 5 seconds.
	          long endTime = System.currentTimeMillis() + 5*1000;
	          while (System.currentTimeMillis() < endTime) {
	        	   Toast.makeText(s, "msg", Toast.LENGTH_SHORT).show();
	              synchronized (this) {	  
	                  try {
	                      wait(endTime - System.currentTimeMillis());
	                      
	                      
	                      
	                      NotificationCompat.Builder mBuilder =
	                    	        new NotificationCompat.Builder(s)
	                    	        .setSmallIcon(R.drawable.ic_launcher)
	                    	        .setContentTitle("My notification")
	                    	        .setContentText("Hello World!");
	                    	// Creates an explicit intent for an Activity in your app
	                    	Intent resultIntent = new Intent(s, ScheduleItem.class);

	                    	// The stack builder object will contain an artificial back stack for the
	                    	// started Activity.
	                    	// This ensures that navigating backward from the Activity leads out of
	                    	// your application to the Home screen.
	                    	TaskStackBuilder stackBuilder = TaskStackBuilder.create(s);
	                    	// Adds the back stack for the Intent (but not the Intent itself)
	                    	stackBuilder.addParentStack(ScheduleItem.class);
	                    	// Adds the Intent that starts the Activity to the top of the stack
	                    	stackBuilder.addNextIntent(resultIntent);
	                    	PendingIntent resultPendingIntent =
	                    	        stackBuilder.getPendingIntent(
	                    	            0,
	                    	            PendingIntent.FLAG_UPDATE_CURRENT
	                    	        );
	                    	mBuilder.setContentIntent(resultPendingIntent);
	                    	NotificationManager mNotificationManager =
	                    	    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	                    	// mId allows you to update the notification later on.
	                    	mNotificationManager.notify(1, mBuilder.build());
	                      
	                      
	                      
	                      
	                      
	                      
	                      
	  
	                  } catch (Exception e) {
	                  }
	              }
	          }
	          // Stop the service using the startId, so that we don't stop
	          // the service in the middle of handling another job
	          stopSelf(msg.arg1);
	      }
	  }

	  @Override
	  public void onCreate() {
	    // Start up the thread running the service.  Note that we create a
	    // separate thread because the service normally runs in the process's
	    // main thread, which we don't want to block.  We also make it
	    // background priority so CPU-intensive work will not disrupt our UI.
	    HandlerThread thread = new HandlerThread("ServiceStartArguments");
	    thread.start();
	    
	    // Get the HandlerThread's Looper and use it for our Handler 
	    mServiceLooper = thread.getLooper();
	    mServiceHandler = new ServiceHandler(mServiceLooper);
	  }

	  @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
	      Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
	      s = this;
	      // For each start request, send a message to start a job and deliver the
	      // start ID so we know which request we're stopping when we finish the job
	      Message msg = mServiceHandler.obtainMessage();
	      msg.arg1 = startId;
	      mServiceHandler.sendMessage(msg);
	      
	      // If we get killed, after returning from here, restart
	      return START_STICKY;
	  }

	  @Override
	  public IBinder onBind(Intent intent) {
	      // We don't provide binding, so return null
	      return null;
	  }
	  
	  @Override
	  public void onDestroy() {
	    Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show(); 
	  }
	}