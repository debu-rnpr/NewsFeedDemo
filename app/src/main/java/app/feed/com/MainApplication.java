package app.feed.com;

import android.app.Application;

import app.feed.com.ui.injection.component.ApplicationComponent;
import app.feed.com.ui.injection.component.DaggerApplicationComponent;
import app.feed.com.ui.injection.module.ApplicationModule;

/**
 * Created by debu on 15/10/17.
 */

public class MainApplication extends Application {
    private static MainApplication application;
    private ApplicationComponent applicationComponent;

    public static MainApplication getInstance() {
        assert application != null;
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initiateInjector();
    }

    private void initiateInjector(){
        this.applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
