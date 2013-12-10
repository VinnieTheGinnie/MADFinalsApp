package com.mobileappdevelopersclub.fapp.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.adapters.HealthResourceAdapter;
import com.mobileappdevelopersclub.fapp.models.HealtResourcesResponse;
import com.mobileappdevelopersclub.fapp.models.HealthResource;

public class HealthResourcesFragment extends FappFragment {

	private View mView;
	private static Context context;
	private ListView mList;
	private HealthResourceAdapter mAdapter;

	public static HealthResourcesFragment newInstance() {
		HealthResourcesFragment fragment = new HealthResourcesFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this.getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);

		mView = inflater.inflate(R.layout.list_layout , null);

		mAdapter = new HealthResourceAdapter(getActivity(), 0,
				new ArrayList<HealthResource>());	
		mList = (ListView) mView.findViewById(R.id.mList);
		mList.setAdapter(mAdapter);
	
		fetchHealthResourceData();


		return mView;
	}



	private void fetchHealthResourceData() {

		Gson gson = new Gson();
		String json = null;
		try {
			json = parseAsString("health_resources.json");
		} catch (IOException e) {
			Toast.makeText(this.getActivity(), 
					"Could not parse JSON to String", Toast.LENGTH_SHORT).show();
		}

		HealtResourcesResponse response = gson.fromJson(json, HealtResourcesResponse.class);

		addToResourcesList(response.getHealthResources());
	}

	private void addToResourcesList(List<HealthResource> resources) {

		mAdapter.addAll(resources);
		mAdapter.notifyDataSetChanged();

	}


	public String parseAsString(String filename) throws IOException {

		AssetManager assetManager = this.getActivity().getAssets();

		InputStream in = assetManager.open(filename);

		int size = in.available();
		byte[] buffer = new byte[size];

		in.read(buffer);
		in.close();

		String fileAsString = new String(buffer);

		return fileAsString;

	}
}
