package com.mobileappdevelopersclub.fapp;

import android.app.Application;
import android.content.Context;

import com.mobileappdevelopersclub.fapp.models.AndroidModule;

import dagger.ObjectGraph;

public class FappApplication extends Application {

    private static Context sAppContext;
    private static ObjectGraph sObjectGraph;

    public static ObjectGraph getObjectGraph(Context context) {
        if (sObjectGraph == null){
            sAppContext = context;
            sObjectGraph = ObjectGraph.create(new AndroidModule(context));
        }
        return sObjectGraph;
    }

    public static Context getContext() {
        return sAppContext;
    }

    @Override
    public void onCreate() {
        sObjectGraph = null;
        getObjectGraph(this).inject(this);
    }

    @Override
    public void onTerminate() {
        sObjectGraph = null;
        sAppContext = null;
    }
    
}
