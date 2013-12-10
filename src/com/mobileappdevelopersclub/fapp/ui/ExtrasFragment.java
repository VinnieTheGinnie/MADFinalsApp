package com.mobileappdevelopersclub.fapp.ui;

import java.net.URLEncoder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.Views;

import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.R;
import com.squareup.picasso.Picasso;

public class ExtrasFragment extends Fragment {
	/**
	 * The argument key for the page number this fragment represents.
	 */
	public static final String ARG_PAGE = "page";

	
	@InjectView(R.id.extrasImage) ImageView extrasImage;

	private int mPageNumber;

	/**
	 * Factory method for this fragment class. Constructs a new fragment for the given page number.
	 */
	public static ExtrasFragment create(int pageNumber) {
		ExtrasFragment fragment = new ExtrasFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE, pageNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public ExtrasFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPageNumber = getArguments().getInt(ARG_PAGE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout containing a title and body text.	
		View rootView = null;
		if(mPageNumber == Constants.cuteAnimalPhotos.length) {
			rootView = inflater.inflate(R.layout.about_us, null);
			
			TextView emailLink = (TextView) rootView.findViewById(R.id.feedbackEmail);
			emailLink.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					String uriText =
						    "mailto:umdfinalsapp@gmail.com" + 
						    "?subject=" + URLEncoder.encode("Finals App Feedback");
						Uri uri = Uri.parse(uriText);
						Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
						sendIntent.setData(uri);
						startActivity(Intent.createChooser(sendIntent, "Send email")); 
				}
				
			});
			
			
		} else {
			rootView = inflater.inflate(R.layout.extras_fragment, null);
			
			Views.inject(this, rootView);
			
			Picasso.with(getActivity()).load(Constants.cuteAnimalPhotos[mPageNumber])
				.into(extrasImage);
		}
		
		return rootView;
	}

	/**
	 * Returns the page number represented by this fragment object.
	 */
	public int getPageNumber() {
		return mPageNumber;
	}
}
