package app.feed.com.presenter;

import java.util.Arrays;

import javax.inject.Inject;

import app.feed.com.data.DataManager;
import app.feed.com.ui.landing.create.view.CreateMVPview;
import app.feed.com.data.entities.ModelCreateFeed;
import app.feed.com.data.entities.response.FeedCreatedResponse;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by debu on 15/10/17.
 */

public class PresenterCreateFeed extends BasePresenter<CreateMVPview> {
    private final DataManager dataManager;
    private Disposable feedDisposable;

    @Inject
    public PresenterCreateFeed(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(CreateMVPview mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if(feedDisposable != null)
            feedDisposable.dispose();
    }

    public void initialize(ModelCreateFeed feed){
        getMVPView().showLoading();
        if(validate(feed)) {
            createTagsList(feed);
            feedDisposable = dataManager.createFeed(feed).subscribeWith(new FeedResponseObserver());
        }else{
            getMVPView().showError("please fill all the feilds");
        }
    }

    private void createTagsList(ModelCreateFeed feed){
        feed.setTags(Arrays.asList(feed.getStringTags().split(",")));
    }

    private boolean validate(ModelCreateFeed feed){

        return !feed.getTitle().equals("") && !feed.getDescription().equals("") && !feed.getAuthor().equals("") && !feed.getStringTags().equals("");

    }

    private final class FeedResponseObserver extends DisposableObserver<FeedCreatedResponse> {

        @Override
        public void onNext(@NonNull FeedCreatedResponse feedCreatedResponse) {
            getMVPView().onFeedCreated(feedCreatedResponse);
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
