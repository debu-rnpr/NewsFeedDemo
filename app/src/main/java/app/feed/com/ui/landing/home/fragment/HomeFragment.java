package app.feed.com.ui.landing.home.fragment;

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
import app.feed.com.ui.details.DetailsActivity;
import app.feed.com.ui.injection.ActivityContext;
import app.feed.com.ui.common.adapter.AdapterListing;
import app.feed.com.ui.landing.home.view.HomeMVPview;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by debu on 17/10/17.
 */

public class HomeFragment extends BaseFragment implements HomeMVPview,AdapterListing.ListItemClickListener {

    @Inject
    @ActivityContext
    Context context;
    @Inject PresenterHome presenterHome;
    @Inject AdapterListing adapterListing;
    @Inject FavouriteManager favouriteManager;
    @BindView(R.id.feed_list)
    RecyclerView feedList;
    private ProgressDialog progressDialog;
    private List<HomeListResponse> listResponses;
    private final String AUTHOR="debu.roy89@gmail.com";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("onnn","Home");
        getActivityComponent().inject(this);
        presenterHome.attachView(this);
        initVariables();
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
        if(listResponses == null)
            presenterHome.initializeByAuthor(AUTHOR);
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        progressDialog.dismiss();
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    private void initVariables(){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait");
    }

    @Override
    public void onFeedsReceived(List<HomeListResponse> listResponses) {
        this.listResponses = listResponses;
        adapterListing.setData(this.listResponses);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterHome.detachView();
    }

    @Override
    public void onListDetailsClicked(int pos) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("feed",listResponses.get(pos));
        startActivity(intent);
    }

    @Override
    public void onFabClicked(int pos, boolean isChecked) {
        if(isChecked)
            favouriteManager.add(listResponses.get(pos));
        else
            favouriteManager.remove(listResponses.get(pos));
    }
}
