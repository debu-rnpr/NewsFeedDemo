package app.feed.com.data;

import java.util.List;

import app.feed.com.data.entities.ModelCreateFeed;
import app.feed.com.data.entities.response.FeedCreatedResponse;
import app.feed.com.data.entities.response.HomeListResponse;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by debu on 15/10/17.
 */

public interface ApiService {
    String BASE_URL = "http://test.peppersquare.com";

    @POST("/api/v1/article")
    Observable<FeedCreatedResponse> createFeed(@Body ModelCreateFeed model);

    @GET("/api/v1/article")
    Observable<List<HomeListResponse>> byAuthor(@Query("author")String author);

    @GET("/api/v1/article")
    Observable<List<HomeListResponse>> byId(@Query("id")int id);

    @GET("/api/v1/article")
    Observable<List<HomeListResponse>> byPublish(@Query("published")boolean isPublished);

    class Factory{
        public static ApiService createService(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);
        }
    }
}
