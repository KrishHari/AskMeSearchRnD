package com.askme.getit.askmesearch;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.orhanobut.wasp.CallBack;
import com.orhanobut.wasp.Wasp;
import com.orhanobut.wasp.WaspError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LeScud on 23/06/15.
 */
public class SearchProcressWasp {

    SearchResponse delegate = null;
    MaterialProgressDialog dialog;
    AskmeServices service;
    Activity activity;


    public SearchProcressWasp(SearchResponse response, Activity activity){
        this.delegate = response;
        this.activity = activity;
        dialog = new MaterialProgressDialog(activity);


         service = new Wasp.Builder(activity)
                .setEndpoint("http://test-pythonstack01.staging.askme.com:9150")
                .build()
                .create(AskmeServices.class);





    }

    void performrequest(String query) {


        service.performSearch("delhi", query, new CallBack<SearchResponseModel>() {
            @Override
            public void onSuccess(SearchResponseModel response) {

                dialog.dismissDialog();
                delegate.postResponse(response);
            }

            @Override
            public void onError(WaspError waspError) {
                dialog.dismissDialog();

            }
        });

        dialog.showDialog();
        // Adding request to request queue
        Log.i("instance", "" + AppController.getInstance());




    }




}
