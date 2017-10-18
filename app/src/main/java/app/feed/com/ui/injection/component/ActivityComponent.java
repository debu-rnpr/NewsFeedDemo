package app.feed.com.ui.injection.component;

import javax.inject.Singleton;

import app.feed.com.ui.common.FavouriteManager;
import app.feed.com.ui.injection.PerActivity;
import app.feed.com.ui.injection.module.ActivityModule;
import app.feed.com.ui.landing.create.fragment.FeedCreateFragment;
import app.feed.com.ui.landing.favourite.fragment.FavouriteFragment;
import app.feed.com.ui.landing.home.fragment.HomeFragment;
import dagger.Component;

/**
 * Created by debu on 16/10/17.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(FeedCreateFragment fragment);
    void inject(HomeFragment fragment);
    void inject(FavouriteFragment fragment);
}
