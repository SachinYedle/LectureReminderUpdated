package com.example.admin1.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FrameLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
    }
    public void callToGrid(View v){
        Intent intent = new Intent(getApplicationContext(),GridLayout.class);
        startActivity(intent);
    }
}
