package com.example.admin1.navigationdrawerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.SlidingDrawer;
import android.widget.Toast;

public class UpButtonActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private  SlidingDrawer slidingDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_button);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        slidingDrawer = (SlidingDrawer)findViewById(R.id.drawer);

        slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                Toast.makeText(UpButtonActivity.this,"opened",Toast.LENGTH_LONG).show();
            }
        });
        slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                Toast.makeText(UpButtonActivity.this,"closed",Toast.LENGTH_LONG).show();
            }
        });
    }
}
