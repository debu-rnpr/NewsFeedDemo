package app.feed.com.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import app.feed.com.MainApplication;
import app.feed.com.ui.injection.component.ActivityComponent;
import app.feed.com.ui.injection.component.DaggerActivityComponent;
import app.feed.com.ui.injection.module.ActivityModule;

/**
 * Created by debu on 16/10/17.
 */

public class BaseFragment extends Fragment {
    private ActivityComponent activityComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule(getActivity())).applicationComponent(MainApplication.getInstance().getApplicationComponent()).build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
