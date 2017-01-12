package com.example.genji.am002_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_two);

        // data passed in using getStringExtra() via Intent i
        Toast.makeText(this,getIntent().getStringExtra("str1"),
                Toast.LENGTH_SHORT).show();
        // ... using getIntExtra()
        Toast.makeText(this,Integer.toString(
                        getIntent().getIntExtra("n3", 999)),
                Toast.LENGTH_SHORT).show();

        // get the Bundle object passed in via Intent i
        Bundle bundle = getIntent().getExtras();
        // get the data using the getString()
        Toast.makeText(this, bundle.getString("str2"),
                Toast.LENGTH_SHORT).show();
        // ... using the getInt() method
        Toast.makeText(this,Integer.toString(bundle.getInt("n2")),
                Toast.LENGTH_SHORT).show();

        // ... trick for landscape
        if(findViewById(R.id.state_land) == null){
            TextView state = (TextView)findViewById(R.id.state);
            state.setText("bundle{str2: \"" + bundle.getString("str2") + "\", n2: " + String.valueOf(bundle.getInt("n2"))+"}");


            Button rok = (Button) findViewById(R.id.rok);
            rok.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    // no arguments in constructor !!!!
                    Intent i = new Intent();
                    // use putExtra() to add new key/value pairs to intent i ---
                    i.putExtra("nok", "RESULT OK");
                    //---set the result with OK and the Intent object---
                    ActivityTwo.this.setResult(RESULT_OK, i);
                    ActivityTwo.this.finish();
                }
            });

            Button finish = (Button) findViewById(R.id.finish);
            finish.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent i = new Intent(ActivityTwo.this, ActivityTwo.class);
                    // use putExtra() to add new key/value pairs to intent i ---
                    i.putExtra("nok", "RESULT OK");
                    //---set the result with OK and the Intent object---
                    // ActivityTwo.this.setResult(RESULT_OK, i);
                    ActivityTwo.this.finish();
                }
            });
        } else {
            TextView state = (TextView)findViewById(R.id.state_land);
            //state.setText("bundle{str2: \"" + bundle.getString("str2") + "\", n2: " + String.valueOf(bundle.getInt("n2"))+"}");
            if(savedInstanceState != null){
                state.setText("bundle{str4: \"" + savedInstanceState.getString("str4") + "\", n4: " + String.valueOf(savedInstanceState.getInt("n4"))+"}");
            }

        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putString("str4", "state saved");
        savedInstanceState.putInt("n4", 1111);
    }
}
