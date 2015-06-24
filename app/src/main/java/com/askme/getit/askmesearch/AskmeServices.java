package com.askme.getit.askmesearch;

import com.orhanobut.wasp.CallBack;
import com.orhanobut.wasp.http.GET;
import com.orhanobut.wasp.http.Path;

/**
 * Created by LeScud on 23/06/15.
 */
public interface AskmeServices {


    @GET("/search/{place}/{query}")
    void performSearch(
        @Path("place") String place,
        @Path("query") String query,
        CallBack<SearchResponseModel> callBack
    );


}
