// Generated code from Butter Knife. Do not modify!
package com.mobileappdevelopersclub.fapp.adapters;

import android.view.View;
import butterknife.Views.Finder;

public class LibraryListItemAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.mobileappdevelopersclub.fapp.adapters.LibraryListItemAdapter target, Object source) {
    View view;
    view = finder.findById(source, 2131296272);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296272' for field 'libraryImage' was not found. If this field binding is optional add '@Optional'.");
    }
    target.libraryImage = (android.widget.ImageView) view;
    view = finder.findById(source, 2131296273);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296273' for field 'libraryName' was not found. If this field binding is optional add '@Optional'.");
    }
    target.libraryName = (android.widget.TextView) view;
    view = finder.findById(source, 2131296275);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296275' for field 'libraryName' was not found. If this field binding is optional add '@Optional'.");
    }
    target.libraryName = (android.widget.TextView) view;
    view = finder.findById(source, 2131296276);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296276' for field 'libraryLocation' was not found. If this field binding is optional add '@Optional'.");
    }
    target.libraryLocation = (android.widget.TextView) view;
<<<<<<< HEAD
    view = finder.findById(source, 2131296277);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296277' for field 'libraryHours' was not found. If this field binding is optional add '@Optional'.");
    }
    target.libraryHours = (android.widget.TextView) view;
    view = finder.findById(source, 2131296274);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296274' for field 'libraryImage' was not found. If this field binding is optional add '@Optional'.");
    }
    target.libraryImage = (android.widget.ImageView) view;
  }

  public static void reset(com.mobileappdevelopersclub.fapp.adapters.LibraryListItemAdapter target) {
    target.libraryName = null;
    target.libraryLocation = null;
    target.libraryHours = null;
    target.libraryImage = null;
=======
  }

  public static void reset(com.mobileappdevelopersclub.fapp.adapters.LibraryListItemAdapter target) {
    target.libraryImage = null;
    target.libraryName = null;
    target.libraryHours = null;
    target.libraryLocation = null;
>>>>>>> 45c76663ee26371e9e005dc3fbd08624305bc72f
  }
}
