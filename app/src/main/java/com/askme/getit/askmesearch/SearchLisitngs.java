package com.askme.getit.askmesearch;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by LeScud on 24/06/15.
 */

@JsonObject
public class SearchLisitngs {
    @JsonField
    public List<SearchModel> listings;
}
