package com.mobileappdevelopersclub.fapp.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;

public class TransportationUrlFragment  extends FappFragment {
	
	public static final String TAG = "TransportationUrlFragment";
	
	private Activity mActivity;
	private LayoutInflater mInflater;
	private String url;
	private View mView;
	
	
	public static TransportationUrlFragment newInstance(String url) {
		TransportationUrlFragment fragment = new TransportationUrlFragment();
		fragment.url = url;
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

		
		mView = inflater.inflate(R.layout.bus_route_dialog, null);	
		WebView wv = (WebView) mView.findViewById(R.id.busRoutePDF);
		wv.getSettings().setJavaScriptEnabled(true);         
		wv.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url);
		
		return mView;
	}
		
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
}
