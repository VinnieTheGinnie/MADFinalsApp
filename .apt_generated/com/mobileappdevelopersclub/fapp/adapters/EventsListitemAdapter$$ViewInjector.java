// Generated code from Butter Knife. Do not modify!
package com.mobileappdevelopersclub.fapp.adapters;

import android.view.View;
import butterknife.Views.Finder;

public class EventsListitemAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.mobileappdevelopersclub.fapp.adapters.EventsListitemAdapter target, Object source) {
    View view;
    view = finder.findById(source, 2131296277);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296277' for field 'dayAndTime' was not found. If this field binding is optional add '@Optional'.");
    }
    target.dayAndTime = (android.widget.TextView) view;
    view = finder.findById(source, 2131296279);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296279' for field 'description' was not found. If this field binding is optional add '@Optional'.");
    }
    target.description = (android.widget.TextView) view;
    view = finder.findById(source, 2131296278);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296278' for field 'location' was not found. If this field binding is optional add '@Optional'.");
    }
    target.location = (android.widget.TextView) view;
    view = finder.findById(source, 2131296275);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296275' for field 'title' was not found. If this field binding is optional add '@Optional'.");
    }
    target.title = (android.widget.TextView) view;
    view = finder.findById(source, 2131296280);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296280' for field 'overflow' was not found. If this field binding is optional add '@Optional'.");
    }
    target.overflow = (android.widget.ImageView) view;
  }

  public static void reset(com.mobileappdevelopersclub.fapp.adapters.EventsListitemAdapter target) {
    target.dayAndTime = null;
    target.description = null;
    target.location = null;
    target.title = null;
    target.overflow = null;
  }
}
