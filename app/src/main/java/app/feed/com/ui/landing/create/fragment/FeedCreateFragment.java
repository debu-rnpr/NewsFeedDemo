package app.feed.com.ui.landing.create.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import app.feed.com.R;
import app.feed.com.databinding.LayoutCreateBinding;
import app.feed.com.presenter.PresenterCreateFeed;
import app.feed.com.ui.common.BaseFragment;
import app.feed.com.ui.injection.ActivityContext;
import app.feed.com.ui.landing.create.view.CreateMVPview;
import app.feed.com.data.entities.ModelCreateFeed;
import app.feed.com.data.entities.response.FeedCreatedResponse;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DefaultObserver;

/**
 * Created by debu on 15/10/17.
 */

public class FeedCreateFragment extends BaseFragment implements CreateMVPview{
    @Inject
    @ActivityContext
    Context context;
    @Inject PresenterCreateFeed presenterCreateFeed;
    ModelCreateFeed feed;
    private ProgressDialog progressDialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        presenterCreateFeed.attachView(this);
        initVariables();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutCreateBinding binding = DataBindingUtil.bind(inflater.inflate(R.layout.layout_create,container,false));
        binding.setModel(feed);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RxView.clicks(view.findViewById(R.id.submit)).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new DefaultObserver<Object>() {
            @Override
            public void onNext(@NonNull Object o) {
                presenterCreateFeed.initialize(feed);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    @Override
    public void onFeedCreated(FeedCreatedResponse response) {
        Toast.makeText(context,"Successfully created, id = "+response.getId(),Toast.LENGTH_SHORT).show();
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
        feed = new ModelCreateFeed();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterCreateFeed.detachView();
    }
}
