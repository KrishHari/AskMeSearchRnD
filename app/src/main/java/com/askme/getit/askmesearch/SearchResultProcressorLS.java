package com.askme.getit.askmesearch;

import android.content.Context;
import android.util.Log;

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
    private ArrayList<SearchModel> searchList;
    long startTime;
    public SearchResultProcressorLS(Context context) {
        this.mContext = context;
    }

    public ArrayList<SearchModel> processResult(String response) throws JSONException {

        ArrayList<SearchModel> tmp = null;

        try {
            JSONObject rootobject = new JSONObject(response.toString());
            String listitngString =rootobject.getJSONObject("deals").getString("listings");

            tmp = new ArrayList<SearchModel>(LoganSquare.parseList(listitngString, SearchModel.class));



        }
        catch (JSONException exception)
        {
            Log.d(Constants.TAG, exception.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }


        long stopTime = System.nanoTime();
        System.out.println("Elapsed time for parsing "+(stopTime - startTime)+searchList.toString());
        return tmp;


    }

}
