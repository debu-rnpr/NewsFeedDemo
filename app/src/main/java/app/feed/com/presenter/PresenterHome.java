package app.feed.com.presenter;

import java.util.List;

import javax.inject.Inject;

import app.feed.com.data.DataManager;
import app.feed.com.data.entities.ModelCreateFeed;
import app.feed.com.data.entities.response.FeedCreatedResponse;
import app.feed.com.data.entities.response.HomeListResponse;
import app.feed.com.ui.landing.create.view.CreateMVPview;
import app.feed.com.ui.landing.home.view.HomeMVPview;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by debu on 15/10/17.
 */

public class PresenterHome extends BasePresenter<HomeMVPview> {
    private final DataManager dataManager;
    private Disposable feedDisposable;

    @Inject
    public PresenterHome(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(HomeMVPview mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        feedDisposable.dispose();
    }

    //////ccan be done for other params as well
    public void initializeByAuthor(String author){
        getMVPView().showLoading();
        feedDisposable = dataManager.getFeedsByAuthor(author).subscribeWith(new HomeResponseObserver());
    }

    private final class HomeResponseObserver extends DisposableObserver<List<HomeListResponse>>{

        @Override
        public void onNext(@NonNull List<HomeListResponse> listResponses) {
            getMVPView().onFeedsReceived(listResponses);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            getMVPView().showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            getMVPView().hideLoading();
        }
    }


}
