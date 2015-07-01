package com.askme.getit.askmesearch;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by LeScud on 30/06/15.
 */
public interface ResultProcressor {

    public ArrayList<SearchModel> processResult(String response) throws JSONException;
}
