package app.feed.com.ui.injection.component;

import javax.inject.Singleton;

import app.feed.com.MainApplication;
import app.feed.com.data.ApiService;
import app.feed.com.data.DataManager;
import app.feed.com.ui.common.FavouriteManager;
import app.feed.com.ui.injection.module.ApplicationModule;
import dagger.Component;

/**
 * Created by debu on 16/10/17.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MainApplication application);

    ApiService provideApiService();
    DataManager provideDataManager();
    FavouriteManager provideFabManager();
}
