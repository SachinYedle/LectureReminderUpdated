package com.example.admin1.sharemessage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String msg = getIntent().getStringExtra(Intent.EXTRA_TEXT);


        Toast.makeText(getApplicationContext(),"Data: " + msg,Toast.LENGTH_LONG).show();
    }
}
