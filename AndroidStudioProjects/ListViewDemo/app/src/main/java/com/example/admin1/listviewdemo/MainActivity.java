package com.example.admin1.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    String [] values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list_view);

        values = new String[] {"Item1","Item2","Item3","Item4","Item5","Item6"};
        listView.setAdapter(new MyAdapter(this,values));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView textview1 = (TextView) view.findViewById(R.id.textvew);
                Toast.makeText(MainActivity.this,"Selected Item: "+values[i],Toast.LENGTH_SHORT).show();
            }
        });
    }

}
