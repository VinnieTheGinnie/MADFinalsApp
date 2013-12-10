// Generated code from Butter Knife. Do not modify!
package com.mobileappdevelopersclub.fapp.adapters;

import android.view.View;
import butterknife.Views.Finder;

public class LibraryListItemAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.mobileappdevelopersclub.fapp.adapters.LibraryListItemAdapter target, Object source) {
    View view;
    view = finder.findById(source, 2131296297);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296297' for field 'libraryImage' was not found. If this field binding is optional add '@Optional'.");
    }
    target.libraryImage = (android.widget.ImageView) view;
    view = finder.findById(source, 2131296298);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296298' for field 'libraryName' was not found. If this field binding is optional add '@Optional'.");
    }
    target.libraryName = (android.widget.TextView) view;
    view = finder.findById(source, 2131296299);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296299' for field 'currentDate' was not found. If this field binding is optional add '@Optional'.");
    }
    target.currentDate = (android.widget.TextView) view;
    view = finder.findById(source, 2131296300);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296300' for field 'libraryHours' was not found. If this field binding is optional add '@Optional'.");
    }
    target.libraryHours = (android.widget.TextView) view;
  }

  public static void reset(com.mobileappdevelopersclub.fapp.adapters.LibraryListItemAdapter target) {
    target.libraryImage = null;
    target.libraryName = null;
    target.currentDate = null;
    target.libraryHours = null;
  }
}
