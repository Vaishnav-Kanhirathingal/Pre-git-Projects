package com.kenetic.testingmultiplelayouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText nameAct1;
    public static final String EXTRA_NAME = "NAME";
    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(this,MainActivity2.class);
        //create intent for activity two
        nameAct1 = findViewById(R.id.nameET);
        //get editText value
        String nameTxt = nameAct1.getText().toString();
        intent.putExtra(EXTRA_NAME,nameTxt);
        //EXTRA_NAME is given as a key
        startActivity(intent);
    }
}