// Code generated by dagger-compiler.  Do not edit.
package com.mobileappdevelopersclub.fapp.models;


import dagger.internal.Binding;
import dagger.internal.ModuleAdapter;
import java.util.Map;
import javax.inject.Provider;

/**
 * A manager of modules and provides adapters allowing for proper linking and
 * instance provision of types served by {@code @Provides} methods.
 */
public final class AndroidModule$$ModuleAdapter extends ModuleAdapter<AndroidModule> {
  private static final String[] INJECTS = { "members/com.mobileappdevelopersclub.fapp.MainActivity", "members/com.mobileappdevelopersclub.fapp.FappApplication", "members/com.mobileappdevelopersclub.fapp.ui.ScheduleFragment", "members/com.mobileappdevelopersclub.fapp.ui.FoodSpecialsFragment", "members/com.mobileappdevelopersclub.fapp.ui.LibraryListFragment", "members/com.mobileappdevelopersclub.fapp.ui.TweetsListFragment", "members/com.mobileappdevelopersclub.fapp.transactions.MotivationalMessageService", "members/com.mobileappdevelopersclub.fapp.ui.EventsListFragment", "members/com.mobileappdevelopersclub.fapp.ui.DailyScheduleFragment", "members/com.mobileappdevelopersclub.fapp.ExtrasActivity", "members/com.mobileappdevelopersclub.fapp.ui.ExtrasFragment", "members/com.mobileappdevelopersclub.fapp.ui.TransportationListFragment", "members/com.mobileappdevelopersclub.fapp.ui.TransportationUrlFragment", "members/com.mobileappdevelopersclub.fapp.ui.HealthResourcesFragment", };
  private static final Class<?>[] STATIC_INJECTIONS = { };
  private static final Class<?>[] INCLUDES = { };

  public AndroidModule$$ModuleAdapter() {
    super(INJECTS, STATIC_INJECTIONS, false /*overrides*/, INCLUDES, true /*complete*/, true /*library*/);
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getBindings(Map<String, Binding<?>> map) {
    map.put("@com.mobileappdevelopersclub.fapp.util.ForApplication()/android.content.Context", new ProvideContextProvidesAdapter(module));
    map.put("org.ektorp.CouchDbInstance", new ProvideCouchDbInstanceProvidesAdapter(module));
  }

  /**
   * A {@code Binder<android.content.Context>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   * 
   * Being a {@code Provider<android.content.Context>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideContextProvidesAdapter extends Binding<android.content.Context>
      implements Provider<android.content.Context> {
    private final AndroidModule module;

    public ProvideContextProvidesAdapter(AndroidModule module) {
      super("@com.mobileappdevelopersclub.fapp.util.ForApplication()/android.content.Context", null, IS_SINGLETON, "com.mobileappdevelopersclub.fapp.models.AndroidModule.provideContext()");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<android.content.Context>}.
     */
    @Override
    public android.content.Context get() {
      return module.provideContext();
    }
  }

  /**
   * A {@code Binder<org.ektorp.CouchDbInstance>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   * 
   * Being a {@code Provider<org.ektorp.CouchDbInstance>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideCouchDbInstanceProvidesAdapter extends Binding<org.ektorp.CouchDbInstance>
      implements Provider<org.ektorp.CouchDbInstance> {
    private final AndroidModule module;

    public ProvideCouchDbInstanceProvidesAdapter(AndroidModule module) {
      super("org.ektorp.CouchDbInstance", null, IS_SINGLETON, "com.mobileappdevelopersclub.fapp.models.AndroidModule.provideCouchDbInstance()");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<org.ektorp.CouchDbInstance>}.
     */
    @Override
    public org.ektorp.CouchDbInstance get() {
      return module.provideCouchDbInstance();
    }
  }
}
