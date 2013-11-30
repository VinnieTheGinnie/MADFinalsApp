// Generated code from Butter Knife. Do not modify!
package com.mobileappdevelopersclub.fapp.adapters;

import android.view.View;
import butterknife.Views.Finder;

public class FinalsResponseAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.mobileappdevelopersclub.fapp.adapters.FinalsResponseAdapter target, Object source) {
    View view;
    view = finder.findById(source, 2131296270);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296270' for field 'className' was not found. If this field binding is optional add '@Optional'.");
    }
    target.className = (android.widget.TextView) view;
  }

  public static void reset(com.mobileappdevelopersclub.fapp.adapters.FinalsResponseAdapter target) {
    target.className = null;
  }
}
