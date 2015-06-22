package com.askme.getit.askmesearch;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hcv on 17/06/15.
 */
public class SearchResultProcessor {
    private Context mContext;
    private ArrayList<SearchModel> searchList;
    long startTime;
    public SearchResultProcessor(Context context) {
        this.mContext = context;
    }

    public ArrayList<SearchModel> processResult(String response) throws JSONException{
        searchList = new ArrayList<SearchModel>();
        startTime = System.nanoTime();
        if(response!=null) {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject jObject = jsonObject.getJSONObject("deals");
            JSONArray jsonArray = jObject.getJSONArray("listings");
            for (int i= 0;i<jsonArray.length();i++){
                JSONObject j = jsonArray.getJSONObject(i);
                SearchModel sModel = new SearchModel();
                sModel.setAddress(j.getString("address"));
                sModel.setCategory(j.getString("category"));
                sModel.setCompanyName(j.getString("company_name"));
                sModel.setMobileNumber(j.getString(" mobile"));
                searchList.add(sModel);
            }

        }

        long stopTime = System.nanoTime();
        System.out.println("Elapsed time for parsing "+(stopTime - startTime));

        return searchList;

    }
}
