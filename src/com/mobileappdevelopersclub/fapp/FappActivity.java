package com.mobileappdevelopersclub.fapp;

import android.app.Activity;
import android.os.Bundle;

public abstract class FappActivity extends Activity  {

	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Perform injection so that when this call returns all dependencies will be available for use.

		FappApplication.getObjectGraph(this.getApplicationContext()).inject(this);	  
	}


}