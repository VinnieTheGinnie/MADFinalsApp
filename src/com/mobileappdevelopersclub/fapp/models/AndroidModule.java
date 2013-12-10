/*
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mobileappdevelopersclub.fapp.models;


import java.io.IOException;
import javax.inject.Singleton;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.impl.StdCouchDbInstance;
import android.content.Context;
import android.util.Log;
import com.couchbase.cblite.CBLServer;
import com.couchbase.cblite.ektorp.CBLiteHttpClient;
import com.mobileappdevelopersclub.fapp.ExtrasActivity;
import com.mobileappdevelopersclub.fapp.FappApplication;
import com.mobileappdevelopersclub.fapp.MainActivity;
import com.mobileappdevelopersclub.fapp.transactions.MotivationalMessageService;
import com.mobileappdevelopersclub.fapp.ui.DailyScheduleFragment;
import com.mobileappdevelopersclub.fapp.ui.EventsListFragment;
import com.mobileappdevelopersclub.fapp.ui.ExtrasFragment;
import com.mobileappdevelopersclub.fapp.ui.FoodSpecialsFragment;
import com.mobileappdevelopersclub.fapp.ui.HealthResourcesFragment;
import com.mobileappdevelopersclub.fapp.ui.LibraryListFragment;
import com.mobileappdevelopersclub.fapp.ui.ScheduleFragment;
import com.mobileappdevelopersclub.fapp.ui.TransportationListFragment;
import com.mobileappdevelopersclub.fapp.ui.TransportationUrlFragment;
import com.mobileappdevelopersclub.fapp.ui.TweetsListFragment;
import com.mobileappdevelopersclub.fapp.util.ForApplication;

import dagger.Module;
import dagger.Provides;

/**
 * A module for Android-specific dependencies which require a {@link Context} or
 * {@link android.app.Application} to create.
 */
@Module(
		injects= { MainActivity.class, FappApplication.class, 
					ScheduleFragment.class, FoodSpecialsFragment.class,
					LibraryListFragment.class, TweetsListFragment.class, 
					MotivationalMessageService.class, EventsListFragment.class,
					DailyScheduleFragment.class, ExtrasActivity.class , 
					ExtrasFragment.class, TransportationListFragment.class, 
					TransportationUrlFragment.class, HealthResourcesFragment.class} , 
		library = true
		)

public class AndroidModule {

	public final static String TAG = "AndroidModule";

	private final Context context;

	public AndroidModule(Context context) {
		this.context = context;
	}

	/**
	 * Allow the application context to be injected but require that it be annotated with
	 * {@link ForApplication @Annotation} to explicitly differentiate it from an activity context.
	 */
	
	@Provides @Singleton @ForApplication Context provideContext() {
		return context;
	}

	
	@Provides @Singleton CouchDbInstance provideCouchDbInstance() {
		String filesDir = context.getFilesDir().getAbsolutePath();
		
		try {
			CBLServer myServer = new CBLServer(filesDir);
			HttpClient httpClient = new CBLiteHttpClient(myServer);
			return new StdCouchDbInstance(httpClient);
		} catch (IOException e) {
			Log.e(TAG, "Caught IO Exception");
			return null;
		}
	}
}
