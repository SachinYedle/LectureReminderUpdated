package com.example.admin1.viewsassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtView=(TextView) findViewById(R.id.marquee);
        txtView.setSelected(true);
    }
    public void hello(View view){
        Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
    }

}
