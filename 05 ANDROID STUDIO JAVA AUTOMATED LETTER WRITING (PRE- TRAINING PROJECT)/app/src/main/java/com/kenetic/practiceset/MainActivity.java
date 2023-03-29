package com.kenetic.practiceset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void copyToClip(View view) {

        EditText firstNameET = findViewById(R.id.yourFirstName);
        EditText lastNameET = findViewById(R.id.yourLastName);
        EditText companyNameET = findViewById(R.id.companyName);
        EditText recruiterNameET = findViewById(R.id.recruiterName);
        EditText enterLetterContentET = findViewById(R.id.enterLetterContent);
        //Button copyToClipBT = findViewById(R.id.copyToClipboard);

        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String companyName = companyNameET.getText().toString();
        String recruiterName = recruiterNameET.getText().toString();
        String enterLetterContent = enterLetterContentET.getText().toString();
        String toCopy = String.format("hello %s,\nmy name is %s %s.\nI have gone through the requirements posted by you and I believe that\nI might be the best fit for the required job post at \n\t%s\nAnd, here is why i think so - \n\n%s",recruiterName,firstName,lastName,companyName,enterLetterContent);

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text",toCopy);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(MainActivity.this,"copied",Toast.LENGTH_SHORT).show();
        //ClipboardManager clip = (ClipboardManager) getSystemService(Context)
    }
}