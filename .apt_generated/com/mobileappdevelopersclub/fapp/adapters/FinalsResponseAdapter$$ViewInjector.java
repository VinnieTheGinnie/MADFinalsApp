// Generated code from Butter Knife. Do not modify!
package com.mobileappdevelopersclub.fapp.adapters;

import android.view.View;
import butterknife.Views.Finder;

public class FinalsResponseAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.mobileappdevelopersclub.fapp.adapters.FinalsResponseAdapter target, Object source) {
    View view;
    view = finder.findById(source, 2131296273);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296273' for field 'examDate' was not found. If this field binding is optional add '@Optional'.");
    }
    target.examDate = (android.widget.TextView) view;
    view = finder.findById(source, 2131296274);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296274' for field 'examTime' was not found. If this field binding is optional add '@Optional'.");
    }
    target.examTime = (android.widget.TextView) view;
    view = finder.findById(source, 2131296277);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296277' for field 'classSection' was not found. If this field binding is optional add '@Optional'.");
    }
    target.classSection = (android.widget.TextView) view;
    view = finder.findById(source, 2131296276);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296276' for field 'examInstructor' was not found. If this field binding is optional add '@Optional'.");
    }
    target.examInstructor = (android.widget.TextView) view;
    view = finder.findById(source, 2131296257);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296257' for field 'className' was not found. If this field binding is optional add '@Optional'.");
    }
    target.className = (android.widget.TextView) view;
    view = finder.findById(source, 2131296275);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296275' for field 'examLocation' was not found. If this field binding is optional add '@Optional'.");
    }
    target.examLocation = (android.widget.TextView) view;
  }

  public static void reset(com.mobileappdevelopersclub.fapp.adapters.FinalsResponseAdapter target) {
    target.examDate = null;
    target.examTime = null;
    target.classSection = null;
    target.examInstructor = null;
    target.className = null;
    target.examLocation = null;
  }
}
