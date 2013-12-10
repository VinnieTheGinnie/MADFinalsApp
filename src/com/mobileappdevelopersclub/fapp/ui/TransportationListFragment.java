package com.mobileappdevelopersclub.fapp.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.adapters.TransportationItemAdapter;
import com.mobileappdevelopersclub.fapp.models.BusRoute;
import com.mobileappdevelopersclub.fapp.models.BusRouteResponse;
import com.squareup.picasso.Picasso;

public class TransportationListFragment extends FappFragment {
	
	public static final String TAG = "TransportationListFragment";
	
	private Activity mActivity;
	private LayoutInflater mInflater;
	private View mView;
	private ListView mList;
	private static TransportationItemAdapter mAdapter;
	
	
	public static TransportationListFragment newInstance() {
		TransportationListFragment fragment = new TransportationListFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}


	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		this.mInflater = inflater;

		mAdapter = new TransportationItemAdapter(getActivity(), 0,
					new ArrayList<BusRoute>());
		
		mView = inflater.inflate(R.layout.list_layout, null);	
		mList = (ListView) mView.findViewById(R.id.mList);
		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
					TransportationUrlFragment frag = 
							TransportationUrlFragment.
								newInstance(mAdapter.getItem(pos).getPdfUrl());
					getFragmentManager().beginTransaction().
						replace(R.id.main_frame, frag).commit();
				}
			
		});
		
		fetchTransportationData();
		
		
		return mView;
	}
	
	private void fetchTransportationData() {

		Gson gson = new Gson();
		String json = null;
		try {
			json = parseAsString("transportation.json");
		} catch (IOException e) {
			Toast.makeText(this.getActivity(), 
					"Could not parse JSON to String", Toast.LENGTH_SHORT).show();
		}

		BusRouteResponse response = gson.fromJson(json, BusRouteResponse.class);

		addToRouteList(response.getBusRoutes());
	}

	private void addToRouteList(List<BusRoute> routes) {

		mAdapter.addAll(routes);
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

	
		
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
	public static class BusRouteDialogFragment extends DialogFragment {

		int busOption = 0;
		
		public static BusRouteDialogFragment newInstance(int couponPos) {
			BusRouteDialogFragment frag = new BusRouteDialogFragment();
			frag.busOption = couponPos;
			return frag;
		}
		
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = 
					new AlertDialog.Builder(getActivity());
			// Get the layout inflater
			LayoutInflater inflater = getActivity().getLayoutInflater();
			
			View view = inflater.inflate(R.layout.bus_route_dialog, null);
			
			WebView wv = (WebView) view.findViewById(R.id.busRoutePDF);
			BusRoute route = mAdapter.getItem(busOption);
			String pdfUrl = route.getPdfUrl();
			wv.loadUrl(pdfUrl);
			
			// Inflate and set the layout for the dialog
			// Pass null as the parent view because its going in the dialog layout
			builder.setView(view);
			
			
			return builder.create();
		}


	}

	

}
