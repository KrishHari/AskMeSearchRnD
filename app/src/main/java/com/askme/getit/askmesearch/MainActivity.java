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

import org.json.JSONException;

import java.util.ArrayList;
import java.util.jar.JarEntry;

public class MainActivity extends AppCompatActivity {

    Button searchButton;
    EditText searchText;
    private ArrayList<SearchModel> searchResultList = new ArrayList<>();
    SearchListAdapter listAdapter;
    ListView searchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton = (Button)findViewById(R.id.searchButton);
        searchText = (EditText)findViewById(R.id.searchText);
        searchList =  (ListView)findViewById(R.id.deals_listview);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
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
    }

    void  performSearch(){


        final SearchResultProcessor searchResultProcessor = new SearchResultProcessor(getApplicationContext());
        SearchResponse responseProcreser = new SearchResponse() {
          @Override
          public void postResponse(String result) {
              try{
                  searchResultList = searchResultProcessor.processResult(result);
                  listAdapter = new SearchListAdapter(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,searchResultList);
                  searchList.setAdapter(listAdapter);
              }catch (JSONException j){
                  j.printStackTrace();
              }

          }
      };


        if(false){
            SearchProcess searchProcess = new SearchProcess(responseProcreser,MainActivity.this);
            searchProcess.execute(searchText.getText().toString().trim());
        }
        else{

            SearchProcressVolley searchProcess = new SearchProcressVolley(responseProcreser,MainActivity.this);
            searchProcess.performrequest(searchText.getText().toString().trim());

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
