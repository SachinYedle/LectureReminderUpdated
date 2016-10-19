package com.example.admin1.eternaldatabasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);

        getAndSetData();
    }
    public void getAndSetData(){
        DatabaseAccess databaseAccess = DatabaseAccess.getAccess(MainActivity.this);
        databaseAccess.open();
        textView.setText(databaseAccess.getData());
        databaseAccess.close();
    }
}
