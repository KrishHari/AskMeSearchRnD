package com.askme.getit.askmesearch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.operationTimeTitle)
    TextView opertionTime;

    @InjectView(R.id.searchButton)
    Button searchButton;

    @InjectView(R.id.searchText)
    EditText searchText;

    @InjectView(R.id.deals_listview)
    ListView searchList;

    @InjectView(R.id.opertionSwitch)
    Spinner httpclientselector;

    @InjectView(R.id.jsonparseSwitchr)
    Spinner jsonparserselector;




//    @InjectView(R.id.jsonTime)
//    TextView jsontime;


    String httptitle ;
    String jsontitle ;

    int jsonparingOption=0,Httpclientoptions=0;
    long strttime,httpRoundTripTime,jsonParseTime;
    private ArrayList<SearchModel> searchResultList = new ArrayList<>();
    SearchListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.http_clients, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        httpclientselector.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.json_client, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jsonparserselector.setAdapter(adapter2);


        httpclientselector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Httpclientoptions=position;

                if(position==2){
                    jsonparserselector.setVisibility(View.INVISIBLE);
                    jsonparingOption=-1;

                }
                else{
                    jsonparserselector.setVisibility(View.VISIBLE);
                    jsonparserselector.setSelection(0);
                    jsonparingOption=0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        jsonparserselector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                jsonparingOption = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    strttime = System.nanoTime();

                    opertionTime.setText(httptitle);
                    // jsontime.setText(jsontitle);
                    performSearch();


                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);


                } catch (NullPointerException Excaption) {

                }
            }
        });

        httptitle = getResources().getString(R.string.httptime);
        jsontitle = getResources().getString(R.string.jsontime);
    }


    void updateList(List<SearchModel> listtings)
    {
        listAdapter = new SearchListAdapter(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,listtings);
        searchList.setAdapter(listAdapter);
    }



    void  performSearch(){








        //jsonParseTime=System.nanoTime();




        SearchResponse responseProcreser;

        if(jsonparingOption==-1) {

            responseProcreser = new SearchResponse() {


                @Override
                public void postResponse(String result) {
                    Log.d(Constants.TAG, "Imvalid output for a Httpclient with inbuild parser");
                }


                @Override
                public void postResponse(SearchResponseModel result) {
                    httpRoundTripTime = System.nanoTime();
                    searchResultList = new ArrayList<SearchModel>(result.deals.listings);
                    opertionTime.setText("Http+Json Time : " + " " + ((double) (httpRoundTripTime - strttime) / 1000000000.0));
                    updateList(result.deals.listings);

                }
            };
        }
        else {

           final ResultProcressor searchResultProcessor;




            if(jsonparingOption==0) {

                searchResultProcessor  = new SearchResultProcessor(getApplicationContext());
            }
            else {
                searchResultProcessor= new SearchResultProcressorLS(getApplicationContext());
            }


            responseProcreser = new SearchResponse() {

                @Override
                public void postResponse(String result) {
                    httpRoundTripTime= System.nanoTime();

                    try{
                        searchResultList = searchResultProcessor.processResult(result);
                        jsonParseTime = System.nanoTime();
                        opertionTime.setText("Http round trip time : " + " " + ((double) (httpRoundTripTime - strttime) / 1000000000.0) + " Json Parser Time :" + ((double)(jsonParseTime-httpRoundTripTime)/1000000000.0));
                        updateList(searchResultList);

                    }catch (JSONException j){
                        Log.d(Constants.TAG,j.getMessage());
                        j.printStackTrace();
                    }

                }

                @Override
                public void postResponse(SearchResponseModel result) {

                    Log.d(Constants.TAG, "Imvalid output for a Httpclient without inbuild parser");

                }
            };


        }


        switch (Httpclientoptions) {
            case 0 :
                SearchProcess  searchProcress = new SearchProcess(responseProcreser,this);
                searchProcress.execute(searchText.getText().toString().trim());
                break;

            case 1 :

                SearchProcressVolley  searchProcress2 = new SearchProcressVolley(responseProcreser,this);
                searchProcress2.performrequest(searchText.getText().toString().trim());
                break;

            case 2 :

                SearchProcressWasp  searchProcress3 = new SearchProcressWasp(responseProcreser,this);
                searchProcress3.performrequest(searchText.getText().toString().trim());
                break;

            default :
                Log.d(Constants.TAG,"invalid http search client selection");
                break;

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
