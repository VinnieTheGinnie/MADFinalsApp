// Generated code from Butter Knife. Do not modify!
package com.mobileappdevelopersclub.fapp.adapters;

import android.view.View;
import butterknife.Views.Finder;

public class FoodItemAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.mobileappdevelopersclub.fapp.adapters.FoodItemAdapter target, Object source) {
    View view;
    view = finder.findById(source, 2131296270);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296270' for field 'resterauntName' was not found. If this field binding is optional add '@Optional'.");
    }
    target.resterauntName = (android.widget.TextView) view;
    view = finder.findById(source, 2131296269);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296269' for field 'resterauntImage' was not found. If this field binding is optional add '@Optional'.");
    }
    target.resterauntImage = (android.widget.ImageView) view;
    view = finder.findById(source, 2131296271);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296271' for field 'resterauntSpecial' was not found. If this field binding is optional add '@Optional'.");
    }
    target.resterauntSpecial = (android.widget.TextView) view;
  }

  public static void reset(com.mobileappdevelopersclub.fapp.adapters.FoodItemAdapter target) {
    target.resterauntName = null;
    target.resterauntImage = null;
    target.resterauntSpecial = null;
  }
}
