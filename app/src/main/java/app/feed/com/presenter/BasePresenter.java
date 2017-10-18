package app.feed.com.presenter;

import app.feed.com.ui.common.MVPview;

/**
 * Created by debu on 15/10/17.
 */

public class BasePresenter<V extends MVPview> implements MVPpresenter<V> {
    private V  mvpView;
    @Override
    public void attachView(V mvpview) {
        this.mvpView = mvpview;
    }

    @Override
    public void detachView() {
        this.mvpView = null;
    }

    public boolean isViewAttached(){
        return this.mvpView != null;
    }

    public V getMVPView() {
        assert this.mvpView != null;
        return mvpView;
    }
}
