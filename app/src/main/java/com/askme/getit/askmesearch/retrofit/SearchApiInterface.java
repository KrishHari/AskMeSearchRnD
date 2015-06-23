package com.askme.getit.askmesearch.retrofit;

import com.askme.getit.askmesearch.SearchModel;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by hcv on 22/06/15.
 */
public interface SearchApiInterface {

   // @GET("/delhi/{keyword}")
    //void getDeals(@Query("keyword") String keyword, Callback<List<SearchModel>> callback);

    @GET("/delhi/{keyword}")
    void getDeals(@Query("keyword") String keyword, Callback<SearchModel> callback);
}
