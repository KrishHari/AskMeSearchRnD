package com.askme.getit.askmesearch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.jar.JarEntry;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.operationTimeTitle)
    TextView httpTime;

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
    long strttime,httpRoundTripTime,jsonParseTime;
    private ArrayList<SearchModel> searchResultList = new ArrayList<>();
    SearchListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);




        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    strttime=System.nanoTime();

                    httpTime.setText(httptitle);
                   // jsontime.setText(jsontitle);
                    performSearch();


                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);


                }
                catch (NullPointerException Excaption)
                {

                }
            }
        });

        httptitle = getResources().getString(R.string.httptime);
        jsontitle = getResources().getString(R.string.jsontime);
    }

    void  performSearch(){


        final SearchResultProcessor searchResultProcessor = new SearchResultProcessor(getApplicationContext());
        SearchResponse responseProcreser = new SearchResponse() {
          @Override
          public void postResponse(String result) {
              try{
                  httpRoundTripTime= System.nanoTime();
                  httpTime.setText(httptitle+" "+((double)(httpRoundTripTime-strttime)/1000000000.0));


                  searchResultList = searchResultProcessor.processResult(result);
                  listAdapter = new SearchListAdapter(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,searchResultList);
                  jsonParseTime=System.nanoTime();





                 // jsontime.setText(jsontitle  +" "+ ((double) (jsonParseTime - httpRoundTripTime) / 1000000000.0));

                  searchList.setAdapter(listAdapter);
              }catch (JSONException j){
                  Log.d(Constants.TAG,j.getMessage());
                  j.printStackTrace();

              }

          }


            @Override
            public void postResponse(SearchResponseModel result) {
                httpRoundTripTime= System.nanoTime();
                searchResultList = new ArrayList<SearchModel>(result.deals.listings);
                httpTime.setText("Http+Json Time : "+" "+((double)(httpRoundTripTime-strttime)/1000000000.0));

                listAdapter = new SearchListAdapter(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,searchResultList);
                //jsonParseTime=System.nanoTime();


               // jsontime.setText(jsontitle  +" "+ ((double) (jsonParseTime - httpRoundTripTime) / 1000000000.0));

                searchList.setAdapter(listAdapter);

            }
      };


        if(false){
            SearchProcess searchProcess = new SearchProcess(responseProcreser,MainActivity.this);
            searchProcess.execute(searchText.getText().toString().trim());
        }
        else{

            if(true){
                SearchProcressWasp searchProcess = new SearchProcressWasp(responseProcreser,MainActivity.this);
                searchProcess.performrequest(searchText.getText().toString().trim());

            }
            else{
                SearchProcressVolley searchProcess = new SearchProcressVolley(responseProcreser,MainActivity.this);
                searchProcess.performrequest(searchText.getText().toString().trim());
            }



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
