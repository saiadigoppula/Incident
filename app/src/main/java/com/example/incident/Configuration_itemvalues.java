package com.example.incident;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Configuration_itemvalues extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {

    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_SYSID = "sys_id";


    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_itemvalues);


        mRecyclerView = findViewById(R.id.recyclerViewconfiguration);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);


        parseJSON();

        EditText editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }


    private void filter(String text) {
        ArrayList<ExampleItem> filteredList = new ArrayList<>();

        for (ExampleItem item : mExampleList) {
            if (item.getCreator().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        mExampleAdapter.filterList(filteredList);
    }


    private void parseJSON() {
        String id = "58140";
        String url = "https://dev"+id+".service-now.com/api/now/table/cmdb_ci?sysparm_fields=name%2Csys_id&sysparm_limit=50";



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("result");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject hit = jsonArray.getJSONObject(i);

                                String creatorName = hit.getString("name");
                                String sys_id = hit.getString("sys_id");


                                mExampleList.add(new ExampleItem(creatorName,sys_id));
                            }





                            mExampleAdapter = new ExampleAdapter(Configuration_itemvalues.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener(Configuration_itemvalues.this);





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {






            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers =  new HashMap<String, String>();
                String enteredusername = "admin";
                String enteredpassword ="Belikebro@123";
                String credentials = enteredusername + ":" + enteredpassword;
                String encoded = "Basic "+ Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headers.put("Authorization", encoded);
                return headers;
            }
        };

        mRequestQueue.add(request);
    }






    @Override
    public void onItemClick(int position) {

        ExampleItem clickedItem = mExampleList.get(position);

        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_CREATOR,clickedItem.getCreator());

        setResult(RESULT_OK, resultIntent);
        finish();

    }

}
