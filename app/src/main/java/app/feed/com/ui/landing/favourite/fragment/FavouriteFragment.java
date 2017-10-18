package app.feed.com.ui.landing.favourite.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import app.feed.com.R;
import app.feed.com.data.entities.response.HomeListResponse;
import app.feed.com.presenter.PresenterHome;
import app.feed.com.ui.common.BaseFragment;
import app.feed.com.ui.common.FavouriteManager;
import app.feed.com.ui.common.adapter.AdapterListing;
import app.feed.com.ui.details.DetailsActivity;
import app.feed.com.ui.injection.ActivityContext;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by debu on 18/10/17.
 */

public class FavouriteFragment extends BaseFragment implements AdapterListing.ListItemClickListener{
    @Inject
    @ActivityContext
    Context context;
    @Inject
    AdapterListing adapterListing;
    @Inject
    FavouriteManager favouriteManager;
    @BindView(R.id.feed_list)
    RecyclerView feedList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        adapterListing.setData(favouriteManager.getFavList());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_list,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        feedList.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        adapterListing.setItemClickListener(this);
        feedList.setAdapter(adapterListing);
    }


    @Override
    public void onListDetailsClicked(int pos) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("feed",favouriteManager.getFavList().get(pos));
        startActivity(intent);
    }

    @Override
    public void onFabClicked(int pos, boolean isChecked) {
        if(!isChecked)
            favouriteManager.remove(pos);
    }
}
