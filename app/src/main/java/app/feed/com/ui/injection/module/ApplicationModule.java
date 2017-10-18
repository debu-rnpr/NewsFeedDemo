package app.feed.com.ui.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import app.feed.com.data.ApiService;
import app.feed.com.data.DataManager;
import app.feed.com.ui.common.FavouriteManager;
import app.feed.com.ui.injection.ActivityContext;
import app.feed.com.ui.injection.ApplicationContext;
import dagger.Module;
import dagger.Provides;

/**
 * Created by debu on 16/10/17.
 */

@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context providesContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    ApiService provideApiService() {
        return ApiService.Factory.createService();
    }

    @Provides
    @Singleton
    DataManager provideDataManager(ApiService apiService){
        return new DataManager(apiService);
    }

    @Provides
    @Singleton
    FavouriteManager provideFabManager(){
        return new FavouriteManager();
    }

}
