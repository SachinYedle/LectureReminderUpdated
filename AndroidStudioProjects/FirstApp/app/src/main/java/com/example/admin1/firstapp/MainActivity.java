package com.example.admin1.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView =(TextView)findViewById(R.id.dob);
        textView.setText("Abc212");

    }
    public void callToRelative(View v){
        Intent intent = new Intent(getApplicationContext(),RelativeLayout.class);
        startActivity(intent);
    }
}
