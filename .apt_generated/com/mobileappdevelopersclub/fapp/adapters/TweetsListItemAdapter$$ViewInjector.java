// Generated code from Butter Knife. Do not modify!
package com.mobileappdevelopersclub.fapp.adapters;

import android.view.View;
import butterknife.Views.Finder;

public class TweetsListItemAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.mobileappdevelopersclub.fapp.adapters.TweetsListItemAdapter target, Object source) {
    View view;
    view = finder.findById(source, 2131296315);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296315' for field 'tweetImage' was not found. If this field binding is optional add '@Optional'.");
    }
    target.tweetImage = (android.widget.ImageView) view;
    view = finder.findById(source, 2131296318);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296318' for field 'tweetHashtags' was not found. If this field binding is optional add '@Optional'.");
    }
    target.tweetHashtags = (android.widget.TextView) view;
    view = finder.findById(source, 2131296317);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296317' for field 'tweetText' was not found. If this field binding is optional add '@Optional'.");
    }
    target.tweetText = (android.widget.TextView) view;
    view = finder.findById(source, 2131296316);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296316' for field 'tweetUsername' was not found. If this field binding is optional add '@Optional'.");
    }
    target.tweetUsername = (android.widget.TextView) view;
  }

  public static void reset(com.mobileappdevelopersclub.fapp.adapters.TweetsListItemAdapter target) {
    target.tweetImage = null;
    target.tweetHashtags = null;
    target.tweetText = null;
    target.tweetUsername = null;
  }
}
