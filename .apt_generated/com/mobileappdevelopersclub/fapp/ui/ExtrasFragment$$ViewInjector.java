// Generated code from Butter Knife. Do not modify!
package com.mobileappdevelopersclub.fapp.ui;

import android.view.View;
import butterknife.Views.Finder;

public class ExtrasFragment$$ViewInjector {
  public static void inject(Finder finder, final com.mobileappdevelopersclub.fapp.ui.ExtrasFragment target, Object source) {
    View view;
    view = finder.findById(source, 2131296281);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296281' for field 'extrasImage' was not found. If this field binding is optional add '@Optional'.");
    }
    target.extrasImage = (android.widget.ImageView) view;
  }

  public static void reset(com.mobileappdevelopersclub.fapp.ui.ExtrasFragment target) {
    target.extrasImage = null;
  }
}
