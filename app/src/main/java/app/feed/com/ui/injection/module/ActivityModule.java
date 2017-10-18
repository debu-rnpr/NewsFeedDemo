package app.feed.com.ui.injection.module;

import android.app.Activity;
import android.content.Context;

import app.feed.com.ui.injection.ActivityContext;
import dagger.Module;
import dagger.Provides;

/**
 * Created by debu on 16/10/17.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}
