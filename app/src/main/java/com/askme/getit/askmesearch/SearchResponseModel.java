package com.askme.getit.askmesearch;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;
import java.util.Map;

/**
 * Created by LeScud on 24/06/15.
 */

@JsonObject
public class SearchResponseModel {


    @JsonField
    public SearchLisitngs ads;
    @JsonField
    public SearchLisitngs outlets;
    @JsonField
    public SearchLisitngs deals;

}
