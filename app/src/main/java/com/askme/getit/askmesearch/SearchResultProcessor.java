package com.askme.getit.askmesearch;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcv on 17/06/15.
 */
public class SearchResultProcessor implements ResultProcressor{
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

            Log.d(Constants.TAG,response
            );

            JSONObject jsonObject = new JSONObject(response);
            JSONObject jObject = jsonObject.getJSONObject("deals");
            JSONArray jsonArray = jObject.getJSONArray("listings");
            for (int i= 0;i<jsonArray.length();i++){
                JSONObject j = jsonArray.getJSONObject(i);
                SearchModel sModel = new SearchModel();

                if (j.has("address")) {
                    sModel.setAddress(j.getString("address"));
                }
                if (j.has("category")) {
                    sModel.setCategory(j.getString("category"));
                }
                if (j.has("company_name")) {
                    sModel.setCompany_name(j.getString("company_name"));
                }

                if (j.has("mobile")) {
                    sModel.setMobile(j.getString(" mobile"));
                }
                if (j.has("dealimage")) {
                    sModel.setDealimage(j.getString("dealimage"));
                }
                if (j.has("description")) {
                    sModel.setDescription(j.getString("description"));
                }
                if (j.has("detailurl")) {
                    sModel.setDetailurl(j.getString("detailurl"));
                }
                if (j.has("discount")) {
                    sModel.setDiscount(j.getString("discount"));
                }
                if (j.has("headerimage")) {
                    sModel.setHeaderimage(j.getString("headerimage"));
                }
                if (j.has("id")) {
                    sModel.setId(j.getString("id"));
                }
                if (j.has("landingurl")) {
                    sModel.setLandingurl(j.getString("landingurl"));
                }
                if (j.has("offeredprice")) {
                    sModel.setOfferedprice(j.getString("offeredprice"));
                }
                if (j.has("originalprice")) {
                    sModel.setOriginalprice(j.getString("originalprice"));
                }
                if (j.has("partnerurl")) {
                    sModel.setPartnerurl(j.getString("partnerurl"));
                }
                if (j.has("price")) {
                    sModel.setPrice(j.getString("price"));
                }
                if (j.has("savedpercentage")) {
                    sModel.setSavedpercentage(j.getString("savedpercentage"));
                }
                if (j.has("savedprice")) {
                    sModel.setSavedprice(j.getString("savedprice"));
                }
                if (j.has("show")) {
                    sModel.setShow(j.getString("show"));
                }
                if (j.has("title")) {
                    sModel.setTitle(j.getString("title"));
                }
                if (j.has("type")) {
                    sModel.setType(j.getString("type"));
                }
                if (j.has("validity")) {
                    sModel.setValidity(j.getString("validity"));
                }
                if (j.has("vertical")) {
                    sModel.setValidity(j.getString("vertical"));
                }
//                if (j.has("keywords")) {
//                    JSONArray keywordarray = j.getJSONArray("keywords");
//                    List<String> keyewordstring = new ArrayList<String>();
//                    for (int x = 0;x < keywordarray.length(); x++) {
//                        keyewordstring.add(keywordarray.getJSONObject(x).toString());
//                    }
//
//                    sModel.setKeywords(keyewordstring);
//                }



                searchList.add(sModel);
            }

        }

        long stopTime = System.nanoTime();
        System.out.println("Elapsed time for parsing "+(stopTime - startTime)+searchList.toString());

        return searchList;

    }
}
