package com.kenetic.testingmultiplelayouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView tv_sec_act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_sec_act = findViewById(R.id.textView_SecondActivity);
        //get text view by id
        Intent intentMainAct2 = getIntent();
        //fetch intent from activity one
        String name = intentMainAct2.getStringExtra(MainActivity.EXTRA_NAME);
        //accessing EXTRA_NAME from MainActivity to be used as key
        TextView txtVw = findViewById(R.id.topTextView);
        txtVw.setText(String.format("content from main activity is - %s", name));
    }
}