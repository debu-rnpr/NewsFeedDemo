package app.feed.com.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.feed.com.data.entities.ModelCreateFeed;
import app.feed.com.data.entities.response.FeedCreatedResponse;
import app.feed.com.data.entities.response.HomeListResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by debu on 15/10/17.
 */

@Singleton
public class DataManager {
    private final ApiService apiService;

    @Inject
    public DataManager(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<FeedCreatedResponse> createFeed(ModelCreateFeed feed){
        return this.apiService.createFeed(feed)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<HomeListResponse>> getFeedsByAuthor(String author){
        return this.apiService.byAuthor(author)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<HomeListResponse>> getFeedsById(int id){
        return this.apiService.byId(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<HomeListResponse>> getFeedsByPublish(boolean isPublished){
        return this.apiService.byPublish(isPublished)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
