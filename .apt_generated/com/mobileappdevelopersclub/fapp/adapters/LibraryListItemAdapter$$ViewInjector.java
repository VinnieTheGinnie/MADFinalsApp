// Generated code from Butter Knife. Do not modify!
package com.mobileappdevelopersclub.fapp.adapters;

import android.view.View;
import butterknife.Views.Finder;

public class LibraryListItemAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.mobileappdevelopersclub.fapp.adapters.LibraryListItemAdapter target, Object source) {
    View view;
    view = finder.findById(source, 2131296289);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296289' for field 'libraryHours' was not found. If this field binding is optional add '@Optional'.");
    }
    target.libraryHours = (android.widget.TextView) view;
    view = finder.findById(source, 2131296288);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296288' for field 'currentDate' was not found. If this field binding is optional add '@Optional'.");
    }
    target.currentDate = (android.widget.TextView) view;
    view = finder.findById(source, 2131296287);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296287' for field 'libraryName' was not found. If this field binding is optional add '@Optional'.");
    }
    target.libraryName = (android.widget.TextView) view;
    view = finder.findById(source, 2131296286);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296286' for field 'libraryImage' was not found. If this field binding is optional add '@Optional'.");
    }
    target.libraryImage = (android.widget.ImageView) view;
  }

  public static void reset(com.mobileappdevelopersclub.fapp.adapters.LibraryListItemAdapter target) {
    target.libraryHours = null;
    target.currentDate = null;
    target.libraryName = null;
    target.libraryImage = null;
  }
}
