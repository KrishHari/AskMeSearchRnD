package com.askme.getit.askmesearch;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
                sModel.setCompany_name(j.getString("company_name"));
                sModel.setMobile(j.getString(" mobile"));

                sModel.setDealimage(jsonObject.getString("dealimage"));
                sModel.setDescription(jsonObject.getString("description"));

                sModel.setDetailurl(jsonObject.getString("detailurl"));
                sModel.setDiscount(jsonObject.getString("discount"));
                sModel.setHeaderimage(jsonObject.getString("headerimage"));
                sModel.setId(jsonObject.getString("id"));
                sModel.setLandingurl(jsonObject.getString("landingurl"));
                sModel.setOfferedprice(jsonObject.getString("offeredprice"));
                sModel.setOriginalprice(jsonObject.getString("originalprice"));
                sModel.setPartnerurl(jsonObject.getString("partnerurl"));
                sModel.setPrice(jsonObject.getString("price"));
                sModel.setSavedpercentage(jsonObject.getString("savedpercentage"));
                sModel.setSavedprice(jsonObject.getString("savedprice"));
                sModel.setShow(jsonObject.getString("show"));
                sModel.setTitle( jsonObject.getString("title"));
                sModel.setType(jsonObject.getString("type"));
                sModel.setValidity(jsonObject.getString("validity"));
                sModel.setValidity(jsonObject.getString("vertical"));

                JSONArray keywordarray = jsonObject.getJSONArray("keywords");
                List<String> keyewordstring = new ArrayList<String>();
                for (int x = 0;x < keywordarray.length(); x++) {
                    keyewordstring.add(keywordarray.getJSONObject(x).toString());
                }

                sModel.setKeywords(keyewordstring);

                searchList.add(sModel);
            }

        }

        long stopTime = System.nanoTime();
        System.out.println("Elapsed time for parsing "+(stopTime - startTime));

        return searchList;

    }
}
