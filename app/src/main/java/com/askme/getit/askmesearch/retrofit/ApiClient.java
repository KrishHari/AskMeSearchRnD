package com.askme.getit.askmesearch.retrofit;

import retrofit.RestAdapter;

/**
 * Created by hcv on 23/06/15.
 */
public class ApiClient {
    private static SearchApiInterface searchApiService;

    public static SearchApiInterface getDealsApiClient(){
        if(searchApiService == null){
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://test-pythonstack01.staging.askme.com:9150/search/")
                    .build();

            searchApiService = restAdapter.create(SearchApiInterface.class);
        }
        return searchApiService;
    }
}
