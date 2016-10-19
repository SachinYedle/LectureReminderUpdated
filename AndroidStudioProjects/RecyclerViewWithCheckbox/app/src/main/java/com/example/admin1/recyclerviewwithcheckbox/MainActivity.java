package com.example.admin1.recyclerviewwithcheckbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String [] checkBoxText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCheckBoxText();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this,checkBoxText));
    }
    public void getCheckBoxText(){
        checkBoxText = new String[30];
        for (int i=0;i<30;i++){
            checkBoxText[i] = "checkBoxText: " + i;
        }
    }
}
