// Generated code from Butter Knife. Do not modify!
package com.mobileappdevelopersclub.fapp.adapters;

import android.view.View;
import butterknife.Views.Finder;

public class HealthResourceAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.mobileappdevelopersclub.fapp.adapters.HealthResourceAdapter target, Object source) {
    View view;
    view = finder.findById(source, 2131296303);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296303' for field 'resourceTitle' was not found. If this field binding is optional add '@Optional'.");
    }
    target.resourceTitle = (android.widget.TextView) view;
    view = finder.findById(source, 2131296304);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296304' for field 'resourceContact' was not found. If this field binding is optional add '@Optional'.");
    }
    target.resourceContact = (android.widget.TextView) view;
    view = finder.findById(source, 2131296305);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296305' for field 'resourceDescription' was not found. If this field binding is optional add '@Optional'.");
    }
    target.resourceDescription = (android.widget.TextView) view;
  }

  public static void reset(com.mobileappdevelopersclub.fapp.adapters.HealthResourceAdapter target) {
    target.resourceTitle = null;
    target.resourceContact = null;
    target.resourceDescription = null;
  }
}
