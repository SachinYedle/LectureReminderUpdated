package com.example.admin1.impliciteintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void callAction(View view){
        Intent sendIntent = new Intent();
        String MY_ACTION = "com.example.admin1.impliciteintent.TEXT_READ";
        sendIntent.setAction(MY_ACTION);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "implicite intent");
        sendIntent.setType("text/plain");

        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }
}
