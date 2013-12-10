package com.mobileappdevelopersclub.fapp.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobileappdevelopersclub.fapp.FappFragment;
import com.mobileappdevelopersclub.fapp.R;
import com.mobileappdevelopersclub.fapp.adapters.TweetsListItemAdapter;
import com.mobileappdevelopersclub.fapp.models.Tweet;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TweetsListFragment extends FappFragment {
	
	private View mView;
	private Context context;
	private LayoutInflater mInflater;
	private ListView mList;
	private TweetsListItemAdapter mAdapter;
	private Twitter mTwitter;

	public static TweetsListFragment newInstance() {
		TweetsListFragment fragment = new TweetsListFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this.getActivity();
        mTwitter = getTwitter();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		mInflater = inflater;
		mView = mInflater.inflate(R.layout.list_layout, null);
		
		mAdapter = new TweetsListItemAdapter(getActivity(), 0, new ArrayList<Tweet>());
		mList = (ListView) mView.findViewById(R.id.mList);
		mList.addHeaderView(getHeaderView());
		
		mList.setAdapter(mAdapter);
		
		//createTestTweets();
		TwitterInfoTask tweetTask = new TwitterInfoTask();
		tweetTask.execute();
		
		
		return mView;
	}
	
	private View getHeaderView() {
		TextView tv = (TextView) 
				mInflater.inflate(R.layout.list_view_header, null);
		
		tv.setText(context.getResources().getString(R.string.twitter_header));
		
		return tv;
	}
	
	/** Test for Tweets list view
	 **/

	
	// Twitter Authentication.  Account info in strings.xml
    private Twitter getTwitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(getString(R.string.consumer_key));
        cb.setOAuthConsumerSecret(getString(R.string.consumer_secret));
        cb.setOAuthAccessToken(getString(R.string.access_token));
        cb.setOAuthAccessTokenSecret(getString(R.string.access_token_secret));
        return new TwitterFactory(cb.build()).getInstance();
    }
	
    // Formats tweets for listview
	private void format_tweets(ArrayList<Status> tweets){
		System.out.println(tweets.toString());
		for (Status tweet : tweets){
			System.out.println(tweet.getUser().getName());
			mAdapter.add(new Tweet('@' + tweet.getUser().getScreenName(), tweet.getText(), tweet.getUser().getOriginalProfileImageURLHttps()) );
		}		
	}
	
	// Gets tweets with specified hashtags
	private ArrayList<Status> get_tweets (ArrayList<String> hashes){
		List<Status> statuses = new ArrayList<Status>();
		
		for (String hash: hashes){
			try {
        		statuses.addAll(mTwitter.search(new Query(hash)).getTweets());
    		} catch (Exception e) {
    			System.out.println("oops no tweets found with text:" + hash);
    		}
		}

		return (ArrayList<Status>) statuses;	
	}
	
	private class TwitterInfoTask extends AsyncTask<Object, Void, ArrayList<twitter4j.Status>>{

		@Override
		protected ArrayList<twitter4j.Status> doInBackground(Object... params) {
			final ArrayList<String> list = new ArrayList<String>();
			list.add("#UMDfinals");
			list.add("#umdfinalscountdown");
			list.add("#UMDgoTerps");
			return get_tweets(list);	
		}

		@Override
		protected void onPostExecute(ArrayList<twitter4j.Status> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			format_tweets(result);
			
		}
	
		
	}
	
	
}
