package app.feed.com.presenter;

import app.feed.com.ui.common.MVPview;

/**
 * Created by debu on 15/10/17.
 */

public interface MVPpresenter<v extends MVPview> {
    void attachView(v mvpview);

    void detachView();
}
