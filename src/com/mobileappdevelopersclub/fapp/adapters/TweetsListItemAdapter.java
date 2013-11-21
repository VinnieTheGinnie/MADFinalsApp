package com.mobileappdevelopersclub.fapp.adapters;

import java.util.List;

import com.mobileappdevelopersclub.fapp.Constants;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.models.Tweet;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;
import butterknife.Views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class TweetsListItemAdapter extends ArrayAdapter<Tweet> {
	
	private Context context;
	private List<Tweet> mTweets;
	
	@InjectView(R.id.tweetImage) ImageView tweetImage;
	@InjectView(R.id.tweetUsername) TextView tweetUsername;
	@InjectView(R.id.tweetText) TextView tweetText;
	@InjectView(R.id.tweetHashtags) TextView tweetHashtags;
	
	public TweetsListItemAdapter(Context context, int resource, List<Tweet> objects){
		super(context, resource, objects);
		this.context = context;
		this.mTweets = objects;
	}
	
	@Override
	public int getCount() {
		return mTweets == null ? 0 : mTweets.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		if(convertView == null){
			convertView = View.inflate(context, R.layout.tweets_list_item, null);
		} else {
			convertView.getTag();
		}
		
		//Inject views (see android butterkinfe)
		Views.inject(this, convertView);
		
		Tweet currTweet = mTweets.get(position);
		
		Picasso.with(context).load(Constants.MCKELDIN).into(tweetImage);
		
		tweetUsername.setText(currTweet.getUsername());
		tweetText.setText(currTweet.getText());
		
		String formatted_hashtags;
		formatted_hashtags = "";
		for (int i = 0; i < currTweet.getHashTags().size(); i++){
			formatted_hashtags.concat(" " + currTweet.getHashTags().get(i));
		}
		System.out.print(formatted_hashtags.toString());
		tweetHashtags.setText(formatted_hashtags);
		
		return convertView;
		
	}
}
