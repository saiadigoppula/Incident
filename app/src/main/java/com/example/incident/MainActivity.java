package com.example.incident;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.example.incident.Configuration_itemvalues.EXTRA_CREATOR;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;

    private TextView State;
    private TextView Priority;
    private TextView Configuration_item;
    private EditText Short_description;
    private EditText Description;
    private TextView Assignment_group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        State = findViewById(R.id.STATE);
        Priority =findViewById(R.id.PRIORITY);
        Configuration_item = findViewById(R.id.CONFIGURATION);
        Short_description = findViewById(R.id.SHORT_DESCRIPTION);
        Description = findViewById(R.id.DESCRIPTION);
        Assignment_group = findViewById(R.id.ASSIGNMENTGROUP);


        Button buttonParse = findViewById(R.id.SUBMIT);


        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }


    public void statefunction(View v)
    {
        Intent intent = new Intent(MainActivity.this, Statevalues.class);

        startActivityForResult(intent, 1);

    }

    public void Priorityfunction(View v)
    {

        Intent intent = new Intent(MainActivity.this, Priorityvalues.class);

        startActivityForResult(intent, 2);
    }

    public void Configuration_itemfunction(View v)
    {

        Intent intent = new Intent(MainActivity.this, Configuration_itemvalues.class);

        startActivityForResult(intent, 3);
    }

    public void Assignmentgroup_itemfunction(View v)
    {

        Intent intent = new Intent(MainActivity.this, Assignmentgroup_itemvalues.class);

        startActivityForResult(intent, 4);

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("result");
                State.setText(result);
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("result");
                Priority.setText(result);
            }
            else
            {
                Priority.setText(" ");
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra(EXTRA_CREATOR);
                Configuration_item.setText(result);
            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("result");
                Assignment_group.setText(result);
            }
        }

    }

    private void jsonParse() {

        final JSONObject jsonObject = new JSONObject();
        try {

            String state = State.getText().toString();
            String priority = Priority.getText().toString();
            String configuration_item = Configuration_item.getText().toString();
            String assignment_group = Assignment_group.getText().toString();
            String short_description = Short_description.getText().toString();
            String description = Description.getText().toString();



            jsonObject.put("short_description", short_description);
            jsonObject.put("description", description);
            jsonObject.put("cmdb_ci", configuration_item);
            jsonObject.put("assignment_group", assignment_group);
            jsonObject.put("state", state);
            jsonObject.put("priority", priority);

        } catch (JSONException e) {
            // handle exception
        }

        String url = "https://dev58140.service-now.com/api/now/table/incident";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public byte[] getBody() {

                try {
                    Log.i("json", jsonObject.toString());
                    return jsonObject.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers =  new HashMap<String, String>();
                String enteredusername = "admin";
                String enteredpassword ="Belikebro@123";
                String credentials = enteredusername + ":" + enteredpassword;
                String encoded = "Basic "+ Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headers.put("Authorization", encoded);
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                return headers;
            }
        };


        mQueue.add(request);
    }
}
