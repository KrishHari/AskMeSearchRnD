package com.askme.getit.askmesearch;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcv on 15/06/15.
 */
public class SearchProcess extends AsyncTask<String, Void,String> {

    private String LOG_TAG = "SearchProcess";
    SearchResponse delegate = null;
    String searchKeyword;
    long startTime;
    Activity activity;
    MaterialProgressDialog dialog;

    public SearchProcess(SearchResponse response, Activity activity){
        this.delegate = response;
        this.activity = activity;
        dialog = new MaterialProgressDialog(activity);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.showDialog();
    }

    @Override
    protected String doInBackground(String... params) {

        OkHttpClient client = new OkHttpClient();
        Response response = null;

        Request request = new Request.Builder()
                .url("http://test-pythonstack01.staging.askme.com:9150/search/delhi/"+params[0])
                .build();
        try {
            startTime = System.nanoTime();
            response = client.newCall(request).execute();
            return response.body().string();

        }
        catch (IOException e){
            Log.i("IOException",e.getMessage());
            return e.getMessage();
        }catch (Exception e){
            Log.w(LOG_TAG, "Java Lang Exception");
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        long stopTime = System.nanoTime();
        System.out.println("Elapsed time for response "+(stopTime - startTime));
        Log.i("response",s);
        delegate.postResponse(s);
        dialog.dismissDialog();
    }
}
