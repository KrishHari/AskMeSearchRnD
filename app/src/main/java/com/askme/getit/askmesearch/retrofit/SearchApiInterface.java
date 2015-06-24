package com.askme.getit.askmesearch.retrofit;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by hcv on 22/06/15.
 */
public interface SearchApiInterface {


    @GET("/delhi/{keyword}")
    void getDeals(@Path("keyword") String keyword, Callback<SearchResponseModel> callback);

}
