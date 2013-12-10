// Generated code from Butter Knife. Do not modify!
package com.mobileappdevelopersclub.fapp.adapters;

import android.view.View;
import butterknife.Views.Finder;

public class TransportationItemAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.mobileappdevelopersclub.fapp.adapters.TransportationItemAdapter target, Object source) {
    View view;
    view = finder.findById(source, 2131296317);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296317' for field 'busRouteTitle' was not found. If this field binding is optional add '@Optional'.");
    }
    target.busRouteTitle = (android.widget.TextView) view;
  }

  public static void reset(com.mobileappdevelopersclub.fapp.adapters.TransportationItemAdapter target) {
    target.busRouteTitle = null;
  }
}
