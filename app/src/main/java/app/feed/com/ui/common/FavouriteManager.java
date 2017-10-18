package app.feed.com.ui.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

import app.feed.com.data.entities.response.HomeListResponse;

/**
 * Created by debu on 17/10/17.
 */
@Singleton
public class FavouriteManager {
    private List<HomeListResponse> favList;

    public FavouriteManager() {
        favList = new ArrayList<>();
    }

    public List<HomeListResponse> getFavList() {
        return favList;
    }

    public boolean add(HomeListResponse response){
        return this.favList.add(response);
    }

    public boolean remove(HomeListResponse response){
        return this.favList.remove(response);
    }

    public void remove(int pos){
        this.favList.remove(pos);
    }
}
