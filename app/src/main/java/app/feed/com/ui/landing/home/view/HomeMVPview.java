package app.feed.com.ui.landing.home.view;

import java.util.List;

import app.feed.com.data.entities.response.HomeListResponse;
import app.feed.com.ui.common.MVPview;

/**
 * Created by debu on 17/10/17.
 */

public interface HomeMVPview extends MVPview {
    void onFeedsReceived(List<HomeListResponse> listResponses);
}
