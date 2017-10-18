package app.feed.com.ui.landing.create.view;

import app.feed.com.data.entities.response.FeedCreatedResponse;
import app.feed.com.ui.common.MVPview;

/**
 * Created by debu on 15/10/17.
 */

public interface CreateMVPview extends MVPview {
    void onFeedCreated(FeedCreatedResponse response);
}
