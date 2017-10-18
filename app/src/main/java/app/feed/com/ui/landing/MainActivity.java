package app.feed.com.ui.landing;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import app.feed.com.R;
import app.feed.com.ui.landing.create.fragment.FeedCreateFragment;
import app.feed.com.ui.landing.favourite.fragment.FavouriteFragment;
import app.feed.com.ui.landing.home.fragment.HomeFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.home)
    ImageView home;
    @BindView(R.id.fab)
    ImageView fab;
    @BindView(R.id.create)
    ImageView create;
    View selView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        onHomeClicked(home);
    }

    public void addFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout,fragment, fragment.getClass().getName());
        transaction.commit();
    }

    @OnClick(R.id.home)
    void onHomeClicked(View v){
        if(selView != null)
            selView.setSelected(false);
        selView = v;
        selView.setSelected(true);
        addFragment(new HomeFragment());
    }

    @OnClick(R.id.fab)
    void onHfabClicked(View v){
        selView.setSelected(false);
        selView = v;
        selView.setSelected(true);
        addFragment(new FavouriteFragment());
    }

    @OnClick(R.id.create)
    void onCreateClicked(View v){
        selView.setSelected(false);
        selView = v;
        selView.setSelected(true);
        addFragment(new FeedCreateFragment());
    }

}
