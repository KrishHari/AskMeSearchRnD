package com.askme.getit.askmesearch;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import com.askme.getit.askmesearch.Constants;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LeScud on 22/06/15.
 */
public class SearchProcressVolley {

    SearchResponse delegate = null;
    MaterialProgressDialog dialog;
    Activity activity;


    public SearchProcressVolley(SearchResponse response, Activity activity){
        this.delegate = response;
        this.activity = activity;
        dialog = new MaterialProgressDialog(activity);
    }

    void performrequest(String query) {

        String queryUrl = Constants.BaseUrl+query;
        StringRequest strReq = new StringRequest(Request.Method.GET,
                queryUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                dialog.dismissDialog();

                delegate.postResponse(response);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismissDialog();
                VolleyLog.d(Constants.TAG, "Error: " + error.getMessage());


            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<>();
                //  headers.put("Content-Type", "application/json");
                //headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }


        };

        dialog.showDialog();

        // Adding request to request queue
        Log.i("instance",""+AppController.getInstance());
        AppController.getInstance().addToRequestQueue(strReq, Constants.TAG);



    }
}




