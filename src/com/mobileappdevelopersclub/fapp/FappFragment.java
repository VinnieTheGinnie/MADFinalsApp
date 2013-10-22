package com.mobileappdevelopersclub.fapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public class FappFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Perform injection so that when this call returns all dependencies will be available for use.
		FappApplication.getObjectGraph(this.getActivity().getApplicationContext()).inject(this);
	}

}
