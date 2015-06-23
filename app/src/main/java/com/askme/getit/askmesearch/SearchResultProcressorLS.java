package com.askme.getit.askmesearch;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.bluelinelabs.logansquare.LoganSquare;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeScud on 23/06/15.
 */
public class SearchResultProcressorLS {

    private Context mContext;

    long startTime;
    public SearchResultProcressorLS(Context context) {
        this.mContext = context;
    }

    public ArrayList<SearchModel> processResult(String response) throws JSONException {

        ArrayList<SearchModel> tmp = null;

        try {
            JSONObject rootobject = new JSONObject(response.toString());
            String listitngString =rootobject.getJSONObject("deals").getString("listings");

            List<SearchModel> temp  =   LoganSquare.parseList(listitngString, SearchModel.class);


            tmp = new ArrayList<SearchModel>(temp);



        }
        catch (JSONException exception)
        {
            Log.d(Constants.TAG, exception.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }


        long stopTime = System.nanoTime();
        System.out.println("Elapsed time for parsing "+(stopTime - startTime)+tmp.toString());
        return tmp;


    }

}
