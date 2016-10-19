package com.example.admin1.recyclerviewmultiplelayouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String []textValues;
    String []buttonValues;
    int []imageValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textValues = new String[]{"TextView1","TextView2","TextView3"};
        imageValues = new int[]{R.drawable.image1,R.drawable.image2,R.drawable.image3};
        buttonValues = new String[]{"Button1","Button2","Button3"};

        recyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this,textValues,buttonValues,imageValues));
    }
}
