package com.askme.getit.askmesearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by hcv on 16/06/15.
 */
public class SearchListAdapter extends ArrayAdapter<SearchModel> {
    List<SearchModel> searchResultList;
    Context mContext;

    public SearchListAdapter(Context context, int resource, List<SearchModel> searchResultList) {
        super(context, resource);
        this.searchResultList = searchResultList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return searchResultList.size();
    }

    @Override
    public SearchModel getItem(int position) {
        return searchResultList.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        final SearchModel result = searchResultList.get(position);
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.search_result_list, null);
        }

        TextView mobileText = (TextView)v.findViewById(R.id.mobileText);
        TextView addressText = (TextView)v.findViewById(R.id.addressText);
        TextView categoryText = (TextView)v.findViewById(R.id.categoryText);
        TextView companyName = (TextView)v.findViewById(R.id.companyNameText);

        if (result !=null){
            mobileText.setText(result.getMobileNumber());
            addressText.setText(result.getAddress());
            categoryText.setText(result.getCategory());
            companyName.setText(result.getCompanyName());
        }

        return v;
    }

}
