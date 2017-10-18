package app.feed.com.ui.details;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import app.feed.com.R;
import app.feed.com.data.entities.response.HomeListResponse;
import app.feed.com.databinding.ActivityDetailsBinding;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private HomeListResponse feedToShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailsBinding detailsBinding = DataBindingUtil.setContentView(this,R.layout.activity_details);
        feedToShow = (HomeListResponse) getIntent().getSerializableExtra("feed");
        detailsBinding.setFeed(feedToShow);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
