


package com.example.incident;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;

        import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class Priorityvalues extends AppCompatActivity {

    private TextView CRITICAL;
    private TextView HIGH;
    private TextView MODERATE;
    private TextView LOW;
    private TextView PLANNING;
    private TextView NONE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priorityvalues);

        CRITICAL = findViewById(R.id.CRITICAL);
        HIGH = findViewById(R.id.HIGH);
        MODERATE = findViewById(R.id.MODERATE);
        LOW = findViewById(R.id.LOW);
        PLANNING = findViewById(R.id.PLANNING);
        NONE = findViewById(R.id.NONE);


    }


    public void Criticalclick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","1- Critical");

        setResult(RESULT_OK, resultIntent);
        finish();

    }
    public void HighClick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","2 - High");

        setResult(RESULT_OK, resultIntent);
        finish();

    }
    public void ModerateClick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","3 - Moderate");

        setResult(RESULT_OK, resultIntent);
        finish();

    }
    public void LowClick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","4 - Low");

        setResult(RESULT_OK, resultIntent);
        finish();

    }
    public void PlanningClick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","5 - Planning");

        setResult(RESULT_OK, resultIntent);
        finish();

    }
    public void Noneclick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result"," ");

        setResult(RESULT_OK, resultIntent);
        finish();

    }
}

