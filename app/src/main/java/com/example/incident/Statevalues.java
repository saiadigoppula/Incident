package com.example.incident;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

public class Statevalues extends AppCompatActivity {

    private TextView New;
    private TextView IN_PROGRESS;
    private TextView ONHOLD;
    private TextView RESOLVED;
    private TextView CLOSED;
    private TextView CANCELED;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statevalues);

        New = findViewById(R.id.NEW);
        IN_PROGRESS = findViewById(R.id.IN_PROGRESS);
        ONHOLD = findViewById(R.id.ONHOLD);
        RESOLVED = findViewById(R.id.RESOLVED);
        CLOSED = findViewById(R.id.CLOSED);
        CANCELED = findViewById(R.id.CANCELED);
    }

    public void Newclick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","New");

        setResult(RESULT_OK, resultIntent);
        finish();

    }

    public void InProgressClick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","In Progress");

        setResult(RESULT_OK, resultIntent);
        finish();

    }
    public void OnholdClick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","On Hold");

        setResult(RESULT_OK, resultIntent);
        finish();

    }
    public void ResolvedClick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","Resolved");

        setResult(RESULT_OK, resultIntent);
        finish();

    }
    public void ClosedClick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","Closed");

        setResult(RESULT_OK, resultIntent);
        finish();

    }
    public void CanceledClick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","Canceled");

        setResult(RESULT_OK, resultIntent);
        finish();

    }
}
